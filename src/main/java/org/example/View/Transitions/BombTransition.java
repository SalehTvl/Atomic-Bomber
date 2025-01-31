package org.example.View.Transitions;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.example.Controller.GameController;
import org.example.Model.*;
import org.example.Model.GameObject.*;

import java.util.ArrayList;

public class BombTransition extends Transition {

    private Jet jet;
    private final Bomb bomb;
    private double velocityX;
    private double velocityY;
    private final  double gravity = 10;
    private double degreeAngle;
    private Game game;
    private Pane gamePane;
    private final int duration = 10;

    public BombTransition(Bomb bomb, Jet jet, Game game, Pane gamePane) {
        this.bomb = bomb;
        this.jet = jet;
        this.game = game;
        this.gamePane = gamePane;
        this.velocityX = jet.getJetTransition().getVelocityX() * 1.4;
        this.velocityY = jet.getJetTransition().getVelocityY();
        this.degreeAngle = jet.getJetTransition().getDegreeAngle();
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(duration));
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public double getDegreeAngle() {
        return degreeAngle;
    }

    public void setDegreeAngle(double degreeAngle) {
        this.degreeAngle = degreeAngle;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    @Override
    protected void interpolate(double v) {
        bomb.setRotate(degreeAngle);

        double elapsedTime = v * getCycleDuration().toSeconds();
        setVelocityY(velocityY + gravity * elapsedTime * 10);

        double deltaX = elapsedTime * velocityX;
        double deltaY = elapsedTime * velocityY;

        setDegreeAngle(getDegreeAngle() + (deltaX < 0 ? -3 : 1.5) * 0.1);

        if (bomb.getX() + deltaX <= gamePane.getScene().getWidth() && bomb.getX() + deltaX + 100 >= 0)
            bomb.setX(bomb.getX() + deltaX);
        else if (jet.getX() + deltaX < 0)
            gamePane.getChildren().remove(bomb);
        else if (bomb.getX() + deltaX > gamePane.getScene().getWidth() - bomb.getWidth())
            gamePane.getChildren().remove(bomb);


        if (bomb.getY() + deltaY + 80 > 0 && bomb.getY() + deltaY < 700)
            bomb.setY(bomb.getY() + deltaY);
        else if (bomb.getY() + deltaY > 700) {
            explode();
        }
    }

    public void explode() {

        if (bomb.isHit())
            return;
        bomb.setHit(true);
        bomb.getBombTransition().stop();
        bomb.explodeBomb();
        destroyNearObjects();

        Wave wave = game.getCurrentWave();
        int numberOfGameObjects = wave.getRemainingTanks() + wave.getRemainingBpms() + wave.getRemainingTrucks()
                                    +wave.getRemainingBuildings() + wave.getRemainingBunkers();

        if (numberOfGameObjects == 0) {
            activateNextWave(wave);
        }
    }

    private void activateNextWave(Wave currentWave) {
        GameController gameController = new GameController();
        gameController.cleanWaveMess(currentWave);

        int index = game.getWaves().indexOf(currentWave) + 1;
        if (index >= game.getNumberOfWaves()) {
            gameController.achievedVictory(currentWave);
            gameController.completedWave(currentWave);
            return;
        }

        game.setCurrentWave(game.getWaves().get(index));

        switch (index) {
            case 1:
                gameController.designWave2(game.getWaves().get(index));
                break;
            case 2:
                gameController.designWave3(game.getWaves().get(index));
                break;
        }
        gameController.completedWave(currentWave);
    }

    private void destroyNearObjects() {
        destroyBpms();
        destroyTanks();
        destroyTrucks();
        destroyBunkers();
        destroyBuildings();
    }

    private void destroyBpms() {
        ArrayList<BPM> destroyedBPMs = new ArrayList<>();
        for (BPM bpm : game.getCurrentWave().getBpms())
            if (Math.abs(bpm.getX() + bpm.getWidth()/2 - bomb.getX() - bomb.getWidth()/2) < bomb.getRadius())
                destroyedBPMs.add(bpm);

        for (BPM bpm : destroyedBPMs) {
            bpm.getEliminated();
        }

        game.getCurrentWave().getBpms().removeAll(destroyedBPMs);
    }

    private void destroyTanks() {
        ArrayList<Tank> destroyedTanks = new ArrayList<>();
        for (Tank tank : game.getCurrentWave().getTanks())
            if (Math.abs(tank.getX() + tank.getWidth()/2 - bomb.getX() - bomb.getWidth()/2) < bomb.getRadius())
                if (!tank.isHit())
                    destroyedTanks.add(tank);

        for (Tank tank : destroyedTanks) {
            tank.getEliminated();
        }

        game.getCurrentWave().getTanks().removeAll(destroyedTanks);
    }

    private void destroyTrucks() {
        ArrayList<Truck> destroyedTrucks = new ArrayList<>();
        for (Truck truck : game.getCurrentWave().getTrucks())
            if (Math.abs(truck.getX() + truck.getWidth()/2 - bomb.getX() - bomb.getWidth()/2) < bomb.getRadius())
                destroyedTrucks.add(truck);

        for (Truck truck : destroyedTrucks) {
            truck.getEliminated();
        }

        game.getCurrentWave().getTanks().removeAll(destroyedTrucks);
    }

    private void destroyBunkers() {
        ArrayList<Bunker> destroyedBunkers = new ArrayList<>();
        for (Bunker bunker : game.getCurrentWave().getBunkers())
            if (Math.abs(bunker.getX() + bunker.getWidth()/2 - bomb.getX() - bomb.getWidth()/2) < bomb.getRadius())
                destroyedBunkers .add(bunker);

        game.getCurrentWave().getBunkers().removeAll(destroyedBunkers);

        for (Bunker bunker : destroyedBunkers) {
            if (!bunker.isHit()) {
                bunker.getEliminated();
            }
        }
    }

    private void destroyBuildings() {
        ArrayList<Building> destroyedBuildings = new ArrayList<>();
        for (Building building : game.getCurrentWave().getBuildings())
            if (Math.abs(building.getX() + building.getWidth()/2 - bomb.getX() - bomb.getWidth()/2) < bomb.getRadius())
                destroyedBuildings .add(building);

        game.getCurrentWave().getBuildings().removeAll(destroyedBuildings);

        for (Building building : destroyedBuildings) {
            building.getEliminated();
        }
    }
}
