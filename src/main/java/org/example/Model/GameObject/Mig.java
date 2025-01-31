package org.example.Model.GameObject;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import org.example.Model.Game;
import org.example.Model.Player;

import java.io.File;

public class Mig extends Vehicle {

    private double radius;
    private int fireRate;
    private Timeline shootingTimeline;

    public Mig(Game game) {
        super(120, 30, game, -1);
        this.radius = 100 * game.getDifficultyLevel();
        this.setFill(new ImagePattern(new Image
                (Jet.class.getResource("/Pics/Objects/Mig.png").toExternalForm())));

        startShooting();

        this.getVehicleTransition().setVelocityX(70 + (double) (70 * (game.getDifficultyLevel() - 1)) / 3);
        setY(200);
        setX(1850);
        this.setScaleX(-1);
    }

    private void startShooting() {
        shootingTimeline = new Timeline(
                new KeyFrame(Duration.seconds(3), event -> {
                    shoot();
                })
        );

        shootingTimeline.setCycleCount(Timeline.INDEFINITE);
        shootingTimeline.play();
    }

    private void shoot() {
        if (getWave().equals(getGame().getCurrentWave()) &&
                !getGame().isStopped() &&
                !getGame().isFrozen() &&
                Math.abs(this.getX() - getGame().getJet().getX()) < this.getRadius() * getGame().getDifficultyLevel() &&
                Math.abs(this.getY() - getGame().getJet().getY()) < this.getRadius() * getGame().getDifficultyLevel()) {
            Bullet bullet1 = new Bullet(getGame(), this, shootingTimeline);
            bullet1.getBulletTransition().play();

            PauseTransition pause2 = new PauseTransition(Duration.seconds(0.3));
            pause2.setOnFinished(event -> {
                if (!getGame().isPaused()) {
                    Bullet bullet2 = new Bullet(getGame(), this, shootingTimeline);
                    bullet2.getBulletTransition().play();
                }
            });
            pause2.play();

            PauseTransition pause3 = new PauseTransition(Duration.seconds(0.6));
            pause3.setOnFinished(event -> {
                if (!getGame().isPaused()) {
                    Bullet bullet3 = new Bullet(getGame(), this, shootingTimeline);
                    bullet3.getBulletTransition().play();
                }
            });
            pause3.play();

            playShootingSound();
        }

        if (this.getY() == 200 && this.getX() < -100) { // hard code to remove mig after passing only once
            getWave().getAnimations().remove(this.getVehicleTransition());
            this.getVehicleTransition().stop();
            this.shootingTimeline.stop();
            getGame().getGamePane().getChildren().remove(this);
        }

        if (!getGame().getGamePane().getChildren().contains(this))
            this.shootingTimeline.stop();
    }

    private void playShootingSound() {
        File file = new File("D:/AP/AtomicBomber/AlphaVersion/src/main/resources/media/sound/" + "BPMTrioShot" +".wav");
        Media media = new Media(file.toURI().toString());

        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(1);
        mediaPlayer.setVolume(0.3 * Player.getLoggedInPlayer().getGameSoundVolume());
        mediaPlayer.play();
    }

    public void playAircraftSound() {
        File file = new File("D:/AP/AtomicBomber/AlphaVersion/src/main/resources/media/sound/" + "JetFlyoverRight" +".wav");
        Media media = new Media(file.toURI().toString());

        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(1);
        mediaPlayer.setVolume(1 * Player.getLoggedInPlayer().getGameSoundVolume());
        mediaPlayer.play();
    }

    public double getRadius() {
        return radius;
    }

    public Timeline getShootingTimeline() {
        return shootingTimeline;
    }

    public int getFireRate() {
        return fireRate;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setFireRate(int fireRate) {
        this.fireRate = fireRate;
    }

    @Override
    public void getEliminated() {

    }

}
