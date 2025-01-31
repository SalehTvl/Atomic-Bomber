package org.example.View.Transitions;

import javafx.animation.Transition;
import javafx.util.Duration;
import org.example.Model.Game;
import org.example.Model.GameObject.DestructionReward;

public class RewardTransition extends Transition {

    private DestructionReward destructionReward;
    private Game game;
    private final int velocityY = -20;

    public RewardTransition(Game game, DestructionReward destructionReward) {
        this.game = game;
        this.destructionReward = destructionReward;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(50));
    }

    @Override
    protected void interpolate(double v) {

        double elapsedTime = v * getCycleDuration().toSeconds();
        double deltaY = elapsedTime * velocityY;
        destructionReward.setY(destructionReward.getY() + deltaY);

        if (destructionReward.intersects(game.getJet().getBoundsInParent())) {
            this.stop();
            game.getCurrentWave().getAnimations().remove(this);
            destructionReward.rewardPlayer();
        }

        else if (destructionReward.getY() + 60 < 0) {
            this.stop();
            game.getCurrentWave().getAnimations().remove(this);
            game.getGamePane().getChildren().remove(destructionReward);
        }
    }
}
