package org.example.View.Transitions;

import javafx.animation.Transition;
import javafx.util.Duration;
import org.example.Model.GameObject.BombCluster;

public class ClusterTrigger extends Transition {

    private final int clusterAltitude = 500;
    private BombCluster bombCluster;

    public ClusterTrigger(BombCluster bombCluster) {
        this.bombCluster = bombCluster;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(50));
    }

    @Override
    protected void interpolate(double v) {
        if (bombCluster.getY() > clusterAltitude) {
            bombCluster.explodeBomb();
            this.stop();
        }
    }
}
