package org.example.Controller;

import org.example.Model.DataManager;
import org.example.Model.Player;
import org.example.Model.Result;

public class ProfileController {

    public Result removePlayerAccount() {
        Player.removePlayer(Player.getLoggedInPlayer());
        DataManager dataManager = new DataManager();
        dataManager.writePlayerData(Player.getPlayers(), dataManager.getFileAddress());
        return new Result("Account removed successfully", true);
    }

    public Result changeUsername(String username) {
        Player.getLoggedInPlayer().setUsername(username);
        DataManager dataManager = new DataManager();
        dataManager.writePlayerData(Player.getPlayers(), dataManager.getFileAddress());
        return new Result("Changed username successfully", true);
    }

    public Result changePassword(String password) {
        Player.getLoggedInPlayer().setPassword(password);
        DataManager dataManager = new DataManager();
        dataManager.writePlayerData(Player.getPlayers(), dataManager.getFileAddress());
        return new Result("Changed password successfully", true);
    }
}
