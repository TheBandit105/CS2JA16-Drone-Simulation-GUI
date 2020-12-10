package Drone_GUI;

/**
 * DroneInterface handles culminates all the features that are run by the other classes onto a single
 * borderpane window to allow for a nicer graphical look and to ultimately use the GUI. Displays the arena, VBox to
 * display the drones added and buttons to interact with the application.
 *
 * @author Shavin Croos
 */

import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DroneInterface extends Application{
    private int CanvasWidth = 900, CanvasHeight = 600;
    private MyCanvas mc;
    private static DroneArena Arena;
    private static AnimationTimer time;

    private void showMessage(String TStr, String CStr) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(TStr);
        alert.setHeaderText(null);
        alert.setContentText(CStr);
        alert.showAndWait();
    }

    /**
     * Function to show the details about the program.
     */
    private void viewAbout() {
        showMessage("About", "Drone Simulator 2020 (GUI version)." + "\n" +
                "Based of the console version and developed by Shavin Croos." + "\n" +
                "The program shows the movement of randomly placed drones in a given arena" +
                " in the form of an animation. ");
    }

    /**
     * Function to show how to use the program.
     */
    private void viewHelp() {
        showMessage("Help", "Click 'INSERT DRONE' to randomly add a drone to your arena. " + "\n" +
                "Press 'START' to watch the drones move about in the arena and then press 'STOP' when you want" +
                " to stop the animation.");
    }

    /**
     * Function to show the credits.
     */

    private void viewCredits() {
        showMessage( "Credits", "Drone Simulator 2020 (GUI Version)\n" +
                "Produced and developed by Shavin Croos");
    }

    /**
     * Function to set up the menu
     */
    public MenuBar setMenu() {
        MenuBar menuBar = new MenuBar();		// create menu

        Menu mInfo = new Menu("Info");			// have entry for Info
        // then add sub menus for About, Help and Credits
        // add the item and then the action to perform
        MenuItem mAbout = new MenuItem("About");
        mAbout.setOnAction(new EventHandler<ActionEvent>(){
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
                viewCredits();				// show the credits message
            }
        });
        mInfo.getItems().addAll(mAbout, mHelp, mCredits); 	// add submenu to Info

        // now add File menu, which here only has Exit
        Menu mFile = new Menu("File");				// create File Menu
        MenuItem mExit = new MenuItem("Exit");		// and Exit submenu
        mExit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {		// and add handler
                System.exit(0);						// quit program
            }
        });
        mFile.getItems().addAll(mExit);	// add Exit submenu to File

        menuBar.getMenus().addAll(mFile, mInfo);	// menu has File and Info

        return menuBar;					// return the menu, so can be added
    }

    /**
     * Function to add the drones to list and display the info on their initial positions
     * when first placed.
     * @param DroneGroup
     */
    public static void listDrones(ListView<Drone> DroneGroup){
        //clear the list view
        DroneGroup.getItems().clear();
        //checks over the drones in the list and adds them to the list view
        for (Drone d : Arena.numDrone)
            DroneGroup.getItems().add(d);
    }

    /**
     * Creates the stage for the application, so that all the features will be displayed on
     * the window upon execution.
     * @param stagePrimary
     * @throws Exception
     */
    public void start(Stage stagePrimary) throws Exception  {
        stagePrimary.setTitle("27015244's Drone Simulator 2020 (GUI version)");

        Text Title = new Text("THE ARENA");
        Title.setX(375);
        Title.setY(-10);
        Title.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.ITALIC, 27));
        Title.setFill(Color.ORANGE);
        Title.setStrokeWidth(1.25);
        Title.setStroke(Color.DARKGREEN);

        Group root = new Group(Title);
        //setting canvas details and adding it to the stage, with the title added
        Canvas canvas = new Canvas(900, 500);
        root.getChildren().add(canvas);
        //creating the canvas using the x and y and adding the drone arena

        mc = new MyCanvas(canvas.getGraphicsContext2D(), CanvasWidth, CanvasHeight);
        Arena = new DroneArena(900, 500);
        mc.setFillArenaColour(CanvasWidth, CanvasHeight);

        //timer used to start and stop the movements of all drones added
        time = new AnimationTimer() {
            public void handle(long now) {
                //will stop or start the moveAllDrones function
                Arena.moveAllDrones(mc);

            }
        };

        //List View Drone contains variables 'Vehicles'
        ListView<Drone> Vehicles = new ListView<>();
        Vehicles.setStyle(
                "-fx-border-color: #00ff6a;" +
                "-fx-border-width: 10px;");

        //vbList positioning and formatiing are determined here, with a title on top for the list.
        Text text = new Text("Active drones: ");
        text.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.ITALIC, 27));
        text.setFill(Color.GOLD);
        text.setStrokeWidth(1.25);
        text.setStroke(Color.BLUE);

        VBox vbList = new VBox(text);
        vbList.getChildren().addAll(Vehicles);
        vbList.setAlignment(Pos.CENTER);
        vbList.setPadding(new Insets(0, 0, 0, 50));

        //Adds image to button
        Image addr = new Image("plus.png");
        ImageView viewInsert = new ImageView(addr);
        viewInsert.setFitHeight(40);
        viewInsert.setFitWidth(40);

        //formatted button to add a drone in a random place in the arena.
        Button insertDroneButton = new Button("INSERT DRONE", viewInsert);
        insertDroneButton.setStyle(
                "-fx-border-color: #000dff; " +
                        "-fx-border-width: 3px; " +
                        "-fx-font-size: 2em; " +
                        "-fx-text-fill: #000dff;" +
                "-fx-background-color: #ffffff;");

        insertDroneButton.setOnAction(e -> {
            //drone added to arena using my canvas functions and 'Vehicles' variable
            Arena.addDrone(mc, Vehicles);
        });

        //Determining button size
        insertDroneButton.setMinSize(229, 50);
        insertDroneButton.setMaxSize(125, 50);

        Image start = new Image("go.jpg");
        ImageView viewStart = new ImageView(start);
        viewStart.setFitHeight(40);
        viewStart.setFitWidth(40);

        //start button to start animation
        Button MoveDronesButton = new Button("START", viewStart);
        MoveDronesButton.setStyle(
                "-fx-border-color: #15c218; " +
                        "-fx-border-width: 3px; " +
                        "-fx-font-size: 2em;" +
                        "-fx-text-fill: #008523;"+
                        "-fx-background-color: #ffffff;");

        //starts timer and calls the moveAllDrones method to move drones
        MoveDronesButton.setOnAction(e -> time.start());

        MoveDronesButton.setMinSize(135, 50);
        MoveDronesButton.setMaxSize(125, 50);

        Image stop = new Image("stop.jpg");
        ImageView viewStop = new ImageView(stop);
        viewStop.setFitHeight(40);
        viewStop.setFitWidth(40);

        //stop button to stop animation
        Button DroneStopMove = new Button("STOP", viewStop);
        DroneStopMove.setStyle(
                "-fx-border-color: #ff0000; " +
                        "-fx-border-width: 3px; " +
                        "-fx-font-size: 2em; " +
                        "-fx-text-fill: #ff0000;" +
                        "-fx-background-color: #ffffff;");

        //stops timer and calls the moveAllDrones method to stop all drone movement
        DroneStopMove.setOnAction(e -> time.stop());

        DroneStopMove.setMinSize(125, 50);
        DroneStopMove.setMaxSize(125, 50);

        Image arrow = new Image("Simpleicons_Interface_arrow-of-double-point-in-diagonal.svg.png");
        ImageView viewArrow = new ImageView(arrow);
        viewArrow.setFitHeight(34);
        viewArrow.setFitWidth(34);

        //opens a info box giving the user the details about the arena and number of drones inside
        Button ArenaDim = new Button(" ARENA STATS", viewArrow);
        ArenaDim.setStyle(
                "-fx-border-color: #d98f18; " +
                        "-fx-border-width: 3px; " +
                        "-fx-font-size: 2.102em; " +
                        "-fx-text-fill: #d98f18;" +
                        "-fx-background-color: #ffffff;"
        );

        ArenaDim.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                showMessage("Arena Dimensions", Arena.toString()); //shows arena stats
            }
        });

        //sets padding and formatting for console
        HBox hbButtons = new HBox(20);
        hbButtons.setAlignment(Pos.CENTER_RIGHT);
        hbButtons.setPadding(new Insets(0, 160, 50, 0));

        //adds all button to the console
        hbButtons.getChildren().addAll(insertDroneButton, MoveDronesButton, DroneStopMove, ArenaDim);

        //creates borderpane

        BorderPane bp = new BorderPane(); // New borderpane to store all
        // features
        //formats borderpane inserting the aforementioned items in certain areas of screen
        bp.setTop(setMenu());
        bp.setCenter(root);
        bp.setBottom(hbButtons);
        bp.setLeft(vbList);

        // Set the scene with the borderpane, which is bigger than the arena size
        Scene scene = new Scene(bp, 1400, 700);

        stagePrimary.setScene(scene); // Put the scene in the window
        stagePrimary.show();
    }

    /**
     * Launches the application.
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
}
