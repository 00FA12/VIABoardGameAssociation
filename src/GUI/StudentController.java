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

    updateTable();
  }

  public void handleAction(ActionEvent e)
  {
    if (e.getSource() == addStudentButton)
    {
      Student student = null;
      String name = nameField.getText();
      int ID = Integer.parseInt(IDField.getText());
      boolean isMember = isMemberCheckBox.isSelected();

      student = new Student(name, ID, isMember);

      Association association = AssociationModelManager.getAssociation();
      association.addStudent(student);
      AssociationModelManager.saveAssociation(association);
      updateTable();
    }
    else if (e.getSource() == deleteStudentButton)
    {

    }
  }

  public void updateTable()
  {
    StudentList studentList = AssociationModelManager.getAssociation()
        .getStudentList();
    studentTable.getItems().clear();

    for (int i = 0; i < studentList.getSize(); i++)
    {
      Student student = studentList.getStudent(i);
      studentTable.getItems().add(student);
    }
    // todo update table

  }
}
