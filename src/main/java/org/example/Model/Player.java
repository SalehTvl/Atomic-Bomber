package org.example.Model;

import com.google.gson.annotations.Expose;
import javafx.beans.property.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    @Expose
    private String username;
    @Expose(serialize = false)
    private String password;
    @Expose
    private String avatarAddress;
    @Expose
    private int gameSoundVolume;
    @Expose
    private boolean isThemeClassic;
    @Expose
    private int totalKills;
    @Expose
    private int difficultyBasedKills;
    @Expose
    private int shotBombs;
    @Expose
    private int hitBombs;
    @Expose
    private int finalWave;
    @Expose
    private int currentDifficulty; // for easy 2 for med 3 for hard
    @Expose(serialize = false)
    public StringProperty tableUsername;
    @Expose(serialize = false)
    public IntegerProperty tableScore;
    @Expose(serialize = false)
    public DoubleProperty tableAccuracy;
    @Expose(serialize = false)
    public IntegerProperty tableDifficultyScore;
    @Expose(serialize = false)
    public IntegerProperty tableFinalWave;
    private static ArrayList<Player> players = new ArrayList<>();
    private static Player loggedInPlayer;

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        this.setRandomAvatar();
        isThemeClassic = false;
        gameSoundVolume = 1;
        currentDifficulty = 1;
        totalKills = 0;
        difficultyBasedKills = 0;
        shotBombs = 0;
        hitBombs = 0;
        finalWave = 0;
        players.add(this);
    }

    private void setRandomAvatar() {
        Random random = new Random();
        int randomNumber = random.nextInt(4) + 1;
        switch (randomNumber) {
            case 1:
                this.avatarAddress = "D:/AP/AtomicBomber/AlphaVersion/src/main/resources/Pics/Avatars/f18hornet.jpg";
                break;
            case 2:
                this.avatarAddress = "D:/AP/AtomicBomber/AlphaVersion/src/main/resources/Pics/Avatars/f16falcon.jpg";
                break;
            case 3:
                this.avatarAddress = "D:/AP/AtomicBomber/AlphaVersion/src/main/resources/Pics/Avatars/f15eagle.jpg";
                break;
            case 4:
                this.avatarAddress = "D:/AP/AtomicBomber/AlphaVersion/src/main/resources/Pics/Avatars/eurotyphoon.jpg";
                break;

        }
    }

    public void setExactAvatarAddress(String avatarName) {
        String address = "D:/AP/AtomicBomber/AlphaVersion/src/main/resources/Pics/Avatars/" + avatarName;
        this.avatarAddress = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarAddress() {
        return avatarAddress;
    }

    public void setAvatarAddress(String avatarAddress) {
        this.avatarAddress = avatarAddress;
    }

    public int getHitBombs() {
        return hitBombs;
    }

    public void setHitBombs(int hitBombs) {
        this.hitBombs = hitBombs;
    }

    public String getAccuracy() {
        return String.format("0.2f", (double) 100 * hitBombs / shotBombs);
    }

    public int getDifficultyBasedKills() {
        return difficultyBasedKills;
    }

    public void setDifficultyBasedKills(int difficultyBasedKills) {
        this.difficultyBasedKills = difficultyBasedKills;
    }

    public int getTotalKills() {
        return totalKills;
    }

    public void setTotalKills(int totalKills) {
        this.totalKills = totalKills;
    }

    public int getShotBombs() {
        return shotBombs;
    }

    public int getGameSoundVolume() {
        return gameSoundVolume;
    }

    public void setGameSoundVolume(int gameSoundVolume) {
        this.gameSoundVolume = gameSoundVolume;
    }

    public boolean isThemeClassic() {
        return isThemeClassic;
    }

    public void setThemeClassic(boolean themeClassic) {
        isThemeClassic = themeClassic;
    }

    public void setShotBombs(int shotBombs) {
        this.shotBombs = shotBombs;
    }

    public int getFinalWave() {
        return finalWave;
    }

    public void setFinalWave(int finalWave) {
        this.finalWave = finalWave;
    }

    public int getCurrentDifficulty() {
        return currentDifficulty;
    }

    public void setCurrentDifficulty(int currentDifficulty) {
        this.currentDifficulty = currentDifficulty;
    }

    public StringProperty tableUsernameProperty() {
        return tableUsername;
    }

    public void setTableUsername() {
        this.tableUsername = new SimpleStringProperty(username);;
    }

    public IntegerProperty tableScoreProperty() {
        return tableScore;
    }

    public void setTableScore() {
        this.tableScore = new SimpleIntegerProperty(totalKills);
    }

    public DoubleProperty tableAccuracyProperty() {
        return tableAccuracy;
    }

    public double getTableAccuracy() {
        return tableAccuracy.get();
    }

    public void setTableAccuracy() {
        double accuracy = ((double) hitBombs/shotBombs * 100) < 100 ? ((double) hitBombs/shotBombs * 100) : 100;
        this.tableAccuracy = new SimpleDoubleProperty(accuracy);
    }

    public IntegerProperty tableDifficultyScoreProperty() {
        return tableDifficultyScore;
    }

    public void setTableDifficultyScore() {
        this.tableDifficultyScore = new SimpleIntegerProperty(difficultyBasedKills);
    }

    public IntegerProperty tableFinalWaveProperty() {
        return tableFinalWave;
    }

    public void setTableFinalWave() {
        this.tableFinalWave = new SimpleIntegerProperty(finalWave);
    }

    public static void setPlayers(ArrayList<Player> players) {
        Player.players = players;
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static Player getPlayerByName(String username) {
        for (Player player:  players) {
            if (player.getUsername().equals(username))
                return player;
        }
        return null;
    }

    public static boolean doesPlayerExist(String username) {
        return getPlayerByName(username) != null;
    }

    public static void setLoggedInPlayer(Player player) {
        loggedInPlayer = player;
    }

    public static Player getLoggedInPlayer() {
        return loggedInPlayer;
    }

    public static void removePlayer(Player player) {
        if (player.equals(loggedInPlayer))
            loggedInPlayer = null;
        players.remove(player);
    }
}
