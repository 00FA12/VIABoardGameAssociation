package GUI;

import com.jfoenix.controls.JFXAutoCompletePopup;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import model.Association;
import model.AssociationModelManager;
import model.GameCandidate;
import model.Student;
import model.lists.StudentList;
import model.lists.VotingList;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

//Kateryna
public class VoteController implements Initializable

{
  @FXML
  private TextField titleField;
  @FXML
  private TableView<GameCandidate> votesTable;

  @FXML
  private TableColumn<GameCandidate, String> titleColumn;
  @FXML
  private TableColumn<GameCandidate, String> votesColumn;

  @FXML private HBox HBoxField;
  private AutoCompletionBinding autoCompletionBinding;
  @FXML
  private Button voteButton;

  private AutoCompletionTextField autoCompletionTextField;

  @Override
  public void initialize(URL location, ResourceBundle resources)
  {
    titleColumn.setCellValueFactory(
            new PropertyValueFactory<>("titleOfGame"));
    votesColumn.setCellValueFactory(
            new PropertyValueFactory<>("numberOfVotesToString"));

    String[] titles = AssociationModelManager.getVotingList().getGameTitlesToArray();

    autoCompletionTextField = new AutoCompletionTextField();
    autoCompletionTextField.getEntries().addAll(List.of(titles));
    double height = titleField.getPrefHeight();
    double width = titleField.getPrefWidth();
    autoCompletionTextField.setPrefWidth(width);
    autoCompletionTextField.setPrefHeight(height);
    autoCompletionTextField.setLayoutY(titleField.getLayoutY());
    autoCompletionTextField.setLayoutX(titleField.getLayoutX());
    titleField.setManaged(false);
    HBoxField.getChildren().add(autoCompletionTextField);

    updateTable();
  }

  public void updateTable()
  {
    VotingList votingList = AssociationModelManager.getAssociation().getVotingList();
    votesTable.getItems().clear();

    for (int i = 0; i < votingList.getSize(); i++)
    {
      GameCandidate voting = votingList.getVote(i);
      votesTable.getItems().add(voting);
    }

    String[] titles = AssociationModelManager.getVotingList().getGameTitlesToArray();
    autoCompletionTextField.getEntries().addAll(List.of(titles));

  }

  public void handleAction(ActionEvent e)
  {
    if (e.getSource() == voteButton)
    {

        String titleOfGame = autoCompletionTextField.getText();


        Association association = AssociationModelManager.getAssociation();
        VotingList votingList = association.getVotingList();
        boolean hasTitle = false;
        for (int i = 0; i < votingList.getSize(); i++)
        {
          if(votingList.getVote(i).getTitleOfGame().equals(titleOfGame))
          {
            votingList.getVote(i).vote();
            hasTitle = true;
            break;
          }
        }
        if(!hasTitle)
          association.addVote(titleOfGame);
        AssociationModelManager.saveAssociation(association);
        updateTable();
    }
  }


}
