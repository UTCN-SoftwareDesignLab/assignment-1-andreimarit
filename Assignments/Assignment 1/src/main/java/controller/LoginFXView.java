package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginFXView {



    @FXML
    protected PasswordField password;

    @FXML
    protected TextField username;

    @FXML
    private Button loginButton;

    public LoginFXView() { }

    public String getUsername(){return username.getText();}
    public String getPassword(){return password.getText();}

    public void setLoginButtonAction(EventHandler<ActionEvent> loginButtonAction){
        loginButton.setOnAction(loginButtonAction);
    }


    @FXML
    void login(ActionEvent event) {
    }

}
