package controller.ActuallyController;

import DTO.AccountDTO;
import DTO.ClientDTO;
import DTO.fxml.AccountFXML;
import DTO.fxml.ClientFXML;
import controller.UserFXView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Account;
import model.Activity;
import model.Client;
import model.builder.AccountBuilder;
import model.builder.ActivityBuilder;
import model.builder.ClientBuilder;
import repository.EntityNotFoundException;
import service.account.AccountService;
import service.activity.ActivityService;
import service.client.ClientService;
import service.user.UserService;
import util.AlertBox;

import java.util.ArrayList;

import static controller.ActuallyController.LoginActuallyController.userLogged;

public class UserAcctuallyController {

    private final UserFXView userFXView;
    private final UserService userService;
    private final AccountService accountService;
    private final ClientService clientService;
    private final ActivityService activityService;

    Scene scene;

    public UserAcctuallyController(UserFXView userFXView, UserService userService, AccountService accountService, ClientService clientService, ActivityService activityService){
        this.userFXView=userFXView;
        this.userService=userService;
        this.accountService=accountService;
        this.clientService=clientService;
        this.activityService=activityService;
        populateClientTable(this.userFXView);
        populateAccountTable(this.userFXView);
        //buttons
        userFXView.setAddClientButtonAction(new AddClientOnAction());
        userFXView.setUpdateClientButtonAction(new UpdateClientOnAction());
        userFXView.setLogoutButtonAction(new LogoutButtonOnAction());
        userFXView.setCreateAccountButtonAction(new CreateAccountOnAction());
        userFXView.setDeleteAccountButtonAction(new DeleteAccountOnAction());
        userFXView.setUpdateAccountButtonAction(new UpdateAccountOnAction());
        userFXView.setTransferButtonAction(new TransferButtonOnAction());
    }

    private void populateClientTable(UserFXView userFXView){
        userFXView.getClientTable().getItems().clear();

        ArrayList<ClientDTO> clients = clientService.getAllClients();

        ObservableList<ClientFXML> dataClinet = userFXView.getClientTable().getItems();

        clients.forEach((c) -> {
            ClientFXML client = new ClientFXML(c.getId(), c.getName(), c.getIdentityNumber(), c.getCnp(),c.getAddress());
            dataClinet.add(client);
        });
    }

    private void populateAccountTable(UserFXView userFXView){
        userFXView.getAccountTable().getItems().clear();

        ArrayList<AccountDTO> accounts = accountService.getAllAccounts();

        ObservableList<AccountFXML> dataAccount = userFXView.getAccountTable().getItems();

        accounts.forEach((a) -> {
            AccountFXML account = new AccountFXML(a.getId(), a.getIdentificationNumber(), a.getType(), a.getAmount(), a.getClient_id(), a.getCreationDate());
            dataAccount.add(account);
        });
    }

