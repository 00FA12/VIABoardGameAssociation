package GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.*;
import model.lists.EventList;
import model.lists.StudentList;
import org.controlsfx.control.CheckComboBox;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

//Together
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
    private CheckComboBox<Student> checkComboBox;
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
    private MyTableListener tableListener;

    private class MyTableListener implements ChangeListener<Event>
    {
        public void changed(ObservableValue<? extends Event> event, Event oldEvent, Event newEvent)
        {
            Event eventTemp = eventsTable.getSelectionModel().getSelectedItem();

            if (eventTemp != null)
            {
                titleField.setText(eventTemp.getTitle());
                descriptionArea.setText(eventTemp.getDescription());
                String[] strDate = eventTemp.getDate().toString().split("\\.");
                int day = Integer.parseInt(strDate[0]);
                int month = Integer.parseInt(strDate[1]);
                int year = Integer.parseInt(strDate[2]);
                LocalDate tempDate = LocalDate.of(year, month, day);
                datePicker.setValue(tempDate);
                checkComboBox.getItems().clear();
                checkComboBox.getItems().addAll(AssociationModelManager.getAssociation().getStudentList().getArrayOfStudents());

                StudentList studentList = eventTemp.getAttenders();
                for(int i = 0; i < studentList.getSize(); i++)
                {
                    if(checkComboBox.getItems().contains(studentList.getStudent(i)))
                    {
                        int index = checkComboBox.getItems().indexOf(studentList.getStudent(i));
                        checkComboBox.getCheckModel().check(index);
                    }
                }
            }
        }
    }

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
    }


    public void handleAction(ActionEvent e)
    {
        if (e.getSource() == addEventButton)
        {
            String title = titleField.getText();
            String description = descriptionArea.getText();

                MyDate date = new MyDate();
                LocalDate tempDate = datePicker.getValue();
                if(tempDate == null)
                    date = null;
                else
                {
                    date.setDay(tempDate.getDayOfMonth());
                    date.setMonth(tempDate.getMonthValue());
                    date.setYear(tempDate.getYear()); // date initialization
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
                    System.err.println("Student list is empty in event tab");
                }

            Association association = AssociationModelManager.getAssociation();
            association.addEvent(event);
            AssociationModelManager.saveAssociation(association);
            updateTable();
        }
        else if (e.getSource() == exportEventButton)
        {
            //todo export
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
        catch (IndexOutOfBoundsException exception)
        {
            System.err.println("The event wasn't chosen");
        }

    }/**/

    public void updateTable()
    {
        int indexSelected = eventsTable.getSelectionModel().getSelectedIndex();
        int indexFocused = eventsTable.getSelectionModel().getFocusedIndex();
        if(indexSelected == -1)
            indexSelected = 0;
        EventList eventList = AssociationModelManager.getAssociation()
                .getEventList();
        eventsTable.getItems().clear();

        for (int i = 0; i < eventList.getSize(); i++)
        {
            Event event = eventList.getEvent(i);
            eventsTable.getItems().add(event);
        }

//      ChechComboBox
        System.out.println(checkComboBox.getItems().size());
        checkComboBox.getItems().clear();
        checkComboBox.getItems().addAll(AssociationModelManager.getAssociation().getStudentList().getArrayOfStudents());
        System.out.println(checkComboBox.getItems().size());

        eventsTable.getSelectionModel().select(indexSelected);
        eventsTable.requestFocus();
    }

}

