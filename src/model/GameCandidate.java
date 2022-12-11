package model;

import model.lists.VotingList;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Kateryna Sokolova, Sevastian Bahynskyi
 */
public class GameCandidate implements Serializable
{
  /**
   * represents the number of votes for the game
   */
  private int numberOfVotes;

  /**
   * represents the title of the game candidate
   */
  private String titleOfGame;

  /**
   * initialize the game {@link GameCandidate#titleOfGame} and set {@link GameCandidate#numberOfVotes} to 0
   * @param titleOfGame
   */
  public GameCandidate(String titleOfGame)
  {
    this.titleOfGame = titleOfGame;
    numberOfVotes = 0;
  }

  /**
   * increment {@link GameCandidate#numberOfVotes}
   */
  public void vote()
  {
    numberOfVotes++;
  }


  /**
   * set {@link GameCandidate#titleOfGame}
   * @param title
   */
  public void setTitleOfGame(String title)
  {
    titleOfGame = title;
  }

  /**
   * get title
   * @return {@link GameCandidate#titleOfGame}
   */
  public String getTitleOfGame()
  {
    return titleOfGame;
  }

  /**
   *
   * @return String representation of game candidate and number of votes
   */
  public String toString()
  {
    return "Number of votes: " + numberOfVotes + "\t Title of game: "
        + titleOfGame;
  }

}
