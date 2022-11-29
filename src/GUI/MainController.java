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
        studentController.initialize(modelManager);
        voteController.initialize(modelManager);
        catalogueController.initialize(modelManager);
        eventController.initialize(modelManager);
    }

    public void tabChanged(Event event)
    {
        if(studentTab.isSelected())
        {
            studentController.updateTable();
        }
        else if (eventTab.isSelected())
        {
            eventController.updateTable();
        }
        else if(voteTab.isSelected())
        {
            voteController.updateTable();
        }
        else if (catalogueTab.isSelected())
        {
            catalogueController.updateTable();
        }
    }

}
