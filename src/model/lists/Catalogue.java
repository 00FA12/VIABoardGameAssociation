package model.lists;

import model.BoardGame;

import java.io.Serializable;
import java.util.ArrayList;

//SEVA

/**
 * store all {@link BoardGame}
 * @author Sevastian Bahynskyi
 */
public class Catalogue implements Serializable
{
  /**
   * {@link ArrayList} of games
   */
  private ArrayList<BoardGame> catalogue;

  /**
   * default constructor
   */
  public Catalogue()
  {
    this.catalogue = new ArrayList<>();
  }

  /**
   * add board game to the catalogue
   * @param boardGame
   */
  public void addBoardGame(BoardGame boardGame)
  {
    this.catalogue.add(boardGame);
  }

  /**
   * 
   * @param index
   * @return {@link BoardGame} by index
   */
  public BoardGame getBoardGame(int index)
  {
    return this.catalogue.get(index);
  }

  /**
   * 
   * @param title
   * @return {@link BoardGame} by it's title if there is no such returns null
   */
  public BoardGame getBoardGameByTitle(String title)
  {
    for (int i = 0; i < catalogue.size(); i++)
    {
      if (catalogue.get(i).getTitle().equals(title))
        return catalogue.get(i);
    }
    return null;
  }

  /**
   * @param title
   * @return index of the {@link BoardGame} or -1 if there is no {@link BoardGame} with such title
   */
  public int getIndexOfBoardGameByTitle(String title)
  {
    for (int i = 0; i < catalogue.size(); i++)
    {
      if (catalogue.get(i).getTitle().equals(title))
        return i;
    }
    return -1;
  }

  /**
   *
   * @return the size of catalogue
   */
  public int getSize()
  {
    return catalogue.size();
  }

  /**
   *
   * put {@link BoardGame} by index
   * @param index
   * @param boardGame
   */
  public void setBoardGame(int index, BoardGame boardGame)
  {
    this.catalogue.set(index, boardGame);
  }

  /**
   * remove specific {@link BoardGame} by it's object
   * @param boardGame
   */
  public void removeBoardGame(BoardGame boardGame)
  {
      this.catalogue.remove(boardGame);
  }

  /**
   * remove {@link BoardGame} by index
   * @param index
   */
  public void removeBoardGame(int index)
  {
    this.catalogue.remove(index);
  }

  /**
   *
   * @return the String representation of whole Catalogue
   */
  public String toString()
  {
    String str = "";
    for (int i = 0; i < catalogue.size(); i++)
    {
      str += catalogue.get(i) + "\n";
    }
    return str;
  }
}
