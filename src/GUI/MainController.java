package GUI;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainController
{
    @FXML
    private TabPane tabs;
    @FXML
    private Tab catalogueTab;
    @FXML
    private Tab voteTab;
    @FXML
    private Tab studentTab;
    @FXML
    private Tab eventTab;
    @FXML
    private VoteController voteTabContentController;
    @FXML
    private EventController eventTabContentController;
    @FXML
    private CatalogueController catalogueTabContentController;
    @FXML
    private StudentController studentTabContentController;

    public void initialize()
    {
        // studentTabController.initialize(modelManager);
        // voteTabController.initialize(modelManager);
        // catalogueTabController.initialize(modelManager);
        // eventTabController.initialize(modelManager);
    }

    public void tabChanged(Event event)
    {
        if (studentTab != null && studentTab.isSelected())
        {
            studentTabContentController.updateTable();
        }
        else if (eventTab != null && eventTab.isSelected())
        {
            eventTabContentController.updateTable();
        }
        else if (voteTab != null && voteTab.isSelected())
        {
            voteTabContentController.updateTable();
        }
        else if (catalogueTab != null && catalogueTab.isSelected())
        {
            catalogueTabContentController.updateTable();
        }
    }

}
