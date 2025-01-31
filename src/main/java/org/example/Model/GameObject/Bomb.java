package org.example.Model.GameObject;

import javafx.animation.Transition;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.shape.Rectangle;
import org.example.Model.Game;
import org.example.Model.Player;
import org.example.View.Transitions.BombTransition;

public abstract class Bomb extends Rectangle {

    private final int height = 40;
    private final int width = 10;
    private final int radius;
    private Jet jet;
    private final Game game;
    private boolean isHit;
    private BombTransition bombTransition;

    public Bomb(Jet jet, Game game, int radius) {
        super(40, 15);
        this.game = game;
        this.jet = jet;
        this.radius = radius;
        setX(jet.getX() + jet.getWidth()/2);
        setY(jet.getY());
        this.bombTransition = new BombTransition(this, jet, game, getGame().getGamePane());

        if (Player.getLoggedInPlayer().isThemeClassic()) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setSaturation(-1);
            this.setEffect(colorAdjust);
        }
    }

    public Game getGame() {
        return game;
    }

    public Jet getJet() {
        return jet;
    }

    public BombTransition getBombTransition() {
        return bombTransition;
    }

    public boolean isHit() {
        return isHit;
    }

    public int getRadius() {
        return radius;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    abstract public void explodeBomb();
}
