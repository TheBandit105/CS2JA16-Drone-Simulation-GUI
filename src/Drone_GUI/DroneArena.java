package Drone_GUI;

/**
 * DroneArena creates the arena within the specified canvas dimension specified in the DroneInterface class,
 * for the drones to be placed and animated on.
 * @author Shavin Croos
 */

import javafx.scene.control.ListView;
import java.util.ArrayList;
import java.util.Random;

public class DroneArena {
    // Arena dimensions
    private int arenaWidth;
    private int arenaHeight;
    private int posX, posY;
    Random randomCoords; // Random Drone Coordinates generation
    //public Drone d;
    public ArrayList<Drone> numDrone; /* Array list type Drone that will include list all the drones that are added
     by the user */
    public int numDroneArena; // Drone counter for GUI when adding drones

    /*
     *  DroneArena constructor
     *
     *  Initialises the drone arena by assigning it attributes like the x coordinate, y coordinate and random generation
     *  of coordinates for the drones.
     * */

    public DroneArena(int x, int y){
        arenaWidth = x;
        arenaHeight = y;
        randomCoords = new Random();
        numDrone = new ArrayList<Drone>();
    }

    /** Drones added to the arena list, which is also in turn added to the ListView method
     * in DroneInterface. Unlike the console version, the GUI will continue to add drones
     * until there is no more space within the canvas for any more drones to be added.
     */
    public void addDrone(MyCanvas mc, ListView<Drone> Vehicles) {
        //randomly generates direction and x and y based on arena
        // and
        boolean decider = true;
        while(decider) {
            posX = randomCoords.nextInt(arenaWidth);
            posY = randomCoords.nextInt(arenaHeight);
            if (posX > 0 && posX < arenaWidth - 20 && posY > 0 && posY < arenaHeight - 25 && !isHere(posX, posY)) {
                Direction.selectDirection x = Direction.selectDirection.getRandomDirection();
                numDrone.add(new Drone(posX, posY, x));
                numDroneArena++;
                decider = false;
            }
            // new drone with unique X & Y and random direction
            mc.createDrone(this);
            DroneInterface.listDrones(Vehicles);
        }
    }

    // Moves all the drones in the arena
    public void moveAllDrones(MyCanvas mc){
        for(Drone d: numDrone){
            d.tryToMove(this) ;
        }
        mc.changeCanvas(this);
    }

    /* Checks if drone can move to given coordinates and checks if drone position might be out
   be out of arena boundaries*/
    public boolean canMoveHere(double x, double y){
        return !isHere(x, y) && x > 0 && y > 0 && x < arenaWidth - 20 && y < arenaHeight - 25;
    }

    /**
     * Compares parameters with existing drone positions and sees if drone occupies that position and now
     * if the drone collides with the wall or another drone, the drone will change direction after the collision.
     */

    public boolean isHere (double lengthwise, double crosswise) {
        int a = 0, b = 0;
        for(Drone d: numDrone){
            if(crosswise == d.getY() || crosswise + 30 == d.getY() || crosswise - 30 == d.getY()){
                ++a;
            }
            if(lengthwise == d.getX() || lengthwise + 30 == d.getX() || lengthwise - 30 == d.getX()){
                ++b;
            } else {
                continue;
            }
        }

        if(a > 0 && b > 0){
            System.out.println("Path blocked!");
            return true;
        } else{
            return false;
        }
    }

    // Returns the information on the arena dimensions and the number of drones in the arena
    public String toString(){
        return "The size of the arena is: " + arenaWidth + " * " + arenaHeight + ".\n" +
                "Drone count: " + numDroneArena + ".";
    }



}
