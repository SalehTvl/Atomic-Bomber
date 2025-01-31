package org.example.Model.GameObject;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import org.example.Model.Game;
import org.example.Model.Player;
import org.example.View.Animations.BombRegularExplosion;

import java.io.File;

public class BombRegular extends Bomb{

    private BombRegularExplosion bombRegularExplosion;

    public BombRegular(Jet jet, Game game) {
        super(jet, game, 100);
        this.setFill(new ImagePattern(new Image
                (Jet.class.getResource("/Pics/Objects/BombRegular.png").toExternalForm())));

        game.getGamePane().getChildren().add(this);
        game.getCurrentWave().addToShotWeaponry(this);
        bombRegularExplosion = new BombRegularExplosion(this, game.getGamePane());
    }

    public BombRegularExplosion getBombRegularExplosion() {
        return bombRegularExplosion;
    }

    public void setBombRegularExplosion(BombRegularExplosion bombRegularExplosion) {
        this.bombRegularExplosion = bombRegularExplosion;
    }

    @Override
    public void explodeBomb() {
        bombRegularExplosion.play();

        File file = new File("D:/AP/AtomicBomber/AlphaVersion/src/main/resources/media/sound/" + "BombRegularExp" +".wav");
        Media media = new Media(file.toURI().toString());

        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(1);
        mediaPlayer.setVolume(0.15 * Player.getLoggedInPlayer().getGameSoundVolume());
        mediaPlayer.play();
    }
}
