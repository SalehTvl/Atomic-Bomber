package org.example.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.Model.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LeaderBoardController {

    public ObservableList<Player> getSortedByScore() {
        ArrayList<Player> players = new ArrayList<>(Player.getPlayers());

        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                if (p1.getTotalKills() != p2.getTotalKills())
                    return Integer.compare(p1.getTotalKills(), p2.getTotalKills());
                else
                    return Integer.compare(p1.getFinalWave(), p2.getFinalWave());
            }
        });

        ObservableList<Player> topTenScore = FXCollections.observableArrayList();

        for (int i = 0; i < Math.min(10, players.size()); i++) {
            topTenScore.add(players.get(i));
        }
        Collections.reverse(topTenScore);
        return topTenScore;
    }

    public ObservableList<Player> getSortedByAccuracy() {
        ArrayList<Player> players = new ArrayList<>(Player.getPlayers());

        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                p1.setTableAccuracy();
                p2.setTableAccuracy();
                if (p1.getTableAccuracy() != p2.getTableAccuracy())
                    return Double.compare(p1.getTableAccuracy(), p2.getTableAccuracy());
                else
                    return Integer.compare(p1.getFinalWave(), p2.getFinalWave());
            }
        });

        ObservableList<Player> topTenScore = FXCollections.observableArrayList();

        for (int i = 0; i < Math.min(10, players.size()); i++) {
            topTenScore.add(players.get(i));
        }
        Collections.reverse(topTenScore);
        return topTenScore;
    }

    public ObservableList<Player> getSortedByDifficultyScore() {
        ArrayList<Player> players = new ArrayList<>(Player.getPlayers());

        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                if (p1.getDifficultyBasedKills() != p2.getDifficultyBasedKills())
                    return Integer.compare(p1.getDifficultyBasedKills(), p2.getDifficultyBasedKills());
                else
                    return Integer.compare(p1.getFinalWave(), p2.getFinalWave());
            }
        });

        ObservableList<Player> topTenScore = FXCollections.observableArrayList();

        for (int i = 0; i < Math.min(10, players.size()); i++) {
            topTenScore.add(players.get(i));
        }
        Collections.reverse(topTenScore);
        return topTenScore;
    }
}
