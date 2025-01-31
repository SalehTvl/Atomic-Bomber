package org.example.View.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.Controller.AvatarController;
import org.example.Model.Player;
import org.example.Model.Result;
import org.example.View.Menu.LoginView;
import org.example.View.Menu.ProfileView;

import java.net.URL;
import java.util.ResourceBundle;

public class AvatarViewController implements Initializable {

    @FXML
    ImageView avatar;
    @FXML
    Label usernameLabel;
    @FXML
    ImageView f18Image;
    @FXML
    ImageView f16Image;
    @FXML
    ImageView f15Image;
    @FXML
    ImageView eurotyphoonImage;

    @FXML
    private void backAction() {
        ProfileView profileView = new ProfileView();

        try {
            profileView.start(LoginView.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void chooseF18Avatar() {
        AvatarController avatarController = new AvatarController();
        Result result = avatarController.setF18Avatar();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Changed Avatar");
        alert.setHeaderText(null);
        alert.setContentText(result.toString());
        alert.showAndWait();
    }

    @FXML
    private void chooseF16Avatar() {
        AvatarController avatarController = new AvatarController();
        Result result = avatarController.setF16Avatar();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Changed Avatar");
        alert.setHeaderText(null);
        alert.setContentText(result.toString());
        alert.showAndWait();
    }

    @FXML
    private void chooseF15Avatar() {
        AvatarController avatarController = new AvatarController();
        Result result = avatarController.setF15Avatar();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Changed Avatar");
        alert.setHeaderText(null);
        alert.setContentText(result.toString());
        alert.showAndWait();
    }

    @FXML
    private void chooseEuroTyphoonAvatar() {
        AvatarController avatarController = new AvatarController();
        Result result = avatarController.setEuroTyphoonAvatar();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Changed Avatar");
        alert.setHeaderText(null);
        alert.setContentText(result.toString());
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image("file:///" + Player.getLoggedInPlayer().getAvatarAddress(), 260, 260, false, false);
        avatar.setImage(image);
        usernameLabel.setText(Player.getLoggedInPlayer().getUsername());

        Image image1 = new Image("file:///D:/AP/AtomicBomber/AlphaVersion/src/main/resources/Pics/Avatars/f18hornet.jpg", 260, 260, false, false);
        f18Image.setImage(image1);
        Image image2 = new Image("file:///D:/AP/AtomicBomber/AlphaVersion/src/main/resources/Pics/Avatars/f16falcon.jpg", 260, 260, false, false);
        f16Image.setImage(image2);
        Image image3 = new Image("file:///D:/AP/AtomicBomber/AlphaVersion/src/main/resources/Pics/Avatars/f15eagle.jpg", 260, 260, false, false);
        f15Image.setImage(image3);
        Image image4 = new Image("file:///D:/AP/AtomicBomber/AlphaVersion/src/main/resources/Pics/Avatars/eurotyphoon.jpg", 260, 260, false, false);
        eurotyphoonImage.setImage(image4);
    }
}
