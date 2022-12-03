package model;

import model.lists.VotingList;

import java.io.Serializable;
import java.util.ArrayList;

//Kateryna
public class GameCandidate implements Serializable
{
  private int numberOfVotes;
  private String titleOfGame;

  public GameCandidate(String titleOfGame)
  {
    this.titleOfGame = titleOfGame;
    numberOfVotes = 0;
  }

  public String getNumberOfVotesToString()
  {
    return Integer.toString(numberOfVotes);
  }

  public void vote()
  {
    numberOfVotes++;
  }

  public void setTitleOfGame(String title)
  {
    titleOfGame = title;
  }

  public String getTitleOfGame()
  {
    return titleOfGame;
  }

  public String toString()
  {
    return "Number of votes: " + numberOfVotes + "\t Title of game: "
        + titleOfGame;
  }

}
