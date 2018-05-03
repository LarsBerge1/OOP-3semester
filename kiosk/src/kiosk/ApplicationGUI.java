package kiosk;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * GUI for the newsstand project
 *
 * @author berge
 *
 */
public class ApplicationGUI extends Application {

    Stage window;
    LiteratureRegister litReg;
    TableView table;
    String VERSION = "V0.9 2018-04-28";
    BorderPane root;
    Boolean saved;


    public ApplicationGUI() {
        litReg = new LiteratureRegister();
        saved = true; //Flag used to ask user about saving if register.
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
        root = new BorderPane();   // Create the root node

        // Make the table for the register:
        table = new TableView(getLiteratureList());        
        TableColumn<Literature, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory("title"));
        TableColumn<Literature, String> publisherCol = new TableColumn<>("Publisher");
        publisherCol.setCellValueFactory(new PropertyValueFactory("publisher"));
        table.getColumns().setAll(titleCol, publisherCol);
        table.setOnMouseClicked(e -> showClickedItemInfo(e)); 
        
        // Creates a button that shows all the literature in the register when pressed
        Button b = new Button("Show All");
        b.setOnAction(e -> changeTableContent(getLiteratureList()));
        
        VBox gridPane = new VBox();
        gridPane.getChildren().addAll(table, b);
        root.setCenter(gridPane);

        // Elements in the top container of the borderpane.
        VBox topContainer = new VBox();            //Creates a container to hold all Menu Objects.
        MenuBar mainMenu = createMainMenuBar();          //Creates our main menu to hold our Sub-Menus.
        topContainer.getChildren().add(mainMenu);// Place the menubar in the topContainer
        root.setTop(topContainer);// Place the topcontainer in the top-section of the BorderPane

        // Elements in the left container of the borderpane.
        VBox leftContainer = createAddMenu();
        root.setLeft(leftContainer);

        // Elements in the bottom conainer of the borderpane.
        HBox bottomContainer = new HBox();
        //bottomContainer.setPadding(new Insets(12, 12, 12, 12));
        GridPane searchInput = createSearchSection();
        bottomContainer.getChildren().addAll(searchInput);//, searchBtn);
        root.setBottom(bottomContainer);

        Scene scene = new Scene(root, 720, 480);

