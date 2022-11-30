package model;

import model.lists.*;

import java.io.Serializable;

//together
public class Association implements Serializable
{
  private GenreList genreList;
  private StudentList studentList;
  private Catalogue catalogue;
  private EventList eventList;
  private VotingList votingList;

  public Association()
  {
    this.studentList = new StudentList();
    this.genreList= new GenreList();
    this.catalogue = new Catalogue();
    this.eventList = new EventList();
    this.votingList = new VotingList();
  }


  public Catalogue getCatalogue()
  {
    return catalogue;
  }

  public EventList getEventList()
  {
    return eventList;
  }

  public GenreList getGenreList()
  {
    return genreList;
  }

  public StudentList getStudentList()
  {
    return studentList;
  }

  public VotingList getVotingList()
  {
    return votingList;
  }

  public void setCatalogue(Catalogue catalogue)
  {
    this.catalogue = catalogue;
  }

  public void setEventList(EventList eventList)
  {
    this.eventList = eventList;
  }

  public void setGenreList(GenreList genreList)
  {
    this.genreList = genreList;
  }

  public void setStudentList(StudentList studentList)
  {
    this.studentList = studentList;
  }

  public void setVotingList(VotingList votingList)
  {
    this.votingList = votingList;
  }

  public Genre getGenre(int index)
  {
    return genreList.getGenre(index);
  }

  public Student getStudent(int index)
  {
    return this.studentList.getStudent(index);
  }

  public BoardGame getBoardGame(int index)
  {
    return this.catalogue.getBoardGame(index);
  }

  public Event getEvent(int index)
  {
    return eventList.getEvent(index);
  }

  public GameCandidate getVote(int index)
  {
    return votingList.getVote(index);
  }

  public BoardGame getBoardGameByTitle(String title)
  {
    return catalogue.getBoardGameByTitle(title);
  }

  public Student getStudentByID(int ID)
  {
    return this.studentList.getStudentById(ID);
  }

  public Event getEventByTitle(String title)
  {
    return eventList.getEventByTitle(title);
  }

  public void addStudent(Student student)
  {
    this.studentList.addStudent(student);
  }

  public void addBoardGame(BoardGame boardGame)
  {
    this.catalogue.addBoardGame(boardGame);
  }

  public void addEvent(Event event)
  {
    this.eventList.addEvent(event);
  }

  public void removeGenre(int index)
  {
    this.genreList.removeGenre(index);
  }

  public void addVote(String titleOfGame)
  {
    GameCandidate candidateGame = new GameCandidate(titleOfGame);
  }

  public void removeBoardGame(int index)
  {
    catalogue.removeBoardGame(getBoardGame(index));
  }

  public void setGenre(Genre genre, int index)
  {
    genreList.setGenre(genre, index);
  }

  public void removeStudent(Student student)
  {
    this.studentList.removeStudent(student);
  }

  public void removeEvent(int index)
  {
    eventList.removeEvent(index);
  }
}