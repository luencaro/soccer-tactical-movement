/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graph;

import core.models.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author lcaba
 */

//Graph class
public class Graph {
    
    //Since we are usually working with diagraphs, we'll use hashmap to simulate the beghavior of the graph
    private Map<Vertex, List<Vertex>> adjVertices;
    
    //Constructor
    public Graph() {
        this.adjVertices = new HashMap<>();
    }
    
    // Method to add vertices
    public void addVertex(Player player) {
        Vertex vertex = new Vertex(player);
        adjVertices.putIfAbsent(vertex, new ArrayList<>());
    }
    
    // Method to add edges
    public void addEdge(Player player1, Player player2) {
        Vertex v1 = new Vertex(player1);
        Vertex v2 = new Vertex(player2);
        adjVertices.get(v1).add(v2);
    }
    
    // Returns the conected vertex
    public List<Vertex> getAdjVertices(Player player) {
        return adjVertices.get(new Vertex(player));
    }
    
    
    
    // Method to create a graph from an adjacency matrix
    public void createGraphFromAdjacencyMatrix(int[][] adjacencyMatrix, List<Player> players) {
        // Step 1: Add all players as vertices to the graph
        for (Player player : players) {
            addVertex(player);
        }

        // Step 2: Add edges based on the adjacency matrix
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                if (adjacencyMatrix[i][j] == 1) {
                    addEdge(players.get(i), players.get(j));
                }
            }
        }
    }
    
    
    
    public boolean areConnected(Player player1, Player player2) {
    // Create a set to track visited vertices
    Set<Vertex> visited = new HashSet<>();
    
    // Perform a depth-first search starting from player1's vertex
    Vertex startVertex = new Vertex(player1);
    Vertex targetVertex = new Vertex(player2);
    
    // Use a helper method to perform DFS and check connectivity
    return dfs(startVertex, targetVertex, visited);
}

    // Helper method for Depth-First Search
    private boolean dfs(Vertex current, Vertex target, Set<Vertex> visited) {
        // If the current vertex is the target, they are connected
        if (current.equals(target)) {
            return true;
        }

        // Mark the current vertex as visited
        visited.add(current);

        // Recursively visit all adjacent vertices
        List<Vertex> neighbors = adjVertices.get(current);
        if (neighbors != null) {
            for (Vertex neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    if (dfs(neighbor, target, visited)) {
                        return true;
                    }
                }
            }
        }

        // If no connection is found, return false
        return false;
    }

    
    
    
    //Method to print the vertex properly by overriding the toString method
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex v : adjVertices.keySet()) {
            sb.append(v.toString() + " -> ");
            sb.append(adjVertices.get(v));
            sb.append("\n");
        }
        return sb.toString();
    }
}
