package GUI;

import com.gluonhq.charm.glisten.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class VoteController
{
  @FXML private Button voteButton;
  @FXML private AutoCompleteTextField titleAutoCompleteField;

  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == voteButton)
    {

    }
  }
}
