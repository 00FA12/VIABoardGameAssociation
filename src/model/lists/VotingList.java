package model.lists;

import model.GameCandidate;

import java.io.Serializable;
import java.util.ArrayList;

//Seva & Hugo
public class VotingList implements Serializable
{
  private ArrayList<GameCandidate> votes;

  public void addVote(GameCandidate vote)
  {
    this.votes.add(vote);
  }

  public void clear()
  {
    votes.clear();
  }

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

  public int getSize()
  {
    return votes.size();
  }

  public GameCandidate getVote(int index)
  {
    return votes.get(index);
  }

  public String toString()
  {
    String temp = "";
    for (int i = 0; i < votes.size(); i++)
    {
      temp+= "Rating number " + i + " is " + votes.get(i) + "\n";
    }
    return temp;
  }
}
