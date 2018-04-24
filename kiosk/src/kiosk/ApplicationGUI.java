package kiosk;

import java.util.Optional;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    LiteratureRegister litReg;
    TableView table;
    String VERSION = "V0.2 2018-04-24"; 

    public ApplicationGUI() {
        litReg = new LiteratureRegister();
        litReg.registrateLiterature(new SingleBook("title", "author", "publisher", "publicationDate", 1));
        litReg.registrateLiterature(new SingleBook("t", "a", "p", "pd", 1));
    }

    
    
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
        
        
        // Make the table for the register:
        table = new TableView(getLiteratureList());
        root.setCenter(table);
        TableColumn<Literature, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory("title")); 
        TableColumn<Literature, String> publisherCol = new TableColumn<>("Publisher");
        publisherCol.setCellValueFactory(new PropertyValueFactory("publisher"));
        table.getColumns().setAll(titleCol, publisherCol);

        
        // Elements in the top container of the borderpane.
        VBox topContainer = new VBox();            //Creates a container to hold all Menu Objects.
        MenuBar mainMenu = createMenus();          //Creates our main menu to hold our Sub-Menus.
        // Place the menubar in the topContainer
        topContainer.getChildren().add(mainMenu);
        // Place the topcontainer in the top-section of the BorderPane
        root.setTop(topContainer);

        // Elements in the bottom container of the borderpane.
        VBox bottomContainer = new VBox(12);
        Button AddBtn = new Button("Add new literature");
        AddBtn.setOnAction(e -> {
            litReg.registrateLiterature(new SingleBook("test", "aw", "pw", "pwd", 2));
            
        });
        bottomContainer.getChildren().addAll(AddBtn);
        root.setBottom(bottomContainer);
        
        
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
        openFile.setOnAction(e -> {
            System.out.println("Print Pressed");
            //confirmBox("testr", "jaoids");
        });

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
        
        // The Help-menu
        Menu menuHelp = new Menu("Help");
        MenuItem about = new MenuItem("About");
        about.setOnAction(e -> doShowAboutDialog());
        MenuItem helpContents = new MenuItem("Help Contents");
        helpContents.setOnAction(e -> doShowHelpContentDialog());
        menuHelp.getItems().addAll(about, helpContents);
        menuBar.getMenus().addAll(menuFile, menuEdit, menuView, menuHelp);
        
        return menuBar;

    }

    private void closeProgram() {
        Boolean answer = confirmBox("Confirm", "Are you sure you want to exit?");
        if (answer) {
            window.close();
        }
    }
    
    private ObservableList<Literature> getLiteratureList()
    {
        ObservableList<Literature> literatures = FXCollections.observableArrayList(litReg.getLiteratures());
        return literatures; 
    }
    
    private void presentInfo()
    {
        Literature l = (Literature) table.getSelectionModel().getSelectedItem();
        LiteratureView lv = ViewFactory.getView(l);
        lv.display();
    }
    
    /**
     * Confirm box to confirm the action spesified.
     * @param title The title of the confirmbox.
     * @param message The message to the user.
     * @return True if user press OK, else false.
     */
    private boolean confirmBox(String title, String message)
    {
        boolean answer = false;
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            answer = true;
        } else if (result.get() == ButtonType.CANCEL) {
            answer = false;
        }
        return answer; 
    }
    /**
     * Displays an info dialog about the application. 
     */
    private void doShowAboutDialog(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog - About:");
        alert.setHeaderText("Literature Register");
        alert.setContentText("A application created by \n"
                            + "(C) Andreas Hatlø \n"
                            + "(C) Jarl Eirik Heide \n"
                            + "(C) Lars Berge \n"
                            + VERSION
                            );
        alert.showAndWait();
    }
    /**
     * Displays an info dialog with help contents. 
     */
    private void doShowHelpContentDialog(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog - Help Content:");
        alert.setHeaderText("Help Conetents: ");
        alert.setContentText("Are you stupid?"
                            );
        alert.showAndWait();
    }

}
