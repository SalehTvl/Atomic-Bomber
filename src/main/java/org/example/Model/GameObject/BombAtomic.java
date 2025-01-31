package org.example.Model.GameObject;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import org.example.Model.Game;
import org.example.Model.Player;
import org.example.View.Animations.BombAtomicExplosion;
import org.example.View.Animations.BombRegularExplosion;

import java.io.File;

public class BombAtomic extends Bomb{

    private final BombAtomicExplosion bombAtomicExplosion;

    public BombAtomic(Jet jet, Game game) {
        super(jet, game, 200);
        this.setFill(new ImagePattern(new Image
                (Jet.class.getResource("/Pics/Objects/BombAtomic.png").toExternalForm())));

        game.getGamePane().getChildren().add(this);
        game.getCurrentWave().addToShotWeaponry(this);
        bombAtomicExplosion = new BombAtomicExplosion(this, game.getGamePane());
    }

    public BombAtomicExplosion getBombAtomicExplosion() {
        return bombAtomicExplosion;
    }

    @Override
    public void explodeBomb() {
        bombAtomicExplosion.addExplosionToPane();
        bombAtomicExplosion.play();

        File file = new File("D:/AP/AtomicBomber/AlphaVersion/src/main/resources/media/sound/" + "BombRegularExp" +".wav");
        Media media = new Media(file.toURI().toString());

        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(1);
        mediaPlayer.setVolume(0.15 * Player.getLoggedInPlayer().getGameSoundVolume());
        mediaPlayer.play();
    }
}
