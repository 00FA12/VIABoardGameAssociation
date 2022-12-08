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

import java.time.LocalDate;

//Together
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

        datePicker.setPromptText(new MyDate(LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(),
                LocalDate.now().getYear()).toString());
    }


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

