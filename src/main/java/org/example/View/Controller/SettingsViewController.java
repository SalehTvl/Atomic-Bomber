package org.example.View.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.Controller.SettingsController;
import org.example.Model.Player;
import org.example.View.Menu.LoginView;
import org.example.View.Menu.MainView;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsViewController implements Initializable {
    @FXML
    private ImageView avatar;
    @FXML
    private Label usernameLabel;

    @FXML
    public void backAction() {
        MainView mainView = new MainView();

        try {
            mainView.start(LoginView.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void setRookieAction() {
        SettingsController settingsController = new SettingsController();
        settingsController.setDifficultyRookie();
    }

    @FXML
    public void setVeteranAction() {
        SettingsController settingsController = new SettingsController();
        settingsController.setDifficultyVeteran();
    }

    @FXML
    public void setLegendAction() {
        SettingsController settingsController = new SettingsController();
        settingsController.setDifficultyLegend();
    }

    @FXML
    public void muteAction() {
        SettingsController settingsController = new SettingsController();
        settingsController.changeSoundMuteness();
    }

    @FXML
    public void classicThemeAction() {
        SettingsController settingsController = new SettingsController();
        settingsController.changeClassicTheme();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image("file:///" + Player.getLoggedInPlayer().getAvatarAddress(), 260, 260, false, false);
        avatar.setImage(image);
        usernameLabel.setText(Player.getLoggedInPlayer().getUsername());
    }
}
