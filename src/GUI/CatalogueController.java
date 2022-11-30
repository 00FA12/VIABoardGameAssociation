package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.AssociationModelManager;
import model.BoardGame;
import model.Genre;

//Michael
public class CatalogueController {


    @FXML private TableView<BoardGame> catalogueTable;
    @FXML private TextField titleField, ownerIDField;
    @FXML private TextArea textArea;
    @FXML private ChoiceBox<Genre> genreChoiceBox; //todo add to astah
    @FXML private Button editGameButton, deleteGameButton, addGameButton, borrowGameButton, reserveGameButton, returnGameButton, addRateButton;
    @FXML private VBox actionsForSelectedGameBox;
    @FXML private TableColumn<BoardGame, String> titleColumn;
    @FXML private TableColumn<BoardGame, String> descriptionColumn;
    @FXML private TableColumn<BoardGame, String> genreColumn;
    @FXML private TableColumn<BoardGame, String> statusColumn;
    @FXML private TableColumn<BoardGame, String> IDColumn;
    @FXML private TableColumn<BoardGame, String> ratingColumn;

    public void initialize(){

        titleColumn.setCellValueFactory(new PropertyValueFactory<BoardGame, String>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<BoardGame, String>("description"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<BoardGame, String>("genre"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<BoardGame, String >("gameAction"));
        IDColumn.setCellValueFactory(new PropertyValueFactory<BoardGame, String>("ownerID"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<BoardGame, String >("ratings.average()"));


        updateTable();
    }

    public void handleAction(ActionEvent e){
        if (e.getSource() == editGameButton){
            //Dialog window opens
        }
        else if (e.getSource() == deleteGameButton)
        {
            BoardGame game = catalogueTable.getSelectionModel().getSelectedItem();
            AssociationModelManager.getAssociation().getCatalogue().removeBoardGame(game);
        }
        else if (e.getSource() == addGameButton)
        {
            String title = titleField.getText();
            String description = textArea.getText();
            int ownerID = Integer.parseInt(ownerIDField.getText());
            Genre genre = genreChoiceBox.getSelectionModel().getSelectedItem();

            AssociationModelManager.getAssociation().getCatalogue().addBoardGame(new BoardGame(title, ownerID, description, genre));
        }
        else if (e.getSource() == borrowGameButton)
        {

        }
        else if (e.getSource() == reserveGameButton)
        {

        }
        else if (e.getSource() == returnGameButton)
        {

        }
        else if (e.getSource() == addRateButton)
        {

        }
    }

    public void updateTable(){

    }
}
