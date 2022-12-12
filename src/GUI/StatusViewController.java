package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.*;
import model.lists.Catalogue;

import java.security.cert.CertPathValidatorException;
import java.time.LocalDate;
import java.util.ArrayList;

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

        if(AssociationModelManager.getAssociation().getStudentByID(ID) == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Entered ID is not listed in the system!");
            alert.show();
            return;
        }
        MyDate endDate = new MyDate();
        MyDate startDate = new MyDate();
        LocalDate localEndDate = statusEndDatePicker.getValue();
        LocalDate localStartDate = statusStartDatePicker.getValue();

        if(localEndDate.isBefore(localStartDate))
        {
            Alert alert  = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("You can't set end date before the start date!");
            alert.show();
            return;
        }

        try
        {
            int day = localStartDate.getDayOfMonth();
            int month = localStartDate.getMonthValue();
            int year = localStartDate.getYear();

            startDate = new MyDate(day, month, year);

            day = localEndDate.getDayOfMonth();
            month = localEndDate.getMonthValue();
            year = localEndDate.getYear();
            endDate = new MyDate(day, month, year);
        }
        catch (IllegalArgumentException exception)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, exception.getMessage());
            alert.setHeaderText(null);
            alert.show();
        }


        Association association = AssociationModelManager.getAssociation();
        Catalogue catalogue = association.getCatalogue();
        int count = 0;
        Student student = null;
        ArrayList<String> gameTitles = new ArrayList<>();
        for (int i = 0; i < catalogue.getSize(); i++)
        {
            if(catalogue.getBoardGame(i).getStatusOfGame() == null)
                continue;
            Student currStudent = catalogue.getBoardGame(i).getStatusOfGame().getStudent();
            if(currStudent == null)
                continue;
            currStudent = association.getStudentByID(currStudent.getID());
            if(currStudent.getID() == ID)
            {
                count++;
                gameTitles.add(catalogue.getBoardGame(i).getTitle());
                student = currStudent;
            }
        }

        if(student != null && student.getStatus().equals("Guest") && count > 0)
        {
            String str = "";
            for (String temp:gameTitles)
            {
                str += temp + "\n";
            }

            Label text = new Label("The guest can borrow/reserve only one game. Return all the games:\n" + str);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.getDialogPane().setContent(text);
            alert.setHeaderText(null);
            alert.show();
            Scene scene = statusBorrowerIDField.getScene();
            Stage window = (Stage) scene.getWindow();
            window.close();
            return;
        }
        else if (student != null && student.getStatus().equals("Member") && count > 0)
        {
            String str = "";
            for (String temp:gameTitles)
            {
                str += temp + "\n";
            }

            Label text = new Label("Please don't forget to return all games:\n" + str);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.getDialogPane().setContent(text);
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
        } catch (Exception exception)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, exception.getMessage());
            alert.setHeaderText(null);
            alert.show();
            return;
        }

        association = AssociationModelManager.getAssociation();
        association.getCatalogue().setBoardGame(indexOfGame, game);
        AssociationModelManager.saveAssociation(association);

        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
