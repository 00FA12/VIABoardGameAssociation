package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.AssociationModelManager;
import model.Event;
import model.MyDate;
import model.Student;
import model.lists.EventList;
import model.lists.StudentList;
import org.controlsfx.control.*;

import java.util.ArrayList;

//Kateryna//
public class EventController {
@FXML private TableView<Event> eventsTable;
    @FXML private TextField titleField;
    @FXML private TextArea descriptionArea;
    @FXML private DatePicker datePicker;
    @FXML private VBox actionsForSelectedEventBox;
    @FXML private CheckComboBox<String> attenders;//todo change in class diagram "participants"
    @FXML private Button editEventButton;
    @FXML private Button deleteEventButton;
    @FXML private Button addEventButton;
    @FXML private Button exportEventButton;
    @FXML private TableColumn<Event, String> titleColumn;
    @FXML private TableColumn<Event, String> descriptionColumn;
    @FXML private TableColumn<Event, String> dateColumn;
    @FXML private TableColumn<Event, String> attendersColumn;


    public void initialize()
    {
        ObservableList<String> items = FXCollections.observableArrayList();
        StudentList studentList = AssociationModelManager.getStudentList();
        for (int i = 0; i < studentList.getSize(); i++)
        {
            items.add(studentList.getStudent(i).toString());
        }
        attenders = new CheckComboBox<String>(items);

        titleColumn.setCellValueFactory(
            new PropertyValueFactory<Event, String>("title"));
        descriptionColumn.setCellValueFactory(
            new PropertyValueFactory<Event, String>("description"));
        dateColumn.setCellValueFactory(
            new PropertyValueFactory<Event, String>("date"));
        attendersColumn.setCellValueFactory(
            new PropertyValueFactory<Event, String>("attenders"));

        updateTable();
    }

    public void handleAction(ActionEvent e)
    {
        if(e.getSource() == addEventButton)
        {
           String title = titleField.getText();
           String description = descriptionColumn.getText();
           String date = datePicker.toString();
           ArrayList<String> attendersList;
        }
        else if (e.getSource()==exportEventButton)
        {

        }
        else if (e.getSource()==editEventButton)
        {
            
        }
        else if (e.getSource()==deleteEventButton)
        {
            
        }
    }
    public void updateTable(){
        EventList eventList = AssociationModelManager.getAssociation().getEventList();
        eventsTable.getItems().clear();

        for (int i = 0; i < eventList.getSize(); i++)
        {
           Event event = eventList.getEvent(i);
           eventsTable.getItems().add(event);
        }
    }

}
