/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Field;

/**
 *
 * @author lcaba
 */
public class FieldController {
    
    public static Response findOptimalPath(Field field, String startPlayer, String endPlayer) {
        try {
            
            if(startPlayer.equals("") || startPlayer == null) {
                return new Response("Start Player field is empty", Status.BAD_REQUEST);
            }
            
            if(endPlayer.equals("") || startPlayer == null) {
                return new Response("End Player field is empty", Status.BAD_REQUEST);
            }
            
            if(!field.isPlayerInField(startPlayer)) {
                return new Response("Start Player is not in the field", Status.NOT_FOUND);
            }
            
            if(!field.isPlayerInField(endPlayer)) {
                return new Response("End Player is not in the field", Status.NOT_FOUND);
            }
            
            return new Response("Path found succsefully", Status.OK);
        }catch(Exception e) {
            return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}
