package kiosk;

import java.io.File;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
    TextField searchFieldPublisher;
    TextField searchFieldTitle;
    Label searchLabelPublisher;
    Label searchLabelTitle;
    

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
        BorderPane root = new BorderPane();   // Create the root node

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
        MenuBar mainMenu = createMainMenuBar();          //Creates our main menu to hold our Sub-Menus.
        topContainer.getChildren().add(mainMenu);// Place the menubar in the topContainer
        root.setTop(topContainer);// Place the topcontainer in the top-section of the BorderPane

        // Elements in the left container of the borderpane.
        HBox leftContainer = new HBox();
        leftContainer.setMinWidth(0x64);
        Menu addMenu = createAddMenu();
        MenuBar addMenuBar = new MenuBar();
        addMenuBar.getMenus().add(addMenu);
        leftContainer.getChildren().add(addMenuBar);
        root.setLeft(leftContainer);
        
        // Elements in the bottom conainer of the borderpane.
        HBox bottomContainer = new HBox();
        bottomContainer.setMinHeight(30);
        Menu searchMenu = createSearchMenu();
        MenuBar searchMenuBar = new MenuBar();
        searchMenuBar.getMenus().add(searchMenu);
        
        HBox searchInput = new HBox();
        searchInput = createSearchInput();
        
        Button searchBtn = new Button("Search");
        searchBtn.setOnAction(e -> searchBtnClicked());
        
        
        bottomContainer.getChildren().addAll(searchMenuBar,searchInput, searchBtn);
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
     * Creates the main menu bar and puts
     * the Menus in
     */
    private MenuBar createMainMenuBar() {
        // Create the Menu Bar to hold all the menus
        MenuBar menuBar = new MenuBar();
        Menu menuFile = createFileMenu();
        Menu menuEdit = createEditMenu();
        Menu menuView = createViewMenu();
        Menu menuHelp = createHelpMenu();
        menuBar.getMenus().addAll(menuFile, menuEdit, menuView, menuHelp);

        return menuBar;
    }
    /**
     * Creat the items in the File menu and
     * add the setOnAction on the items
     */
    private Menu createFileMenu(){
             // The File-menu
        Menu menuFile = new Menu("File");
        MenuItem openFile = new MenuItem("Open");
        openFile.setOnAction(e -> openTextFile());

        MenuItem saveFile = new MenuItem("Save");
        saveFile.setOnAction(e -> saveTextFile());

        MenuItem exitApp = new MenuItem("Exit");
        exitApp.setOnAction(e -> closeProgram());

        menuFile.getItems().addAll(openFile, saveFile);
        menuFile.getItems().add(new SeparatorMenuItem());
        menuFile.getItems().add(exitApp);
        
        return menuFile;
    }
    
    /**
     'Creat the items in the Edit menu and
     * add the setOnAction on the items
     */
    private Menu createEditMenu(){
        // The Edit-menu
        Menu menuEdit = new Menu("Edit");
        MenuItem editSingle = new MenuItem("Change singlebook\n"
                                        + "to book series");
        editSingle.setOnAction(e -> changeSingleBookToSeries());
        menuEdit.getItems().add(editSingle);
        return menuEdit;
    }
    
    /**
     * Creat the items in the View menu and
     * add the setOnAction on the items
     */
    private Menu createViewMenu(){
        Menu menuView = new Menu("View");
        MenuItem fullScreen = new MenuItem("Full Screen");
        fullScreen.setOnAction(e -> window.setFullScreen(true) );
        menuView.getItems().addAll(fullScreen);
        
        return menuView;
    }
    
    /**
     * Creat the items in the Help menu and
     * add the setOnAction on the items
     */
    private Menu createHelpMenu(){
        
        Menu menuHelp = new Menu("Help");
        MenuItem about = new MenuItem("About");
        about.setOnAction(e -> doShowAboutDialog());
        MenuItem helpContents = new MenuItem("Help Contents");
        helpContents.setOnAction(e -> doShowHelpContentDialog());
        menuHelp.getItems().addAll(about, helpContents);
        
        return menuHelp;
    }
    
    /**
     * Creat the items in the Search menu and
     * add the setOnAction on the items
     */
    private Menu createSearchMenu() {
        // The Search menu
        Menu menuSearch = new Menu("Search Options");
        CheckMenuItem publisherCheck = new CheckMenuItem("Publisher");
        publisherCheck.setOnAction(e -> searchSettings());
        CheckMenuItem titleCheck = new CheckMenuItem("Title");
        titleCheck.setOnAction(e -> System.out.println("Not finished, title"));
        menuSearch.getItems().addAll(publisherCheck,titleCheck);
        return menuSearch;
    }

    /**
     * Creat the items in the Add menu and
     * add the setOnAction on the items
     */
    private Menu createAddMenu() {
        // The Add-menu
        Menu menuAdd = new Menu("Add");
        MenuItem singleBook = new MenuItem("Single Book");
        singleBook.setOnAction(e -> addSingleBook());
        MenuItem seriesBook = new MenuItem("Book in Series");
        seriesBook.setOnAction(e -> addBookInSeries());
        menuAdd.getItems().addAll(singleBook,seriesBook);
        
        return menuAdd;
    }
    
    /**
     * Close the program properly, asks
     * for confirmation before closeing app
     */
    private void closeProgram() {
        Boolean answer = Confirmbox.confirmBox("Confirm", "Are you sure you want to exit?");
        if (answer) {
            window.close();
        }
    }

    private ObservableList<Literature> getLiteratureList() {
        ObservableList<Literature> literatures = FXCollections.observableArrayList(litReg.getLiteratures());
        return literatures;
    }

    private void presentInfo() {
        Literature l = (Literature) table.getSelectionModel().getSelectedItem();
        LiteratureView lv = ViewFactory.getView(l);
        lv.display();
    }

    
    /**
     * Displays an info dialog about the application.
     */
    private void doShowAboutDialog() {
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
     * Displays an info dialog with help contents
     */
    private void doShowHelpContentDialog() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog - Help Content:");
        alert.setHeaderText("Help Conetents: ");
        alert.setContentText("Are you stupid? \n -Lars"
        );
        alert.showAndWait();
    }

    /**
     * Opens a text file spesified by the user
     * TODO: finsish this function
     */
    private void openTextFile() {
        File test = FileChooserView.textFileChooserView();
        if (null != test){
        String path = test.getAbsolutePath();
        System.out.println(path);
        }
    }
    /**
     * Save the content in the register
     */
    private void saveTextFile(){
        
    }
    
    /**
     * Change book
     */
    private void changeSingleBookToSeries(){
        System.out.println("TODO: finish");
        // Step 1, finn hvilket som er markert
        // 2. do it.
    }
    
    /**
     * 
     */
    private void searchSettings(){
        searchFieldPublisher.setVisible(true);
        searchFieldTitle.setVisible(true);
        searchLabelPublisher.setVisible(true);
        searchLabelTitle.setVisible(true);
    }
    /**
     * Add button clicked
     */
    private void addBtnClicked(){
        System.out.println("Add");
    }
    /**
     * Delete button clicked
     */
    private void deleteBtnClicked(){
        System.out.println("Delete");
    }
    /**
     * Search button clicked
     */
    private void searchBtnClicked(){
        System.out.println("Search");
    }

    /** 
     * Add a single book to the register
     */
    private void addSingleBook(){
        System.out.println("addSingleBook");
    }
    /**
     * 
     */
    private void addBookInSeries(){
        System.out.println("addSeriesBook");
    }
    
    private HBox createSearchInput()
    {
        HBox test = new HBox();
        searchLabelPublisher = new Label("Publisher:");
        searchLabelPublisher.setVisible(false);
        searchLabelPublisher.setMinHeight(25);
        searchLabelTitle = new Label("Title:");
        searchLabelTitle.setVisible(false);
        searchLabelTitle.setMinHeight(25);
        searchFieldPublisher = new TextField();
        searchFieldPublisher.setVisible(false);
        searchFieldPublisher.setPromptText("Publisher");
        searchFieldTitle = new TextField();
        searchFieldTitle.setVisible(false);
        searchFieldTitle.setPromptText("Title");
        
        test.getChildren().addAll(  searchLabelPublisher,
                                    searchFieldPublisher,
                                    searchLabelTitle,
                                    searchFieldTitle
                                    
                );
        
        return test;
    }
    
}
