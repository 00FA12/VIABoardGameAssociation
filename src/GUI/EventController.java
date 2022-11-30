package GUI;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.controlsfx.control.CheckComboBox;

//Kateryna//
public class EventController {
@FXML private TableView<Event> eventsTable;
    @FXML private TextField titleField;
    @FXML private TextArea descriptionArea;
    @FXML private DatePicker datePicker;
    @FXML private VBox actionsForSelectedEventBox;
    @FXML private CheckComboBox participants;
    @FXML private Button editEventButton;
    @FXML private Button deleteEventButton;
    @FXML private Button addEventButton;
    @FXML private Button exportEventButton;
    @FXML private TableColumn<Event, String> titleColumn;
    @FXML private TableColumn<Event, String> descriptionColumn;
    @FXML private TableColumn<Event, String> dateColumn;
    @FXML private TableColumn<Event, String> attendersColumn;





}
