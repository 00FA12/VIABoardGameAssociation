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
import parser.ParserException;

import java.io.IOException;

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
    private ComboBox<Genre> genreChoiceBox;
    @FXML
    private Button editGameButton, deleteGameButton, addGameButton, borrowGameButton,
            reserveGameButton, returnGameButton, addRateButton, statusInfoButton;

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

    private String theme;


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
                new PropertyValueFactory<BoardGame, String>("statusToString"));
        IDColumn.setCellValueFactory(
                new PropertyValueFactory<BoardGame, String>("ownerID"));
        ratingColumn.setCellValueFactory(
                new PropertyValueFactory<BoardGame, String>("average"));

        catalogueTable.getSelectionModel().selectFirst();


        updateTable();
    }

    public void handleAction(ActionEvent e) throws IOException, ParserException
    {
        try
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
                catch (NumberFormatException exception)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Enter only digits in \"Owner ID\" field!");
                    alert.setHeaderText(null);
                    alert.show();
                    return;
                }

                Genre genre = genreChoiceBox.getSelectionModel().getSelectedItem();

                Association association = AssociationModelManager.getAssociation();
                try
                {
                    association.getCatalogue().addBoardGame(new BoardGame(title, ownerID, description, genre));
                }
                catch (IllegalArgumentException exception)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR, exception.getMessage());
                    alert.setHeaderText(null);
                    alert.show();
                    return;
                }
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
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Enter VIA ID in \"Owner ID\" field!");
                    alert.setHeaderText(null);
                    alert.show();
                    return;
                }

                Genre genre = genreChoiceBox.getSelectionModel().getSelectedItem();


                Association association = AssociationModelManager.getAssociation();
                int index = catalogueTable.getSelectionModel().getSelectedIndex();
                BoardGame boardGameTemp;
                try
                {
                    boardGameTemp = new BoardGame(title, ownerID, description, genre);
                }
                catch (IllegalArgumentException exception)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR, exception.getMessage());
                    alert.setHeaderText(null);
                    alert.show();
                    return;
                }
                association.getCatalogue().setBoardGame(index, boardGameTemp);
                AssociationModelManager.saveAssociation(association);
                updateTable();

            }
            else if (e.getSource() == deleteGameButton)
            {
                int index = catalogueTable.getSelectionModel().getSelectedIndex();
                Association association = AssociationModelManager.getAssociation();
                association.removeBoardGame(index);
                AssociationModelManager.saveAssociation(association);
                updateTable();
            }
            else if (e.getSource() == borrowGameButton)
            {
                int index = catalogueTable.getSelectionModel().getSelectedIndex();
                BoardGame game = AssociationModelManager.getAssociation().getCatalogue().getBoardGame(index);
                if(game.getStatusOfGame() != null)
                    return;


                Stage window = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/set_status_dialog_window.fxml"));
                Parent root = (Parent) loader.load();
                StatusViewController statusViewController = loader.getController();
                statusViewController.passData(index, true, theme);
                Scene scene = new Scene(root);
                window.setScene(scene);
                window.showAndWait();


                updateTable();
            }
            else if (e.getSource() == reserveGameButton)
            {
                int index = catalogueTable.getSelectionModel().getSelectedIndex();
                BoardGame game = AssociationModelManager.getAssociation().getCatalogue().getBoardGame(index);
                if(game.getStatusOfGame() != null)
                    return;


                Stage window = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/set_status_dialog_window.fxml"));
                Parent root = (Parent) loader.load();
                StatusViewController statusViewController = loader.getController();
                statusViewController.passData(index, false, theme);
                Scene scene = new Scene(root);
                window.setScene(scene);
                window.showAndWait();

                updateTable();
            }
            else if (e.getSource() == returnGameButton)
            {
                int index = catalogueTable.getSelectionModel().getSelectedIndex();
                BoardGame game = AssociationModelManager.getAssociation().getCatalogue().getBoardGame(index);

                if(game.getStatusOfGame() == null)
                    return;

                Association association = AssociationModelManager.getAssociation();
                association.getCatalogue().getBoardGame(index).setStatusOfGame(null);
                AssociationModelManager.saveAssociation(association);
                updateTable();



                Stage window = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/rate_dialog_window.fxml"));
                Parent root = (Parent) loader.load();
                RateController rateController = loader.getController();
                rateController.passData(index, theme);
                Scene scene = new Scene(root);
                window.setScene(scene);
                window.showAndWait();
                updateTable();
            }
            else if (e.getSource() == addRateButton)
            {
                int index = catalogueTable.getSelectionModel().getSelectedIndex();
                BoardGame game = AssociationModelManager.getAssociation().getCatalogue().getBoardGame(index);


                Stage window = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/rate_dialog_window.fxml"));
                Parent root = (Parent) loader.load();
                RateController rateController = loader.getController();
                rateController.passData(index, theme);
                Scene scene = new Scene(root);
                window.setScene(scene);
                window.showAndWait();
                updateTable();
            }
            else if (e.getSource() == statusInfoButton)
            {
                int index = catalogueTable.getSelectionModel().getSelectedIndex();
                BoardGame game = AssociationModelManager.getAssociation().getBoardGame(index);

                String str = "";
                if(game.getStatusOfGame() == null)
                    str = "Game is available!";
                else
                {
                    Student student = game.getStatusOfGame().getStudent();
                    str = "Game is " + game.getStatusToString() + " by " + student.getName() +
                            " ( ID:" + student.getID() + " ). " + "\nStart date: " +
                            game.getStatusOfGame().getStartDate().toString() + ". End date: " +
                            game.getStatusOfGame().getEndDate().toString();
                }
                Label text = new Label(str);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.getDialogPane().setContent(text);
                alert.show();
            }

            MainController.exportData();
        }
        catch (IndexOutOfBoundsException exception)
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

    public void setTheme(String theme)
    {
        this.theme = theme;
    }
}
