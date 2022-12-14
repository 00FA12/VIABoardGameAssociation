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

/**
 * A class that controls all the tabs of the application window. It specifies all the controller classes, handles the switch of the tabs.
 * @author Sevastian Bahynskyi
 * @version 1.0
 */
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

    /**
     * Method which is runned at the start of the GUI
     */
    public void initialize()
    {
        parser = new XmlJsonParser();
        String lightTheme = "GUI/styles-light-mode.css";
        catalogueTabContentController.setTheme(lightTheme);

    }

    /**
     * Method that listens to the tab switches.
     * @param event An action done by user. In our case, the tab has been changed.
     */
    public void tabChanged(Event event) throws ParserException
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

    /**
     * Method that listens to the mouse clicks by user.
     * @param e An action done by user. In this case, we the theme switch has been pressed.
     * @throws ParserException
     */
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

    /**
     * Static method to be able to export data at any point. All the information is parsed into XML file.
     * @throws ParserException
     */
    public static void exportData() throws ParserException
    {
        parser.toXml(AssociationModelManager.getAssociation().getCatalogue(), "WebPage/Export/catalogue.xml");

        parser.toXml(AssociationModelManager.getAssociation().getVotingList(), "WebPage/Export/votes.xml");

        parser.toXml(AssociationModelManager.getAssociation().getEventList(), "WebPage/Export/events.xml");
    }

    /**
     * Method that listens to the interactions with the buttons.
     * @throws ParserException
     */
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
