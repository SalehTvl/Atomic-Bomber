package org.example.View.Transitions;

import javafx.animation.PauseTransition;
import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.example.Controller.GameController;
import org.example.Model.Game;
import org.example.Model.GameObject.*;
import org.example.View.Animations.JetExplosion;

public class JetTransition extends Transition {

    private Jet jet;
    private double velocityX;
    private double velocityY;
    private double degreeAngle;
    private Game game;
    private Pane gamePane;
    private final int duration = 10;
    private final double initialVelocityX = 100;

    public JetTransition(Jet jet, Game game, Pane gamePane) {
        this.jet = jet;
        this.game = game;
        this.gamePane = gamePane;
        this.degreeAngle = 0;
        this.velocityX = initialVelocityX;
        this.velocityY = 0;
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

    public double getInitialVelocityX() {
        return initialVelocityX;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    @Override
    protected void interpolate(double v) {

        if (degreeAngle >= 360)
            degreeAngle -= 360;
        if (degreeAngle < 0)
            degreeAngle += 360;
        jet.setRotate(degreeAngle);
        if ((degreeAngle >= 90 && degreeAngle <= 270) || (degreeAngle <= -90 && degreeAngle >= -270)) {
            jet.setScaleY(-1);
        } else {
            jet.setScaleY(1);
        }

        setVelocityX(initialVelocityX * Math.cos(Math.toRadians(degreeAngle)));
        setVelocityY(initialVelocityX * Math.sin(Math.toRadians(degreeAngle)));

        double elapsedTime = v * getCycleDuration().toSeconds();

        double deltaX = elapsedTime * velocityX;
        double deltaY = elapsedTime * velocityY;

        if (jet.getX() + deltaX <= gamePane.getScene().getWidth() && jet.getX() + deltaX + 100 >= 0)
            jet.setX(jet.getX() + deltaX);
        else if (jet.getX() + deltaX < 0)
           jet.setX(gamePane.getScene().getWidth() - jet.getWidth());
        else if (jet.getX() + deltaX > gamePane.getScene().getWidth() - jet.getWidth()){
            jet.setX(0);
        }

        if (jet.getY() + deltaY + 80 > 0 && jet.getY() + deltaY < 700)
            jet.setY(jet.getY() + deltaY);
        else if (jet.getY() + deltaY > 700) {
            explode();
        }
        else {
            if (velocityY < 0)
                velocityY = -velocityY;
            if (degreeAngle >= -90)
                degreeAngle = -degreeAngle;
            else
                degreeAngle = -degreeAngle;
            jet.setY(0);
        }

        checkCollision();
    }

    public void explode() {
        if(jet.isInvulnerable())
            return;
        if (jet.isHit())
            return;
        jet.setHit(true);
        jet.getJetTransition().stop();
        JetExplosion jetExplosion = new JetExplosion(jet, gamePane);
        jetExplosion.play();

        GameController gameController = new GameController();
        gameController.pauseTransitions(jet.getGame().getCurrentWave());

        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            gameController.defeated(game.getCurrentWave());
        });
        pause.play();
    }

    private void checkCollision() {
        for (Tank tank : game.getCurrentWave().getTanks())
            if (tank.intersects(jet.getBoundsInParent())) {
                explode();
                tank.getVehicleTransition().explode();
                return;
            }


        for (Truck truck : game.getCurrentWave().getTrucks())
            if (truck.intersects(jet.getBoundsInParent())) {
                explode();
                truck.getVehicleTransition().explode();
                return;
            }


        for (BPM bpm : game.getCurrentWave().getBpms())
            if (bpm.intersects(jet.getBoundsInParent())) {
                explode();
                bpm.getVehicleTransition().explode();
                return;
            }


        for (Bunker bunker : game.getCurrentWave().getBunkers())
            if (bunker.intersects(jet.getBoundsInParent())) {
                explode();
                return;
            }


        for (Building building : game.getCurrentWave().getBuildings())
            if (building.intersects(jet.getBoundsInParent())) {
                explode();
                return;
            }
    }
}
