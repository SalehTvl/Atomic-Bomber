package org.example.Model.GameObject;

import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import org.example.Model.Game;
import org.example.Model.Player;
import org.example.View.Transitions.BulletTransition;

import java.io.File;

public class Bullet extends Rectangle {

    private final int height = 5;
    private final int width = 5;
    private final Game game;
    private BulletTransition bulletTransition;
    private final Rectangle vehicle;
    private Timeline timeline;
    private boolean hasHit;

    public Bullet(Game game, Rectangle vehicle, Timeline timeline) {
        super(10, 10);
        this.game = game;
        this.vehicle = vehicle;
        this.hasHit = false;
        this.timeline = timeline;

        setY(vehicle.getY());
        setX(vehicle.getX() + 60);
        game.getGamePane().getChildren().add(this);
        this.setFill(new ImagePattern(new Image
                (Jet.class.getResource("/Pics/Objects/bullet.png").toExternalForm())));
        if (Player.getLoggedInPlayer().isThemeClassic()) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setSaturation(-1);
            this.setEffect(colorAdjust);
        }


        bulletTransition = new BulletTransition(game, this);

        getGame().getCurrentWave().addToAnimations(this.getBulletTransition());
        getGame().getCurrentWave().addToShotWeaponry(this);
    }

    public Game getGame() {
        return game;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public BulletTransition getBulletTransition() {
        return bulletTransition;
    }

    public void setBulletTransition(BulletTransition bulletTransition) {
        this.bulletTransition = bulletTransition;
    }

    public Rectangle getVehicle() {
        return vehicle;
    }

    public boolean isHasHit() {
        return hasHit;
    }

    public void setHasHit(boolean hasHit) {
        this.hasHit = hasHit;
    }

    public void explode() {

        this.setHasHit(true);
        playExplosionSound();

        Jet jet = getGame().getJet();
        if (!jet.isInvulnerable()) {
            jet.setRemainingLives(jet.getRemainingLives() - 1);
            if (jet.getRemainingLives() == 0) {
                jet.getJetTransition().explode();
                getTimeline().stop();
            }
        }
    }

    protected void playExplosionSound() {
        File file = new File("D:/AP/AtomicBomber/AlphaVersion/src/main/resources/media/sound/" + "BulletHitJet" +".wav");
        Media media = new Media(file.toURI().toString());

        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(1);
        mediaPlayer.setVolume(0.3 * Player.getLoggedInPlayer().getGameSoundVolume());
        mediaPlayer.play();
    }
}
