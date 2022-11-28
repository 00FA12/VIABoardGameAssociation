package model.lists;

import model.BoardGame;

import java.util.ArrayList;

public class Catalogue
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

  public BoardGame getBoardGameByTitle(String title)
  {
    for (int i = 0; i < catalogue.size(); i++)
    {
      if(catalogue.get(i).getTitle().equals(title))
        return catalogue.get(i);
    }
    return null;
  }

  /**
   *
   * @param title
   * @return -1 if there is no BoardGame with such title
   */
  public int getIndexOfBoardGameByTitle(String title)
  {
    for (int i = 0; i < catalogue.size(); i++)
    {
      if(catalogue.get(i).getTitle().equals(title))
        return i;
    }
    return -1;
  }

  public void setBoardGame(int index, BoardGame boardGame)
  {
    this.catalogue.set(index, boardGame);
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
