package org.example.Model.GameObject;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import org.example.Model.Game;

public class Tank extends Vehicle {

    public Tank(Game game, int direction) {
        super(150, 50, game, direction);
        this.setFill(new ImagePattern(new Image
                (Jet.class.getResource("/Pics/Objects/T-72GroundTank.png").toExternalForm())));

        setY(700);
        switch (direction) {
            case 1:
                setX(-350);
                break;
            case -1:
                setX(1800);
                this.setScaleX(-1);
                break;
        }
    }

    private void activateNext() {
        int index = 0;
        for (Tank tank : getGame().getCurrentWave().getTanks())
            if (tank.equals(this))
                index = getGame().getCurrentWave().getTanks().indexOf(this) + 1;
        if (getGame().getCurrentWave().getTanks().size() > index)
            getGame().getCurrentWave().getTanks().get(index).getVehicleTransition().play();
    }

    @Override
    public void getEliminated() {
        if (!isHit()) {
            setHit(true);
            getWave().setRemainingTanks(getWave().getRemainingTanks() - 1);
            getWave().setHitBombs(getWave().getHitBombs() + 1);
            getGame().setHitBombs(getGame().getHitBombs() + 1);
            getGame().setKills(getGame().getKills() + 2);
            getGame().getJet().setFreezeChargeLevel(getGame().getJet().getFreezeChargeLevel() + 2);
            playExplosionSound();
            this.getVehicleTransition().explode();
            activateNext();
        }
    }
}
