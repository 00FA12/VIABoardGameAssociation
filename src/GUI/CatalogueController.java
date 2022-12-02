package GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;
import model.lists.Catalogue;
import model.lists.EventList;
import model.lists.StudentList;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;

//Michael
public class CatalogueController
{

    @FXML
    private TableView<BoardGame> catalogueTable;
    @FXML
    private TextField titleField, ownerIDField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private ChoiceBox<Genre> genreChoiceBox; //todo add to astah
    @FXML
    private Button editGameButton, deleteGameButton, addGameButton, borrowGameButton, reserveGameButton, returnGameButton, addRateButton;
    @FXML
    private VBox actionsForSelectedGameBox;
    @FXML
    private TableColumn<BoardGame, String> titleColumn;
    @FXML
    private TableColumn<BoardGame, String> descriptionColumn;
    @FXML
    private TableColumn<BoardGame, String> genreColumn;
    @FXML
    private TableColumn<BoardGame, String> statusColumn;
    @FXML
    private TableColumn<BoardGame, String> IDColumn;
    @FXML
    private TableColumn<BoardGame, String> ratingColumn;

    private MyTableListener tableListener;

    private class MyTableListener implements ChangeListener<BoardGame>
    {
        public void changed(ObservableValue<? extends BoardGame> boardGame, BoardGame oldBoardGame, BoardGame newBoardGame)
        {
            BoardGame boardGameTemp = catalogueTable.getSelectionModel().getSelectedItem();

            if (boardGameTemp != null)
            {
                titleField.setText(boardGameTemp.getTitle());
                descriptionArea.setText(boardGameTemp.getDescription());
                ownerIDField.setText(String.valueOf(boardGameTemp.getOwnerID()));
                genreChoiceBox.getItems().clear();
                genreChoiceBox.getItems().addAll(AssociationModelManager.getAssociation().getGenreList().getArrayOfGenres());
                genreChoiceBox.setValue(boardGameTemp.getGenre());
                statusColumn.setText(boardGameTemp.getStatus());
                ratingColumn.setText(boardGameTemp.getAverage());
            }
        }
    }


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

    public void handleAction(ActionEvent e) throws IOException
    {
        if (e.getSource() == addGameButton)
        {
            String title = titleField.getText();
            String description = descriptionArea.getText();
            int ownerID = 0;

            try
            {
                ownerID = Integer.parseInt(ownerIDField.getText());

                if (ownerID < 100000 || ownerID > 999999)
                    throw new IllegalArgumentException();
            } catch (Exception exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Enter VIA ID in \"owner ID\" field!");
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
            String title = titleField.getText();
            String description = descriptionArea.getText();
            int ownerID = 0;
            try
            {
                ownerID = Integer.parseInt(ownerIDField.getText());
            } catch (Exception exception)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, exception.getMessage());
                alert.setHeaderText(null);
                alert.show();
                return;
            }

            Genre genre = genreChoiceBox.getSelectionModel().getSelectedItem();


            Association association = AssociationModelManager.getAssociation();
            int index = catalogueTable.getSelectionModel().getSelectedIndex();
            BoardGame boardGameTemp = AssociationModelManager.getAssociation().getCatalogue().getBoardGame(index);
            boardGameTemp.setTitle(title);
            boardGameTemp.setOwnerID(ownerID);
            boardGameTemp.setDescription(description);
            boardGameTemp.setGenre(genre);
            association.getCatalogue().setBoardGame(index, boardGameTemp);
            AssociationModelManager.saveAssociation(association);
            updateTable();

        }
        else if (e.getSource() == deleteGameButton)
        {
            BoardGame game = catalogueTable.getSelectionModel().getSelectedItem();
            AssociationModelManager.getAssociation().getCatalogue().removeBoardGame(game);
            updateTable();
        }
        else if (e.getSource() == borrowGameButton)
        {
            int index = catalogueTable.getSelectionModel().getSelectedIndex();
            BoardGame game = AssociationModelManager.getAssociation().getCatalogue().getBoardGame(index);


            Stage window = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/set_status_dialog_window.fxml"));
            Parent root = (Parent) loader.load();
            StatusViewController statusViewController = loader.getController();
            statusViewController.passData(game, true, index);
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.showAndWait();


            updateTable();
        }
        else if (e.getSource() == reserveGameButton)
        {
            int index = catalogueTable.getSelectionModel().getSelectedIndex();
            BoardGame game = AssociationModelManager.getAssociation().getCatalogue().getBoardGame(index);


            Stage window = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/set_status_dialog_window.fxml"));
            Parent root = (Parent) loader.load();
            StatusViewController statusViewController = loader.getController();
            statusViewController.passData(game, false, index);
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.showAndWait();

            updateTable();
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
        int indexSelected = catalogueTable.getSelectionModel().getSelectedIndex();
        if (indexSelected == -1)
            indexSelected = 0;
        Catalogue catalogue = AssociationModelManager.getAssociation().getCatalogue();
        catalogueTable.getItems().clear();

        for (int i = 0; i < catalogue.getSize(); i++)
        {
            BoardGame boardGame = catalogue.getBoardGame(i);
            catalogueTable.getItems().add(boardGame);
        }

        catalogueTable.getSelectionModel().select(indexSelected);
    }
}
