package org.example.View.Menu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;

import java.io.File;

public class MainView extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/MainMenu.fxml"));
        stage.setResizable(false);
        stage.setScene(new Scene(root, 1580, 900));

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double centerX = (screenBounds.getWidth() - 1580) / 2;
        double centerY = (screenBounds.getHeight() - 900) / 2;
        stage.setX(centerX);
        stage.setY(centerY);

        stage.show();
    }
}
