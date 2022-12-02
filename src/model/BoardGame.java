package model;

import model.lists.RatingList;

import java.io.Serializable;

//HUGO
public class BoardGame implements Serializable
{
  private String title;
  private int ownerID;
  private String description;
  private Genre genre;
  private RatingList ratings;
  private GameAction gameAction;

  public BoardGame(String title, int ownerID, String description, Genre genre)
  {
    this.title = title;
    this.ownerID = ownerID;
    this.description = description;
    this.genre = genre;
    gameAction = null;
    this.ratings = new RatingList();
  }

  public String getTitle()
  {
    return title;
  }

  public int getOwnerID()
  {
    return ownerID;
  }

  public String getDescription()
  {
    return description;
  }

  public Genre getGenre()
  {
    return genre;
  }

  public GameAction getStateOfGame()
  {
    return gameAction;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getStatus()
  {
    if(gameAction == null)
      return "None";
    return gameAction.toString();
  }
  public String getAverage()
  {
    return ratings.average();
  }

  public void setGenre(Genre genre)
  {
    this.genre = genre;
  }

  public void setOwnerID(int ownerID)
  {
    this.ownerID = ownerID;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public void borrow(int ID, MyDate startDate, MyDate endDate)
  {
    gameAction = new Borrow(ID, startDate, endDate);
  }

  public void reserve(int ID, MyDate startDate, MyDate endDate)
  {
    gameAction = new Reserve(ID, startDate, endDate);
  }

  public void removeBorrow()
  {
    gameAction = null;
  }

  public void removeReserve()
  {
    gameAction = null;
  }

  public void addRating(int rating)
  {
    ratings.addRating(rating);
  }

}
