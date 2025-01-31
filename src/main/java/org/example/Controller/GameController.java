package org.example.Controller;

import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.example.Model.*;
import org.example.Model.GameObject.*;
import org.example.View.Menu.LoginView;
import org.example.View.Menu.MainView;

import java.util.Random;

public class GameController {

    private BackgroundImage createBackgroundImage () {
        Image image = new Image(GameController.class.getResource("/Pics/Backgrounds/dayForest.jpg").toExternalForm(), 1570 ,900, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        return backgroundImage;
    }

    public void designWave1 (Wave wave) {
        if (Player.getLoggedInPlayer().isThemeClassic()) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setSaturation(-1);
            wave.getPane().setEffect(colorAdjust);
        }

        relocateJet(wave);

        wave.getPane().setBackground(new Background(createBackgroundImage()));
        wave.getPane().getChildren().add(wave.getGame().getJet());

        Building building1 = new Building(wave.getGame(), 200);
        wave.addToBuildings(building1);
        wave.setRemainingBuildings(1);

        Bunker bunker1 = new Bunker(wave.getGame(), 900);
        wave.addToBunkers(bunker1);
        wave.setRemainingBunkers(1);

        Truck truck1 = new Truck(wave.getGame(), -1);
        Truck truck2 = new Truck(wave.getGame(), 1);
        wave.addToTrucks(truck1);
        wave.addToTrucks(truck2);
        wave.addToAnimations(truck1.getVehicleTransition());
        wave.addToAnimations(truck2.getVehicleTransition());
        wave.setRemainingTrucks(2);
        truck1.getVehicleTransition().play();

        Tank tank1 = new Tank(wave.getGame(), 1);
        Tank tank2 = new Tank(wave.getGame(), -1);
        Tank tank3 = new Tank(wave.getGame(), 1);
        wave.addToTanks(tank1);
        wave.addToTanks(tank2);
        wave.addToTanks(tank3);
        wave.addToAnimations(tank1.getVehicleTransition());
        wave.addToAnimations(tank2.getVehicleTransition());
        wave.addToAnimations(tank3.getVehicleTransition());
        wave.setRemainingTanks(3);
        tank1.getVehicleTransition().play();

        announceWave("Wave 1", wave);
    }

    public void designWave2 (Wave wave) {
        relocateJet(wave);

        Building building1 = new Building(wave.getGame(), 1200);
        wave.addToBuildings(building1);
        wave.setRemainingBuildings(1);

        Bunker bunker1 = new Bunker(wave.getGame(), 300);
        wave.addToBunkers(bunker1);
        wave.setRemainingBunkers(1);

        BPM Bpm1 = new BPM(wave.getGame(), -1);
        BPM Bpm2 = new BPM(wave.getGame(), 1);
        wave.addToBpms(Bpm1);
        wave.addToBpms(Bpm2);
        wave.addToAnimations(Bpm1.getVehicleTransition());
        wave.addToAnimations(Bpm2.getVehicleTransition());
        wave.setRemainingBpms(2);
        Bpm1.getVehicleTransition().play();

        Truck truck1 = new Truck(wave.getGame(), -1);
        Truck truck2 = new Truck(wave.getGame(), 1);
        wave.addToTrucks(truck1);
        wave.addToTrucks(truck2);
        wave.addToAnimations(truck1.getVehicleTransition());
        wave.addToAnimations(truck2.getVehicleTransition());
        wave.setRemainingTrucks(2);
        truck1.getVehicleTransition().play();

        Tank tank1 = new Tank(wave.getGame(), 1);
        Tank tank2 = new Tank(wave.getGame(), -1);
        Tank tank3 = new Tank(wave.getGame(), 1);
        Tank tank4 = new Tank(wave.getGame(), 1);
        wave.addToTanks(tank1);
        wave.addToTanks(tank2);
        wave.addToTanks(tank3);
        wave.addToTanks(tank4);
        wave.addToAnimations(tank1.getVehicleTransition());
        wave.addToAnimations(tank2.getVehicleTransition());
        wave.addToAnimations(tank3.getVehicleTransition());
        wave.addToAnimations(tank4.getVehicleTransition());
        wave.setRemainingTanks(4);
        tank1.getVehicleTransition().play();

        announceWave("Wave 2", wave);
    }

