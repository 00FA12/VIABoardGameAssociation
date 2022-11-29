package GUI;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.AssociationModelManager;
import model.Student;

import javax.swing.*;

public class StudentController
{
    private AssociationModelManager modelManager;
    private TableView<Student> studentTable;
    private TextField nameField;
    private TextField IDField;
    private CheckBox isMemberCheckBox;
    private Button addStudentButton;
    private Button deleteStudentButton;
    private Button editStudentButton;


    public void initialize(AssociationModelManager modelManager)
    {
        this.modelManager = modelManager;
        updateTable();
    }

    public void handleAction(ActionEvent e)
    {
        if(e.getSource() == addStudentButton)
        {

        }
        else if(e.getSource() == deleteStudentButton)
        {

        }
    }

    public void updateTable()
    {
        if(modelManager != null)
        {
            // todo update table
        }
    }
}
