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
    private String theme;


    public void initialize()
    {
    }


    public void passData(int indexOfGame, boolean isBorrowing, String theme)
    {
        this.game = AssociationModelManager.getAssociation().getBoardGame(indexOfGame);
        this.isBorrowing = isBorrowing;
        this.indexOfGame = indexOfGame;
        this.theme = theme;

        setStatusDialogBox.getStylesheets().clear();
        setStatusDialogBox.getStylesheets().add(theme);
    }


    public void handleActions(ActionEvent e)
    {
        int ID = Integer.parseInt(statusBorrowerIDField.getText());
        MyDate endDate = new MyDate();
        MyDate startDate = new MyDate();
        LocalDate localEndDate = statusEndDatePicker.getValue();
        LocalDate localStartDate = statusStartDatePicker.getValue();
        if (localStartDate == null)
        {
            startDate = null;
        }
        else
        {
            startDate.setDay(localStartDate.getDayOfMonth());
            startDate.setMonth(localStartDate.getMonthValue());
            startDate.setYear(localStartDate.getYear());
        }
        if (localEndDate == null)
        {
            endDate = null;
        }
        else
        {
            endDate.setDay(localEndDate.getDayOfMonth());
            endDate.setMonth(localEndDate.getMonthValue());
            endDate.setYear(localEndDate.getYear());
        }
        if (localEndDate.isBefore(localStartDate))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "End date can't be before the start date");
            alert.setHeaderText(null);
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
        } catch (NullPointerException exception)
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
