package Drone_GUI;

/**
 * Drone stores the details about each drone that is added to the canvas, from its
 * id to the direction the drone is facing and will go next.
 * @author Shavin Croos
 */

public class Drone {
    private double dx, dy;
    private int droneID;
    private Direction.selectDirection facing;
    public static int droneCount = 1;

    //drone constructor
    public Drone(int x, int y, Direction.selectDirection f) {
        dx = x;
        dy = y;
        droneID = droneCount++;
        facing = f;
    }

    public double getX() {
        return dx;
    }
    public double getY() {
        return dy;
    }

    public String toString() {
        return "Drone " + droneID + " at " + Math.round(dx) + "," + Math.round(dy) + " facing " + facing + ".";
    }

    /**
     * Drone will move in the given direction, if the space is not
     * occupied by another drone or a wall, based on the directions given on a 16-point compass.
     * If it can't, then the drone changes direction and tries again.
     */
    public void tryToMove(DroneArena a) {
        double horizontal = 0, vertical = 0;
        if (facing == Direction.selectDirection.North) {
            horizontal = dx;
            vertical = dy + 1;
        } else if (facing == Direction.selectDirection.NorthNorthEast) {
            horizontal = dx + 0.5;
            vertical = dy + 1;
        } else if (facing == Direction.selectDirection.EastNorthEast) {
            horizontal = dx + 1;
            vertical = dy + 0.5;
        } else if (facing == Direction.selectDirection.NorthEast) {
            horizontal = dx + 1;
            vertical = dy + 1;
        } else if (facing == Direction.selectDirection.NorthNorthWest) {
            horizontal = dx - 0.5;
            vertical = dy + 1;
        } else if (facing == Direction.selectDirection.WestNorthWest) {
            horizontal = dx - 1;
            vertical = dy + 0.5;
        } else if (facing == Direction.selectDirection.NorthWest) {
            horizontal = dx + 1;
            vertical = dy - 1;
        } else if (facing == Direction.selectDirection.East) {
            horizontal = dx + 1;
            vertical = dy;
        } else if (facing == Direction.selectDirection.EastSouthEast) {
            horizontal = dx + 1;
            vertical = dy - 0.5;
        } else if (facing == Direction.selectDirection.SouthSouthEast) {
            horizontal = dx + 0.5;
            vertical = dy - 1;
        }else if (facing == Direction.selectDirection.South) {
            horizontal = dx;
            vertical = dy - 1;
        } else if (facing == Direction.selectDirection.SouthEast) {
            horizontal = dx - 1;
            vertical = dy + 1;
        } else if (facing == Direction.selectDirection.WestSouthWest) {
            horizontal = dx - 1;
            vertical = dy - 0.5;
        } else if (facing == Direction.selectDirection.SouthSouthWest) {
            horizontal = dx - 0.5;
            vertical = dy - 1;
        } else if (facing == Direction.selectDirection.SouthWest) {
            horizontal = dx - 1;
            vertical = dy - 1;
        } else if (facing == Direction.selectDirection.West) {
            horizontal = dx - 1;
            vertical = dy;
        }

        boolean go = a.canMoveHere(horizontal, vertical);
        if (go) {
            this.dx = horizontal;
            this.dy = vertical;
            System.out.println(this.toString());
        } else {
            facing = facing.getNextDirection(facing);
        }

    }
}
