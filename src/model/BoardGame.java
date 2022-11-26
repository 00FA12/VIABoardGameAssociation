package model;

public class BoardGame
{
  private String title;
  private int ownerID;
  private String description;
  private GameAction gameAction;
  private RatingList ratings;
  private Genre genre;

  public BoardGame(String title, int ownerID, String description, Genre genre)
  {
    this.title = title;
    this.ownerID = ownerID;
    this.description = description;
    this.genre = genre;
  }

  public String getTitle()
  {
    return title;
  }

  public String getDescription()
  {
    return description;
  }

  public Genre getGenre()
  {
    return genre;
  }

  public GameAction getStatusOfGame()
  {
    return gameAction;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public void setOwnerID(int ownerID)
  {
    this.ownerID = ownerID;
  }

  public void borrow(int ID, MyDate startDate, MyDate endDate)
  {
    gameAction = new Borrow(ID, startDate, endDate);
  }

  public void reserve(int ID, MyDate startDate, MyDate endDate)
  {
    gameAction = new Reserve(ID, startDate, endDate);
  }

  public void removeBorrowing()
  {
    this.gameAction = null;
  }

  public void removeReservation()
  {
    this.gameAction = null;
  }
}
