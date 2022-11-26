package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Catalogue implements Serializable
{
  private ArrayList<BoardGame> catalogue;

  public Catalogue()
  {
    this.catalogue = new ArrayList<>();
  }

  public void addBoardGame(BoardGame boardGame)
  {
    this.catalogue.add(boardGame);
  }

  public BoardGame getBoardGame(int index)
  {
    return this.catalogue.get(index);
  }

  public void setBoardGame(int index, BoardGame boardGame)
  {
    this.catalogue.set(index, boardGame);
  }

  public BoardGame getBoardGameByTitle(String title)
  {
    for (int i = 0; i < catalogue.size(); i++)
    {
      if(catalogue.get(i).getTitle().equals(title))
        return catalogue.get(i);
    }
    return null;
  }

  public void removeBoardGame(int index)
  {
    this.catalogue.remove(index);
  }

  public String toString()
  {
    String str = "";
    for (int i = 0; i < catalogue.size() - 1; i++)
    {
      str += catalogue.get(i) + "\n";
    }
    str += catalogue.get(catalogue.size() - 1);
    return str;
  }
}
