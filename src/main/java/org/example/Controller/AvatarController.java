package org.example.Controller;

import org.example.Model.DataManager;
import org.example.Model.Player;
import org.example.Model.Result;

public class AvatarController {

    public Result setF18Avatar() {
        Player.getLoggedInPlayer().setExactAvatarAddress("f18hornet.jpg");
        DataManager dataManager = new DataManager();
        dataManager.writePlayerData(Player.getPlayers(), dataManager.getFileAddress());
        return new Result("Changed Avatar to F-18 Hornet successfully", true);
    }

    public Result setF16Avatar() {
        Player.getLoggedInPlayer().setExactAvatarAddress("f16falcon.jpg");
        DataManager dataManager = new DataManager();
        dataManager.writePlayerData(Player.getPlayers(), dataManager.getFileAddress());
        return new Result("Changed Avatar to F-16 Falcon successfully", true);
    }

    public Result setF15Avatar() {
        Player.getLoggedInPlayer().setExactAvatarAddress("f15eagle.jpg");
        DataManager dataManager = new DataManager();
        dataManager.writePlayerData(Player.getPlayers(), dataManager.getFileAddress());
        return new Result("Changed Avatar to F-15 Eagle successfully", true);
    }

    public Result setEuroTyphoonAvatar() {
        Player.getLoggedInPlayer().setExactAvatarAddress("eurotyphoon.jpg");
        DataManager dataManager = new DataManager();
        dataManager.writePlayerData(Player.getPlayers(), dataManager.getFileAddress());
        return new Result("Changed Avatar to EuroTyphoon successfully", true);
    }
}
