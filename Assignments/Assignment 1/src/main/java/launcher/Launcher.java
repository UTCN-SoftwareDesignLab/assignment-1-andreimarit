package launcher;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class  Launcher extends Application {


    private Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        ClassLoader classLoader = Launcher.class.getClassLoader();
        Parent root = FXMLLoader.load(classLoader.getResource("login.fxml"));
        window=primaryStage;

        Scene mainScene = new Scene(root);
        window.setScene(mainScene);
        window.setTitle("Assignment1");
        window.show();

    }

    public static Scene changeScene(String fxml) throws IOException{
        ClassLoader classLoader = Launcher.class.getClassLoader();
        Parent pane = FXMLLoader.load(classLoader.getResource(fxml));

        Stage stage = new Stage();
        Scene newScene = new Scene(pane);
        stage.setScene(newScene);
        stage.show();

        return newScene;
    }


//@Override
//public void start(Stage stage) {
//    FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
//    Parent root = fxWeaver.loadView(LoginController.class);
//    window = stage;
//
//    Scene scene = new Scene(root);
//    window.setScene(scene);
//    window.setTitle("Urban Tickets");
//    window.show();
//}
//
//    public static Scene changeScene(Class controller){
//        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
//        Parent pane = fxWeaver.loadView((Class<Object>) controller);
//
//        //Stage stage = new Stage();
//        Scene newScene = new Scene(pane);
//        window.setScene(newScene);
//        window.show();
//
//        return newScene;
//    }
//
//    @Override
//    public void stop() {
//        this.applicationContext.close();
//        Platform.exit();
//    }
}
