package org.example.View.Transitions;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.example.Model.Game;
import org.example.Model.GameObject.BPM;
import org.example.Model.GameObject.Building;
import org.example.Model.GameObject.Bullet;
import org.example.Model.GameObject.Jet;
import org.example.View.Animations.BulletExplosion;

public class BulletTransition extends Transition {

    private final Rectangle vehicle;
    private final Bullet bullet;
    private final double velocity = 100;
    private double velocityX;
    private double velocityY;
    private Game game;
    private Pane gamePane;
    private final int duration = 10;

    public BulletTransition(Game game, Bullet bullet) {
        this.game = game;
        this.gamePane = game.getGamePane();
        this.bullet = bullet;
        this.vehicle = bullet.getVehicle();
        setVelocityX();
        setVelocityY();
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(duration));
    }

    public Rectangle getVehicle() {
        return vehicle;
    }

    public Bullet getBullet() {
        return bullet;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getVelocityX() {
        return velocityX;
    }

    private void setVelocityX() {
        double distanceX = Math.abs(vehicle.getX() - bullet.getGame().getJet().getX());
        double distanceY = Math.abs(vehicle.getY() - bullet.getGame().getJet().getY());
        velocityX = velocity * Math.cos(Math.atan(distanceY / distanceX));
        if (vehicle.getX() > bullet.getGame().getJet().getX())
            velocityX = -1 * velocityX - 10;
        else velocityX += 10;
    }

    public double getVelocityY() {
        return velocityY;
    }

    private void setVelocityY() {
        double distanceX = Math.abs(vehicle.getX() - bullet.getGame().getJet().getX());
        double distanceY = Math.abs(vehicle.getY() - bullet.getGame().getJet().getY());
        velocityY = velocity * Math.sin(Math.atan(distanceY / distanceX));
        if (vehicle.getY() > bullet.getGame().getJet().getY())
            velocityY = -velocityY;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Pane getGamePane() {
        return gamePane;
    }

    public void setGamePane(Pane gamePane) {
        this.gamePane = gamePane;
    }

    @Override
    protected void interpolate(double v) {
        double elapsedTime = v * getCycleDuration().toSeconds();
        double deltaX = elapsedTime * velocityX;
        double deltaY = elapsedTime * velocityY;

        if (bullet.getX() + deltaX <= gamePane.getScene().getWidth() && bullet.getX() + deltaX + 100 >= 0)
            bullet.setX(bullet.getX() + deltaX);
        else if (bullet.getX() + deltaX < 0) {
            destroyBullet();
        }
        else if (bullet.getX() + deltaX > gamePane.getScene().getWidth() - bullet.getWidth()) {
            destroyBullet();
        }


        if (bullet.getY() + deltaY + 80 > 0 && bullet.getY() + deltaY < 700)
            bullet.setY(bullet.getY() + deltaY);
        else if (bullet.getY() + deltaY < -80) {
            destroyBullet();

        }
        else if (bullet.getY() + deltaY >= 700) {
            destroyBullet();
        }

        if (bullet.intersects(bullet.getGame().getJet().getBoundsInParent()))
            explode();
    }

    public void explode() {
        if (bullet.isHasHit())
            return;
        bullet.setHasHit(true);
        bullet.getBulletTransition().stop();
        new BulletExplosion(bullet, gamePane).play();
        bullet.explode();
    }

    public void destroyBullet() {
        if (bullet.isHasHit())
            return;
        bullet.setHasHit(true);
        bullet.getBulletTransition().stop();
        gamePane.getChildren().remove(bullet);
    }
}
