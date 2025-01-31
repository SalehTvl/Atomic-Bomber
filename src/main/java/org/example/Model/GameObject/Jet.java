package org.example.Model.GameObject;


import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import org.example.Model.Game;
import org.example.Model.Player;
import org.example.View.Transitions.JetTransition;

public class Jet extends Rectangle {

    private final int height = 30;
    private final int width = 100;
    private final Game game;
    private int remainingAtomicBombs;
    private int remainingClusterBombs;
    private int freezeChargeLevel;
    private boolean isInvulnerable;
    private int remainingLives;
    private boolean isHit;
    private JetTransition jetTransition;

    public Jet(Game game) {
        super(110, 22);
        setX(300);
        setY(500);
        this.game = game;
        remainingLives= 3;
        remainingAtomicBombs  = 0;
        remainingClusterBombs = 0;
        freezeChargeLevel = 0;
        isInvulnerable = false;
        this.setFill(new ImagePattern(new Image
                (Jet.class.getResource("/Pics/Objects/F-35Jet.png").toExternalForm())));

        if (Player.getLoggedInPlayer().isThemeClassic()) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setSaturation(-1);
            this.setEffect(colorAdjust);
        }
    }

    public Game getGame() {
        return game;
    }

    public int getRemainingAtomicBombs() {
        return remainingAtomicBombs;
    }

    public int getRemainingClusterBombs() {
        return remainingClusterBombs;
    }

    public int getFreezeChargeLevel() {
        return freezeChargeLevel;
    }

    public String getFreezeWeaponStatus() {
        if (freezeChargeLevel > 5)
            return "Ready";
        return "Empty";
    }

    public boolean isInvulnerable() {
        return isInvulnerable;
    }

    public boolean isHit() {
        return isHit;
    }

    public JetTransition getJetTransition() {
        return jetTransition;
    }

    public void setJetTransition(JetTransition jetTransition) {
        this.jetTransition = jetTransition;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public void setRemainingAtomicBombs(int remainingAtomicBombs) {
        this.remainingAtomicBombs = remainingAtomicBombs;
    }

    public void setRemainingClusterBombs(int remainingClusterBombs) {
        this.remainingClusterBombs = remainingClusterBombs;
    }

    public void setFreezeChargeLevel(int freezeChargeLevel) {
        this.freezeChargeLevel = freezeChargeLevel;
    }

    public void setInvulnerable(boolean invulnerable) {
        isInvulnerable = invulnerable;
    }

    public int getRemainingLives() {
        return remainingLives;
    }

    public void setRemainingLives(int remainingLives) {
        this.remainingLives = remainingLives;
    }
}
