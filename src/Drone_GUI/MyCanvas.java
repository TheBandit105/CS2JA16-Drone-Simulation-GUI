package Drone_GUI;

import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MyCanvas extends Application {
    int canvasWidthSize = 512;                // constants for relevant sizes, default values set
    int canvasHeightSize = 512;
    Image hexadrone = new Image("Drone.png");
    GraphicsContext gc;

    /*
     * onstructor sets up relevant Graphics context and size of canvas
     * @param g
     * @param cs
     */
    public MyCanvas(GraphicsContext g, int xcs, int ycs) {
        gc = g;
        canvasWidthSize = xcs;
        canvasHeightSize = ycs;
    }

    public int getXCanvasSize() {
        return canvasWidthSize;
    }
    public int getYCanvasSize() {
        return canvasHeightSize;
    }

    /**
     * clear the canvas
     */
    public void setFillColour(int CanvasWidth, int CanvasHeight) {
        gc.setFill(Color.CYAN);
        gc.fillRect(0, 0, CanvasWidth, CanvasHeight);
        gc.setStroke(Color.BLACK);
        gc.strokeRect(0, 0, CanvasWidth, CanvasHeight);
    }

    public void canvasChanger(DroneArena Arena) {
        gc.clearRect(0, 0, canvasWidthSize, canvasHeightSize);        // clear canvas
        setFillColour(canvasWidthSize, canvasHeightSize);
    }

    public void drawDrone(DroneArena myArena) {
        //loops through drone array multidrone
        for (Drone d : myArena.numDrone) {
            //draws images based on information inside of multidrone, giving size and location
            gc.drawImage(hexadrone, d.getX(), d.getY(), 25, 25);
        }

    }

        @Override
        public void start (Stage primaryStage) throws Exception {
            // TODO Auto-generated method stub

        }

}
