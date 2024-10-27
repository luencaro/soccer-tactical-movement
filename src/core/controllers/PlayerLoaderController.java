/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Field;
import core.models.Player;
import core.models.PlayerLoader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 *
 * @author lcaba
 */
public class PlayerLoaderController {
    
    public static Response loadPlayersFromCSV(String filepath, Field field) {
        try {
            
            if (filepath.equals("") || filepath == null) {
                return new Response("Please provide a .csv file.", Status.BAD_REQUEST);
            }
            
            // Verificar que el archivo tenga la extensión .csv
            if (!filepath.toLowerCase().endsWith(".csv")) {
                return new Response("Invalid file type. Please provide a .csv file.", Status.BAD_REQUEST);
            }

            // Cargar jugadores desde el archivo CSV
            List<Player> players = PlayerLoader.loadPlayersFromCSV(filepath);
            
            // Validar los jugadores cargados
            if (!validatePlayers(players)) {
                return new Response("Failed to load players. Please check the input file for errors.", Status.BAD_REQUEST);
            }
            
            // Si todo es correcto, retornar los jugadores cargados
            field.setPlayers((ArrayList<Player>) players);
            return new Response("Players loaded successfully", Status.OK, players);
            
        } catch(FileNotFoundException e) { 
            return new Response("File not found: " + filepath, Status.NOT_FOUND);
            
        }catch (InputMismatchException e) {
            return new Response("Invalid data format in the file. Please ensure all values are correct.", Status.BAD_REQUEST);
            
        } catch (NullPointerException e) {
            return new Response("Unexpected null value encountered. Check the file for missing data.", Status.BAD_REQUEST);
            
        }catch (Exception e) {
            return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    // Método para validar la lista de jugadores
    private static boolean validatePlayers(List<Player> players) {
        // Validar cantidad de jugadores
        if (players == null || players.size() != 11) {
            return false;
        }
        
        // Validar que no falte información y que las estadísticas sean mayores que cero
        for (Player player : players) {
            if (player.getName() == null || player.getName().isEmpty() ||
                player.getSpeed() <= 0 || player.getShot() <= 0 || player.getPossession() <= 0) {
                return false;
            }
        }
        
        return true;
    }
}
