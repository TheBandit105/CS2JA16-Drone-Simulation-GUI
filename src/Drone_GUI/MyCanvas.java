package Drone_GUI;

import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * MyCanvas will contain info based on drone images and canvas size
 * @author Shavin Croos
 */

public class MyCanvas extends Application {
    //variables and declarations related to the file
    int canvasWidthSize; // constants for relevant sizes, default values set
    int canvasHeightSize;
    //Images loaded in at the start
    Image droneVehicle = new Image("Drone.png");
    GraphicsContext gc;


    /**
     * constructor for canvas
     */
    public MyCanvas(GraphicsContext g, int xcs, int ycs) {
        //Sets up the canvas
        gc = g;
        canvasWidthSize = xcs;
        canvasHeightSize = ycs;
    }

    /**
     * Fills canvas and border with the colours inputed
     * within the canvas dimensions
     * @param CanvasWidth
     * @param CanvasHeight
     */
    public void setFillArenaColour(int CanvasWidth, int CanvasHeight) {
        //sets colour, size and formatting for canvas
        gc.setFill(Color.CYAN);
        gc.fillRect(0, 0, CanvasWidth, CanvasHeight);
        gc.setStroke(Color.CYAN);
        gc.strokeRect(0, 0, CanvasWidth, CanvasHeight);
    }

    /**
     * Will keep changing canvas to match the drone images position when moving
     * and will recreate the drone image for each canvas change
     * @param Arena
     */
    public void changeCanvas(DroneArena Arena){
        //sets formatting and clears canvas
        gc.clearRect(0, 0, canvasWidthSize, canvasHeightSize);
        //fills the canvas
        setFillArenaColour(canvasWidthSize, canvasHeightSize);
        //draws all drones
        createDrone(Arena);

    }

    /**
     * Draws the drone image onto the canvas
     * @param Arena
     */
    public void createDrone(DroneArena Arena) {
        //loops through drone array multidrone
        for (Drone d : Arena.numDrone) {
            //draws images based on information inside of multidrone, giving size and location
            gc.drawImage(droneVehicle, d.getX(), d.getY(), 25, 25);
        }

    }

        @Override
        public void start (Stage primaryStage) throws Exception {
            // TODO Auto-generated method stub

        }

}
