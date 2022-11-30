package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import model.AssociationModelManager;
import model.GameCandidate;

//Kateryna
public class VoteController
{
  @FXML private TextField titleField;
  @FXML private TableView<GameCandidate> votesTable;
  @FXML private TextField titleAutoCompleteField;
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
    if (e.getSource() == votesTable)
    {

    }
    else if (e.getSource() == titleAutoCompleteField)
    {

    }
    else if (e.getSource() == voteButton)
    {

    }
  }
}
