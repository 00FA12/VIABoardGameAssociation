package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Association;
import model.AssociationModelManager;
import model.Student;
import model.lists.StudentList;

public class StudentController
{

  @FXML private TableView<Student> studentTable;
  @FXML private TextField nameField;
  @FXML private TextField IDField;
  @FXML private CheckBox isMemberCheckBox;
  @FXML private Button addStudentButton;
  @FXML private Button deleteStudentButton;
  @FXML private Button editStudentButton;
  @FXML private TableColumn<Student, String> nameColumn;
  @FXML private TableColumn<Student, String> IDColumn;
  @FXML private TableColumn<Student, String> statusColumn;

  public void initialize()
  {
    nameColumn.setCellValueFactory(
        new PropertyValueFactory<Student, String>("name"));
    IDColumn.setCellValueFactory(
        new PropertyValueFactory<Student, String>("ID"));
    statusColumn.setCellValueFactory(
        new PropertyValueFactory<Student, String>("status"));

    studentTable.getSelectionModel().selectFirst();

    updateTable();
  }

  public void handleAction(ActionEvent e)
  {
    try
    {
      if (e.getSource() == addStudentButton)
      {
        Student student = null;
        String name = nameField.getText();
        int ID = 0;
        try
        {
          ID = Integer.parseInt(IDField.getText());
        }
        catch (NumberFormatException exception)
        {
          Alert alert = new Alert(Alert.AlertType.ERROR, "Enter only digits in \"ID\" field!");
          alert.setHeaderText(null);
          alert.show();
          return;
        }
        boolean isMember = isMemberCheckBox.isSelected();

        try
        {
          student = new Student(name, ID, isMember);
          Association association = AssociationModelManager.getAssociation();
          association.addStudent(student);
          AssociationModelManager.saveAssociation(association);

        }
        catch (IllegalArgumentException exception)
        {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setHeaderText(null);
          alert.setContentText(exception.getMessage());
          alert.show();
          return;
        }


        updateTable();
      }
      else if (e.getSource() == deleteStudentButton)
      {
        Association association = AssociationModelManager.getAssociation();
        int temp = studentTable.getSelectionModel().getSelectedIndex();
        association.removeStudent(temp);
        AssociationModelManager.saveAssociation(association);
        updateTable();
      }
    }
    catch (IndexOutOfBoundsException exception)
    {
      System.err.println("The student wasn't chosen");
    }
  }

  public void updateTable()
  {
    int index = studentTable.getSelectionModel().getSelectedIndex();
    if (index == -1)
      index = 0;
    StudentList studentList = AssociationModelManager.getAssociation()
        .getStudentList();
    studentTable.getItems().clear();

    for (int i = 0; i < studentList.getSize(); i++)
    {
      Student student = studentList.getStudent(i);
      studentTable.getItems().add(student);
    }
    // todo update table

    studentTable.getSelectionModel().select(index);

  }
}
