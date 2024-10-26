/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Field;
import core.models.Player;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lcaba
 */
public class FieldController {
    
    public static Response findOptimalPath(Field field, String startPlayer, String endPlayer, List<Player> dijkstra) {
        try {
            
            if(startPlayer.equals("") || startPlayer == null) {
                return new Response("Start Player field is empty", Status.BAD_REQUEST);
            }
            
            if(endPlayer.equals("") || startPlayer == null) {
                return new Response("End Player field is empty", Status.BAD_REQUEST);
            }
            
            Player start = field.isPlayerInField(startPlayer);
            Player end = field.isPlayerInField(endPlayer);
            
            if( start == null) {
                return new Response("Start Player is not in the field", Status.NOT_FOUND);
            }
            
            if(end == null) {
                return new Response("End Player is not in the field", Status.NOT_FOUND);
            }
            
            if(!field.getMyGraph().areConnected(start, end)) {
                return new Response("Players are not connected", Status.NOT_FOUND);
            }
            
            dijkstra.clear();
            dijkstra.addAll(field.findOptimalPath(startPlayer, endPlayer));
            //System.out.println(dijkstra);
            return new Response("Path found succsefully", Status.OK);
        }catch(Exception e) {
            return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
    
//    public static Response createGraphFromAdjMatrix(int[][] adjacencyMatrix, List<Player> players) {
//        try {
//            
//            
//            return new Response("Path found succsefully", Status.OK);
//        }catch(Exception e) {
//            return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
//        }
//    }
}