    public class LogoutButtonOnAction implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Stage stage = (Stage) userFXView.getLogoutButton().getScene().getWindow();
            stage.close();
//            try {
//                scene = Launcher.changeScene("login.fxml");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        }
    }

    public class AddClientOnAction implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            if(!(userFXView.getNameFieldUser().equals("")) && !(userFXView.getIdentityNumberFieldUser().equals("")) && !(userFXView.getCnpFieldUser().equals("")) && !(userFXView.getAddressFieldUser().equals(""))){
                Client client = new ClientBuilder()
                        .setName(userFXView.getNameFieldUser())
                        .setIdentityNumber(userFXView.getIdentityNumberFieldUser())
                        .setCnp(userFXView.getCnpFieldUser())
                        .setAddress(userFXView.getAddressFieldUser())
                        .build();

                boolean mor_incet=clientService.saveClient(client);

                Activity activity = new ActivityBuilder()
                        .setDate("2021-03-31")
                        .setDescription("addingClient")
                        .setUserId(userLogged)
                        .build();

                boolean morincetSigur = activityService.saveActivity(activity);
                populateClientTable(userFXView);
            }
            else {
                AlertBox.display("No input", "You forgot to write some data");
            }
        }
    }

    public class UpdateClientOnAction implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            if (!(userFXView.getNameFieldUser().equals("")) && !(userFXView.getIdentityNumberFieldUser().equals("")) && !(userFXView.getCnpFieldUser().equals("")) && !(userFXView.getAddressFieldUser().equals(""))) {
                Client client = new ClientBuilder()
                        .setId(Integer.parseInt(userFXView.getIdFieldUser()))
                        .setName(userFXView.getNameFieldUser())
                        .setIdentityNumber(userFXView.getIdentityNumberFieldUser())
                        .setCnp(userFXView.getCnpFieldUser())
                        .setAddress(userFXView.getAddressFieldUser())
                        .build();

                boolean mor_incet2 = clientService.updateClient(client);

                Activity activity = new ActivityBuilder()
                        .setDate("2021-03-31")
                        .setDescription("updateClient")
                        .setUserId(userLogged)
                        .build();

                boolean morincetSigur = activityService.saveActivity(activity);
                populateClientTable(userFXView);
            }
            else {
                AlertBox.display("No input", "You forgot to write some data");
            }
        }
    }

    public class CreateAccountOnAction implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            if(!(userFXView.getClient_idField().equals("")) && !(userFXView.getIdentificationNumberFieldAccount().equals("")) && !(userFXView.getTypeFieldAccount().equals("")) && !(userFXView.getAmountFieldAccount().equals("")) && !(userFXView.getDateOfCreationFieldAccount().equals(""))){
                Account account = new AccountBuilder()
                        .setAmount(Integer.parseInt(userFXView.getAmountField()))
                        .setId(Integer.parseInt(userFXView.getIdFieldAccount()))
                        .setType(userFXView.getTypeFieldAccount())
                        .setClientId(Integer.parseInt(userFXView.getClient_idField()))
                        .setIdentificationNumber(userFXView.getIdentificationNumberFieldAccount())
                        .setCreationDate(userFXView.getDateOfCreationFieldAccount())
                        .build();

                boolean mor_incet=accountService.saveAccount(account);

                Activity activity = new ActivityBuilder()
                        .setDate("2021-03-31")
                        .setDescription("createAccount")
                        .setUserId(userLogged)
                        .build();

                boolean morincetSigur = activityService.saveActivity(activity);
                populateAccountTable(userFXView);
            }
            else {
                AlertBox.display("No input", "You forgot to write some data");
            }
        }
    }

    public class DeleteAccountOnAction implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            if(!userFXView.getIdFieldAccount().equals("")){
                accountService.removeAccount(Integer.parseInt(userFXView.getIdFieldAccount()));
            }

            Activity activity = new ActivityBuilder()
                    .setDate("2021-03-31")
                    .setDescription("deleteAccount")
                    .setUserId(userLogged)
                    .build();

            boolean morincetSigur = activityService.saveActivity(activity);

            populateAccountTable(userFXView);
        }
    }

    public class UpdateAccountOnAction implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            if(!(userFXView.getClient_idField().equals("")) && !(userFXView.getIdentificationNumberFieldAccount().equals("")) && !(userFXView.getTypeFieldAccount().equals("")) && !(userFXView.getAmountFieldAccount().equals("")) && !(userFXView.getDateOfCreationFieldAccount().equals(""))){
                Account account = new AccountBuilder()
                        .setAmount(Integer.parseInt(userFXView.getAmountFieldAccount()))
                        .setId(Integer.parseInt(userFXView.getIdFieldAccount()))
                        .setType(userFXView.getTypeFieldAccount())
                        .setClientId(Integer.parseInt(userFXView.getClient_idField()))
                        .setIdentificationNumber(userFXView.getIdentificationNumberFieldAccount())
                        .setCreationDate(userFXView.getDateOfCreationFieldAccount())
                        .build();

                boolean mor_incet=accountService.updateAccount(account);

                Activity activity = new ActivityBuilder()
                        .setDate("2021-03-31")
                        .setDescription("updateAccount")
                        .setUserId(userLogged)
                        .build();

                boolean morincetSigur = activityService.saveActivity(activity);
                populateAccountTable(userFXView);

            }
            else {
                AlertBox.display("No input", "You forgot to write some data");
            }
        }
    }

    public class TransferButtonOnAction implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (!(userFXView.getDonorField().equals("")) && !(userFXView.getRecieverField().equals("")) && !(userFXView.getAmountField().equals(""))) {
                Account donorAccount = null;
                try {
                    donorAccount = accountService.findAccount(Integer.parseInt(userFXView.getDonorField()));
                } catch (EntityNotFoundException e) {
                    e.printStackTrace();
                }
                Account recieverAccount = null;
                try {
                    recieverAccount = accountService.findAccount(Integer.parseInt(userFXView.getRecieverField()));
                } catch (EntityNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println("donor:" + donorAccount.getId() + " " + donorAccount.getAmount());
                System.out.println("reciver:" + recieverAccount.getId() + " " + recieverAccount.getAmount());
                donorAccount.setAmount(donorAccount.getAmount() - Integer.parseInt(userFXView.getAmountField()));
                recieverAccount.setAmount(recieverAccount.getAmount() + Integer.parseInt(userFXView.getAmountField()));
                System.out.println("transfer has been made");
                System.out.println("donor:" + donorAccount.getId() + " " + donorAccount.getAmount());
                System.out.println("reciver:" + recieverAccount.getId() + " " + recieverAccount.getAmount());

                System.out.println(recieverAccount.getId() + "---------" + recieverAccount.getIdentificationNumber() + "---------" + recieverAccount.getClient_id() + "---------" + recieverAccount.getType() + "---------" + recieverAccount.getAmount() + "---------" + recieverAccount.getCreationDate());
                boolean mor = accountService.updateAccount(donorAccount);
                boolean incet = accountService.updateAccount(recieverAccount);

                Activity activity = new ActivityBuilder()
                        .setDate("2021-03-31")
                        .setDescription("transferMoney")
                        .setUserId(userLogged)
                        .build();

                boolean morincetSigur = activityService.saveActivity(activity);
                //accountRepository.transferMoney(donorAccount, recieverAccount, Integer.parseInt(amountField.getText()));
                populateAccountTable(userFXView);
            }
        }
    }

}
