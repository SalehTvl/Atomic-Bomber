package org.example.View.Animations;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import org.example.Model.GameObject.BombRegular;

public class BombRegularExplosion extends Transition {

    private Pane pane;
    private BombRegular bombRegular;

    public BombRegularExplosion(BombRegular bombRegular, Pane pane) {
        this.bombRegular = bombRegular;
        this.pane = pane;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(400));
    }

    @Override
    protected void interpolate(double v) {
        int number = 1;
        for (int i = 1; i < 7 ;i++) {
            if (v > (i - 1) * 0.1 && v <= i * 0.1) {
                number = i;
                break;
            }
            else if (v == 0) {
                number = 1;
                break;
            }
        }

        bombRegular.setWidth(100);
        bombRegular.setHeight(100);
        bombRegular.setFill(new ImagePattern(new Image(
                JetExplosion.class.getResource("/Frames/JetExplosion/destroy-" + number + ".png").toExternalForm())));

        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane.getChildren().remove(bombRegular);
            }
        });
    }
}
