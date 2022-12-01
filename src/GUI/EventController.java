package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.*;
import model.lists.EventList;
import model.lists.StudentList;
import org.controlsfx.control.CheckComboBox;

import java.time.LocalDate;
import java.util.ArrayList;

//Kateryna//
public class EventController
{
    // todo delete all modelManager fields in astah!!
    @FXML
    private TableView<Event> eventsTable;
    @FXML
    private TextField titleField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private DatePicker datePicker;
    @FXML
    private VBox actionsForSelectedEventBox;
    @FXML
    private CheckComboBox<Student> attenders;//todo change in class diagram "participants"
    @FXML
    private Button editEventButton;
    @FXML
    private Button deleteEventButton;
    @FXML
    private Button addEventButton;
    @FXML
    private Button exportEventButton;
    @FXML
    private TableColumn<Event, String> titleColumn;
    @FXML
    private TableColumn<Event, String> descriptionColumn;
    @FXML
    private TableColumn<Event, String> dateColumn;
    @FXML
    private TableColumn<Event, String> attendersColumn;

    public void initialize()
    {
        titleColumn.setCellValueFactory(
                new PropertyValueFactory<>("title"));
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
        if (e.getSource() == addEventButton)
        {
            String title = titleField.getText();
            String description = descriptionColumn.getText();

            MyDate date = new MyDate();
            LocalDate tempDate = datePicker.getValue();
            date.setDay(tempDate.getDayOfMonth());
            date.setMonth(tempDate.getMonthValue());
            date.setYear(tempDate.getYear()); // date initialization



            Event event = new Event(title, description, date);

            try
            {
                for (Student attender : attenders.getItems())
                {
                    event.addAttender(attender);
                }
            }
            catch (ArrayIndexOutOfBoundsException exception)
            {
                System.err.println("Student list is empty in event tab");
            }

            Association association = AssociationModelManager.getAssociation();
            association.addEvent(event);
            AssociationModelManager.saveAssociation(association);
            updateTable();
        }
        else if (e.getSource() == exportEventButton)
        {


        }
        else if (e.getSource() == editEventButton)
        {
            String title = titleField.getText();
            String description = descriptionColumn.getText();



            Association association = AssociationModelManager.getAssociation();
            association.getEventList().setEvent(new Event(title, description, date), eventsTable.getSelectionModel().getSelectedIndex());
            AssociationModelManager.saveAssociation(association);
            updateTable();
        }
        else if (e.getSource() == deleteEventButton)
        {
            Association association = AssociationModelManager.getAssociation();
            int temp = eventsTable.getSelectionModel().getSelectedIndex();
            association.removeEvent(temp);
            AssociationModelManager.saveAssociation(association);
            updateTable();
        }
        else if (e.getSource() == eventsTable)
        {
            titleField.setText(titleColumn.getText());
            descriptionArea.setText(descriptionColumn.getText());
            String tempDate = dateColumn.getText();
            MyDate eDate = new MyDate();
            //eDate.setDay(dateColumn.getText());
            //datePicker.setValue();

        }
    }/**/

    public void updateTable()
    {
        EventList eventList = AssociationModelManager.getAssociation()
                .getEventList();
        eventsTable.getItems().clear();

        for (int i = 0; i < eventList.getSize(); i++)
        {
            Event event = eventList.getEvent(i);
            eventsTable.getItems().add(event);
        }

        ObservableList<Student> items = FXCollections.observableArrayList();
        StudentList studentList = AssociationModelManager.getAssociation().getStudentList();
        items.addAll(studentList.getArrayOfStudents());

        attenders = new CheckComboBox<>(items);
    }

}

