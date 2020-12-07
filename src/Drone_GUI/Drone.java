package Drone_GUI;

public class Drone {
    private Direction facing; // Which direction the drone faces
    private int dx, dy; // Drone coordinates
    private int droneID; // Main ID of the drone
    public static int droneCount = 0; // Static variable to constantly count existing drones

    /*
     *  Drone constructor
     *
     *  Instantiates a drone by assigning it attributes like the x coordinate, y coordinate, id and
     *  it's direction.
     * */

    public Drone(int x, int y, Direction f) {
        dx = x;
        dy = y;
        droneID = droneCount++;
        facing = f;
    }

    /* --- GETTER FUNCTIONS ---- */

    // Returns private attributes of drones

    public int getX() {
        return dx;
    }

    public int getY() {
        return dy;
    }

    public Direction getFacing() {
        return facing;
    }

    /* --- GETTER FUNCTIONS ---- */

    /* --- SETTER FUNCTIONS ---- */

    /* --- SETTER FUNCTIONS ---- */

    // Compares parameters with existing drone positions and sees if drone occupies that position
    public boolean isHere(int sx, int sy) {
        if (sx == dx && sy == dy) {
            System.out.println("Can't go to spot");
            return true;
        }
        else {
            return false;
        }
    }

    /* Checks if drone can move. If so, then drone moves in direction specified and
    if not, then changes direction and tries again */

    public void tryToMove(DroneArena a) {
        switch (facing) {
            case North:
                if (a.canMoveHere(dx - 1, dy))
                    dx = dx - 1;
                else
                    facing = facing.nextDirection();
                break;
            case East:
                if (a.canMoveHere(dx, dy + 1))
                    dy = dy + 1;
                else
                    facing = facing.nextDirection();
                break;
            case South:
                if (a.canMoveHere(dx + 1, dy))
                    dx = dx + 1;
                else
                    facing = facing.nextDirection();
                break;
            case West:
                if (a.canMoveHere(dx, dy - 1))
                    dy = dy - 1;
                else
                    facing = facing.nextDirection();
                break;
        }
    }

    // Shows drone information in a string format
    public String toString() {
        return "Drone " + droneID + " at " + Math.round(dx) + ", " + Math.round(dy) + " facing " + facing.toString();
    }

}
