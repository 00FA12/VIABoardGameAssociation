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

/**
 * A class that controls the Catalogue tab in the GUI. It is responsible for initializing, updating the GUI and handling the input.
 * @author Michael Leo, Sevastian Bahynskyi (logic for handling user input)
 * @version 1.0
*/
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

    /**
     * A nested class that notifies us of the changes to the BoardGame, implements ChangeListener.
     */
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

    /**
     * Method that is invoked at the start of the application and sets up the view.
     */
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

    /**
     * Method that is invoked when user interacts with the application, handles the actions of the user.
     * @param e An action done by user. In our case, the button pressed.
     * @throws IOException
     * @throws ParserException
     */
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
                    // tries to parse ownerIDField. If the input is a number, the method can continue
                    ownerID = Integer.parseInt(ownerIDField.getText());
                }
                catch (NumberFormatException exception) // if the input is not an integer, show an alert message and exit method
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Enter only digits in \"Owner ID\" field!");
                    alert.setHeaderText(null);
                    alert.show();
                    return;
                }

                // if student with such ID is not found show an alert and exit method
                if(AssociationModelManager.getAssociation().getStudentByID(ownerID) == null)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Entered ID is not listed in the system!");
                    alert.show();
                    return;
                }

                // gets the selected genre from the dropdown menu (combo box)
                Genre genre = genreChoiceBox.getSelectionModel().getSelectedItem();

                // gets the last version of "Association" by reading the file
                Association association = AssociationModelManager.getAssociation();
                try
                {
                    // system tries to create a "BoardGame" object
                    // if it was created successfully, it will be added to the association's catalogue
                    association.getCatalogue().addBoardGame(new BoardGame(title, ownerID, description, genre));
                }
                catch (IllegalArgumentException exception) // handles the exception if the input has found invalid format/value
                {
                    // informs the user with an alert
                    Alert alert = new Alert(Alert.AlertType.ERROR, exception.getMessage());
                    alert.setHeaderText(null);
                    alert.show();
                    return;
                }
                // if all code above was executed, system can save a new "Association" version
                AssociationModelManager.saveAssociation(association);
                // refreshes the table view
                updateTable();
            }
            else if (e.getSource() == editGameButton)
            {
                String title = titleField.getText();
                String description = descriptionArea.getText();
                int ownerID = 0;

                Genre genre = genreChoiceBox.getSelectionModel().getSelectedItem();


                Association association = AssociationModelManager.getAssociation();
                int index = catalogueTable.getSelectionModel().getSelectedIndex();
                BoardGame boardGame;
                try
                {
                    BoardGame boardGameTemp = AssociationModelManager.getAssociation().getBoardGame(index);
                    ownerID = AssociationModelManager.getAssociation().getBoardGame(index).getOwnerID();
                    boardGame = new BoardGame(title, ownerID, description, genre);
                    boardGame.setStatusOfGame(boardGameTemp.getStatusOfGame());
                    boardGame.setRatingList(boardGameTemp.getRatingList());
                }
                catch (IllegalArgumentException exception)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR, exception.getMessage());
                    alert.setHeaderText(null);
                    alert.show();
                    return;
                }
                association.getCatalogue().setBoardGame(index, boardGame);
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

    /**
     * Method that updates the table that contains the games.
     */
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

    /**
     * Method that sets the theme
     * @param theme the desired theme
     */
    public void setTheme(String theme)
    {
        this.theme = theme;
    }
}
