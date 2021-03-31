package controller;

import database.DBConnectionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import launcher.Launcher;
import model.User;
import model.validator.Notification;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceMySQL;
import util.AlertBox;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.function.LongUnaryOperator;

public class LoginControllerFX {


    public static int userLogged;
    Scene scene;
    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Button loginButton;

    private final Connection connection = new DBConnectionFactory().getConnectionWrapper(false).getConnection();
    UserRepository userRepository = new UserRepositoryMySQL(connection);
    AuthenticationService myAuth = new AuthenticationServiceMySQL(userRepository);

    @FXML
    void login(ActionEvent event) throws IOException {
        String usernameText =username.getText();
        String passwordText = password.getText();

        System.out.println(usernameText + "                "+ passwordText);
        if(username.getText().equals("") || password.getText().equals("")){
            AlertBox.display("No input", "You forgot to write your mail/password");
        }
        else {
            Notification<User> loginNotification = myAuth.login(usernameText, passwordText);

            if (loginNotification.hasErrors()) {
                AlertBox.display("Error", "Something is wrong!");
            } else {
                User loggedUser= loginNotification.getResult();
                if(loggedUser.getRole().equals("admin")) {
                    scene = Launcher.changeScene("admin.fxml");
                }
                else scene = Launcher.changeScene("user.fxml");
                userLogged = loggedUser.getId();
                AlertBox.display("Confirmation", "Login successful!");
            }
        }
    }

}
