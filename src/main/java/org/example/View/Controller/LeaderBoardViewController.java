package org.example.View.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.Controller.LeaderBoardController;
import org.example.Model.Player;
import org.example.View.Menu.LoginView;
import org.example.View.Menu.MainView;
import javax.swing.text.TabableView;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

public class LeaderBoardViewController implements Initializable {

    @FXML
    ImageView avatar;
    @FXML
    Label usernameLabel;
    @FXML
    private TableView<Player> tableView;
    @FXML
    private TableColumn<Player, String> usernameColumn;
    @FXML
    private TableColumn<Player, Integer> scoreColumn;
    @FXML
    private TableColumn<Player, Double> accuracyColumn;
    @FXML
    private TableColumn<Player, Integer> difficultyScoreColumn;
    @FXML
    private TableColumn<Player, Integer> finalWaveColumn;
    private ObservableList<Player> players = FXCollections.observableArrayList();

    @FXML
    private void backAction() {
        MainView mainView = new MainView();

        try {
            mainView.start(LoginView.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image("file:///" + Player.getLoggedInPlayer().getAvatarAddress(), 260, 260, false, false);
        avatar.setImage(image);
        usernameLabel.setText(Player.getLoggedInPlayer().getUsername());

        for (Player player : Player.getPlayers()) {
            player.setTableAccuracy();
            player.setTableDifficultyScore();
            player.setTableFinalWave();
            player.setTableScore();
            player.setTableUsername();
        }

        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().tableUsernameProperty());
        scoreColumn.setCellValueFactory(cellData -> cellData.getValue().tableScoreProperty().asObject());
        accuracyColumn.setCellValueFactory(cellData -> cellData.getValue().tableAccuracyProperty().asObject());
        difficultyScoreColumn.setCellValueFactory(cellData -> cellData.getValue().tableDifficultyScoreProperty().asObject());
        finalWaveColumn.setCellValueFactory(cellData -> cellData.getValue().tableFinalWaveProperty().asObject());
    }

    private void updateTableView(ObservableList<Player> sortedPlayers) {
        for (Player player : Player.getPlayers()) {
            player.setTableAccuracy();
            player.setTableDifficultyScore();
            player.setTableFinalWave();
            player.setTableScore();
            player.setTableUsername();
        }

        tableView.setItems(sortedPlayers);
    }

    public void sortByAccuracy() {
        LeaderBoardController leaderBoardController = new LeaderBoardController();
        ObservableList<Player> sortedPlayers = leaderBoardController.getSortedByAccuracy();
        updateTableView(sortedPlayers);
    }

    public void sortByScore() {
        LeaderBoardController leaderBoardController = new LeaderBoardController();
        ObservableList<Player> sortedPlayers = leaderBoardController.getSortedByScore();
        updateTableView(sortedPlayers);
    }

    public void sortByDifficultyScore() {
        LeaderBoardController leaderBoardController = new LeaderBoardController();
        ObservableList<Player> sortedPlayers = leaderBoardController.getSortedByDifficultyScore();
        updateTableView(sortedPlayers);
    }
}

