package org.example.View.Controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import org.example.Controller.LoginController;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.Model.DataManager;
import org.example.Model.Player;
import org.example.Model.Result;
import org.example.View.Menu.LoginView;
import org.example.View.Menu.MainView;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Button guestButton;


    @FXML
    private void registerAction() {
        registerButton.setOnMousePressed(event -> {
            registerButton.getStyleClass().add("pressed");
        });

        String username = usernameField.getText();
        String password = passwordField.getText();

        LoginController loginController = new LoginController();
        Result result = loginController.registerUser(username, password);

        if (!result.isSuccessful()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Register Error");
            alert.setHeaderText(null);
            alert.setContentText("Username is already taken!");
            alert.showAndWait();
            return;
        }

        usernameField.clear();
        passwordField.clear();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Register");
        alert.setHeaderText(null);
        alert.setContentText(result.getMessage());
        alert.showAndWait();
    }

    @FXML
    private void loginAction() {
        loginButton.setOnMousePressed(event -> {
            loginButton.getStyleClass().add("pressed");
        });

        String username = usernameField.getText();
        String password = passwordField.getText();

        LoginController loginController = new LoginController();
        Result result = loginController.loginUser(username, password);

        if (!result.isSuccessful()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login Error");
            alert.setHeaderText(null);
            alert.setContentText(result.getMessage());
            alert.showAndWait();
            return;
        }

        usernameField.clear();
        passwordField.clear();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login");
        alert.setHeaderText(null);
        alert.setContentText(result.getMessage());
        alert.showAndWait();

        MainView mainView = new MainView();
        try {
            mainView.start(LoginView.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void guestLoginAction() {
        guestButton.setOnMousePressed(event -> {
            guestButton.getStyleClass().add("pressed");
        });

        usernameField.clear();
        passwordField.clear();

        LoginController loginController = new LoginController();
        Result result = loginController.guestLogin();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login");
        alert.setHeaderText(null);
        alert.setContentText(result.getMessage());
        alert.showAndWait();

        MainView mainView = new MainView();
        try {
            mainView.start(LoginView.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DataManager dataManager = new DataManager();
        Player.setPlayers(dataManager.readPlayerData(dataManager.getFileAddress()));
    }
}
