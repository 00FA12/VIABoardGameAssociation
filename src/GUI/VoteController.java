package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Association;
import model.AssociationModelManager;
import model.GameCandidate;
import model.Student;
import model.lists.StudentList;
import model.lists.VotingList;

//Kateryna
public class VoteController

{
  @FXML
  private TextField titleField;
  @FXML
  private TableView<GameCandidate> votesTable;

  @FXML
  private TableColumn<GameCandidate, String> titleColumn;
  @FXML
  private TableColumn<GameCandidate, String> votesColumn;
  @FXML
  private Button voteButton;

  public void initialize()
  {
    titleColumn.setCellValueFactory(
            new PropertyValueFactory<>("titleOfGame"));
    votesColumn.setCellValueFactory(
            new PropertyValueFactory<>("numberOfVotes"));

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
  }

  public void handleAction(ActionEvent e)
  {
    if (e.getSource() == voteButton)
    {

        String titleOfGame = titleField.getText();


        Association association = AssociationModelManager.getAssociation();
        association.addVote(titleOfGame);
        AssociationModelManager.saveAssociation(association);
        updateTable();
    }
  }
}
