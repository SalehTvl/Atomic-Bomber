package org.example.View.Menu;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.example.Controller.GameController;
import org.example.Model.*;
import org.example.Model.GameObject.Jet;
import org.example.View.Transitions.HUDupdate;

import java.io.File;
import java.util.ArrayList;

public class GameView extends Application {

    private MediaPlayer mediaPlayer;
    private Game game;
    private Pane gamePane;
    private Jet jet;
    private Wave wave1;
    private Wave wave2;
    private Wave wave3;
    private boolean isUpKeyPressed = false;
    private boolean isDownKeyPressed = false;
    private boolean isRKeyPressed = false;
    private boolean isGKeyPressed = false;
    private boolean isCKeyPressed = false;
    private boolean isControlKeyPressed = false;
    private boolean isTabKeyPressed = false;
    private boolean isHKeyPressed = false;
    private boolean isPKeyPressed = false;
    private boolean isOKeyPressed = false;
    private boolean isLKeyPressed = false;
    private boolean isTKeyPressed = false;
    private boolean isSpaceKeyPressed = false;


    @Override
    public void start(Stage stage) throws Exception {
        setMediaPlayerMusic("fortunateSon");
        musicPlayCommand(true);

        gamePane = new Pane();
        setSize(gamePane);
        game = new Game(Player.getLoggedInPlayer(), this, gamePane, 3);
        jet = game.getJet();

        jet.getJetTransition().play();
        setUpInitialWave();
        game.setCurrentWave(wave1);
        setUpHud();
        startWaves();

        Scene scene = new Scene(gamePane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

        scene.setOnKeyPressed(event -> {

            GameController gameController = new GameController();
            if (!game.isStopped()) {
                switch (event.getCode()) {
                    case UP:
                        isUpKeyPressed = true;
                        break;
                    case DOWN:
                        isDownKeyPressed = true;
                        break;
                    case SPACE:
                        isSpaceKeyPressed = true;
                        break;
                    case R:
                        isRKeyPressed = true;
                        break;
                    case C:
                        isCKeyPressed = true;
                        break;
                    case L:
                        isLKeyPressed = true;
                        break;
                    case O:
                        isOKeyPressed = true;
                        break;
                    case P:
                        isPKeyPressed = true;
                        break;
                    case H:
                        isHKeyPressed = true;
                        break;
                    case G:
                        isGKeyPressed = true;
                        break;
                    case CONTROL:
                        isControlKeyPressed = true;
                        break;
                    case TAB:
                        isTabKeyPressed = true;
                        break;
                    case T:
                        isTKeyPressed = true;
                        break;
                    default:
                        break;
                }
            }
            else if(!game.isPaused()) {
                if (event.getCode() == KeyCode.O)
                    gameController.resumeTransitions(game.getCurrentWave());
            }

            checkInput();
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP:
                    isUpKeyPressed = false;
                    break;
                case DOWN:
                    isDownKeyPressed = false;
                    break;
                case SPACE:
                    isSpaceKeyPressed = false;
                    break;
                case R:
                    isRKeyPressed = false;
                    break;
                case C:
                    isCKeyPressed = false;
                    break;
                case L:
                    isLKeyPressed = false;
                    break;
                case O:
                    isOKeyPressed = false;
                    break;
                case P:
                    isPKeyPressed = false;
                    break;
                case H:
                    isHKeyPressed = false;
                    break;
                case G:
                    isGKeyPressed = false;
                    break;
                case CONTROL:
                    isControlKeyPressed = false;
                    break;
                case TAB:
                    isTabKeyPressed = false;
                    break;
                case T:
                    isTKeyPressed = false;
                    break;
                default:
                    break;
            }
        });
    }

    private void checkInput() {
        GameController gameController = new GameController();

        if (isUpKeyPressed)
            gameController.setJetDirectionUp(jet);
        if (isDownKeyPressed)
            gameController.setJetDirectionDown(jet);
        if (isSpaceKeyPressed)
            gameController.releaseBombRegular(game);
        if (isCKeyPressed)
            gameController.releaseBombCluster(game);
        if (isRKeyPressed)
            gameController.releaseBombAtomic(game);
        if (isTabKeyPressed)
            gameController.activeFreezeWeapon(game);
        if (isControlKeyPressed)
            gameController.addToJetBombClusters(game);
        if (isGKeyPressed)
            gameController.addToJetBombAtomics(game);
        if (isHKeyPressed)
            gameController.changeJetVulnerability(game);
        if (isPKeyPressed)
            gameController.passWave(game);
        if (isTKeyPressed)
            gameController.addTank(game.getCurrentWave());
        if (isOKeyPressed)
            gameController.resumeTransitions(game.getCurrentWave());
        if (isLKeyPressed)
            gameController.pauseTransitions(game.getCurrentWave());
    }

