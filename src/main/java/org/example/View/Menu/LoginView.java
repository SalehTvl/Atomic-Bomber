package org.example.View.Menu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class LoginView extends Application {
    public static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        LoginView.stage = stage;

        Image icon = new Image(getClass().getResourceAsStream("/Pics/icon.png"));
        stage.getIcons().add(icon);

        Parent root = FXMLLoader.load(getClass().getResource("/FXML/LoginMenu.fxml"));
        stage.setTitle("Atomic Bomber");
        stage.setScene(new Scene(root, 600, 400));
        stage.centerOnScreen();
        stage.show();
    }
}
