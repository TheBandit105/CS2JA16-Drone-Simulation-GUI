package Drone_GUI;

/**
 * Direction determines all aspects of the drones direction, including which way the drone should
 * go next should it collide with another drone or the arena wall.
 * @author Shavin Croos
 */

import java.util.Random;

public class Direction {

    /**
     * selectDirection sets all the possible 16 (based of the
     * 16 point compass) directions that the drone can take.
     */

    public enum selectDirection {
        North,
        NorthNorthEast,
        NorthEast,
        EastNorthEast,
        NorthNorthWest,
        NorthWest,
        WestNorthWest,
        South,
        EastSouthEast,
        SouthEast,
        SouthSouthEast,
        WestSouthWest,
        SouthWest,
        SouthSouthWest,
        East,
        West;

        // getRandomDirection selects the next random direction from selectDirection
        public static selectDirection getRandomDirection() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }

        /**
         * getNextDirection checks if drone in direction given and if it can't, the drone will change to the
         * next appropriate direction.
         */

        public selectDirection getNextDirection(selectDirection n) {
            if (n == selectDirection.North) {
                return selectDirection.NorthNorthEast;
            }
            if (n == selectDirection.NorthNorthEast) {
                return selectDirection.NorthEast;
            }
            if (n == selectDirection.NorthEast) {
                return selectDirection.EastNorthEast;
            }
            if (n == selectDirection.WestNorthWest) {
                return selectDirection.NorthWest;
            }
            if (n == selectDirection.NorthWest) {
                return selectDirection.NorthNorthWest;
            }
            if (n == selectDirection.NorthNorthWest) {
                return selectDirection.North;
            }
            if (n == selectDirection.EastNorthEast) {
                return selectDirection.East;
            }
            if (n == selectDirection.East) {
                return selectDirection.EastSouthEast;
            }
            if (n == selectDirection.EastSouthEast) {
                return selectDirection.SouthEast;
            }
            if (n == selectDirection.South) {
                return selectDirection.SouthSouthWest;
            }
            if (n == selectDirection.SouthEast) {
                return selectDirection.SouthSouthEast;
            }
            if (n == selectDirection.SouthSouthEast) {
                return selectDirection.South;
            }
            if (n == selectDirection.SouthSouthWest) {
                return selectDirection.SouthWest;
            }
            if (n == selectDirection.SouthWest) {
                return selectDirection.WestSouthWest;
            }
            if (n == selectDirection.WestSouthWest) {
                return selectDirection.West;
            }
            if (n == selectDirection.West) {
                return selectDirection.WestNorthWest;
            }
            return selectDirection.North;
        }
    }
}
