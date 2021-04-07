package controller.ActuallyController;

import controller.LoginFXView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import launcher.Launcher;
import model.User;
import model.validator.Notification;
import service.user.AuthenticationService;
import util.AlertBox;

import java.io.IOException;

public class LoginActuallyController  {

    private final LoginFXView loginFXView;
    private final AuthenticationService authenticationService;

    public static int userLogged;

    Scene scene;


    public LoginActuallyController(LoginFXView loginFXView, AuthenticationService authenticationService){
        this.authenticationService=authenticationService;
        this.loginFXView=loginFXView;
        loginFXView.setLoginButtonAction(new LoginButtonOnAction());
    }


    public class LoginButtonOnAction implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {

            String username = loginFXView.getUsername();
            String password = loginFXView.getPassword();

            System.out.println(username + "                " + password);
            if (username.equals("") || password.equals("")) {
                AlertBox.display("No input", "You forgot to write your mail/password");
            } else {
                Notification<User> loginNotification = authenticationService.login(username, password);

                if (loginNotification.hasErrors()) {
                    AlertBox.display("Error", "Something is wrong!");
                } else {
                    User loggedUser = loginNotification.getResult();
                    if (loggedUser.getRole().equals("admin")) {
                        try {
                            scene = Launcher.changeScene("admin.fxml");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            scene = Launcher.changeScene("user.fxml");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    userLogged = loggedUser.getId();
                    AlertBox.display("Confirmation", "Login successful!");
                }
            }
        }
    }


}
