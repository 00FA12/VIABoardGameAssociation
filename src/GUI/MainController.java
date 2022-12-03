package GUI;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.ToggleSwitch;

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
    @FXML
    private ToggleSwitch darkModeToggleSwitch;
    @FXML
    private VBox mainPane;

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

    public void handleAction(MouseEvent e)
    {
        if(e.getSource() == darkModeToggleSwitch)
        {
            String darkTheme = "GUI/styles-dark-mode.css";
            String lightTheme = "GUI/styles-light-mode.css";
            if(darkModeToggleSwitch.isSelected())
            {
                mainPane.getStylesheets().remove(lightTheme);
                mainPane.getStylesheets().add(darkTheme);
            }
            else
            {
                mainPane.getStylesheets().remove(darkTheme);
                mainPane.getStylesheets().add(lightTheme);
            }
        }
    }

}
