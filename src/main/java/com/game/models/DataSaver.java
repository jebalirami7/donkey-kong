package main.java.com.game.models;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DataSaver {

    private static DataSaver instance;
    private Map<String, Integer> playerData;

    private DataSaver() {
        playerData = new HashMap<>();
        load();
    }

    public static DataSaver getInstance() {
        if (instance == null) {
            synchronized (DataSaver.class) {
                if (instance == null) {
                    instance = new DataSaver();
                }
            }
        }
        return instance;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name: ")) {
                    String name = line.substring("Name: ".length());
                    int score = Integer.parseInt(reader.readLine().substring("Score: ".length()));
                    playerData.put(name, score);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }

        System.out.println(playerData);
    }

    public void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Integer> entry : playerData.entrySet()) {
                writer.write("Name: " + entry.getKey() + "\n");
                writer.write("Score: " + entry.getValue() + "\n");
            }
            System.out.println("Player information has been updated in " + filePath);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void update(String name, int score) {
        playerData.put(name, score);
        save();
    }

    public Map<String, Integer> read() {
        return playerData;
    }

    public void write(Player player) {
        playerData.put(player.getName(), player.getScore());
        save();
    }

    final static String filePath = "./src/main/resources/media/data.txt";
}
