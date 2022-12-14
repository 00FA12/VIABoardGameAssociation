package GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import model.*;
import model.lists.VotingList;
import parser.ParserException;

import java.io.IOException;
import java.util.List;

//Kateryna
/**
 * A class that controls the Vote tab in the GUI. It is responsible for initializing, updating the GUI and handling the input.
 * @author Kateryna Sokolova, Sevastian Bahynskyi (logic for handling user input)
 * @version 1.0
 */
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

  @FXML private HBox HBoxField;
  @FXML
  private Button voteButton;

  private AutoCompletionTextField autoCompletionTextField = new AutoCompletionTextField();

  private MyTableListener tableListener;

  /**
   * A nested class that notifies us of the changes to the GameCandidate, implements ChangeListener.
   */
  private class MyTableListener implements ChangeListener<GameCandidate>
  {
    public void changed(ObservableValue<? extends GameCandidate> gameCandidate, GameCandidate oldVote, GameCandidate newVote)
    {
      GameCandidate gameCandidateTemp = votesTable.getSelectionModel().getSelectedItem();

      if (gameCandidateTemp != null)
      {
          autoCompletionTextField.setText(gameCandidateTemp.getTitleOfGame());
      }
    }
  }

  /**
   * Method that is invoked at the start of the application and sets up the view.
   */
  public void initialize()
  {
    tableListener = new MyTableListener();
    votesTable.getSelectionModel().selectedItemProperty().addListener(tableListener);

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
    String str = titleField.getText();
    autoCompletionTextField.setText(str);
    titleField.setManaged(false);
    HBoxField.getChildren().add(autoCompletionTextField);



    votesTable.getSelectionModel().selectFirst();
    updateTable();
  }

  /**
   * Method that updates the table that contains the votes.
   */
  public void updateTable()
  {
    int indexSelected = votesTable.getSelectionModel().getSelectedIndex();
    if (indexSelected == -1)
      indexSelected = 0;


    VotingList votingList = AssociationModelManager.getAssociation().getVotingList();
    votesTable.getItems().clear();

    for (int i = 0; i < votingList.getSize(); i++)
    {
      GameCandidate voting = votingList.getVote(i);
      votesTable.getItems().add(voting);
    }

    String[] titles = AssociationModelManager.getVotingList().getGameTitlesToArray();
    autoCompletionTextField.getEntries().addAll(List.of(titles));


    votesTable.getSelectionModel().select(indexSelected);

  }

  /**
   * Method that is invoked when user interacts with the application, handles the actions of the user.
   * @param e An action done by user. In our case, the button pressed.
   */
  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == voteButton)
    {

        String titleOfGame = autoCompletionTextField.getText();
        if(titleOfGame.isEmpty())
          return;


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
