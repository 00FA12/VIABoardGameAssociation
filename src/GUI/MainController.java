package GUI;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import model.Association;
import model.AssociationModelManager;

public class MainController
{
    @FXML private TabPane tabs;
    @FXML private Tab catalogueTab;
    @FXML private Tab voteTab;
    @FXML private Tab studentTab;
    @FXML private Tab eventTab;
    @FXML private VoteController voteTabController;
    @FXML private EventController eventTabController;
    @FXML private CatalogueController catalogueTabController;
    @FXML private StudentController studentTabController;

    public void initialize()
    {
       // studentTabController.initialize(modelManager);
       // voteTabController.initialize(modelManager);
       // catalogueTabController.initialize(modelManager);
       // eventTabController.initialize(modelManager);
    }

    public void tabChanged(Event event)
    {
        if(studentTab.isSelected())
        {
            studentTabController.updateTable();
        }
        else if (eventTab.isSelected())
        {
            eventTabController.updateTable();
        }
        else if(voteTab.isSelected())
        {
            voteTabController.updateTable();
        }
        else if (catalogueTab.isSelected())
        {
            catalogueTabController.updateTable();
        }
    }

}
