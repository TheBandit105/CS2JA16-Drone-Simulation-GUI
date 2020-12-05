package Drone_GUI;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;

public class DroneInterface extends Application{
    private int CanvasWidth = 400, CanvasHeight = 500;  // Sets the size of the canvas
    private MyCanvas mc;                                // Calls upon the canvas where system is drawn

    private void showMessage(String TStr, String CStr) { // Shows any message in the form of the title, then the message
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(TStr);
        alert.setHeaderText(null);
        alert.setContentText(CStr);
        alert.showAndWait();
    }

    // Shows About box, with information about the simulator
    private void viewAbout() {
        showMessage("About", "Drone Simulator 2020 (GUI version)." + "\n" +
                "Based of the console version and developed by Shavin Croos." + "\n" +
                "The program shows the movement of randomly placed drones in a given arena" +
                " in the form of an animation. ");
    }

    // Shows Help box, with information on how to use the simulator
    private void viewHelp() {
        showMessage( "Help", "Click 'Insert Drone' to randomly add a drone to your arena. " + "\n" +
                "Press 'START' to watch the drones move about in the arena and then press 'STOP' when you want" +
                " to stop the animation.");
    }

    MenuBar setMenu() {
        MenuBar menuBar = new MenuBar();		// create menu

        Menu mInfo = new Menu("Info");			// have entry for help
        // then add sub menus for About and Help
        // add the item and then the action to perform
        MenuItem mAbout = new MenuItem("About");
        mAbout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                viewAbout();				// show the about message
            }
        });
        MenuItem mHelp = new MenuItem("Help");
        mHelp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                viewHelp();				// show the help message
            }
        });
        mInfo.getItems().addAll(mAbout, mHelp); 	// add submenus to Help

        // now add File menu, which here only has Exit
        Menu mFile = new Menu("File");
        MenuItem mExit = new MenuItem("Exit");
        mExit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                System.exit(0);						// quit program
            }
        });
        mFile.getItems().addAll(mExit);

        menuBar.getMenus().addAll(mFile, mInfo);	// menu has File and Help

        return menuBar;					// return the menu, so can be added
    }

    public void start (Stage stagePrimary) throws Exception {
        stagePrimary.setTitle("Drone Simulator 2020 (GUI version)");

        BorderPane bp = new BorderPane();

        bp.setTop(setMenu());

        Group root = new Group();
        Canvas canvas = new Canvas(CanvasWidth, CanvasHeight);

        Scene scene = new Scene(bp, CanvasWidth * 1.5, CanvasHeight * 1.2);

        stagePrimary.setScene(scene);
        stagePrimary.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
