package org.example.Model;

import javafx.scene.layout.Pane;
import org.example.Model.GameObject.Jet;
import org.example.View.Menu.GameView;
import org.example.View.Transitions.JetTransition;

import java.util.ArrayList;

public class Game {

    private final Player player;
    private final GameView gameView;
    private final Pane gamePane;
    private boolean isStopped;
    private boolean isPaused;
    private boolean isFrozen;
    private final Jet jet;
    private int difficultyLevel;
    private final int numberOfWaves;
    private Wave currentWave;
    private ArrayList<Wave> waves = new ArrayList<>();
    private int shotBombs;
    private int hitBombs;
    private int kills;

    public Game(Player player, GameView gameView, Pane gamePane, int numberOfWaves) {
        this.player = player;
        this.gameView = gameView;
        this.gamePane = gamePane;
        this.numberOfWaves = numberOfWaves;
        this.difficultyLevel = Player.getLoggedInPlayer().getCurrentDifficulty();
        shotBombs = 0;
        hitBombs = 0;
        kills = 0;
        isStopped = true;
        jet = new Jet(this);
        jet.setJetTransition(new JetTransition(jet, this, gamePane));
    }

    public Player getPlayer() {
        return player;
    }

    public GameView getGameView() {
        return gameView;
    }

    public ArrayList<Wave> getWaves() {
        return waves;
    }

    public int getHitBombs() {
        return hitBombs;
    }

    public void setHitBombs(int hitBombs) {
        this.hitBombs = hitBombs;
    }

    public boolean isStopped() {
        return isStopped;
    }

    public void setStopped(boolean stopped) {
        isStopped = stopped;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public Wave getCurrentWave() {
        return currentWave;
    }

    public Pane getGamePane() {
        return gamePane;
    }

    public Jet getJet() {
        return jet;
    }

    public void setCurrentWave(Wave currentWave) {
        this.currentWave = currentWave;
    }

    public int getNumberOfWaves() {
        return numberOfWaves;
    }

    public void addToWaves(Wave wave) {
        this.waves.add(wave);
    }

    public int getShotBombs() {
        return shotBombs;
    }

    public int getKills() {
        return kills;
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    public void setFrozen(boolean frozen) {
        isFrozen = frozen;
    }

    public void setShotBombs(int shotBombs) {
        this.shotBombs = shotBombs;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void endGame() {}
}
