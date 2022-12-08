package GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
  private MyTableListener tableListener;

  private class MyTableListener implements ChangeListener<Student>
  {
    public void changed(ObservableValue<? extends Student> student, Student oldStudent, Student newStudent)
    {
      Student studentTemp = studentTable.getSelectionModel().getSelectedItem();

      if (studentTemp != null)
      {
        nameField.setText(studentTemp.getName());
        IDField.setText(String.valueOf(studentTemp.getID()));
        if (studentTable.getSelectionModel().getSelectedItem().getStatus().equals("Member"))
        {
          isMemberCheckBox.setSelected(true);
        }
        else isMemberCheckBox.setSelected(false);
      }
    }
  }


  public void initialize()
  {
    tableListener = new MyTableListener();
    studentTable.getSelectionModel().selectedItemProperty().addListener(tableListener);

    nameColumn.setCellValueFactory(
        new PropertyValueFactory<Student, String>("name"));
    IDColumn.setCellValueFactory(
        new PropertyValueFactory<Student, String>("ID"));
    statusColumn.setCellValueFactory(
        new PropertyValueFactory<Student, String>("status"));

    studentTable.getSelectionModel().selectFirst();

    updateTable();
  }

  public void handleActions(ActionEvent e)
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
          student = new Student(name, ID);
          if(isMember)
            student.makeMember();
          else
            student.makeGuest();
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
      else if (e.getSource() == editStudentButton)
      {
        String name = nameField.getText();
        int ID = 0;
        try
        {
          ID = studentTable.getSelectionModel().getSelectedItem().getID();
        }
        catch (Exception exception)
        {
          Alert alert = new Alert(Alert.AlertType.ERROR, "Enter proper ID in \"ID\" field.");
        }
        boolean isMember = isMemberCheckBox.isSelected();


        int index = studentTable.getSelectionModel().getSelectedIndex();
        Association association = AssociationModelManager.getAssociation();
        try
        {
          association.getStudentList().setStudent(index, new Student(name, ID, isMember));
        }
        catch (IllegalArgumentException exception)
        {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setHeaderText(null);
          alert.setContentText(exception.getMessage());
          alert.show();
          return;
        }
        AssociationModelManager.saveAssociation(association);
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

    studentTable.getSelectionModel().select(index);

  }
}
