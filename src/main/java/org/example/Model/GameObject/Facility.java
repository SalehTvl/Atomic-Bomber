package org.example.Model.GameObject;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import org.example.Model.Game;
import org.example.Model.Player;
import org.example.Model.Wave;
import org.example.View.Animations.FacilityExplosion;

import java.io.File;

public abstract class Facility extends Rectangle {

    private final int height;
    private final int width;
    private final Game game;
    private final Wave wave;
    private boolean isHit;
    private FacilityExplosion facilityExplosion;

    public Facility(Game game,int width, int height, int x) {
        super(width, height);
        this.width = width;
        this.height = height;
        this.game = game;
        this.wave = game.getCurrentWave();
        this.isHit = false;
        setX(x);
        setY(740 - height);
        game.getGamePane().getChildren().add(this);
        facilityExplosion = new FacilityExplosion(this, game.getGamePane());

        if (Player.getLoggedInPlayer().isThemeClassic()) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setSaturation(-1);
            this.setEffect(colorAdjust);
        }
    }

    public Game getGame() {
        return game;
    }

    public boolean isHit() {
        return isHit;
    }

    public Wave getWave() {
        return wave;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public FacilityExplosion getFacilityExplosion() {
        return facilityExplosion;
    }

    public void setFacilityExplosion(FacilityExplosion facilityExplosion) {
        this.facilityExplosion = facilityExplosion;
    }

    protected void playExplosionSound() {
        File file = new File("D:/AP/AtomicBomber/AlphaVersion/src/main/resources/media/sound/" + "FacilityExp" +".wav");
        Media media = new Media(file.toURI().toString());

        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(1);
        mediaPlayer.setVolume(0.3 * Player.getLoggedInPlayer().getGameSoundVolume());
        mediaPlayer.play();
    }

    abstract public void getEliminated();
}
