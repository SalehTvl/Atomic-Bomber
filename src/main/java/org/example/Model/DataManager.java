package org.example.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private String fileAddress = new String("D:/AP/AtomicBomber/AlphaVersion/src/main/resources/PlayersData/players.json");

    public String getFileAddress() {
        return fileAddress;
    }

    public void writePlayerData(ArrayList<Player> dataList, String filename) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(dataList);

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Player> readPlayerData(String filename) {
        ArrayList<Player> playerList = new ArrayList<>();
        Gson gson = new Gson();

        File file = new File(filename);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("New file created: " + filename);
                } else {
                    System.out.println("Failed to create new file: " + filename);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return playerList;
        }

        try (Reader reader = new FileReader(filename)) {
            Player[] array = gson.fromJson(reader, Player[].class);
            for (Player player : array) {
                playerList.add(player);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return playerList;
    }
}
