package controller;

import DTO.AccountDTO;
import DTO.ClientDTO;
import DTO.UserDTO;
import DTO.fxml.AccountFXML;
import DTO.fxml.ClientFXML;
import DTO.fxml.UserFXML;
import database.DBConnectionFactory;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Account;
import model.Activity;
import model.Client;
import model.User;
import model.builder.AccountBuilder;
import model.builder.ActivityBuilder;
import model.builder.ClientBuilder;
import model.builder.UserBuilder;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryImplementation;
import repository.activity.ActivityRepository;
import repository.activity.ActivityRepositoryImplementation;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryImplementation;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.account.AccountService;
import service.account.AccountServiceImplementation;
import service.activity.ActivityService;
import service.activity.ActivityServiceImplementation;
import service.client.ClientService;
import service.client.ClientServiceImplementation;
import service.user.UserService;
import service.user.UserServiceImplementation;
import util.AlertBox;

import java.sql.Connection;
import java.util.ArrayList;

import static controller.LoginControllerFX.userLogged;

public class UserController {


    private final Connection connection = new DBConnectionFactory().getConnectionWrapper(false).getConnection();
    UserRepository userRepository = new UserRepositoryMySQL(connection);
    UserService userService = new UserServiceImplementation(userRepository);

    AccountRepository accountRepository = new AccountRepositoryImplementation(connection);
    AccountService accountService = new AccountServiceImplementation(accountRepository);

    ClientRepository clientRepository = new ClientRepositoryImplementation(connection);
    ClientService clientService = new ClientServiceImplementation(clientRepository);

    ActivityRepository activityRepository = new ActivityRepositoryImplementation(connection, userRepository);
    ActivityService activityService = new ActivityServiceImplementation(activityRepository);

    @FXML
    private Button logoutButton;

    @FXML
    private Label employeeUsername = new Label(Integer.toString(userLogged));


    @FXML
    private TableView<ClientFXML> clientTable;

    @FXML
    private TableView<AccountFXML> accountTable;

    @FXML
    private TextField idFieldUser;

    @FXML
    private TextField nameFieldUser;

    @FXML
    private TextField identityNumberFieldUser;

    @FXML
    private TextField cnpFieldUser;

    @FXML
    private TextField addressFieldUser;

    @FXML
    private Button addClientButton;

    @FXML
    private Button updateClientButton;

    @FXML
    private Button createAccountButton;

    @FXML
    private Button updateAccountButton;

    @FXML
    private Button deleteAccountButton;

    @FXML
    private Button transferButton;

    @FXML
    private TextField donorField;

    @FXML
    private TextField recieverField;

    @FXML
    private TextField client_idField;

    @FXML
    private TextField amountField;

    @FXML
    private TextField idFieldAccount;

    @FXML
    private TextField identificationNumberFieldAccount;

    @FXML
    private TextField typeFieldAccount;

    @FXML
    private TextField amountFieldAccount;

    @FXML
    private TextField dateOfCreationFieldAccount;

    @FXML
    private void initialize(){
        populateTables();
        System.out.println("populated");
    }

    private void populateTables(){
        populateClientTable();
        populateAccountTable();
    }

    private void populateClientTable(){
        clientTable.getItems().clear();

        ArrayList<ClientDTO> clients = clientService.getAllClients();

        ObservableList<ClientFXML> dataClinet = clientTable.getItems();

        clients.forEach((c) -> {
            ClientFXML client = new ClientFXML(c.getId(), c.getName(), c.getIdentityNumber(), c.getCnp(),c.getAddress());
            dataClinet.add(client);
        });
    }

    private void populateAccountTable(){
        accountTable.getItems().clear();

        ArrayList<AccountDTO> accounts = accountService.getAllAccounts();

        ObservableList<AccountFXML> dataAccount = accountTable.getItems();

        accounts.forEach((a) -> {
            AccountFXML account = new AccountFXML(a.getId(), a.getIdentificationNumber(), a.getType(), a.getAmount(), a.getClient_id(), a.getCreationDate());
            dataAccount.add(account);
        });
    }

    @FXML
    void addClientAction(ActionEvent event) {
        if(!(nameFieldUser.getText().equals("")) && !(identityNumberFieldUser.getText().equals("")) && !(cnpFieldUser.getText().equals("")) && !(addressFieldUser.getText().equals(""))){
            Client client = new ClientBuilder()
                        .setName(nameFieldUser.getText())
                        .setIdentityNumber(identityNumberFieldUser.getText())
                        .setCnp(cnpFieldUser.getText())
                        .setAddress(addressFieldUser.getText())
                        .build();

            boolean mor_incet=clientRepository.save(client);

            Activity activity = new ActivityBuilder()
                    .setDate("2021-03-31")
                    .setDescription("addingClient")
                    .setUserId(userLogged)
                    .build();

            boolean morincetSigur = activityRepository.save(activity);
            populateTables();
        }
        else {
            AlertBox.display("No input", "You forgot to write some data");
        }
    }

