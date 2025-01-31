package org.example.Model.GameObject;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import org.example.Model.Game;

public class Bunker extends Facility {

    private final int height = 80;
    private final int width = 300;

    public Bunker(Game game, int x) {
        super(game, 130, 50, x);

        this.setFill(new ImagePattern(new Image
                (Jet.class.getResource("/Pics/Objects/Bunker.png").toExternalForm())));
    }

    @Override
    public void getEliminated() {
        if (!this.isHit()) {
            setHit(true);
            createReward();
            getWave().setRemainingBunkers(getWave().getRemainingBunkers() - 1);
            getWave().setHitBombs(getWave().getHitBombs() + 1);
            getGame().setHitBombs(getGame().getHitBombs() + 1);
            getGame().setKills(getGame().getKills() + 2);
            getGame().getJet().setFreezeChargeLevel(getGame().getJet().getFreezeChargeLevel() + 2);
            playExplosionSound();
            this.getFacilityExplosion().play();
        }
    }

    private void createReward() {
        new BombClusterReward(getGame(), this);
    }
}
