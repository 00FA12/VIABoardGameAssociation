package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.AssociationModelManager;
import model.GameCandidate;
import model.Student;
import model.lists.StudentList;

//Kateryna
public class VoteController
{
  @FXML private TextField titleField;
  @FXML private Button voteButton;

  @FXML private AssociationModelManager modelManager;
  public void initialize(AssociationModelManager modelManager)
  {
    this.modelManager = modelManager;
    updateTable();
  }

  public void updateTable()
  {

  }

  public void handleAction(ActionEvent e)
  {
  }
}
