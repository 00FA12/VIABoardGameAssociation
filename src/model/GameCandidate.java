package model;

import model.lists.VotingList;

import java.io.Serializable;

//Kateryna
public class GameCandidate implements Serializable
{
  private int numberOfVotes;
  private String titleOfGame;

  public GameCandidate(String titleOfGame)
  {
    VotingList votes = AssociationModelManager.getVotingList();
    boolean flag = false;
    if(votes != null)
    {
      for (int i = 0; i < votes.getSize(); i++)
      {
        if (votes.getVote(i).getTitleOfGame().equals(titleOfGame))
        {
          vote();
          flag = true;
          break;
        }
      }
      if(!flag)
      {
        numberOfVotes = 1;
        this.titleOfGame = titleOfGame;
      }
    }
    else
    {
        numberOfVotes = 1;
        this.titleOfGame = titleOfGame;
    }
  }

  public void vote()
  {
    numberOfVotes++;
  }


  public void setTitleOfGame(String title)
  {
    titleOfGame=title;
  }
  public String getTitleOfGame()
  {
    return titleOfGame;
  }

  public String toString()
  {
    return "Number of votes: " + numberOfVotes+ "\n Title of game: "+ titleOfGame;
  }

}
