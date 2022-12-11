package model;

import model.lists.RatingList;

import java.io.Serializable;

//HUGO

/**
 * describes the board game
 * @author Hugo Madrid Peñarrubia
 */

public class BoardGame implements Serializable
{
  /**
   * represents the title of the game
   */
  private String title;

  /**
   * represents the ID of the proprietary of the game. It has to be unique
   */
  private int ownerID;

  /**
   * a brief description of the board game
   */
  private String description;

  /**
   * shows the genre to which the board game belongs. It has to be unique
   */
  private Genre genre;

  /**
   * {@link java.util.ArrayList} that will store the ratings that the users give the board game
   */
  private RatingList ratingList;

  /**
   * it determines if the book is either free, borrowed or reserved
   */
  private GameAction gameAction;

  /**
   * it will show if the game is either free, borrowed or reserved
   */
  private String status = getStatusToString();

  /**
   * unique class constructor
   * {@link BoardGame#ownerID} has to have 6 digits. If it doesn't match the requirement throws an exception
   * both the {@link BoardGame#title} and the {@link BoardGame#description} fields only can contain spaces and letters. If the provided title doesn't match the requirements, an exception is throw
   * {@link BoardGame#genre} will be selected from a list of already settled genres
   * {@link BoardGame#gameAction} will be set to null
   * a new {@link RatingList} will be created
   * @param title
   * @param ownerID
   * @param description
   * @param genre
   * @throws IllegalArgumentException for all possible mistakes
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

  /**
   * @return {@link BoardGame#title}
   */
  public String getTitle()
  {
    return title;
  }

  /**
   * @return {@link BoardGame#ownerID}
   */
  public int getOwnerID()
  {
    return ownerID;
  }

  /**
   * @return {@link BoardGame#description}
   */
  public String getDescription()
  {
    return description;
  }

  /**
   * @return {@link BoardGame#genre}
   */
  public Genre getGenre()
  {
    return genre;
  }

  /**
   * {@link BoardGame#gameAction} = @param status
   * {@link BoardGame#status} = {@link BoardGame#getStatusOfGame()}
   */
  public void setStatusOfGame(GameAction status)
  {
    this.gameAction = status;
    this.status = getStatusToString();
  }

  /**
   * @return {@link BoardGame#gameAction}
   */
  public GameAction getStatusOfGame()
  {
    return gameAction;
  }

  /**
   * {@link BoardGame#title} = @param title
   */
  public void setTitle(String title)
  {
    this.title = title;
  }

  /**
   * get the status as a String
   * @return String as either borrow, reserved or none
   */
  public String getStatusToString()
  {
    if(gameAction == null)
      return "None";
    this.status = gameAction.toString();
    return status;
  }

  /**
   * @return the average of all ratings of the game
   */
  public String getAverage()
  {
    return ratingList.averageToString();
  }

  /**
   * @param genre {@link BoardGame#genre}
   */
  public void setGenre(Genre genre)
  {
    this.genre = genre;
  }

  /**
   * @param description {@link BoardGame#description}
   */
  public void setDescription(String description)
  {
    this.description = description;
  }

  /**
   * sets {@link GameAction} to {@link Borrow} by polymorphism
   * @param ID
   * @param startDate
   * @param endDate
   */
  //I want to kill Michael. Damn, he's never here. He's the real rat.
  //#EasterEgg
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
