package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Association;
import model.AssociationModelManager;
import org.controlsfx.control.Rating;

/**
 * A class that controls all the rate pop-up window.It is responsible for initializing, updating the GUI and handling the input.
 * @author Sevastian Bahynskyi
 * @version 1.0
 */
public class RateController
{
    @FXML private Label gameTitle;

    @FXML private Button rateButton;

    @FXML private Rating rating;
    @FXML private VBox rateDialogWindowBox;
    private int index;

    /**
     * Method to pass the information about the game that is about to be rated.
     * @param theme the file path of the theme
     * @param index the index of the game which we use to get the title later on.
     */
    public void passData(int index, String theme)
    {
        this.index = index;
        Association association = AssociationModelManager.getAssociation();

        String title = association.getBoardGame(index).getTitle();
        gameTitle.setText(title);
        rateDialogWindowBox.getStylesheets().clear();
        rateDialogWindowBox.getStylesheets().add(theme);
    }

    /**
     * Method that listens for the rate stars to be pressed and handles it.
     * @param e the event which is in our case the rate stars pressed.
     */
    public void handleAction(ActionEvent e)
    {
        if(e.getSource() == rateButton)
        {
            Association association = AssociationModelManager.getAssociation();

            association.getCatalogue().getBoardGame(index).addRating((int) Math.round(rating.getRating()));
            AssociationModelManager.saveAssociation(association);

            Node source = (Node) e.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }
}
