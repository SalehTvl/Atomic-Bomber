package org.example.Controller;

import org.example.Model.DataManager;
import org.example.Model.Player;

public class SettingsController {

    public void setDifficultyRookie() {
        Player.getLoggedInPlayer().setCurrentDifficulty(1);
        DataManager dataManager = new DataManager();
        dataManager.writePlayerData(Player.getPlayers(), dataManager.getFileAddress());
    }

    public void setDifficultyVeteran() {
        Player.getLoggedInPlayer().setCurrentDifficulty(2);
        DataManager dataManager = new DataManager();
        dataManager.writePlayerData(Player.getPlayers(), dataManager.getFileAddress());
    }

    public void setDifficultyLegend() {
        Player.getLoggedInPlayer().setCurrentDifficulty(3);
        DataManager dataManager = new DataManager();
        dataManager.writePlayerData(Player.getPlayers(), dataManager.getFileAddress());
    }

    public void changeSoundMuteness() {
        if (Player.getLoggedInPlayer().getGameSoundVolume() == 1)
            Player.getLoggedInPlayer().setGameSoundVolume(0);
        else
            Player.getLoggedInPlayer().setGameSoundVolume(1);
        DataManager dataManager = new DataManager();
        dataManager.writePlayerData(Player.getPlayers(), dataManager.getFileAddress());
    }

    public void changeClassicTheme () {
        Player.getLoggedInPlayer().setThemeClassic(!Player.getLoggedInPlayer().isThemeClassic());
        DataManager dataManager = new DataManager();
        dataManager.writePlayerData(Player.getPlayers(), dataManager.getFileAddress());
    }
}
