package org.example.Model.GameObject;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import org.example.Model.Game;
import org.example.View.Animations.BombAtomicExplosion;
import org.example.View.Transitions.ClusterTrigger;

public class BombCluster extends Bomb{

    private ClusterTrigger clusterTrigger;

    public BombCluster(Jet jet, Game game) {
        super(jet, game, 0);
        this.setFill(new ImagePattern(new Image
                (Jet.class.getResource("/Pics/Objects/BombCluster.png").toExternalForm())));

        game.getGamePane().getChildren().add(this);
        game.getCurrentWave().addToShotWeaponry(this);
        clusterTrigger = new ClusterTrigger(this);
        clusterTrigger.play();
    }

    @Override
    public void explodeBomb() {
        for (int i = 0; i < 3; i++) {
            BombRegular bombRegular = new BombRegular(getJet(), getGame());
            bombRegular.setX(this.getX());
            bombRegular.setY(this.getY());
            bombRegular.getBombTransition().play();
            bombRegular.getBombTransition().setVelocityX((i - 1) * 80 + this.getBombTransition().getVelocityX());
            bombRegular.getBombTransition().setVelocityY(this.getBombTransition().getVelocityY());
            bombRegular.getBombTransition().setDegreeAngle(90 + (i - 1) * 30);
        }
        getGame().getGamePane().getChildren().remove(this);
        this.getBombTransition().stop();
        getGame().getCurrentWave().getAnimations().remove(this.getBombTransition());
    }
}
