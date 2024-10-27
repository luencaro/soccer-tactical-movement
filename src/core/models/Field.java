/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import graph.GraphVisualizer;
import graph.MyGraph;
import graph.Vertex;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author lcaba
 */
public class Field {
    //Atributes
    private MyGraph myGraph;
    private ArrayList<Player> players;
    String strategy;
    
    //Constructors
    public Field() {
        this.myGraph = new MyGraph();
        this.players = new ArrayList();
        this.strategy = "possesion"; //deafult startegy
    }
    
//    public Field(MyGraph myGraph, String filepath) {
//        this.myGraph = new MyGraph();
//        this.players = (ArrayList<Player>) PlayerLoader.loadPlayersFromCSV(filepath);
//        this.strategy = "possesion"; //deafult startegy
//    }
    
    //Getters and setters
    public MyGraph getMyGraph() {
        return myGraph;
    }

    public void setMyGraph(MyGraph myGraph) {
        this.myGraph = myGraph;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = new ArrayList(players); //Copies the list to avoid changes
    }
    
//    public void setPlayers(String filepath) {
//        this.players = (ArrayList<Player>) PlayerLoader.loadPlayersFromCSV(filepath);
//    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }   
    
    //Method to ferify if the player is in the myGraph
    public Player isPlayerInField(String playerName) {
        for (Player player : players) {
            if (player.getName().equalsIgnoreCase(playerName)) {
                return player; // The player is in the myGraph
            }
        }
        return null; // The player is not in the myGraph
    }

    
    
    // Method to find the optimal path based on the specified strategy
    public List<Player> findOptimalPath(Player startPlayer) {
        int startPlayerIndex = players.indexOf(startPlayer);
        if (startPlayerIndex == -1) {
            throw new IllegalArgumentException("Start player not found in the team.");
        }

        // Get weights based on the current strategy
        int[] weights = new int[players.size()];
        for (int i = 0; i < players.size(); i++) {
            switch (strategy.toLowerCase()) {
                case "speed":
                    weights[i] = players.get(i).getSpeed();
                    break;
                case "possession":
                    weights[i] = players.get(i).getPossession();
                    break;
                case "shot":
                    weights[i] = players.get(i).getShot();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid strategy: " + strategy);
            }
        }

        // Initialize arrays for Dijkstra's algorithm
        int[] minWeights = new int[players.size()];
        boolean[] visited = new boolean[players.size()];
        int[] previous = new int[players.size()];

        // Set initial values
        Arrays.fill(minWeights, Integer.MAX_VALUE);
        Arrays.fill(visited, false);
        Arrays.fill(previous, -1);

        minWeights[startPlayerIndex] = 0;

        // Dijkstra's Algorithm
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(i -> minWeights[i]));
        queue.add(startPlayerIndex);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            visited[current] = true;

            // Get the current player
            Player currentPlayer = players.get(current);

            // Get adjacent players
            List<Vertex> adjVertices = myGraph.getAdjVertices(currentPlayer);
            for (Vertex vertex : adjVertices) {
                Player neighborPlayer = vertex.getData(); // Assuming Vertex has a method to get the Player
                int neighborIndex = players.indexOf(neighborPlayer);
                
                if (!visited[neighborIndex]) {
                    int newWeight = minWeights[current] + weights[neighborIndex];
                    if (newWeight < minWeights[neighborIndex]) {
                        minWeights[neighborIndex] = newWeight;
                        previous[neighborIndex] = current;
                        queue.add(neighborIndex);
                    }
                }
            }
        }

        // Build the optimal path
        List<Player> optimalPath = new ArrayList<>();
        for (int at = previous.length - 1; at != -1; at = previous[at]) {
            if (at != -1) {
                optimalPath.add(players.get(at));
            }
        }
        Collections.reverse(optimalPath); // Reverse to get the correct order
        return optimalPath;
    }
    
    
    
    // Method to find the optimal path based on the specified strategy from startPlayer to endPlayer
    public List<Player> findOptimalPath(String startPlayer, String endPlayer) {
        int startPlayerIndex = players.indexOf(isPlayerInField(startPlayer));
        int endPlayerIndex = players.indexOf(isPlayerInField(endPlayer));

        if (startPlayerIndex == -1) {
            throw new IllegalArgumentException("Start player not found in the team.");
        }
        if (endPlayerIndex == -1) {
            throw new IllegalArgumentException("End player not found in the team.");
        }

        // Get weights based on the current strategy
        int[] weights = new int[players.size()];
        for (int i = 0; i < players.size(); i++) {
            switch (strategy.toLowerCase()) {
                case "speed":
                    weights[i] = players.get(i).getSpeed();
                    break;
                case "possession":
                    weights[i] = players.get(i).getPossession();
                    break;
                case "shot":
                    weights[i] = players.get(i).getShot();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid strategy: " + strategy);
            }
        }

        // Initialize arrays for Dijkstra's algorithm
        int[] minWeights = new int[players.size()];
        boolean[] visited = new boolean[players.size()];
        int[] previous = new int[players.size()];

        // Set initial values
        Arrays.fill(minWeights, Integer.MAX_VALUE);
        Arrays.fill(visited, false);
        Arrays.fill(previous, -1);

        minWeights[startPlayerIndex] = 0;

        // Dijkstra's Algorithm
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(i -> minWeights[i]));
        queue.add(startPlayerIndex);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            visited[current] = true;

            // Get the current player
            Player currentPlayer = players.get(current);

            // Check if we reached the end player
            if (current == endPlayerIndex) {
                break; // Stop searching if we reached the end player
            }

            // Get adjacent players
            List<Vertex> adjVertices = myGraph.getAdjVertices(currentPlayer);
            for (Vertex vertex : adjVertices) {
                Player neighborPlayer = vertex.getData();
                int neighborIndex = players.indexOf(neighborPlayer);

                if (!visited[neighborIndex]) {
                    int newWeight = minWeights[current] + weights[neighborIndex];
                    if (newWeight < minWeights[neighborIndex]) {
                        minWeights[neighborIndex] = newWeight;
                        previous[neighborIndex] = current;
                        queue.add(neighborIndex);
                    }
                }
            }
        }

        // Build the optimal path
        List<Player> optimalPath = new ArrayList<>();
        for (int at = endPlayerIndex; at != -1; at = previous[at]) {
            optimalPath.add(players.get(at));
        }
        Collections.reverse(optimalPath); // Reverse to get the correct order
        return optimalPath;
    }
      
    public void showGraphVisualization() {
        System.setProperty("org.graphstream.ui", "swing");
        GraphVisualizer.visualizeGraph(this.myGraph);
    }
    
}
