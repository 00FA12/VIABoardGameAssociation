package model.lists;

import model.GameCandidate;

import java.io.Serializable;
import java.util.ArrayList;

//Seva & Hugo

/**
 * class describes the list of {@link GameCandidate} and stores votes for each game
 * @author Hugo Pennarubia, Sevastian Bahynskyi
 */
public class VotingList implements Serializable
{
  private ArrayList<GameCandidate> votes;

  /**
   * an empty constructor that creates an empty list
   */
  public VotingList()
  {
    this.votes = new ArrayList<>();
  }

  /**
   * add {@link GameCandidate} object to the end of the list
   * @param vote
   */
  public void addVote(GameCandidate vote)
  {
    this.votes.add(vote);
  }

  /**
   * make the list empty
   */
  public void clear()
  {
    votes.clear();
  }

  /**
   *
   * @param title
   * @return {@link GameCandidate} by introduced {@param title}
   */
  public GameCandidate getVoteByGameTitle(String title)
  {
    for (int i = 0; i < votes.size(); i++)
    {
      if (votes.get(i).getTitleOfGame().equals(title))
      {
        return votes.get(i);
      }
    }
    return null;
  }

  /**
   *
   * @return an array representation of list
   */
  public GameCandidate[] getVotingListToArray()
  {
    return this.votes.toArray(new GameCandidate[votes.size()]);
  }


  /**
   *
   * @return list
   */
  public ArrayList<GameCandidate> getVotes()
  {
    return this.votes;
  }


  /**
   *
   * @return string array which consist of game titles
   */
  public String[] getGameTitlesToArray()
  {
    ArrayList<String> titles = new ArrayList<>();

    for (int i = 0; i < votes.size(); i++)
    {
      titles.add(votes.get(i).getTitleOfGame());
    }
    return titles.toArray(new String[titles.size()]);
  }

  /**
   *
   * @return list size
   */
  public int getSize()
  {
    if(votes != null)
      return votes.size();
    else return -1;
  }


  /**
   *
   * @param index
   * @return {@link GameCandidate} object by index
   */
  public GameCandidate getVote(int index)
  {
    return votes.get(index);
  }

  /**
   *
   * @return String representation of the list
   */
  public String toString()
  {
    String temp = "";
    for (int i = 0; i < votes.size(); i++)
    {
      temp += votes.get(i) + "\n";
    }
    return temp;
  }
}
