
package kiosk;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * 
 */
public class GUI extends Application {
    LiteratureRegister litReg;
    TableView table;
    public GUI()
    {     
        litReg = new LiteratureRegister();
        litReg.registrateLiterature(new SingleBook("title", "author", "publisher", "publicationDate", 1));
        litReg.registrateLiterature(new SingleBook("t", "a", "p", "pd",1));
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {       
       launch(args);
    }    
    @Override
    public void start(Stage stage)
    {
        
        BorderPane root = new BorderPane();
        
        table = new TableView(getLiteratureList());
        root.setCenter(table);
        TableColumn<Literature, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory("title")); 
        TableColumn<Literature, String> publisherCol = new TableColumn<>("Publisher");
        publisherCol.setCellValueFactory(new PropertyValueFactory("publisher"));
        table.getColumns().setAll(titleCol, publisherCol);
        
        Button b = new Button("Info");
        b.setOnAction(e -> presentInfo());
        root.setTop(b);
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
    
}
