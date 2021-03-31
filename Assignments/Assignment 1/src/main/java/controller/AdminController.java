package controller;

import DTO.UserDTO;
import DTO.fxml.UserFXML;
import database.DBConnectionFactory;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import launcher.Launcher;
import model.User;
import model.builder.UserBuilder;
import repository.EntityNotFoundException;
import repository.activity.ActivityRepository;
import repository.activity.ActivityRepositoryImplementation;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.activity.ActivityService;
import service.activity.ActivityServiceImplementation;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceMySQL;
import service.user.UserService;
import service.user.UserServiceImplementation;
import util.AlertBox;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

public class AdminController {

    private final Connection connection = new DBConnectionFactory().getConnectionWrapper(false).getConnection();
    UserRepository userRepository = new UserRepositoryMySQL(connection);
    UserService userService = new UserServiceImplementation(userRepository);

    ActivityRepository activityRepository = new ActivityRepositoryImplementation(connection, userRepository);
    ActivityService activityService = new ActivityServiceImplementation(activityRepository);

    Scene scene;
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

    @FXML
    private Button deleteButton;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField roleTextField;

    @FXML
    private void initialize(){
        populateTable();
        System.out.println("populated");
    }

    private void populateTable(){
        userTable.getItems().clear();

        ArrayList<UserDTO> users = userService.getAllUsers();

        ObservableList<UserFXML>dataUser = userTable.getItems();

        users.forEach((u) -> {
            UserFXML user = new UserFXML(u.getId(), u.getUsername(), u.getPassword(), u.getRole());
            dataUser.add(user);
        });
    }

    @FXML
    void createAction(ActionEvent event) {
        if(!(usernameTextField.getText().equals("")) && !(passwordTextField.getText().equals(""))){
            User user;
            if(roleTextField.equals("admin")){
                user = new UserBuilder()
                        .setUsername(usernameTextField.getText())
                        .setPassword(passwordTextField.getText())
                        .setRole("admin")
                        .build();
            }
            else{
                user = new UserBuilder()
                        .setUsername(usernameTextField.getText())
                        .setPassword(passwordTextField.getText())
                        .setRole("employee")
                        .build();
            }
            boolean mor_incet=userRepository.save(user);
            populateTable();

        }
    }

    @FXML
    void deleteAction(ActionEvent event) throws EntityNotFoundException {
            if(!idTextField.getText().equals("")){
                if(!roleTextField.equals("admin")){
                    userRepository.delete(Integer.parseInt(idTextField.getText()));
                    populateTable();
                }
            }
    }

    @FXML
    void generateFunction(ActionEvent event) {
        if(generateDateTextField.getText().equals("")) {
            AlertBox.display("No input", "You forgot to write starting date");
        }
        else{

        }
    }

    @FXML
    void logoutFuncton(ActionEvent event) {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
        //scene = Launcher.changeScene("login.fxml");
    }

    @FXML
    void updateAction(ActionEvent event) {
        if((!idTextField.getText().equals("")) && (!usernameTextField.getText().equals("")) && (!passwordTextField.getText().equals("")) && (!roleTextField.getText().equals(""))){
            User user = new UserBuilder()
                    .setId(Integer.parseInt(idTextField.getText()))
                    .setUsername(usernameTextField.getText())
                    .setPassword(passwordTextField.getText())
                    .setRole(roleTextField.getText())
                    .build();
            userRepository.update(user);
            populateTable();
        }
    }

}
