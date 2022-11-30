package model;

import java.io.Serializable;

//MICHAEL
public abstract class GameAction implements Serializable
{
  private MyDate startDate, endDate;
  private Student student;

  public GameAction(int ID, MyDate startDate, MyDate endDate)
  {
    //find the student by static method. if student is not existent, create a new one
    try
    {
      student = AssociationModelManager.getStudentList().getStudentById(ID)
          .copy();
    }
    catch (NullPointerException e)
    {
      throw new NullPointerException(
          "Student is not listed in the association");
    }

    this.startDate = startDate.copy();
    this.endDate = endDate.copy();
  }

  public Student getStudent()
  {
    return student.copy();
  }

  public MyDate getStartDate()
  {
    return startDate.copy();
  }

  public MyDate getEndDate()
  {
    return endDate.copy();
  }
}
