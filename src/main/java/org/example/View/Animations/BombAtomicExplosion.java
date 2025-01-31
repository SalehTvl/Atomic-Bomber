package org.example.View.Animations;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.example.Model.GameObject.BombAtomic;
import org.example.Model.GameObject.BombRegular;
import org.example.Model.Player;

public class BombAtomicExplosion extends Transition {

    private Pane pane;
    private BombAtomic bombAtomic;
    private Rectangle explosion;
    public BombAtomicExplosion(BombAtomic bombAtomic, Pane pane) {
        this.bombAtomic = bombAtomic;
        this.pane = pane;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(1200));
    }

    public void addExplosionToPane() {
        explosion = new Rectangle(300, 500);
        explosion.setX(bombAtomic.getX() - explosion.getWidth()/2);
        explosion.setY(bombAtomic.getY() - explosion.getHeight() + bombAtomic.getHeight() * 4);
        pane.getChildren().remove(bombAtomic);
        explosion.setFill(Color.TRANSPARENT);
        if (Player.getLoggedInPlayer().isThemeClassic()) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setSaturation(-1);
            explosion.setEffect(colorAdjust);
        }
        pane.getChildren().add(explosion);
    }

    @Override
    protected void interpolate(double v) {
        int number = 1;
        for (int i = 1; i < 16 ;i++) {
            if (v > (i - 1) * 0.1 && v <= i * 0.1) {
                number = i;
                break;
            }
            else if (v == 0) {
                number = 1;
                break;
            }
        }

        explosion.setFill(new ImagePattern(new Image(
                JetExplosion.class.getResource("/Frames/AtomicExplosion/" + number + ".png").toExternalForm())));

        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane.getChildren().remove(explosion);
            }
        });
    }
}
