package GUI;

import javafx.event.Event;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import model.AssociationModelManager;

public class MainController
{
    private final String fileName = "FXML/main.fxml";
    private TabPane tabs;
    private Tab catalogueTab;
    private Tab voteTab;
    private Tab studentTab;
    private Tab eventTab;

    private VoteController voteController;
    private EventController studentController;
    private CatalogueController eventController;
    private StudentController catalogueController;
    private AssociationModelManager modelManager;

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
