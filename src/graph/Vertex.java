/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graph;

import core.models.Player;

/**
 *
 * @author lcaba
 */
public class Vertex {
    Player data;
    
    Vertex(Player data) {
        this.data = data;
    }

    public Player getData() {
        return data;
    }

    public void setData(Player data) {
        this.data = data;
    }
    
    // Sobrescribir equals y hashCode para usar correctamente los objetos Vertex en un HashMap
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return data.equals(vertex.data);
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }

    @Override
    public String toString() {
        return data.toString();
    }
    
    // Method to get the value of the attribute based on the strategy
    public int getAttributeValue(String strategy) {
        switch (strategy.toLowerCase()) {
            case "speed":
                return data.getSpeed();
            case "possesion":
                return data.getPossession();
            case "shot":
                return data.getShot();
            default:
                return data.getPossession();
        }
    }
}
