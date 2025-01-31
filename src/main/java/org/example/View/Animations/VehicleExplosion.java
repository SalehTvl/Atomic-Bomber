package org.example.View.Animations;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import org.example.Model.GameObject.Vehicle;

public class VehicleExplosion extends Transition {

    private Pane pane;
    private Vehicle vehicle;

    public VehicleExplosion(Vehicle vehicle, Pane pane) {
        this.vehicle = vehicle;
        this.pane = pane;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(1200));
    }

    @Override
    protected void interpolate(double v) {
        int number = 1;
        for (int i = 1; i < 13 ;i++) {
            if (v > (i - 1) * 0.1 && v <= i * 0.1) {
                number = i;
                break;
            }
            else if (v == 0) {
                number = 1;
                break;
            }
        }

        vehicle.setWidth(100);
        vehicle.setHeight(100);
        vehicle.setFill(new ImagePattern(new Image(
                VehicleExplosion.class.getResource("/Frames/VehicleExplosion/" + number + ".png").toExternalForm())));

        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane.getChildren().remove(vehicle);
            }
        });
    }
}
