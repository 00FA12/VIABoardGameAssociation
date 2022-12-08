package GUI;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

    @FXML private MenuItem exportMenuItem;
    @FXML private MenuItem helpMenuItem;
    @FXML private MenuItem aboutMenuItem;
    @FXML
    private ToggleSwitch darkModeToggleSwitch;
    @FXML
    private VBox mainPane;
    private static XmlJsonParser parser;

    public void initialize()
    {
        parser = new XmlJsonParser();
        String lightTheme = "GUI/styles-light-mode.css";
        catalogueTabContentController.setTheme(lightTheme);

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
                catalogueTabContentController.setTheme(darkTheme);
            }
            else
            {
                mainPane.getStylesheets().remove(darkTheme);
                mainPane.getStylesheets().add(lightTheme);
                catalogueTabContentController.setTheme(lightTheme);
            }
        }

    }

    public static void exportData() throws ParserException
    {
        parser.toXml(AssociationModelManager.getAssociation().getCatalogue(), "WebPage/Export/catalogue.xml");

        parser.toXml(AssociationModelManager.getAssociation().getVotingList(), "WebPage/Export/votes.xml");

        parser.toXml(AssociationModelManager.getAssociation().getEventList(), "WebPage/Export/events.xml");
    }
    public void handleAction(ActionEvent e) throws ParserException, IOException
    {
        if(e.getSource() == exportMenuItem)
        {
            exportData();
        }
        else if (e.getSource()==helpMenuItem)
        {
            Alert alert =new Alert(Alert.AlertType.INFORMATION, "Please, contact to IT support\nbut nobody will help you, Bob!");
            alert.setHeaderText(null);
            alert.show();
        }
        else if(e.getSource()== aboutMenuItem)
        {
            Alert alert=new Alert(Alert.AlertType.INFORMATION,"IT assistance:" +
                    "\nSevastian - 333425@via.dk " +
                    "\n Michael- 332872@via.dk " +
                    "\n Hugo -331335@via.dk " +
                    "\n Kateryna-333469@via.dk");
            alert.setHeaderText(null);
            alert.show();
        }
    }

}
