package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Association;
import model.AssociationModelManager;
import org.controlsfx.control.Rating;

public class RateController
{
    @FXML private Label gameTitle;

    @FXML private Button rateButton;

    @FXML private Rating rating;

    private int index;
    public void passData(int index)
    {
        this.index = index;
        Association association = AssociationModelManager.getAssociation();
        
        String title = association.getBoardGame(index).getTitle();
        gameTitle.setText(title);
    }

    @FXML private void handleAction(ActionEvent e)
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
