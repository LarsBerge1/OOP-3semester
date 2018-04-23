package kiosk;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * GUI for the newsstand project.
 *
 * @author berge
 * 
 */



public class ApplicationGUI extends Application {
    
Stage window;

    @Override
    public void start(Stage primaryStage) {
        
        window = primaryStage;
        window.setTitle("Newsstand for OOP group 32.");
        //Close the program properly:
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        
        BorderPane root = new BorderPane();        // Create the root node. The Menu will be placed at the top
        VBox topContainer = new VBox();            //Creates a container to hold all Menu Objects.
        MenuBar mainMenu = createMenus();          //Creates our main menu to hold our Sub-Menus.
        // Place the menubar in the topContainer
        topContainer.getChildren().add(mainMenu);
        // Place the topcontainer in the top-section of the BorderPane
        root.setTop(topContainer);

        
        Scene scene = new Scene(root, 720, 480);

        window.setScene(scene);
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Creates the menus to be displayed.
     */
    private MenuBar createMenus() {
        // Create the Menu Bar to hold all the menus
        MenuBar menuBar = new MenuBar();
        // The File-menu
        Menu menuFile = new Menu("File");
        MenuItem openFile = new MenuItem("Open");
        openFile.setOnAction(e -> System.out.println("Open Pressed"));
        
        MenuItem printFile = new MenuItem("Print");
        printFile.setOnAction(e -> System.out.println("Print Pressed"));
        
        MenuItem exitApp = new MenuItem("Exit");
        exitApp.setOnAction(e -> closeProgram());
        
        menuFile.getItems().addAll(openFile, printFile);
        menuFile.getItems().add(new SeparatorMenuItem());
        menuFile.getItems().add(exitApp);
        // The Edit-menu
        Menu menuEdit = new Menu("Edit");
        // The View-menu
        Menu menuView = new Menu("View");
        menuBar.getMenus().addAll(menuFile, menuEdit, menuView);
        return menuBar;

    }

    private void closeProgram() {
        Boolean answer = Confirmbox.display("Confirm","Are you sure you want to exit?");
        if (answer){
            window.close();
        }
    }

}
