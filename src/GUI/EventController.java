package GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import model.lists.EventList;
import model.lists.StudentList;
import org.controlsfx.control.CheckComboBox;

import java.io.IOException;
import java.time.LocalDate;

//Together
/**
 * A class that controls the Event tab in the GUI. It is responsible for initializing, updating the GUI and handling the input.
 * @author Sevastian Bahynskyi (logic for handling user input), Michael Leo, Hugo Madrid Peñarrubia, Kateryna Sokolova
 * @version 1.0
 */
public class EventController
{
    @FXML
    private TableView<Event> eventsTable;
    @FXML
    private TextField titleField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private DatePicker datePicker;
    @FXML
    private CheckComboBox<Student> checkComboBox;
    @FXML
    private Button editEventButton;
    @FXML
    private Button deleteEventButton;
    @FXML
    private Button addEventButton;
    @FXML
    private TableColumn<Event, String> titleColumn;
    @FXML
    private TableColumn<Event, String> descriptionColumn;
    @FXML
    private TableColumn<Event, String> dateColumn;
    @FXML
    private TableColumn<Event, String> attendersColumn;
    private MyTableListener tableListener;

    /**
     * A nested class that notifies us of the changes to the BoardGame, implements ChangeListener.
     */
    private class MyTableListener implements ChangeListener<Event>
    {
        public void changed(ObservableValue<? extends Event> event, Event oldEvent, Event newEvent)
        {
            // whenever user selects item from the table, all the input fields will take corresponding data from the event
            // we store selected event from the table, 1 for get and 1 for initialization (2)
            Event eventTemp = eventsTable.getSelectionModel().getSelectedItem();

            if (eventTemp != null) // safety check, 1
            {
                titleField.setText(eventTemp.getTitle()); // title field get selected event title, 1 - get and 1 for set (2)
                descriptionArea.setText(eventTemp.getDescription()); // description field get selected event description, 1 - get and 1 for set (2)
                String[] strDate = eventTemp.getDate().toString().split("\\."); // get String data version and process it later, 2
                int day = Integer.parseInt(strDate[0]); // 1 for parsing and 1 for initialization, 2
                int month = Integer.parseInt(strDate[1]); // 2
                int year = Integer.parseInt(strDate[2]); // 2
                LocalDate tempDate = LocalDate.of(year, month, day); // 2
                datePicker.setValue(tempDate); // 1
                checkComboBox.getItems().clear(); // clear checkCombBox, 1
                // add all students that was read from the file
                checkComboBox.getItems().addAll(AssociationModelManager.getAssociation().getStudentList().getArrayOfStudents()); // 2

                StudentList studentList = eventTemp.getAttenders(); // create student list of the event attenders, 2
                for(int i = 0; i < studentList.getSize(); i++) // 1 + n-1 + n
                {
                    // condition checks if the student is the event attender, check it
                    if(checkComboBox.getItems().contains(studentList.getStudent(i))) // 1 for get, 1 for contains method, 2
                    {
                        // get the index of the student that was found
                        // 1 for get, 1 for getting index, 1 for initialization, 3
                        int index = checkComboBox.getItems().indexOf(studentList.getStudent(i));
                        checkComboBox.getCheckModel().check(index); // 1
                    }
                }
            }
            // O(30) + O(n-1) + O(n) ~ O(n)
        }
    }

    /**
     * Method that is invoked at the start of the application and sets up the view.
     */
    public void initialize()
    {
        tableListener = new MyTableListener();
        eventsTable.getSelectionModel().selectedItemProperty().addListener(tableListener);

        titleColumn.setCellValueFactory(
                new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(
                new PropertyValueFactory<Event, String>("description"));
        dateColumn.setCellValueFactory(
                new PropertyValueFactory<Event, String>("date"));
        attendersColumn.setCellValueFactory(
                new PropertyValueFactory<Event, String>("attenders"));



        eventsTable.getSelectionModel().selectFirst();

        updateTable();

        datePicker.setPromptText(new MyDate(LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(),
                LocalDate.now().getYear()).toString());
    }


    /**
     * Method that is invoked when user interacts with the application, handles the actions of the user.
     * @param e An action done by user. In our case, the button pressed.
     */
    public void handleActions(ActionEvent e)
    {
        try
        {
            if (e.getSource() == addEventButton)
            {
                String title = titleField.getText();
                String description = descriptionArea.getText();

                MyDate date = new MyDate();
                LocalDate tempDate = datePicker.getValue();
                int day = tempDate.getDayOfMonth();
                int month = tempDate.getMonthValue();
                int year = tempDate.getYear();

                try
                {
                    date = new MyDate(day, month, year);// date initialization
                }
                catch (IllegalArgumentException exception)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR, exception.getMessage());
                    alert.setHeaderText(null);
                    alert.show();
                    return;
                }

                Event event = null;
                try
                {
                    event = new Event(title, description, date);
                }
                catch (IllegalArgumentException exception)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR, exception.getMessage());
                    alert.setHeaderText(null);
                    alert.show();
                    return;
                }

                try
                {
                    for (int i = 0; i < checkComboBox.getCheckModel().getCheckedItems().size(); i++)
                    {
                        event.addAttender(checkComboBox.getCheckModel().getCheckedItems().get(i));
                    }
                }
                catch (ArrayIndexOutOfBoundsException exception)
                {
                    System.err.println("Student list is empty");
                }

                Association association = AssociationModelManager.getAssociation();
                association.addEvent(event);
                AssociationModelManager.saveAssociation(association);
                updateTable();
            }
            else if (e.getSource() == editEventButton)
            {
                String title = titleField.getText();
                String description = descriptionArea.getText();

                MyDate date = new MyDate();
                LocalDate tempDate = datePicker.getValue();
                date.setDay(tempDate.getDayOfMonth());
                date.setMonth(tempDate.getMonthValue());
                date.setYear(tempDate.getYear()); // date initialization

                Association association = AssociationModelManager.getAssociation();
                int index = eventsTable.getSelectionModel().getSelectedIndex();


                Event eventTemp = new Event(title, description, date);
                eventTemp.addAttenderList(checkComboBox.getCheckModel().getCheckedItems());
                association.getEventList().setEvent(eventTemp, index);
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
        }
        catch (IndexOutOfBoundsException exception)
        {

        }

    }

    /**
     * Method that updates the table that contains the events.
     */
    public void updateTable()
    {
        // we store selected index of the table this takes 2 (get method and assigning it to variable)
        int indexSelected = eventsTable.getSelectionModel().getSelectedIndex();
        if(indexSelected == -1) // safety check, 1
            indexSelected = 0; // 1

        // read event list from the file, it takes 2
        EventList eventList = AssociationModelManager.getAssociation()
                .getEventList();
        eventsTable.getItems().clear(); // clear items of the table, 1

        // the loop add all events to the table from the stored event list
        for (int i = 0; i < eventList.getSize(); i++) // 1 + n-1 + n
        {
            Event event = eventList.getEvent(i); // 2 (1 for getter and 1 for assigning)
            eventsTable.getItems().add(event); // 1
        }

        eventsTable.getSelectionModel().select(indexSelected); // select the row in the table by index, 2
        eventsTable.requestFocus(); // 1

//      ChechComboBox
        checkComboBox.getItems().clear(); // clear check combo box, 1
        // add all students that was read from the file, this takes 2
        checkComboBox.getItems().addAll(AssociationModelManager.getAssociation().getStudentList().getArrayOfStudents());
        // O(17) + O(n - 1) + O(n) ~ O(n)
    }

}

