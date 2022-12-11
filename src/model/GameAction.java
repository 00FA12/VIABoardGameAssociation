package model;

import model.lists.Catalogue;

import java.io.Serializable;

//MICHAEL

/**
 * describes the game status, use polymorphism
 * @author Michael Leo, Sevastian Bahynskyi
 */
public abstract class GameAction implements Serializable
{
  /**
   * represents the start date and the end date of borrowing/reservation
   */
  private MyDate startDate, endDate;

  /**
   * the student who made the game action (borrowing/reservation)
   */
  private Student student;


  /**
   * initialize all fields
   * @param ID search the student in the association by ID
   * @param startDate
   * @param endDate
   * @throws NullPointerException if students is not listed in the association
   */
  public GameAction(int ID, MyDate startDate, MyDate endDate)
  {
    try
    {
      student = AssociationModelManager.getStudentList().getStudentById(ID);
    }
    catch (NullPointerException e)
    {
      throw new NullPointerException(
          "Student is not listed in the association");
    }

    this.startDate = startDate.copy();
    this.endDate = endDate.copy();
  }

  /**
   *
   * @return {@link Student}
   */
  public Student getStudent()
  {
    return this.student;
  }

  /**
   *
   * @return the start date
   */
  public MyDate getStartDate()
  {
    return startDate.copy();
  }

  /**
   *
   * @return the end date
   */
  public MyDate getEndDate()
  {
    return endDate.copy();
  }

  /**
   *
   * @return the String representation of game status
   */
  public abstract String toString();
}