        window.setScene(scene);
        window.show();
    }
    
    /**
     * Shows information about an item when clicked on
     * @param e the mouse event
     */
    private void showClickedItemInfo(MouseEvent e)
    {
        Literature literature = (Literature) table.getSelectionModel().getSelectedItem();
        if (literature != null)
        {
            presentInfo(literature);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Creates the main menu bar and puts the Menus in
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
     * Create the items in the File menu and add the setOnAction on the items
     */
    private Menu createFileMenu() {
        // The File-menu
        Menu menuFile = new Menu("File");
        MenuItem openFile = new MenuItem("Open");
        openFile.setOnAction(e -> openFromSavedFile());

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
     * Create the items in the Edit menu and add the setOnAction on the items
     */
    private Menu createEditMenu() {
        // The Edit-menu
        Menu menuEdit = new Menu("Edit");
        MenuItem editSingle = new MenuItem("Change singlebook\n"
                + "to book series");
        editSingle.setOnAction(e -> changeSingleBookToSeries());
        MenuItem delete = new MenuItem("Delete");
        delete.setOnAction(e -> deleteBtnClicked());
        menuEdit.getItems().addAll(editSingle, delete);
        return menuEdit;
    }

    /**
     * Create the items in the View menu and add the setOnAction on the items
     */
    private Menu createViewMenu() {
        Menu menuView = new Menu("View");
        MenuItem fullScreen = new MenuItem("Full Screen");
        fullScreen.setOnAction(e -> window.setFullScreen(true));
        menuView.getItems().addAll(fullScreen);

        return menuView;
    }

    /**
     * Create the items in the Help menu and add the setOnAction on the items
     */
    private Menu createHelpMenu() {

        Menu menuHelp = new Menu("Help");
        MenuItem about = new MenuItem("About");
        about.setOnAction(e -> doShowAboutDialog());
        MenuItem helpContents = new MenuItem("Help Contents");
        helpContents.setOnAction(e -> doShowHelpContentDialog());
        menuHelp.getItems().addAll(about, helpContents);

        return menuHelp;
    }

    /**
     * Close the program properly, asks for confirmation before closeing app
     */
    private void closeProgram() {
        if (!saved){
        Boolean answerSave =  AlertBox.confirmBox("Confirm", "Do you want to save before \n"
                + "exit the application? ");
        if (answerSave){
            saveTextFile();
        }
        }
        Boolean answerClose = AlertBox.confirmBox("Confirm", "Are you sure you want to exit?");
        if (answerClose) {
            window.close();
        }
    }
    
    /**
     * Returns the literature list as an ObservableList
     * @return literatures An ObservebleList of all the products in the literature
     * register
     */
    private ObservableList<Literature> getLiteratureList() {
        ObservableList<Literature> literatures = FXCollections.observableArrayList(litReg.getLiteratures());
        return literatures;
    }
    
    /**
     * Presents the info about a literature
     * @param l the literature to present
     */
    private void presentInfo(Literature l) {
        LiteratureView lv = ViewFactory.getView(l);
        VBox vBox = new VBox();
        TextArea infoArea = new TextArea();
        lv.display(infoArea);
        infoArea.setEditable(false);
        infoArea.setPrefWidth(200);
        vBox.getChildren().add(infoArea);
        root.setRight(vBox);
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
     * Opens a file spesified by the user, file contains saved literature
     */
    private void openFromSavedFile() {
        File fileToOpen = FileChooserView.datFileChooserOpen();
        if (null != fileToOpen) {
            try {
            LiteratureRegisterFileHandler fileHandler = new LiteratureRegisterFileHandler(litReg, fileToOpen);
            LiteratureRegister lr = fileHandler.readSavedFile();
            for (Literature l : lr){
                table.getItems().add(l);
                litReg.registrateLiterature(l);
            }
            AlertBox.information("Register loaded", "Register opend");
            saved = false;
            }
            catch (IOException e){
                AlertBox.information("Problem with open", "Failed to open the file");
            }
            catch ( ClassNotFoundException e)
            {
                AlertBox.information("File has the wrong content", "The file does  "+
                        "not contain a literature register.");
            }
        }
    }

    /**
     * Save the content in the register
     */
    private void saveTextFile() {
            File saveFile = FileChooserView.datFileChooserSave();
            if (saveFile != null) {
                try {
                    LiteratureRegisterFileHandler fileHandler = new LiteratureRegisterFileHandler(litReg, saveFile);
                    fileHandler.saveAllToFile();
                    saved = true;
                }
                catch (IOException e){
                    AlertBox.information("Error", "Error in saving");
                    saved = false;
                }
            }
    }

    /**
     * Change a single book to a book in a series
     */
    private void changeSingleBookToSeries() {
        Object l = table.getSelectionModel().getSelectedItem();
        
        if (l instanceof SingleBook)
        {
           SingleBook sb = (SingleBook) l;
           String seriesTitle = AlertBox.stringInputBox("Input", "Provide a title to the series");
           litReg.changeToBookInSeries(seriesTitle, sb);
           this.changeTableContent(getLiteratureList());
        }
        else 
        {
            AlertBox.information("Not a single book", "You can only add single books to a series");
        }
    }

    /**
     * Delete button clicked
     */
    private void deleteBtnClicked() {
        Object itemSelected = table.getSelectionModel().getSelectedItem();
        if (itemSelected instanceof Literature) {
            Literature productSelected = (Literature) itemSelected;
            if (productSelected != null) {
                Boolean answer = AlertBox.confirmBox("Confirm delete",
                        " Are you sure you want to\n "
                        + "delete selceted literature?");
                if (answer) {
                    table.getItems().remove(productSelected);
                    Boolean result = litReg.deleteProduct(productSelected);
                    if (result) {
                        AlertBox.information("Delete", "Literatur deleted.");
                        saved = false;
                    }
                }
            }
        } else {
            AlertBox.information("Delete failed ", "Please select item to remove.");
        }

    }

    /**
     * Search for literatur in the register
     *
     * @param titleON If true title is used as a seacrh criteria
     * @param publisherOn If true publisher is used as a seacrh criteria
     * @param title The title to search for
     * @param publisher The publisher to search for
     */
    private void search(Boolean titleON, Boolean publisherON, String title, String publisher) {
        Literature foundProduct;
        if (titleON && publisherON) {
            try {
                foundProduct = litReg.searchProductBy(title, publisher);
                presentInfo(foundProduct);

            } catch (NoSuchElementException e) {
                AlertBox.information("Search", "No literature was found.");
            }

        } else if (titleON) {
            try {
                foundProduct = litReg.searchProductByTitle(title);
                presentInfo(foundProduct);
            } catch (NoSuchElementException e) {
                AlertBox.information("Search", "No literature with this title was found.");
            }

        } else if (publisherON) {
            ArrayList<Literature> productsByPublisher = litReg.searchProductByPublisher(publisher);
            changeTableContent(productsByPublisher);
          
        } else {
            AlertBox.information("Information:", "Please select at leaste one search option");
        }
    }
    
    /**
     * Changes the content of the table
     * @param productsByPublisher 
     */
    private void changeTableContent(List<Literature> productsByPublisher)
    {
        table.getItems().clear();
        table.getItems().addAll(productsByPublisher);  
    }

    /**
     * Add a single book to the register
     */
    private void addSingleBook(TextField titleField,
            TextField publisherField,
            TextField authorField,
            TextField publidateField,
            Spinner<Integer> editionSpinner
    ) {
        String title = titleField.getText();
        String publisher = publisherField.getText();
        String author = authorField.getText();
        String publidate = publidateField.getText();
        Integer edition = editionSpinner.getValue();
        SingleBook sb = new SingleBook(title, author, publisher, publidate, edition);
        table.getItems().add(sb);
        litReg.registrateLiterature(sb);
        saved = false;
    }

    /**
     * Add a book to a series
     * @param 
     */
    private void addBookInSeries(TextField titleField, TextField seriesTitleField, TextField publisherField, TextField authorField, TextField pubField) {
        String title = titleField.getText();
        String publisher = publisherField.getText();
        String author = authorField.getText();
        String publicationDate = pubField.getText();
        String seriesTitle = seriesTitleField.getText();
        BookInSeries bs = new BookInSeries(title, seriesTitle, author, publisher, publicationDate);
        table.getItems().add(bs);
        litReg.registrateLiterature(bs);
    }

    /**
     * Add a periodical to the register
     */
    private void addPeriodical(TextField titleField, TextField publisherField, Spinner<Integer> yearlyReleases, TextField subjectField) {
        String title = titleField.getText();
        String publisher = publisherField.getText();
        Integer numberOfReleases = yearlyReleases.getValue();
        String subject = subjectField.getText();
        Periodical p = new Periodical(title, publisher, numberOfReleases, subject);
        table.getItems().add(p);
        litReg.registrateLiterature(p);
    }

    /**
     * Creates a section in the gui containing different ways for the user to 
     * search for a product. Title and publisher are avaiable as search criteria.
     * @return HBox containing the elements for search
     */
    private GridPane createSearchSection() {

        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setPadding(new Insets(5, 5, 5, 5));

        // Input:
        Label searchLabelPublisher = new Label("Publisher:");
        Label searchLabelTitle = new Label("Title:");
        TextField searchFieldPublisher = new TextField();
        searchFieldPublisher.setEditable(false);
        searchFieldPublisher.setPromptText("Publisher");
        TextField searchFieldTitle = new TextField();
        searchFieldTitle.setEditable(false);
        searchFieldTitle.setPromptText("Title");

        // Search settings: 
        Label optionsLabel = new Label("Search options: ");
        optionsLabel.setFont(new Font("Arial", 15));
        RadioButton titleOption = new RadioButton("Title");
        titleOption.setOnAction(e -> {
            if (titleOption.isSelected()) {
                searchFieldTitle.setEditable(true);
            } else {
                searchFieldTitle.clear();
                searchFieldTitle.setEditable(false);
            }
        });
        RadioButton publisherOption = new RadioButton("Publisher");
        publisherOption.setOnAction(e -> {
            if (publisherOption.isSelected()) {
                searchFieldPublisher.setEditable(true);
            } else {
                searchFieldPublisher.clear();
                searchFieldPublisher.setEditable(false);
            }
        });
        VBox options = new VBox();
        options.getChildren().addAll(optionsLabel, titleOption, publisherOption);

        Button searchBtn = new Button("Search");
        searchBtn.setMinWidth(200);
        searchBtn.setOnAction(e -> search(titleOption.isSelected(),
                publisherOption.isSelected(),
                searchFieldTitle.getText(),
                searchFieldPublisher.getText()
        ));

        grid.add(optionsLabel, 0, 0);
        grid.add(titleOption, 0, 1);
        grid.add(publisherOption, 0, 2);
        grid.add(searchFieldTitle, 2, 1);
        grid.add(searchFieldPublisher, 2, 2);
        grid.add(searchBtn, 2, 0);

        return grid;
    }

    /**
     * Make the add single book menu
     *
     * @return the add single book menu
     */
    private TitledPane addSingleBookMenu() {

        //Text fields for the user to enter information
        TextField titleField = new TextField();
        titleField.setPromptText("Title");
        TextField publisherField = new TextField();
        publisherField.setPromptText("Publisher");
        TextField authorField = new TextField();
        authorField.setPromptText("Author");
        TextField pubField = new TextField();
        pubField.setPromptText("PublicationDate");
        Spinner<Integer> edition = new Spinner<>(0, 1000, 1);

        // Add the fields to the grid.
        TitledPane gridTitlePane = new TitledPane();
        gridTitlePane.setExpanded(false);
        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(new Label("Title: "), 0, 0);
        grid.add(titleField, 1, 0);
        grid.add(new Label("Publisher: "), 0, 1);
        grid.add(publisherField, 1, 1);
        grid.add(new Label("Author: "), 0, 2);
        grid.add(authorField, 1, 2);
        grid.add(new Label("PublicationDate: "), 0, 3);
        grid.add(pubField, 1, 3);
        grid.add(new Label("Edition:"), 0, 4);
        grid.add(edition, 1, 4);
        // Make the add button and set the onAction
        Button btn = new Button("Add Single Book");
        btn.setOnAction(e -> addSingleBook(titleField, publisherField, authorField, pubField, edition));
        grid.add(btn, 1, 5);
        gridTitlePane.setText("Add Single book");
        gridTitlePane.setContent(grid);
        return gridTitlePane;
    }

    /**
     * Make the add book in series menu
     *
     * @return the add single book menu
     */
    private TitledPane addBookInSeriesMenu() {

        //Text fields for the user to enter information
        TextField titleField = new TextField();
        titleField.setPromptText("Title");
        TextField seriesTitleField = new TextField();
        seriesTitleField.setPromptText("Series Title");
        TextField publisherField = new TextField();
        publisherField.setPromptText("Publisher");
        TextField authorField = new TextField();
        authorField.setPromptText("Author");
        TextField pubField = new TextField();
        pubField.setPromptText("PublicationDate");
        // Add the fields to the grid.
        TitledPane gridTitlePane = new TitledPane();
        gridTitlePane.setExpanded(false);
        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(new Label("Title: "), 0, 0);
        grid.add(titleField, 1, 0);
        grid.add(new Label("Series Title: "), 0, 1);
        grid.add(seriesTitleField, 1, 1);
        grid.add(new Label("Publisher: "), 0, 2);
        grid.add(publisherField, 1, 2);
        grid.add(new Label("Author: "), 0, 3);
        grid.add(authorField, 1, 3);
        grid.add(new Label("PublicationDate: "), 0, 4);
        grid.add(pubField, 1, 4);
        
        // Make the add button and set the onAction
        Button btn = new Button("Add Book in Series");
        btn.setOnAction(e -> addBookInSeries(titleField, seriesTitleField, publisherField, authorField, pubField));
        grid.add(btn, 1, 5);
        gridTitlePane.setText("Add Book in Series");
        gridTitlePane.setContent(grid);
        return gridTitlePane;
    }

    /**
     * Make the add single book menu
     *
     * @return the add single book menu
     */
    private TitledPane addPeriodicalMenu() {

        //Text fields for the user to enter information
        TextField titleField = new TextField();
        titleField.setPromptText("Title");
        TextField publisherField = new TextField();
        publisherField.setPromptText("Publisher");
        TextField subjectField = new TextField();
        subjectField.setPromptText("Subject");
        Spinner<Integer> yearlyReleases = new Spinner<>(1, 1000, 1);

        // Add the fields to the grid.
        TitledPane gridTitlePane = new TitledPane();
        gridTitlePane.setExpanded(false);
        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(new Label("Title: "), 0, 0);
        grid.add(titleField, 1, 0);
        grid.add(new Label("Publisher: "), 0, 1);
        grid.add(publisherField, 1, 1);
        grid.add(new Label("Yearly releases: "), 0, 2);
        grid.add(yearlyReleases, 1, 2);
        grid.add(new Label("Subject: "), 0, 3);
        grid.add(subjectField, 1, 3);
        // Make the add button and set the onAction
        Button btn = new Button("Add Periodical");
        btn.setOnAction(e -> addPeriodical(titleField, publisherField, yearlyReleases, subjectField));
        grid.add(btn, 1, 4);
        gridTitlePane.setText("Add Periodical");
        gridTitlePane.setContent(grid);
        return gridTitlePane;
    }
    
    /**
     * Creates all the add literature section
     * @return addMenu The menu where the user can add literatures.
     */
    private VBox createAddMenu() {
        VBox addMenu = new VBox();
        TitledPane single = addSingleBookMenu();
        TitledPane series = addBookInSeriesMenu();
        TitledPane periodical = addPeriodicalMenu();
        addMenu.getChildren().addAll(single, series, periodical);
        return addMenu;
    }
}