    @FXML
    void createAccountAction(ActionEvent event) {
        if(!(client_idField.getText().equals("")) && !(identificationNumberFieldAccount.getText().equals("")) && !(typeFieldAccount.getText().equals("")) && !(amountFieldAccount.getText().equals("")) && !(dateOfCreationFieldAccount.getText().equals(""))){
            Account account = new AccountBuilder()
                    .setAmount(Integer.parseInt(amountFieldAccount.getText()))
                    .setId(Integer.parseInt(idFieldAccount.getText()))
                    .setType(typeFieldAccount.getText())
                    .setClientId(Integer.parseInt(client_idField.getText()))
                    .setIdentificationNumber(identificationNumberFieldAccount.getText())
                    .setCreationDate(dateOfCreationFieldAccount.getText())
                    .build();

            boolean mor_incet=accountRepository.save(account);

            Activity activity = new ActivityBuilder()
                    .setDate("2021-03-31")
                    .setDescription("createAccount")
                    .setUserId(userLogged)
                    .build();

            boolean morincetSigur = activityRepository.save(activity);
            populateTables();
        }
        else {
            AlertBox.display("No input", "You forgot to write some data");
        }
    }

    @FXML
    void deleteAccountAction(ActionEvent event) {

        if(!idFieldAccount.getText().equals("")){
                accountRepository.remove(Integer.parseInt(idFieldAccount.getText()));
        }

        Activity activity = new ActivityBuilder()
                .setDate("2021-03-31")
                .setDescription("deleteAccount")
                .setUserId(userLogged)
                .build();

        boolean morincetSigur = activityRepository.save(activity);

        populateTables();
    }

    @FXML
    void logoutAction(ActionEvent event) {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void transferAction(ActionEvent event) throws EntityNotFoundException {
        if(!(donorField.getText().equals("")) && !(recieverField.getText().equals("")) && !(amountField.getText().equals(""))){
            Account donorAccount = accountRepository.findById(Integer.parseInt(donorField.getText()));
            Account recieverAccount = accountRepository.findById(Integer.parseInt(recieverField.getText()));
            System.out.println("donor:" + donorAccount.getId() + " " + donorAccount.getAmount());
            System.out.println("reciver:" + recieverAccount.getId() + " " + recieverAccount.getAmount());
            donorAccount.setAmount(donorAccount.getAmount() - Integer.parseInt(amountField.getText()));
            recieverAccount.setAmount(recieverAccount.getAmount() + Integer.parseInt(amountField.getText()));
            System.out.println("transfer has been made");
            System.out.println("donor:" + donorAccount.getId() + " " + donorAccount.getAmount());
            System.out.println("reciver:" + recieverAccount.getId() + " " + recieverAccount.getAmount());

            System.out.println(recieverAccount.getId()+"---------"+recieverAccount.getIdentificationNumber()+"---------"+recieverAccount.getClient_id()+"---------"+recieverAccount.getType()+"---------"+recieverAccount.getAmount()+"---------"+recieverAccount.getCreationDate());
            boolean mor = accountRepository.update(donorAccount);
            boolean incet = accountRepository.update(recieverAccount);

            Activity activity = new ActivityBuilder()
                    .setDate("2021-03-31")
                    .setDescription("transferMoney")
                    .setUserId(userLogged)
                    .build();

            boolean morincetSigur = activityRepository.save(activity);
            //accountRepository.transferMoney(donorAccount, recieverAccount, Integer.parseInt(amountField.getText()));
            populateTables();

        }
    }

    @FXML
    void updateAccountAction(ActionEvent event) {
        if(!(client_idField.getText().equals("")) && !(identificationNumberFieldAccount.getText().equals("")) && !(typeFieldAccount.getText().equals("")) && !(amountFieldAccount.getText().equals("")) && !(dateOfCreationFieldAccount.getText().equals(""))){
            Account account = new AccountBuilder()
                    .setAmount(Integer.parseInt(amountFieldAccount.getText()))
                    .setId(Integer.parseInt(idFieldAccount.getText()))
                    .setType(typeFieldAccount.getText())
                    .setClientId(Integer.parseInt(client_idField.getText()))
                    .setIdentificationNumber(identificationNumberFieldAccount.getText())
                    .setCreationDate(dateOfCreationFieldAccount.getText())
                    .build();

            boolean mor_incet=accountRepository.update(account);

            Activity activity = new ActivityBuilder()
                    .setDate("2021-03-31")
                    .setDescription("updateAccount")
                    .setUserId(userLogged)
                    .build();

            boolean morincetSigur = activityRepository.save(activity);
            populateTables();

        }
        else {
            AlertBox.display("No input", "You forgot to write some data");
        }
    }

    @FXML
    void updateClientAction(ActionEvent event) {
            if (!(nameFieldUser.getText().equals("")) && !(identityNumberFieldUser.getText().equals("")) && !(cnpFieldUser.getText().equals("")) && !(addressFieldUser.getText().equals(""))) {
                Client client = new ClientBuilder()
                        .setId(Integer.parseInt(idFieldUser.getText()))
                        .setName(nameFieldUser.getText())
                        .setIdentityNumber(identityNumberFieldUser.getText())
                        .setCnp(cnpFieldUser.getText())
                        .setAddress(addressFieldUser.getText())
                        .build();

                boolean mor_incet2 = clientRepository.update(client);

                Activity activity = new ActivityBuilder()
                        .setDate("2021-03-31")
                        .setDescription("updateClient")
                        .setUserId(userLogged)
                        .build();

                boolean morincetSigur = activityRepository.save(activity);
                populateTables();
            }
            else {
                AlertBox.display("No input", "You forgot to write some data");
            }
    }

}
