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

public class BPM extends Vehicle {

    private double radius;
    private int fireRate;
    private Timeline shootingTimeline;

    public BPM(Game game, int direction) {
        super(150, 50, game, direction);
        this.radius = 100 * game.getDifficultyLevel();
        this.setFill(new ImagePattern(new Image
                (Jet.class.getResource("/Pics/Objects/russianBPM.png").toExternalForm())));

        startShooting();

        setY(700);
        switch (direction) {
            case 1:
                setX(-450);
                break;
            case -1:
                setX(1850);
                this.setScaleX(-1);
                break;
        }
    }

    private void startShooting() {
        shootingTimeline = new Timeline(
                new KeyFrame(Duration.seconds(4), event -> {
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
                Math.abs(this.getX() - getGame().getJet().getX()) < this.getRadius() * getGame().getDifficultyLevel()) {

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
    }

    private void playShootingSound() {
        File file = new File("D:/AP/AtomicBomber/AlphaVersion/src/main/resources/media/sound/" + "BPMTrioShot" +".wav");
        Media media = new Media(file.toURI().toString());

        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(1);
        mediaPlayer.setVolume(0.15 * Player.getLoggedInPlayer().getGameSoundVolume());
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

    private void activateNext() {
        int index = 0;
        for (BPM bpm : getGame().getCurrentWave().getBpms())
            if (bpm.equals(this))
                index = getGame().getCurrentWave().getBpms().indexOf(this) + 1;
        if (getGame().getCurrentWave().getBpms().size() > index) {
            getGame().getCurrentWave().getBpms().get(index).getVehicleTransition().play();
        }
    }

    @Override
    public void getEliminated() {
        if (!isHit()) {
            setHit(true);
            getWave().setRemainingBpms(getWave().getRemainingBpms() - 1);
            getWave().setHitBombs(getWave().getHitBombs() + 1);
            getGame().setHitBombs(getGame().getHitBombs() + 1);
            getGame().setKills(getGame().getKills() + 3);
            getGame().getJet().setFreezeChargeLevel(getGame().getJet().getFreezeChargeLevel() + 3);
            playExplosionSound();
            shootingTimeline.stop();
            this.getVehicleTransition().explode();
            activateNext();
        }
    }
}
