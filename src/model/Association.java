package model;

import model.lists.*;

import java.io.Serializable;



/**
 * class stores all list, file works with association and GUI call all methods from here
 * while association class calls method of lists
 * @author Michael Leo, Hugo Pennarubia, Kateryna Sokolove, Sevatian Bahynskyi
 */
public class Association implements Serializable
{
  /**
   * genre list
   */
  private GenreList genreList;

  /**
   * student list
   */
  private StudentList studentList;

  /**
   * catalogue
   */
  private Catalogue catalogue;

  /**
   * event list
   */
  private EventList eventList;

  /**
   * list of votes
   */
  private VotingList votingList;

  /**
   * creates an association, creates all list
   */
  public Association()
  {
    this.studentList = new StudentList();
    this.genreList = new GenreList();
    this.catalogue = new Catalogue();
    this.eventList = new EventList();
    this.votingList = new VotingList();
  }

  /**
   *
   * @return catalogue
   */
  public Catalogue getCatalogue()
  {
    return catalogue;
  }

  /**
   *
   * @return event list
   */
  public EventList getEventList()
  {
    return eventList;
  }

  /**
   *
   * @return genre list
   */
  public GenreList getGenreList()
  {
    return genreList;
  }

  /**
   *
   * @return student list
   */
  public StudentList getStudentList()
  {
    return studentList;
  }

  /**
   *
   * @return list of votes
   */
  public VotingList getVotingList()
  {
    return votingList;
  }

  /**
   * set catalogue
   * @param catalogue
   */
  public void setCatalogue(Catalogue catalogue)
  {
    this.catalogue = catalogue;
  }

  /**
   * set event list
   * @param eventList
   */
  public void setEventList(EventList eventList)
  {
    this.eventList = eventList;
  }

  /**
   * initialize the list
   * @param genreList
   */
  public void setGenreList(GenreList genreList)
  {
    this.genreList = genreList;
  }

  /**
   * initialize the student list
   * @param studentList
   */
  public void setStudentList(StudentList studentList)
  {
    this.studentList = studentList;
  }

  /**
   * initialize voting list
   * @param votingList
   */
  public void setVotingList(VotingList votingList)
  {
    this.votingList = votingList;
  }

  /**
   *
   * @param index
   * @return genre by index
   */
  public Genre getGenre(int index)
  {
    return genreList.getGenre(index);
  }

  /**
   *
   * @param index
   * @return {@link Student} object by index
   */
  public Student getStudent(int index)
  {
    return this.studentList.getStudent(index);
  }

  /**
   *
   * @param index
   * @return {@link BoardGame} by index
   */
  public BoardGame getBoardGame(int index)
  {
    return this.catalogue.getBoardGame(index);
  }

  /**
   *
   * @param index
   * @return {@link Event} by index
   */
  public Event getEvent(int index)
  {
    return eventList.getEvent(index);
  }

  /**
   *
   * @param index
   * @return {@link GameCandidate} by index
   */
  public GameCandidate getVote(int index)
  {
    return votingList.getVote(index);
  }

  /**
   *
   * @param title
   * @return {@link BoardGame} by title
   */
  public BoardGame getBoardGameByTitle(String title)
  {
    return catalogue.getBoardGameByTitle(title);
  }

  /**
   *
   * @param ID
   * @return {@link Student} by ID
   */
  public Student getStudentByID(int ID)
  {
    return this.studentList.getStudentById(ID);
  }

  /**
   *
   * @param title
   * @return {@link Event} by title
   */
  public Event getEventByTitle(String title)
  {
    return eventList.getEventByTitle(title);
  }

  /**
   * adds {@link Student} to the end of the list
   * @param student
   */
  public void addStudent(Student student)
  {
    this.studentList.addStudent(student);
  }

  /**
   * adds {@link BoardGame} to the end of the catalogue
   * @param boardGame
   */
  public void addBoardGame(BoardGame boardGame)
  {
    this.catalogue.addBoardGame(boardGame);
  }

  /**
   * adds {@link BoardGame} to the end of the list
   * @param event
   */
  public void addEvent(Event event)
  {
    this.eventList.addEvent(event);
  }

  /**
   * removes {@link Genre} by index
   * @param index
   */
  public void removeGenre(int index)
  {
    this.genreList.removeGenre(index);
  }

  /**
   * creates new {@link GameCandidate} by title and add it to the end of the list
   * @param titleOfGame
   */
  public void addVote(String titleOfGame)
  {
    votingList.addVote(new GameCandidate(titleOfGame));
  }

  /**
   * removes {@link BoardGame} by index
   * @param index
   */
  public void removeBoardGame(int index)
  {
    catalogue.removeBoardGame(index);
  }

  /**
   * set {@link Genre} by index
   * @param genre
   * @param index
   */
  public void setGenre(Genre genre, int index)
  {
    genreList.setGenre(genre, index);
  }

  /**
   * removes {@link Student} from the list by {@link Student} object
   * @param student
   */
  public void removeStudent(Student student)
  {
    this.studentList.removeStudent(student);
  }
  public void removeStudent(int index)
  {
    this.studentList.removeStudent(index);
  }

  public void removeEvent(int index)
  {
    eventList.removeEvent(index);
  }
}