package org.example.View.Transitions;

import javafx.animation.Transition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.example.Model.Game;

public class HUDupdate extends Transition {
    private Game game;
    private final int duration = 10;
    private Rectangle infoBar;
    private Rectangle infoBarEnd;
    private Label atomicBombCount;
    private Label clusterBombCount;
    private Label jetRemainingLives;
    private ProgressBar freezeWeaponCharge;
    private Label freezeWeaponStatus;
    private Label killsCount;
    private Label currentWaveNumber;
    private Button pauseButton;

    public HUDupdate(Game game,
                     Rectangle infoBar,
                     Rectangle infoBarEnd,
                     Label atomicBombCount,
                     Label clusterBombCount,
                     Label jetRemainingLives,
                     ProgressBar freezeWeaponCharge,
                     Label freezeWeaponStatus,
                     Label killsCount,
                     Label currentWaveNumber,
                     Button pauseButton) {

        this.game = game;
        this.infoBar = infoBar;
        this.infoBarEnd = infoBarEnd;
        this.atomicBombCount = atomicBombCount;
        this.clusterBombCount = clusterBombCount;
        this.jetRemainingLives = jetRemainingLives;
        this.freezeWeaponCharge = freezeWeaponCharge;
        this.freezeWeaponStatus = freezeWeaponStatus;
        this.killsCount = killsCount;
        this.currentWaveNumber = currentWaveNumber;
        this.pauseButton = pauseButton;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(duration));
    }

    @Override
    protected void interpolate(double v) {
        setNodesToFront();

        atomicBombCount.setText("Atomic Bombs: " + game.getJet().getRemainingAtomicBombs());
        clusterBombCount.setText("cluster Bombs: " + game.getJet().getRemainingClusterBombs());
        jetRemainingLives.setText("Jet Lives: " + game.getJet().getRemainingLives());
        killsCount.setText("Kills: " + game.getKills());
        currentWaveNumber.setText("Wave: " + game.getCurrentWave().getNumber());

        freezeWeaponCharge.setProgress((double) game.getJet().getFreezeChargeLevel() / 6);
    }

    private void setNodesToFront() {
        infoBar.toFront();
        infoBarEnd.toFront();
        atomicBombCount.toFront();
        clusterBombCount.toFront();
        jetRemainingLives.toFront();
        freezeWeaponCharge.toFront();
        freezeWeaponStatus.toFront();
        killsCount.toFront();
        currentWaveNumber.toFront();
        pauseButton.toFront();
    }
}
