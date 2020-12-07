package Drone_GUI;

import javafx.application.Application;
import javafx.scene.paint.Color;
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
    private int CanvasWidth = 800, CanvasHeight = 600;  // Sets the size of the canvas
    private MyCanvas mc;                                // Calls upon the canvas where system is drawn
    private static DroneArena Arena;

    private void showMessage(String TStr, String CStr) { // Shows any message in the form of the title, then the message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
        showMessage( "Help", "Click 'INSERT DRONE' to randomly add a drone to your arena. " + "\n" +
                "Press 'START' to watch the drones move about in the arena and then press 'STOP' when you want" +
                " to stop the animation.");
    }

    private void viewCredits() {
        showMessage( "Credits", "Drone Simulator 2020 (GUI Version)\n" +
                "Produced and developed by Shavin Croos");
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
        MenuItem mCredits = new MenuItem("Credits");
        mCredits.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                viewCredits();				// show the help message
            }
        });
        mInfo.getItems().addAll(mAbout, mHelp, mCredits); 	// add submenus to Help

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

    public static void fillList(ListView<Drone> ListDrone){
        //clear the list view
        ListDrone.getItems().clear();
        //loop through drones and add them to the list view
        for (Drone d : Arena.numDrone)
            ListDrone.getItems().add(d);


    }

    @Override
    public void start (Stage stagePrimary) throws Exception {
        stagePrimary.setTitle("27015244's Drone Simulator 2020 (GUI version)");

        Group root = new Group();
        Canvas canvas = new Canvas(900, 500);
        root.getChildren().add(canvas);
        mc = new MyCanvas(canvas.getGraphicsContext2D(), CanvasWidth, CanvasHeight);
        mc.setFillColour(900, 500);

        ListView<Drone> Vehicles = new ListView<>();

        VBox numDroneList = new VBox();
        numDroneList.getChildren().addAll(Vehicles);
        numDroneList.setAlignment(Pos.CENTER);
        numDroneList.setPadding(new Insets(0, 0, 0, 50));


        Button insertDroneButton = new Button("INSERT DRONE");
        insertDroneButton.setStyle("-fx-border-color: #000dff; -fx-border-width: 3px; " +
                "-fx-font-size: 2em; -fx-text-fill: #000dff;");

        insertDroneButton.setOnAction(e -> {
            Arena.addDrone(mc, Vehicles);
        });

        insertDroneButton.setMinSize(200, 50);
        insertDroneButton.setMaxSize(125, 50);

        Button Start_btn = new Button("START");
        Start_btn.setStyle("-fx-border-color: #15c218; -fx-border-width: 3px; " +
                "-fx-font-size: 2em; -fx-text-fill: #15c218;");

        Start_btn.setMinSize(125, 50);
        Start_btn.setMaxSize(125, 50);

        Button Stop_btn = new Button("STOP");
        Stop_btn.setStyle("-fx-border-color: #ff0000; -fx-border-width: 3px; " +
                "-fx-font-size: 2em; -fx-text-fill: #ff0000;");

        Stop_btn.setMinSize(125, 50);
        Stop_btn.setMaxSize(125, 50);

        HBox Buttons = new HBox(20);
        Buttons.setAlignment(Pos.CENTER);
        Buttons.setPadding(new Insets(0, 0, 50, 0));

        Buttons.getChildren().addAll(insertDroneButton, Start_btn, Stop_btn);

        BorderPane bp = new BorderPane();
        bp.setTop(setMenu());
        bp.setRight(numDroneList);
        bp.setCenter(root);
        bp.setBottom(Buttons);

        Scene scene = new Scene(bp, CanvasWidth * 1.5, CanvasHeight * 1.2);

        stagePrimary.setScene(scene);
        stagePrimary.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
