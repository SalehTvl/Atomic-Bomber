package org.example.Model.GameObject;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import org.example.Model.Game;

public class BombAtomicReward extends DestructionReward{
    public BombAtomicReward(Game game, Building building) {
        super(game, building);
        this.setFill(new ImagePattern(new Image
                (Jet.class.getResource("/Pics/Objects/BombAtomicReward.png").toExternalForm())));
        game.getGamePane().getChildren().add(this);
    }

    @Override
    public void rewardPlayer() {
        getJet().setRemainingAtomicBombs(getJet().getRemainingAtomicBombs() + 1);
        getGame().getGamePane().getChildren().remove(this);
    }
}
