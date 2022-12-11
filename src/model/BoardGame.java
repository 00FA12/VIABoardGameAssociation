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
  private RatingList ratingList;
  private GameAction gameAction;

  private String status = getStatusToString();

  /**
   *
   * @param title
   * @param ownerID
   * @param description
   * @param genre
   */
  public BoardGame(String title, int ownerID, String description, Genre genre)
  {
    if(title.isEmpty() || ownerID < 100000 || ownerID > 999999 || genre == null)
      throw new IllegalArgumentException("Some field is empty or has wrong data.");

    for (Character ch:title.toCharArray())
    {
      if(!Character.isLetterOrDigit(ch) && ch != ' ')
        throw new IllegalArgumentException("Field \"Title\" can only consist of digits and letters!");
    }

    this.title = title;
    this.ownerID = ownerID;
    this.description = description;
    this.genre = genre;
    gameAction = null;
    this.ratingList = new RatingList();
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

  public void setStatusOfGame(GameAction status)
  {
    this.gameAction = status;
    this.status = getStatusToString();
  }

  public GameAction getStatusOfGame()
  {
    return gameAction;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getStatusToString()
  {
    if(gameAction == null)
      return "None";
    this.status = gameAction.toString();
    return status;
  }
  public String getAverage()
  {
    return ratingList.averageToString();
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
    status = getStatusToString();
  }

  /**
   * sets {@link GameAction} to {@link Reserve} by polymorphism
   * marks game as reserved
   * @param ID
   * @param startDate
   * @param endDate
   */
  public void reserve(int ID, MyDate startDate, MyDate endDate)
  {
    gameAction = new Reserve(ID, startDate, endDate);
    status = getStatusToString();
  }


  /**
   * sets {@link GameCandidate} to null which means that BoardGame is available
   */
  public void returnBoardGame()
  {
    gameAction = null;
    status = "None";
  }


  /**
   *
   * @return {@link RatingList}
   */
  public RatingList getRatingList()
  {
    return ratingList;
  }


  /**
   *
   * @param ratingList
   */
  public void setRatingList(RatingList ratingList)
  {
    this.ratingList = ratingList;
  }

  /**
   * add rating to the {@link RatingList}
   * @param rating
   */
  public void addRating(int rating)
  {
    ratingList.addRating(rating);
  }

}
