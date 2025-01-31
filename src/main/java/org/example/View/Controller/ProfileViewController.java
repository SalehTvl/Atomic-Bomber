package org.example.View.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.Controller.ProfileController;
import org.example.Model.Player;
import org.example.Model.Result;
import org.example.View.Menu.AvatarView;
import org.example.View.Menu.LoginView;
import org.example.View.Menu.MainView;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileViewController implements Initializable {

    @FXML
    ImageView avatar;
    @FXML
    Label usernameLabel;
    @FXML
    TextField newUsernameTextField;
    @FXML
    TextField newPassordTextField;

    @FXML
    private void backAction() {
        MainView mainView = new MainView();

        try {
            mainView.start(LoginView.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void changeUsernameAction() {
        ProfileController profileController = new ProfileController();
        Result result = profileController.changeUsername(newUsernameTextField.getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Change Account Info");
        alert.setHeaderText(null);
        alert.setContentText(result.getMessage());
        alert.showAndWait();
    }

    @FXML
    private void changePasswordAction() {
        ProfileController profileController = new ProfileController();
        Result result = profileController.changePassword(newPassordTextField.getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Change Account Info");
        alert.setHeaderText(null);
        alert.setContentText(result.getMessage());
        alert.showAndWait();
    }

    @FXML
    private void logoutAction() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("Logged out successfully");
        alert.showAndWait();

        LoginView loginView = new LoginView();
        try {
            loginView.start(LoginView.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void removeAccountAction() {
        ProfileController profileController = new ProfileController();
        Result result = profileController.removePlayerAccount();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account removed");
        alert.setHeaderText(null);
        alert.setContentText(result.getMessage());
        alert.showAndWait();

        LoginView loginView = new LoginView();
        try {
            loginView.start(LoginView.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void avatarMenuAction() {
        AvatarView avatarView = new AvatarView();
        try {
            avatarView.start(LoginView.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image("file:///" + Player.getLoggedInPlayer().getAvatarAddress(), 260, 260, false, false);
        avatar.setImage(image);
        usernameLabel.setText(Player.getLoggedInPlayer().getUsername());
    }
}
