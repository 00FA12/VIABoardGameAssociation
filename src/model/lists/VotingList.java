package model.lists;

import model.CandidateGame;

import java.util.ArrayList;
//Seva & Hugo
public class VotingList
{
  private ArrayList<CandidateGame> votes;

  public void addVoid(CandidateGame vote)
  {
    this.votes.add(vote);
  }

  public void clear()
  {
    votes.clear();
  }

  public CandidateGame getVoteByGameTitle(String title)
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

  public CandidateGame getVote(int index)
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
