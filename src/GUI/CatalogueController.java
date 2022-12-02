package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.AssociationModelManager;
import model.BoardGame;
import model.Genre;

//Michael
public class CatalogueController
{

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
  private MyTableListener tableListener;

  public void initialize()
  {
    tableListener = new MyTableListener();
    catalogueTable.getSelectionModel().selectedItemProperty().addListener(tableListener);
    genreChoiceBox.getItems().addAll(AssociationModelManager.getAssociation().getGenreList().getArrayOfGenres());

    titleColumn.setCellValueFactory(
        new PropertyValueFactory<BoardGame, String>("title"));
    descriptionColumn.setCellValueFactory(
        new PropertyValueFactory<BoardGame, String>("description"));
    genreColumn.setCellValueFactory(
        new PropertyValueFactory<BoardGame, String>("genre"));
    statusColumn.setCellValueFactory(
        new PropertyValueFactory<BoardGame, String>("status"));
    IDColumn.setCellValueFactory(
        new PropertyValueFactory<BoardGame, String>("ownerID"));
    ratingColumn.setCellValueFactory(
        new PropertyValueFactory<BoardGame, String>("average"));

    catalogueTable.getSelectionModel().selectFirst();

    updateTable();
  }

  public void handleAction(ActionEvent e)
  {
    if (e.getSource() == addGameButton)
    {
      String title = titleField.getText();
      String description = descriptionArea.getText();
      int ownerID = 0;

      try
      {
        ownerID = Integer.parseInt(ownerIDField.getText());
      }
      catch (Exception exception)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR, exception.getMessage());
        alert.setHeaderText(null);
        alert.show();
        return;
      }

      Genre genre = genreChoiceBox.getSelectionModel().getSelectedItem();

      Association association = AssociationModelManager.getAssociation();
      association.getCatalogue().addBoardGame(new BoardGame(title, ownerID, description, genre));
      AssociationModelManager.saveAssociation(association);
      updateTable();
    }
    else if (e.getSource() == editGameButton)
    {
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

      AssociationModelManager.getAssociation().getCatalogue()
              .addBoardGame(new BoardGame(title, ownerID, description, genre));
    }
    else if (e.getSource() == borrowGameButton)
    {
      BoardGame game = catalogueTable.getSelectionModel().getSelectedItem();
//      AssociationModelManager.getAssociation().getBoardGameByTitle(game.getTitle()).borrow();
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

  public void updateTable()
  {

  }
}
