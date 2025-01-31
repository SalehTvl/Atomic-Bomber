package org.example.Model.GameObject;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import org.example.Model.Game;
import org.example.Model.Player;
import org.example.Model.Wave;
import org.example.View.Animations.VehicleExplosion;
import org.example.View.Transitions.VehicleTransition;

import java.io.File;

public abstract class Vehicle extends Rectangle {

    private int direction;
    private final int width;
    private final int length;
    private final Game game;
    private final Wave wave;
    private final VehicleTransition vehicleTransition;
    private final VehicleExplosion vehicleExplosion;
    private boolean isFrozen;
    private boolean isHit;
    private double difficultyLevel;

    public Vehicle(int width, int length, Game game, int direction) {
        super(width, length);
        this.width = width;
        this.length = length;
        this.game = game;
        this.wave = game.getCurrentWave();
        this.direction = direction;
        game.getGamePane().getChildren().add(this);
        this.vehicleTransition = new VehicleTransition(this, game);
        this.vehicleExplosion = new VehicleExplosion(this, game.getGamePane());

        if (Player.getLoggedInPlayer().isThemeClassic()) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setSaturation(-1);
            this.setEffect(colorAdjust);
        }
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Game getGame() {
        return game;
    }

    public Wave getWave() {
        return wave;
    }

    public VehicleTransition getVehicleTransition() {
        return vehicleTransition;
    }

    public VehicleExplosion getVehicleExplosion() {
        return vehicleExplosion;
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    public void setFrozen(boolean frozen) {
        isFrozen = frozen;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public double getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(double difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    protected void playExplosionSound() {
        File file = new File("D:/AP/AtomicBomber/AlphaVersion/src/main/resources/media/sound/" + "VehicleExp" +".wav");
        Media media = new Media(file.toURI().toString());

        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(1);
        mediaPlayer.setVolume(0.3 * Player.getLoggedInPlayer().getGameSoundVolume());
        mediaPlayer.play();
    }

    abstract public void getEliminated();
}
