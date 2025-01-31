package org.example.Model.GameObject;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import org.example.Model.Game;

public class Truck extends Vehicle {

    public Truck(Game game, int direction) {
        super(120, 37, game, direction);
        this.setFill(new ImagePattern(new Image
                (Jet.class.getResource("/Pics/Objects/Truck.png").toExternalForm())));

        setY(700);
        switch (direction) {
            case 1:
                setX(-200);
                break;
            case -1:
                setX(1740);
                this.setScaleX(-1);
                break;
        }
    }

    private void activateNext() {
        int index = 0;
        for (Truck truck : getGame().getCurrentWave().getTrucks())
            if (truck.equals(this))
                index = getGame().getCurrentWave().getTrucks().indexOf(this) + 1;
        if (getGame().getCurrentWave().getTrucks().size() > index)
            getGame().getCurrentWave().getTrucks().get(index).getVehicleTransition().play();
    }

    @Override
    public void getEliminated() {
        if (!isHit()) {
            setHit(true);
            getWave().setRemainingTrucks(getWave().getRemainingTrucks() - 1);
            getWave().setHitBombs(getWave().getHitBombs() + 1);
            getGame().setHitBombs(getGame().getHitBombs() + 1);
            getGame().setKills(getGame().getKills() + 1);
            getGame().getJet().setFreezeChargeLevel(getGame().getJet().getFreezeChargeLevel() + 1);
            playExplosionSound();
            this.getVehicleTransition().explode();
            activateNext();
        }
    }
}
