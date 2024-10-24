/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lcaba
 */

//Class to read the CSV files with the info of the players
public class PlayerLoader {
    
    //Static method to get the stats of the players from a CSV file
    public static List<Player> loadPlayersFromCSV(String filePath) throws IOException {
        List<Player> players = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Saltar la primera línea (encabezados)
            br.readLine();
            
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String name = values[0].trim(); // Nombre
                int speed = Integer.parseInt(values[1].trim()); // Velocidad
                int shot = Integer.parseInt(values[2].trim()); // Remates
                int possession = Integer.parseInt(values[3].trim()); // Posesión
                
                // Crear una nueva instancia de Player y agregarla a la lista
                players.add(new Player(name, speed, shot, possession));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error al convertir un valor a número: " + e.getMessage());
        }
        return players;
    }
}
