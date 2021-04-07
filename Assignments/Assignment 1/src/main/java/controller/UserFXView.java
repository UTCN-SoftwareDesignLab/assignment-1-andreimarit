package controller;

import DTO.fxml.AccountFXML;
import DTO.fxml.ClientFXML;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import repository.EntityNotFoundException;

import static controller.ActuallyController.LoginActuallyController.userLogged;

public class UserFXView {

//
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

//    @FXML
//    private void initialize(){
//        populateTables();
//        System.out.println("populated");
//    }

    public String getIdFieldUser() {
        return idFieldUser.getText();
    }

    public String getNameFieldUser() {
        return nameFieldUser.getText();
    }

    public String getIdentityNumberFieldUser() {
        return identityNumberFieldUser.getText();
    }

    public String getCnpFieldUser() {
        return cnpFieldUser.getText();
    }

    public String getAddressFieldUser() {
        return addressFieldUser.getText();
    }

    public String getDonorField() {
        return donorField.getText();
    }

    public String getRecieverField() {
        return recieverField.getText();
    }

    public String getClient_idField() {
        return client_idField.getText();
    }

    public String getAmountField() {
        return amountField.getText();
    }

    public String getIdFieldAccount() {
        return idFieldAccount.getText();
    }

    public String getIdentificationNumberFieldAccount() {
        return identificationNumberFieldAccount.getText();
    }

    public String getTypeFieldAccount() {
        return typeFieldAccount.getText();
    }

    public String getAmountFieldAccount() {
        return amountFieldAccount.getText();
    }

    public String getDateOfCreationFieldAccount() {
        return dateOfCreationFieldAccount.getText();
    }

    public TableView<ClientFXML> getClientTable() {
        return clientTable;
    }

    public TableView<AccountFXML> getAccountTable() {
        return accountTable;
    }


    public void setAddClientButtonAction(EventHandler<ActionEvent> addClientButtonAction) {
        addClientButton.setOnAction(addClientButtonAction);
    }

    @FXML
    void addClientAction(ActionEvent event) {
//
    }


    public void setCreateAccountButtonAction(EventHandler<ActionEvent> createAccountButtonAction) {
        createAccountButton.setOnAction(createAccountButtonAction);
    }
    @FXML
    void createAccountAction(ActionEvent event) {
//
    }

    public void setDeleteAccountButtonAction(EventHandler<ActionEvent> deleteAccountButtonAction) {
        deleteAccountButton.setOnAction(deleteAccountButtonAction);
    }
    @FXML
    void deleteAccountAction(ActionEvent event) {

    }

    public Button getLogoutButton() {
        return logoutButton;
    }

    public void setLogoutButtonAction(EventHandler<ActionEvent> logoutButtonAction) {
        logoutButton.setOnAction(logoutButtonAction);
    }

    @FXML
    void logoutAction(ActionEvent event) {
    }

    public void setTransferButtonAction(EventHandler<ActionEvent> transferButtonAction) {
        transferButton.setOnAction(transferButtonAction);
    }

    @FXML
    void transferAction(ActionEvent event) throws EntityNotFoundException {
    }

    public void setUpdateAccountButtonAction(EventHandler<ActionEvent> updateAccountButtonAction) {
        updateAccountButton.setOnAction(updateAccountButtonAction);
    }
    @FXML
    void updateAccountAction(ActionEvent event) {
    }


    public void setUpdateClientButtonAction(EventHandler<ActionEvent> updateClientButtonAction) {
        updateClientButton.setOnAction(updateClientButtonAction);
    }
    @FXML
    void updateClientAction(ActionEvent event) {
    }

}
