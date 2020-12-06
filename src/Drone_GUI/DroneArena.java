package Drone_GUI;

import java.util.ArrayList;
import java.util.Random;

public class DroneArena {
    // Arena dimensions
    private int arenaWidth;
    private int arenaHeight;
    Random randomCoords; // Random Drone Coordinates generation
    ArrayList<Drone> numDrone; /* Array list type Drone that will include list all the drones that are added
     by the user */
    public int numDroneArena; // Drone counter for interface when adding drones

    /*
     *  DroneArena constructor
     *
     *  Initialises the drone arena by assigning it attributes like the x coordinate, y coordinate and random generation
     *  of coordinates for the drones.
     * */
    public DroneArena(int x, int y) {
        arenaWidth = x;
        arenaHeight = y;
        randomCoords = new Random();
        numDrone = new ArrayList<Drone>();
    }

    /* Drones added to the arena list until number of drones in
    list is the same as the given values of the arena dimensions
     */
    public void addDrone() {
        int posX;
        int posY;
        if (numDrone.size() < (arenaWidth * arenaHeight)) {
            do {
                posX = randomCoords.nextInt(arenaWidth);
                posY = randomCoords.nextInt(arenaHeight);
            } while (getDroneAt(posX, posY) != null);

            Drone anyPlace = new Drone(posX, posY, Direction.getRandomDirection());
            numDrone.add(anyPlace);
        }
    }

    /* --- GETTER FUNCTIONS ---- */

    // Returns the private attributes of the Arena

    public int getArenaWidth() {
        return arenaWidth;
    }

    public int getArenaHeight() {
        return arenaHeight;
    }

    /* --- GETTER FUNCTIONS ---- */

    /* Checks if the number of drones placed in the arena is smaller than arena size.
       Keeps adding drones until the number reaches the arena size, by which it then
       prints an error message stating arena is full on the interface
     */

    public int arenaDroneNum() {
        if (numDroneArena < (arenaWidth * arenaHeight)) {
            numDroneArena++;
        } else {
            System.err.println("\nMaximum drone capacity reached!");
        }
        return numDroneArena;
    }


    /* Checks if drone can move to given coordinates and checks if drone position might be out
    be out of arena boundaries*/
    public boolean canMoveHere(int x, int y) {
        if (getDroneAt(x, y) != null || x >= arenaWidth || y >= arenaHeight || x < 0 || y < 0) {
            return false;
        } else {
            return true;
        }
    }

    // Shows all drones in the arena
    public void showDrones(MyCanvas c) {
        for (Drone d : numDrone) {
            d.displayDrone(c);
        }
    }

    // Moves all the drones in the arena
    public void moveAllDrones(DroneArena a) {
        for (Drone d : numDrone)
            d.tryToMove(a);
    }

    // Checks the drone arraylist to see if there is a drone at the given coordinates
    public Drone getDroneAt(int x, int y) {
        Drone temp = null; // Assumes no drone found yet
        for (Drone a : numDrone) {
            if (a.isHere(x, y) == true) { // If drone exists, returns said drone
                temp = a;
            }
        }
        return temp; // If drone doesn't exist, returns null
    }

    /* Displays arena dimensions and drone information in a string format.*/
    public String toString() {
        String spot = "";
        if (!numDrone.isEmpty()) {
            spot = "";
            spot += "The size of the arena is: " + arenaWidth + " * " + arenaHeight;
            for (int i = 0; i < numDrone.size(); i++) {
                spot += "\n" + numDrone.get(i).toString();
            }
        }
        return spot;
    }

}