    public void designWave3 (Wave wave) {
        relocateJet(wave);

        Building building1 = new Building(wave.getGame(), 100);
        wave.addToBuildings(building1);
        wave.setRemainingBuildings(1);

        Bunker bunker1 = new Bunker(wave.getGame(), 1100);
        wave.addToBunkers(bunker1);
        wave.setRemainingBunkers(1);

        BPM Bpm1 = new BPM(wave.getGame(), -1);
        wave.addToBpms(Bpm1);
        wave.addToAnimations(Bpm1.getVehicleTransition());
        wave.setRemainingBpms(1);
        Bpm1.getVehicleTransition().play();

        Truck truck1 = new Truck(wave.getGame(), -1);
        Truck truck2 = new Truck(wave.getGame(), 1);
        wave.addToTrucks(truck1);
        wave.addToTrucks(truck2);
        wave.addToAnimations(truck1.getVehicleTransition());
        wave.addToAnimations(truck2.getVehicleTransition());
        wave.setRemainingTrucks(2);
        truck1.getVehicleTransition().play();

        Tank tank1 = new Tank(wave.getGame(), 1);
        Tank tank2 = new Tank(wave.getGame(), -1);
        Tank tank3 = new Tank(wave.getGame(), 1);
        wave.addToTanks(tank1);
        wave.addToTanks(tank2);
        wave.addToTanks(tank3);
        wave.addToAnimations(tank1.getVehicleTransition());
        wave.addToAnimations(tank2.getVehicleTransition());
        wave.addToAnimations(tank3.getVehicleTransition());
        wave.setRemainingTanks(3);
        tank1.getVehicleTransition().play();

        Mig mig = new Mig(wave.getGame());
        wave.addToAnimations(mig.getVehicleTransition());
        PauseTransition pause = new PauseTransition(Duration.seconds(20));
        pause.setOnFinished(event -> {
            if (wave.getGame().isPaused()) {
                pause.play();
                return;
            }
            mig.getVehicleTransition().play();
            mig.playAircraftSound();
            giveMigAlarm(wave.getPane());
        });
        pause.play();

        announceWave("Wave 3", wave);
    }

    public void cleanWaveMess(Wave wave) {
        wave.getPane().getChildren().removeAll(wave.getShotWeaponry());
        wave.getPane().getChildren().removeAll(wave.getTanks());
        wave.getPane().getChildren().removeAll(wave.getTrucks());
        wave.getPane().getChildren().removeAll(wave.getBpms());
        wave.getPane().getChildren().removeAll(wave.getBuildings());
        wave.getPane().getChildren().removeAll(wave.getBunkers());
    }

    public void setJetDirectionUp(Jet jet) {
        jet.getJetTransition().setDegreeAngle(jet.getJetTransition().getDegreeAngle() - 5);
    }

    public void setJetDirectionDown(Jet jet) {
        jet.getJetTransition().setDegreeAngle(jet.getJetTransition().getDegreeAngle() + 5);
    }

    public void releaseBombRegular(Game game) {
        BombRegular bombRegular = new BombRegular(game.getJet(), game);
        game.getCurrentWave().addToAnimations(bombRegular.getBombTransition());
        bombRegular.getBombTransition().play();
        game.setShotBombs(game.getShotBombs() + 1);
        game.getCurrentWave().setShotBombs(game.getCurrentWave().getShotBombs() + 1);
    }

    public void releaseBombAtomic(Game game) {
        if (game.getJet().getRemainingAtomicBombs() > 0) {
            game.getJet().setRemainingAtomicBombs(game.getJet().getRemainingAtomicBombs() - 1);
            BombAtomic bombAtomic = new BombAtomic(game.getJet(), game);
            game.getCurrentWave().addToAnimations(bombAtomic.getBombTransition());
            bombAtomic.getBombTransition().play();
            game.setShotBombs(game.getShotBombs() + 1);
            game.getCurrentWave().setShotBombs(game.getCurrentWave().getShotBombs() + 1);
        }
    }

    public void releaseBombCluster(Game game) {
        if (game.getJet().getRemainingClusterBombs() > 0) {
            game.getJet().setRemainingClusterBombs(game.getJet().getRemainingClusterBombs() - 1);
            BombCluster bombCluster = new BombCluster(game.getJet(), game);
            game.getCurrentWave().addToAnimations(bombCluster.getBombTransition());
            bombCluster.getBombTransition().play();
            game.setShotBombs(game.getShotBombs() + 1);
            game.getCurrentWave().setShotBombs(game.getCurrentWave().getShotBombs() + 1);
        }
    }

