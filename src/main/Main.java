/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import core.models.Field;
import core.models.Player; 
import core.models.PlayerLoader;
import graph.Graph;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lcaba
 */
public class Main {
    
    public static void main(String[] args) throws IOException {
        
        String path = "C:/Users/lcaba/OneDrive/Desktop/jugadores.csv";
        
        ArrayList<Player> p = (ArrayList<Player>) PlayerLoader.loadPlayersFromCSV(path);
        // Create the players
        // Crear un ArrayList de jugadores
        List<Player> players = new ArrayList<>();

        // Agregar jugadores a la lista
        players.add(new Player("Jugador1", 85, 70, 75));
        players.add(new Player("Jugador2", 80, 65, 80));
        players.add(new Player("Jugador3", 75, 80, 70));
        players.add(new Player("Jugador4", 70, 85, 65));
        players.add(new Player("Jugador5", 82, 75, 78));
        players.add(new Player("Jugador6", 78, 82, 73));
        players.add(new Player("Jugador7", 75, 70, 80));
        players.add(new Player("Jugador8", 79, 73, 85));
        players.add(new Player("Jugador9", 81, 68, 75));
        players.add(new Player("Jugador10", 73, 80, 77));
        players.add(new Player("Jugador11", 77, 75, 78));


        // Define the adjacency matrix
        int[][] adjacencyMatrix = {
            {0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}
        };
        
        
        
        int[][] adjacencyMatrix2 = {
            {0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0},
            {0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}
        };
        
        // Create the graph
        Graph graph = new Graph();
        graph.createGraphFromAdjacencyMatrix(adjacencyMatrix2, p);

        // Print the graph
        System.out.println(graph);
        
        Field field = new Field(graph);

        // Cargar jugadores
        field.setPlayers(p);
        
        field.setField(graph);

        // Establecer estrategia
        field.setStrategy("speed");

        // Elegir un jugador inicial
        Player startPlayer = field.getPlayers().get(0); // Elige el jugador que desees
        Player endPlayer = field.getPlayers().get(10); // Elige el jugador que desees
        // Encontrar el camino óptimo
        List<Player> optimalPath = field.findOptimalPath(startPlayer);
        List<Player> optimalPath2 = field.findOptimalPath(startPlayer, endPlayer);
        
        // Imprimir el camino óptimo
        System.out.println("===================OPTIMAL PATH=============================================");
        for (Player player : optimalPath) {
            System.out.println(player);
        }
        
        System.out.println("===================OPTIMAL PATH #2=============================================");
        for (Player player : optimalPath2) {
            System.out.println(player);
        }
        
        
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(graph.areConnected(startPlayer, endPlayer));
        
    }
}
