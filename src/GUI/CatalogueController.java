package GUI;

import com.gluonhq.charm.glisten.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.AssociationModelManager;
import model.BoardGame;

public class CatalogueController {

    private TableView<BoardGame> catalogueTable;
    private TextField titleField, ownerIDField;
    private TextArea textArea;
    private ComboBox genreChoiceBox;
    private Button editGameButton, deleteGameButton, addGameButton, borrowGameButton, reserveGameButton, returnGameButton, addRateButton;
    private VBox actionsForSelectedGameBox;

    public void initialize(AssociationModelManager modelManager){

    }

    public void handleAction(ActionEvent e){

    }

    public void updateTable(){

    }
}