    public void activeFreezeWeapon(Game game) {
        Wave wave = game.getCurrentWave();
        pauseTransitions(game.getCurrentWave());
        game.getJet().getJetTransition().play();
        game.setStopped(false);
        game.setFrozen(true);
        game.getJet().setFreezeChargeLevel(0);

        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(7));
        pauseTransition.setOnFinished(event -> {
            if (game.getCurrentWave().equals(wave))
                resumeTransitions(wave);
            game.setFrozen(false);
        });
        pauseTransition.play();
    }

    public void pauseTransitions(Wave wave) {
        wave.getGame().setStopped(true);
        for (Transition transition : wave.getAnimations()) {
            if (transition.getStatus() == Animation.Status.RUNNING)
                transition.pause();
        }
        wave.getGame().getJet().getJetTransition().pause();
    }

    public void resumeTransitions(Wave wave) {
        wave.getGame().setStopped(false);
        for (Transition transition : wave.getAnimations()) {
            if (transition.getStatus() == Animation.Status.PAUSED)
                transition.play();
        }
        wave.getGame().getJet().getJetTransition().play();
    }

    public void passWave(Game game) {
        if (game.getCurrentWave().getNumber() == 3)
            return;

        cleanWaveMess(game.getCurrentWave());
        int index = game.getWaves().indexOf(game.getCurrentWave()) + 1;
        game.setCurrentWave(game.getWaves().get(index));
        switch (index) {
            case 1:
                designWave2(game.getWaves().get(index));
                break;
            case 2:
                designWave3(game.getWaves().get(index));
                break;
        }
    }

    public void changeJetVulnerability(Game game) {
        game.getJet().setInvulnerable(!game.getJet().isInvulnerable());
    }

    public void addToJetBombAtomics(Game game) {
        game.getJet().setRemainingAtomicBombs(game.getJet().getRemainingAtomicBombs() + 1);
    }

    public void addToJetBombClusters(Game game) {
        game.getJet().setRemainingClusterBombs(game.getJet().getRemainingClusterBombs() + 1);
    }

    public void addTank(Wave wave) {
        Tank tank = new Tank(wave.getGame(), -1);
        wave.addToTanks(tank);
        wave.setRemainingTanks(wave.getRemainingTanks() + 1);
        wave.addToAnimations(tank.getVehicleTransition());
        tank.getVehicleTransition().play();

        Random random = new Random();
        int randomNumber = random.nextInt(100, 800);
        tank.setX(randomNumber);
    }

    private void relocateJet(Wave wave) {
        Jet jet = wave.getGame().getJet();
        jet.setX(500);
        jet.setY(400);
        jet.getJetTransition().setDegreeAngle(0);
    }

    private void giveMigAlarm(Pane pane) {
        Label alarmLabel = new Label("Alert: Incoming Mig!");
        alarmLabel.setStyle("-fx-text-fill: linear-gradient(#fd6651, #c2201a); -fx-font-family: \"Arial\"; -fx-font-size: 60px; -fx-font-weight: bold;");
        pane.getChildren().add(alarmLabel);
        alarmLabel.toFront();
        alarmLabel.setLayoutX((1200 - alarmLabel.getWidth()) / 2);
        alarmLabel.setLayoutY(300);

        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
        pause.setOnFinished(event -> {
            pane.getChildren().remove(alarmLabel);
        });
        pause.play();
    }

    private void announceWave(String message, Wave wave) {
        pauseTransitions(wave);

        Rectangle rectangle = new Rectangle(1600, 1000);
        rectangle.setFill(Color.BLACK);
        wave.getPane().getChildren().add(rectangle);
        rectangle.toFront();

        Label waveLabel = new Label(message);
        waveLabel.setStyle("-fx-text-fill: linear-gradient(#FFA500, #FF4500); -fx-font-family: \"Arial\"; -fx-font-size: 60px; -fx-font-weight: bold;");
        wave.getPane().getChildren().add(waveLabel);
        waveLabel.toFront();
        waveLabel.setLayoutX((1450 - waveLabel.getWidth()) / 2);
        waveLabel.setLayoutY(300);

        PauseTransition pause = new PauseTransition(Duration.seconds(6));
        pause.setOnFinished(event -> {
            wave.getPane().getChildren().remove(waveLabel);
            wave.getPane().getChildren().remove(rectangle);
            resumeTransitions(wave);
        });
        pause.play();
    }

    public void completedWave(Wave wave) {
        pauseTransitions(wave);

        Rectangle rectangle = new Rectangle(1600, 1000);
        rectangle.setFill(Color.BLACK);
        wave.getPane().getChildren().add(rectangle);
        rectangle.toFront();

        Label waveLabel = new Label("Wave " + wave.getNumber() + " Completed Successfully!");
        waveLabel.setStyle("-fx-text-fill: linear-gradient(#FFA500, #FF4500); -fx-font-family: \"Arial\"; -fx-font-size: 60px; -fx-font-weight: bold;");
        wave.getPane().getChildren().add(waveLabel);
        waveLabel.toFront();
        waveLabel.setLayoutX((700 - waveLabel.getWidth()) / 2);
        waveLabel.setLayoutY(300);

        double accuracy = (double)100 * wave.getHitBombs() / wave.getShotBombs();
        accuracy = accuracy > 100 ? 100 : accuracy;
        String formattedAccuracy = String.format("%.2f", accuracy);

        Label accuracyLabel = new Label("Your Accuracy During This Wave: " + formattedAccuracy);
        accuracyLabel.setStyle("-fx-text-fill: linear-gradient(#FFA500, #FF4500); -fx-font-family: \"Arial\"; -fx-font-size: 40px; -fx-font-weight: bold;");
        wave.getPane().getChildren().add(accuracyLabel);
        accuracyLabel.toFront();
        accuracyLabel.setLayoutX((800 - accuracyLabel.getWidth()) / 2);
        accuracyLabel.setLayoutY(380);

        PauseTransition pause = new PauseTransition(Duration.seconds(3.5));
        pause.setOnFinished(event -> {
            wave.getPane().getChildren().remove(waveLabel);
            wave.getPane().getChildren().remove(accuracyLabel);
            wave.getPane().getChildren().remove(rectangle);
        });
        pause.play();
    }

    public void achievedVictory(Wave wave) {
        wave.getGame().getGameView().musicPlayCommand(false);

        Rectangle rectangle = new Rectangle(1600, 1000);
        rectangle.setFill(Color.BLACK);
        wave.getPane().getChildren().add(rectangle);
        rectangle.toFront();

        Label victoryLabel = new Label("Mobarake Mohandes! Bordi.");
        victoryLabel.setStyle("-fx-text-fill: linear-gradient(#29ec0b, #52f8be); -fx-font-family: \"Arial\"; -fx-font-size: 60px; -fx-font-weight: bold;");
        wave.getPane().getChildren().add(victoryLabel);
        victoryLabel.toFront();
        victoryLabel.setLayoutX((900 - victoryLabel.getWidth()) / 2);
        victoryLabel.setLayoutY(380);

        Label waveLabel = new Label("Last wave: " + wave.getGame().getCurrentWave().getNumber());
        waveLabel.setStyle("-fx-text-fill: linear-gradient(#29ec0b, #52f8be); -fx-font-family: \"Arial\"; -fx-font-size: 40px; -fx-font-weight: bold;");
        wave.getPane().getChildren().add(waveLabel);
        waveLabel.toFront();
        waveLabel.setLayoutX((900 - waveLabel.getWidth()) / 2);
        waveLabel.setLayoutY(460);

        Label killsLabel = new Label("You killed " + wave.getGame().getKills() + " enemies.");
        killsLabel.setStyle("-fx-text-fill: linear-gradient(#29ec0b, #52f8be); -fx-font-family: \"Arial\"; -fx-font-size: 40px; -fx-font-weight: bold;");
        wave.getPane().getChildren().add(killsLabel);
        killsLabel.toFront();
        killsLabel.setLayoutX((900 - killsLabel.getWidth()) / 2);
        killsLabel.setLayoutY(540);

        double accuracy = ((double) wave.getGame().getHitBombs()/wave.getGame().getShotBombs() * 100) < 100 ? ((double) wave.getGame().getHitBombs()/wave.getGame().getShotBombs() * 100) : 100;
        String formatAccuracy = String.format("%.2f", accuracy);
        Label accuracyLabel = new Label("Your accuracy: " + formatAccuracy);
        accuracyLabel.setStyle("-fx-text-fill: linear-gradient(#29ec0b, #52f8be); -fx-font-family: \"Arial\"; -fx-font-size: 40px; -fx-font-weight: bold;");
        wave.getPane().getChildren().add(accuracyLabel);
        accuracyLabel.toFront();
        accuracyLabel.setLayoutX((900 - accuracyLabel.getWidth()) / 2);
        accuracyLabel.setLayoutY(620);

        PauseTransition pause = new PauseTransition(Duration.seconds(10));
        pause.setOnFinished(event -> {
            wave.getPane().getChildren().remove(victoryLabel);
            wave.getPane().getChildren().remove(rectangle);
            endGame(wave.getGame());
        });
        pause.play();
    }

    public void defeated(Wave wave) {
        wave.getGame().getGameView().musicPlayCommand(false);

        Rectangle rectangle = new Rectangle(1600, 1000);
        rectangle.setFill(Color.BLACK);
        wave.getPane().getChildren().add(rectangle);
        rectangle.toFront();

        Label defeatLabel = new Label("Bakhti Mohandes!");
        defeatLabel.setStyle("-fx-text-fill: linear-gradient(#ec4f0b, #d00d0d); -fx-font-family: \"Arial\"; -fx-font-size: 60px; -fx-font-weight: bold;");
        wave.getPane().getChildren().add(defeatLabel);
        defeatLabel.toFront();
        defeatLabel.setLayoutX((1000 - defeatLabel.getWidth()) / 2);
        defeatLabel.setLayoutY(380);

        Label waveLabel = new Label("Last wave: " + wave.getGame().getCurrentWave().getNumber());
        waveLabel.setStyle("-fx-text-fill: linear-gradient(#ec4f0b, #d00d0d); -fx-font-family: \"Arial\"; -fx-font-size: 40px; -fx-font-weight: bold;");
        wave.getPane().getChildren().add(waveLabel);
        waveLabel.toFront();
        waveLabel.setLayoutX((1000 - waveLabel.getWidth()) / 2);
        waveLabel.setLayoutY(460);

        Label killsLabel = new Label("You killed " + wave.getGame().getKills() + " enemies.");
        killsLabel.setStyle("-fx-text-fill: linear-gradient(#ec4f0b, #d00d0d); -fx-font-family: \"Arial\"; -fx-font-size: 40px; -fx-font-weight: bold;");
        wave.getPane().getChildren().add(killsLabel);
        killsLabel.toFront();
        killsLabel.setLayoutX((1000 - killsLabel.getWidth()) / 2);
        killsLabel.setLayoutY(540);

        double accuracy = ((double) wave.getGame().getHitBombs()/wave.getGame().getShotBombs() * 100) < 100 ? ((double) wave.getGame().getHitBombs()/wave.getGame().getShotBombs() * 100) : 100;
        String formatAccuracy = String.format("%.2f", accuracy);
        Label accuracyLabel = new Label("Your accuracy: " + formatAccuracy);
        accuracyLabel.setStyle("-fx-text-fill: linear-gradient(#ec4f0b, #d00d0d); -fx-font-family: \"Arial\"; -fx-font-size: 40px; -fx-font-weight: bold;");
        wave.getPane().getChildren().add(accuracyLabel);
        accuracyLabel.toFront();
        accuracyLabel.setLayoutX((1000 - accuracyLabel.getWidth()) / 2);
        accuracyLabel.setLayoutY(620);

        PauseTransition pause = new PauseTransition(Duration.seconds(4));
        pause.setOnFinished(event -> {
            wave.getPane().getChildren().remove(defeatLabel);
            wave.getPane().getChildren().remove(rectangle);
            endGame(wave.getGame());
        });
        pause.play();
    }

    public void endGame(Game game) {
        Player player = Player.getLoggedInPlayer();
        player.setDifficultyBasedKills(player.getDifficultyBasedKills() + game.getKills() * player.getCurrentDifficulty());
        player.setTotalKills(player.getTotalKills() + game.getKills());
        player.setHitBombs(player.getHitBombs() + game.getHitBombs());
        player.setShotBombs(player.getShotBombs() + game.getShotBombs());

        if (player.getFinalWave() < game.getCurrentWave().getNumber())
            player.setFinalWave(game.getCurrentWave().getNumber());

        DataManager dataManager = new DataManager();
        dataManager.writePlayerData(Player.getPlayers(), dataManager.getFileAddress());

        game.getGameView().musicPlayCommand(false);
        try {
            new MainView().start(LoginView.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
