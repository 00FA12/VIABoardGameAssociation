package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Association;
import model.AssociationModelManager;
import model.BoardGame;
import model.MyDate;
import model.lists.Catalogue;

import java.security.cert.CertPathValidatorException;
import java.time.LocalDate;

//Kateryna//
public class StatusViewController

{
    @FXML
    private HBox setStatusDialogBox;
    @FXML
    private TextField statusBorrowerIDField;
    @FXML
    private DatePicker statusStartDatePicker;
    @FXML
    private DatePicker statusEndDatePicker;
    @FXML
    private Button statusConfirmButton;
    private boolean isBorrowing;
    private int indexOfGame;

    private BoardGame game;


    public void initialize()
    {
        statusStartDatePicker.setPromptText(new MyDate(LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(),
                LocalDate.now().getYear()).toString());
    }


    public void passData(int indexOfGame, boolean isBorrowing, String theme)
    {
        this.game = AssociationModelManager.getAssociation().getBoardGame(indexOfGame);
        this.isBorrowing = isBorrowing;
        this.indexOfGame = indexOfGame;

        setStatusDialogBox.getStylesheets().clear();
        setStatusDialogBox.getStylesheets().add(theme);
    }


    public void handleActions(ActionEvent e)
    {
        int ID = 0;
        try
        {
            ID = Integer.parseInt(statusBorrowerIDField.getText());
        }
        catch (NumberFormatException exception)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Enter proper ID in \"ID\" field!");
            alert.setHeaderText(null);
            alert.show();
            return;
        }
        MyDate endDate = new MyDate();
        MyDate startDate = new MyDate();
        LocalDate localEndDate = statusEndDatePicker.getValue();
        LocalDate localStartDate = statusStartDatePicker.getValue();

        try
        {
            int day = localStartDate.getDayOfMonth();
            int month = localStartDate.getMonthValue();
            int year = localStartDate.getYear();

            startDate = new MyDate(day, month, year);

            day = localEndDate.getDayOfMonth();
            month = localEndDate.getMonthValue();
            year = localEndDate.getYear();
        }
        catch (IllegalArgumentException exception)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, exception.getMessage());

            alert.show();
        }


        try
        {
            if (isBorrowing)
            {
                game.borrow(ID, startDate, endDate);
            }
            else
            {
                game.reserve(ID, startDate, endDate);
            }
        } catch (Exception exception)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, exception.getMessage());
            alert.setHeaderText(null);
            alert.show();
            return;
        }

        Association association = AssociationModelManager.getAssociation();
        association.getCatalogue().setBoardGame(indexOfGame, game);
        AssociationModelManager.saveAssociation(association);

        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
