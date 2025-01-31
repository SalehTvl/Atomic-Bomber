package org.example.Model.GameObject;

import javafx.scene.shape.Rectangle;
import org.example.Model.Game;
import org.example.View.Transitions.RewardTransition;


public abstract class DestructionReward extends Rectangle {

    private final Game game;
    private final Jet jet;
    private final Facility facility;
    private final RewardTransition rewardTransition;

    public DestructionReward(Game game, Facility facility) {
        super(50, 50);
        this.game = game;
        this.jet = game.getJet();
        this.facility = facility;

        this.setX(facility.getX() + facility.getWidth() / 2 - this.getWidth() / 2);
        this.setY(facility.getY() - this.getHeight());
        this.rewardTransition = new RewardTransition(game, this);
        game.getCurrentWave().getAnimations().add(rewardTransition);
        rewardTransition.play();
    }

    public Game getGame() {
        return game;
    }

    public Jet getJet() {
        return jet;
    }

    public Facility getFacility() {
        return facility;
    }

    public RewardTransition getRewardTransition() {
        return rewardTransition;
    }

    abstract public void rewardPlayer();
}
