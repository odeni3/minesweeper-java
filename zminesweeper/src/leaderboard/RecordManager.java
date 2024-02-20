package leaderboard;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import players.Player;

public class RecordManager implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    //definindo atributos
    private static RecordManager instance;
    private List<Player> players;

    private RecordManager() {
        players = new ArrayList<>();
    }
    
    //fazendo com que RecordManager seja um singleton, ou seja, possa ser instanciada apenas uma vez
    public static RecordManager getInstance() {
        if (instance == null) {
            instance = new RecordManager();
        }
        return instance;
    }
    
    //método para adicionar um player
    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }
    
    //método para salvar os recordes
    public void saveRecords(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Player player : players) {
                writer.println(player.getName() + "," + player.getScore());
            }
            //System.out.println("Records saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //método para carregar a lista de jogadores de um arquivo
    public void loadRecords(String filename) {
        //limpa a lista de jogadores antes de carregar os registros do arquivo
        players.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String playerName = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    players.add(new Player(playerName, score));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //método para obter a lista de jogadores ordenada pelo score
    public List<Player> getSortedPlayers() {
        List<Player> sortedPlayers = new ArrayList<>(players);
        Collections.sort(sortedPlayers, Comparator.comparingInt(Player::getScore).reversed());
        return sortedPlayers;
    }
}
