package GUI;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.AssociationModelManager;
import org.controlsfx.control.ToggleSwitch;
import parser.ParserException;
import parser.XmlJsonParser;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

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

    @FXML private MenuItem catalogueMenuItem;
    @FXML private MenuItem votesMenuItem;
    @FXML private MenuItem eventsMenuItem;
    @FXML private MenuItem helpMenuItem;
    @FXML private MenuItem aboutMenuItem;
    @FXML
    private ToggleSwitch darkModeToggleSwitch;
    @FXML
    private VBox mainPane;
    private XmlJsonParser parser;

    public void initialize()
    {
        parser = new XmlJsonParser();
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

    public void handleMouseEvent(MouseEvent e) throws ParserException
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

    public void handleAction(ActionEvent e) throws ParserException, IOException
    {
        if(e.getSource() == catalogueMenuItem)
        {
            File file = parser.toXml(AssociationModelManager.getAssociation().getCatalogue(), "WebPage/Export/catalogue.xml");
        }
        else if(e.getSource() == votesMenuItem)
        {
            File file = parser.toXml(AssociationModelManager.getAssociation().getVotingList(), "WebPage/Export/votes.xml");
        }
        else if(e.getSource() == eventsMenuItem)
        {
            File file = parser.toXml(AssociationModelManager.getAssociation().getEventList(), "WebPage/Export/events.xml");
        }
    }

}