    private void setSize(Pane pane) {
        pane.setMinHeight(900);
        pane.setMaxHeight(900);
        pane.setMinWidth(1570);
        pane.setMaxWidth(1570);
    }

    private void setUpInitialWave() {
        wave1 = new Wave(game, gamePane, 1);
        wave2 = new Wave(game, gamePane, 2);
        wave3 = new Wave(game, gamePane, 3);

        game.addToWaves(wave1);
        game.addToWaves(wave2);
        game.addToWaves(wave3);
    }

    public void startWaves() {
        GameController gameController = new GameController();
        gameController.designWave1(wave1);
    }

    public void setMediaPlayerMusic(String musicName) {
        File file = new File("D:/AP/AtomicBomber/AlphaVersion/src/main/resources/media/" + musicName +".mp3");
        Media media = new Media(file.toURI().toString());

        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.5 * Player.getLoggedInPlayer().getGameSoundVolume());
    }

    public void musicPlayCommand(boolean play) {
        if (play)
            mediaPlayer.play();
        else
            mediaPlayer.stop();
    }

    private void setUpHud() {

        Rectangle infoBar = new Rectangle(1600, 100);
        infoBar.setFill(Color.BLACK);
        infoBar.setLayoutX(0);
        infoBar.setY(0);
        gamePane.getChildren().add(infoBar);

        Rectangle infoBarEnd = new Rectangle(1600, 8);
        infoBarEnd.setFill(Color.ORANGE);
        infoBarEnd.setLayoutX(0);
        infoBarEnd.setY(100);
        gamePane.getChildren().add(infoBarEnd);


        Label atomicBombCount = new Label("Atomic Bombs: " + jet.getRemainingAtomicBombs());
        atomicBombCount.setStyle("-fx-text-fill: linear-gradient(#FFA500, #FF4500); -fx-font-family: \"Arial\"; -fx-font-size: 20px; -fx-font-weight: bold;");
        gamePane.getChildren().add(atomicBombCount);
        atomicBombCount.setLayoutX(50);
        atomicBombCount.setLayoutY(25);


        Label clusterBombCount = new Label("cluster Bombs: " + jet.getRemainingClusterBombs());
        clusterBombCount.setStyle("-fx-text-fill: linear-gradient(#FFA500, #FF4500); -fx-font-family: \"Arial\"; -fx-font-size: 20px; -fx-font-weight: bold;");
        gamePane.getChildren().add(clusterBombCount);
        clusterBombCount.setLayoutX(300);
        clusterBombCount.setLayoutY(25);


        Label jetRemainingLives = new Label("Jet Lives: " + jet.getRemainingLives());
        jetRemainingLives.setStyle("-fx-text-fill: linear-gradient(#FFA500, #FF4500); -fx-font-family: \"Arial\"; -fx-font-size: 20px; -fx-font-weight: bold;");
        gamePane.getChildren().add(jetRemainingLives);
        jetRemainingLives.setLayoutX(950);
        jetRemainingLives.setLayoutY(25);


        ProgressBar freezeWeaponCharge = new ProgressBar();
        freezeWeaponCharge.setProgress(0);
        freezeWeaponCharge.setStyle("-fx-background-color: linear-gradient(#FFA500, #FF4500);" +
                "-fx-font-family: \"Arial\";" +
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;");
        gamePane.getChildren().add(freezeWeaponCharge);
        freezeWeaponCharge.setPrefWidth(200);
        freezeWeaponCharge.setLayoutX(635);
        freezeWeaponCharge.setLayoutY(65);


        Label freezeWeaponStatus = new Label("Freeze Weapon");
        freezeWeaponStatus.setStyle("-fx-text-fill: linear-gradient(#FFA500, #FF4500); -fx-font-family: \"Arial\"; -fx-font-size: 20px; -fx-font-weight: bold;");
        gamePane.getChildren().add(freezeWeaponStatus);
        freezeWeaponStatus.setLayoutX(655);
        freezeWeaponStatus.setLayoutY(65);


        Label killsCount = new Label("Kills: " + game.getKills());
        killsCount.setStyle("-fx-text-fill: linear-gradient(#FFA500, #FF4500); -fx-font-family: \"Arial\"; -fx-font-size: 20px; -fx-font-weight: bold;");
        gamePane.getChildren().add(killsCount);
        killsCount.setLayoutX(1150);
        killsCount.setLayoutY(25);


        Label currentWaveNumber = new Label("Wave: " + game.getCurrentWave().getNumber());
        currentWaveNumber.setStyle("-fx-text-fill: linear-gradient(#FFA500, #FF4500); -fx-font-family: \"Arial\"; -fx-font-size: 20px; -fx-font-weight: bold;");
        gamePane.getChildren().add(currentWaveNumber);
        currentWaveNumber.setLayoutX(700);
        currentWaveNumber.setLayoutY(25);

        Button pauseButton = new Button("Pause");
        pauseButton.setPrefWidth(100);
        pauseButton.setPrefHeight(50);
        pauseButton.setStyle("-fx-background-color: linear-gradient(#FFA500, #FF4500);\n" +
                "    -fx-background-radius: 10;\n" +
                "    -fx-background-insets: 0;\n" +
                "    -fx-text-fill: linear-gradient(#d00d0d, #ec4f0b);\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-font-size: 15px;\n" +
                "    -fx-font-weight: bold;");
        gamePane.getChildren().add(pauseButton);
        pauseButton.setLayoutX(1400);
        pauseButton.setLayoutY(20);
        pauseButton.setFocusTraversable(false);
        pauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!game.isPaused())
                    pauseGame();
            }
        });

        HUDupdate hudUpdate = new HUDupdate(game, infoBar, infoBarEnd,
                atomicBombCount, clusterBombCount,
                jetRemainingLives, freezeWeaponCharge, freezeWeaponStatus,
                killsCount, currentWaveNumber, pauseButton);
        hudUpdate.play();
    }

    private void pauseGame() {
        GameController gameController = new GameController();
        gameController.pauseTransitions(game.getCurrentWave());
        game.setPaused(true);

        ArrayList<Node> pauseNodes = new ArrayList<>();

        Rectangle boxOutline = new Rectangle(520, 720);
        pauseNodes.add(boxOutline);
        boxOutline.setY(90);
        boxOutline.setX(530);
        boxOutline.setFill(Color.ORANGE);
        gamePane.getChildren().add(boxOutline);

        Rectangle box = new Rectangle(500, 700);
        pauseNodes.add(box);
        box.setY(100);
        box.setX(540);
        box.setFill(Color.BLACK);
        gamePane.getChildren().add(box);


        Button resumeButton = new Button("Resume");
        pauseNodes.add(resumeButton);
        resumeButton.setPrefWidth(100);
        resumeButton.setPrefHeight(50);
        resumeButton.setStyle("-fx-background-color: linear-gradient(#FFA500, #FF4500);\n" +
                "    -fx-background-radius: 10;\n" +
                "    -fx-background-insets: 0;\n" +
                "    -fx-text-fill: linear-gradient(#d00d0d, #ec4f0b);\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-font-size: 15px;\n" +
                "    -fx-font-weight: bold;");
        gamePane.getChildren().add(resumeButton);
        resumeButton.setLayoutX(box.getX() + box.getWidth()/2 - 50);
        resumeButton.setLayoutY(box.getY() + 50);
        resumeButton.setFocusTraversable(false);
        resumeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameController.resumeTransitions(game.getCurrentWave());
                game.setPaused(false);
                gamePane.getChildren().removeAll(pauseNodes);
            }
        });


        Button exitButton = new Button("Exit");
        pauseNodes.add(exitButton);
        exitButton.setPrefWidth(100);
        exitButton.setPrefHeight(50);
        exitButton.setStyle("-fx-background-color: linear-gradient(#FFA500, #FF4500);\n" +
                "    -fx-background-radius: 10;\n" +
                "    -fx-background-insets: 0;\n" +
                "    -fx-text-fill: linear-gradient(#d00d0d, #ec4f0b);\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-font-size: 15px;\n" +
                "    -fx-font-weight: bold;");
        gamePane.getChildren().add(exitButton);
        exitButton.setLayoutX(box.getX() + box.getWidth()/2 - 50);
        exitButton.setLayoutY(box.getY() + 150);
        exitButton.setFocusTraversable(false);
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameController.cleanWaveMess(game.getCurrentWave());
                gameController.endGame(game);
            }
        });


        Button pauseMusicButton = new Button("Pause Music");
        pauseNodes.add(pauseMusicButton);
        pauseMusicButton.setPrefWidth(150);
        pauseMusicButton.setPrefHeight(50);
        pauseMusicButton.setStyle("-fx-background-color: linear-gradient(#FFA500, #FF4500);\n" +
                "    -fx-background-radius: 10;\n" +
                "    -fx-background-insets: 0;\n" +
                "    -fx-text-fill: linear-gradient(#d00d0d, #ec4f0b);\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-font-size: 15px;\n" +
                "    -fx-font-weight: bold;");
        gamePane.getChildren().add(pauseMusicButton);
        pauseMusicButton.setLayoutX(box.getX() + box.getWidth()/2 - 75);
        pauseMusicButton.setLayoutY(box.getY() + 250);
        pauseMusicButton.setFocusTraversable(false);
        pauseMusicButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                musicPlayCommand(mediaPlayer.isMute());
            }
        });


        Button FortunateSonButton = new Button("Play Fortunate Son");
        pauseNodes.add(FortunateSonButton);
        FortunateSonButton.setPrefWidth(300);
        FortunateSonButton.setPrefHeight(50);
        FortunateSonButton.setStyle("-fx-background-color: linear-gradient(#FFA500, #FF4500);\n" +
                "    -fx-background-radius: 10;\n" +
                "    -fx-background-insets: 0;\n" +
                "    -fx-text-fill: linear-gradient(#d00d0d, #ec4f0b);\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-font-size: 15px;\n" +
                "    -fx-font-weight: bold;");
        gamePane.getChildren().add(FortunateSonButton);
        FortunateSonButton.setLayoutX(box.getX() + box.getWidth()/2 - 150);
        FortunateSonButton.setLayoutY(box.getY() + 350);
        FortunateSonButton.setFocusTraversable(false);
        FortunateSonButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                musicPlayCommand(false);
                setMediaPlayerMusic("fortunateSon");
                musicPlayCommand(true);
            }
        });

        Button VirusButton = new Button("Play Virus Music");
        pauseNodes.add(VirusButton);
        VirusButton.setPrefWidth(300);
        VirusButton.setPrefHeight(50);
        VirusButton.setStyle("-fx-background-color: linear-gradient(#FFA500, #FF4500);\n" +
                "    -fx-background-radius: 10;\n" +
                "    -fx-background-insets: 0;\n" +
                "    -fx-text-fill: linear-gradient(#d00d0d, #ec4f0b);\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-font-size: 15px;\n" +
                "    -fx-font-weight: bold;");
        gamePane.getChildren().add(VirusButton);
        VirusButton.setLayoutX(box.getX() + box.getWidth()/2 - 150);
        VirusButton.setLayoutY(box.getY() + 450);
        VirusButton.setFocusTraversable(false);
        VirusButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                musicPlayCommand(false);
                setMediaPlayerMusic("Virus");
                musicPlayCommand(true);
            }
        });


        Button SympathyButton = new Button("Play Sympathy FD Music");
        pauseNodes.add(SympathyButton);
        SympathyButton.setPrefWidth(300);
        SympathyButton.setPrefHeight(50);
        SympathyButton.setStyle("-fx-background-color: linear-gradient(#FFA500, #FF4500);\n" +
                "    -fx-background-radius: 10;\n" +
                "    -fx-background-insets: 0;\n" +
                "    -fx-text-fill: linear-gradient(#d00d0d, #ec4f0b);\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-font-size: 15px;\n" +
                "    -fx-font-weight: bold;");
        gamePane.getChildren().add(SympathyButton);
        SympathyButton.setLayoutX(box.getX() + box.getWidth()/2 - 150);
        SympathyButton.setLayoutY(box.getY() + 550);
        SympathyButton.setFocusTraversable(false);
        SympathyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                musicPlayCommand(false);
                setMediaPlayerMusic("Sympathy FD");
                musicPlayCommand(true);
            }
        });
    }
}
