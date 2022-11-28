package model;

import model.lists.StudentList;

import java.io.Serializable;

//MICHAEL Unfinished
public abstract class GameAction implements Serializable
{
  private MyDate startDate, endDate;
  private Student student;

  public GameAction(int ID, MyDate startDate, MyDate endDate){
//find the student by static method. if student is not existent, create a new one
  }

  public Student getStudent()
  {
    return student.copy();
  }
  public MyDate getStartDate() {
      return startDate.copy();
  }
  public MyDate getEndDate() {
    return endDate.copy();
  }
}
