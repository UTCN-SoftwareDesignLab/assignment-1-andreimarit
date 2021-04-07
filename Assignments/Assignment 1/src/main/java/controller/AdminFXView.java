package controller;

import DTO.fxml.UserFXML;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import repository.EntityNotFoundException;

public class AdminFXView {

    @FXML
    private TextField generateDateTextField;

    @FXML
    private Button generateButton;

    @FXML
    private Button logoutButton;

    @FXML
    private TableView<UserFXML> userTable;

    @FXML
    private Button createButton;

    @FXML
    private Button updateButton;

    public TableView<UserFXML> getUserTable() {
        return userTable;
    }

    @FXML
    private Button deleteButton;

    public String getGenerateDate() {
        return generateDateTextField.getText();
    }

    @FXML
    private TextField idTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField roleTextField;

    public String getId() {
        return idTextField.getText();
    }

    public String getUsername() {
        return usernameTextField.getText();
    }

    public String getPassword() {
        return passwordTextField.getText();
    }

    public String getRole() {
        return roleTextField.getText();
    }

    public void setCreateButtonAction(EventHandler<ActionEvent> createButtonAction) {
        createButton.setOnAction(createButtonAction);
    }

    @FXML
    void createAction(ActionEvent event) {
    }

    public void setDeleteButtonAction(EventHandler<ActionEvent> deleteButtonAction) {
        deleteButton.setOnAction(deleteButtonAction);
    }


    @FXML
    void deleteAction(ActionEvent event) throws EntityNotFoundException {
    }

    public void setGenerateButtonAction(EventHandler<ActionEvent> generateeButtonAction) {
        generateButton.setOnAction(generateeButtonAction);
    }


    @FXML
    void generateFunction(ActionEvent event) {
    }

    public Button getLogoutButton() {
        return logoutButton;
    }

    public void setLogoutButtonAction(EventHandler<ActionEvent> logoutButtonAction) {
        logoutButton.setOnAction(logoutButtonAction);
    }

    @FXML
    void logoutFuncton(ActionEvent event) {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
        //scene = Launcher.changeScene("login.fxml");
    }

    public void setUpdateButtonAction(EventHandler<ActionEvent> updateButtonAction) {
        updateButton.setOnAction(updateButtonAction);
    }


    @FXML
    void updateAction(ActionEvent event) {
    }

}
