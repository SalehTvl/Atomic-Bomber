package org.example.Controller;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.Model.DataManager;
import org.example.Model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.Model.Result;

public class LoginController {

    public Result registerUser(String username, String password) {

        if (username.equals(""))
            return new Result("Username is empty!", false);

        if (password.equals(""))
            return new Result("Password is empty!", false);

        if (Player.doesPlayerExist(username))
            return new Result("Username is already taken!", false);

        new Player(username, password);
        DataManager dataManager = new DataManager();
        dataManager.writePlayerData(Player.getPlayers(), dataManager.getFileAddress());
        return new Result("Registered successfully!", true);
    }

    public Result loginUser(String username, String password) {

        if (username.equals(""))
            return new Result("Username is empty!", false);

        if (password.equals(""))
            return new Result("Password is empty!", false);

        if (!Player.doesPlayerExist(username))
            return new Result("Username does not exist!", false);

        if (!Player.getPlayerByName(username).getPassword().equals(password))
            return new Result("Incorrect password!", false);

        Player.setLoggedInPlayer(Player.getPlayerByName(username));
        return new Result("Logged in successfully!", true);
    }

    public Result guestLogin() {
        Player guest = new Player("guest", "non");
        Player.setLoggedInPlayer(guest);
        return new Result("Logged in as guset, no data will be saved!", true);
    }
}
