/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

/**
 *
 * @author lcaba
 */
public class Player {
    //Atributes
    private String name;
    private int speed;
    private int shot;
    private int possession;
    
    //Constructor
    public Player(String name, int speed, int shot, int possession) {
        this.name = name;
        this.speed = speed;
        this.shot = shot;
        this.possession = possession;
    }
    
    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getShot() {
        return shot;
    }

    public void setShot(int shot) {
        this.shot = shot;
    }

    public int getPossession() {
        return possession;
    }

    public void setPossession(int possession) {
        this.possession = possession;
    }
    
    // Override the toString method
    @Override
    public String toString() {
        return name + " (Speed: " + speed + ", Shot: " + shot + ", Possession: " + possession + ")";
    }
     
}
