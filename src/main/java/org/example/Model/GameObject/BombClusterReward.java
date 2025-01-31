package org.example.Model.GameObject;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import org.example.Model.Game;

public class BombClusterReward extends DestructionReward{

    public BombClusterReward(Game game, Bunker bunker) {
        super(game, bunker);
        this.setFill(new ImagePattern(new Image
                (Jet.class.getResource("/Pics/Objects/BombClusterReward.png").toExternalForm())));
        game.getGamePane().getChildren().add(this);
    }

    @Override
    public void rewardPlayer() {
        getJet().setRemainingClusterBombs(getJet().getRemainingClusterBombs() + 1);
        getGame().getGamePane().getChildren().remove(this);
    }
}
