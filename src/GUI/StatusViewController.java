package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
//        Scene scene = new Scene(root);
//        window.setScene(scene);
    }


    public void passData(BoardGame game, boolean isBorrowing, int indexOfGame)
    {
        this.game = game;
        this.isBorrowing = isBorrowing;
        this.indexOfGame = indexOfGame;

    }


    public void handleActions(ActionEvent e)
    {

    }
}
