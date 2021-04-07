package controller.ActuallyController;

import DTO.UserDTO;
import DTO.fxml.UserFXML;
import controller.AdminFXView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import launcher.Launcher;
import model.User;
import model.builder.UserBuilder;
import repository.EntityNotFoundException;
import service.activity.ActivityService;
import service.user.UserService;
import util.AlertBox;

import java.io.IOException;
import java.util.ArrayList;

public class AdminActuallyController {

    private final AdminFXView adminFXView;
    private final UserService userService;
    private final ActivityService activityService;

    Scene scene;

    public AdminActuallyController(AdminFXView adminFXView, UserService userService, ActivityService activityService){

        this.adminFXView=adminFXView;
        this.userService=userService;
        this.activityService=activityService;
        populateTable(adminFXView);
        //buttons
        adminFXView.setDeleteButtonAction(new DeleteButtonOnAction());
        adminFXView.setCreateButtonAction(new CreateButtonOnAction());
        adminFXView.setGenerateButtonAction(new GenerateButtonOnAction());
        adminFXView.setLogoutButtonAction(new LogoutButtonOnAction());
        adminFXView.setUpdateButtonAction(new UpdateButtonOnAction());
    }


    private void populateTable(AdminFXView adminFXView){
        adminFXView.getUserTable().getItems().clear();

        ArrayList<UserDTO> users = userService.getAllUsers();

        ObservableList<UserFXML> dataUser = adminFXView.getUserTable().getItems();

        users.forEach((u) -> {
            UserFXML user = new UserFXML(u.getId(), u.getUsername(), u.getPassword(), u.getRole());
            dataUser.add(user);
        });
    }


    public class DeleteButtonOnAction implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){

            if(!adminFXView.getId().equals("")){
                if(!adminFXView.getRole().equals("admin")){
                    try {
                        boolean mor = userService.deleteUserById(Integer.parseInt(adminFXView.getId()));
                    } catch (EntityNotFoundException e) {
                        e.printStackTrace();
                    }
                    populateTable(adminFXView);
                }
            }
        }
    }

    public class CreateButtonOnAction implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            if(!(adminFXView.getUsername().equals("")) && !(adminFXView.getPassword().equals(""))){
                User user;
                if(adminFXView.getRole().equals("admin")){
                    user = new UserBuilder()
                            .setUsername(adminFXView.getUsername())
                            .setPassword(adminFXView.getPassword())
                            .setRole("admin")
                            .build();
                }
                else{
                    user = new UserBuilder()
                            .setUsername(adminFXView.getUsername())
                            .setPassword(adminFXView.getPassword())
                            .setRole("employee")
                            .build();
                }
                boolean mor_incet= userService.saveUser(user);
                populateTable(adminFXView);

            }
        }
    }

    public class GenerateButtonOnAction implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            if(adminFXView.getGenerateDate().equals("")) {
                AlertBox.display("No input", "You forgot to write starting date");
            }
            else{
                try {
                    activityService.printAllActivitiesWithCondition(adminFXView.getGenerateDate());
                } catch (EntityNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public class LogoutButtonOnAction implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Stage stage = (Stage) adminFXView.getLogoutButton().getScene().getWindow();
            stage.close();
//            try {
//                scene = Launcher.changeScene("login.fxml");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        }
    }

        public class UpdateButtonOnAction implements EventHandler<ActionEvent> {
            @Override
            public void handle(ActionEvent event) {

                if ((!adminFXView.getId().equals("")) && (!adminFXView.getUsername().equals("")) && (!adminFXView.getPassword().equals("")) && (!adminFXView.getRole().equals(""))) {
                    User user = new UserBuilder()
                            .setId(Integer.parseInt(adminFXView.getId()))
                            .setUsername(adminFXView.getUsername())
                            .setPassword(adminFXView.getPassword())
                            .setRole(adminFXView.getRole())
                            .build();
                    boolean mor = userService.updateUser(user);
                    populateTable(adminFXView);
                }
            }
        }

}
