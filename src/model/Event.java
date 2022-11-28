package model;

import model.lists.StudentList;

import java.io.Serializable;
import java.util.ArrayList;

public class Event implements Serializable
{
  private String title;
  private String description;
  private MyDate date;
  private StudentList attenders;

  public Event (String title, String description, MyDate date)
  {
    this.title=title;
    this.description=description;
    this.date=date;
    attenders = new StudentList();
  }
  public String getTitle()
  {
    return title;
  }
  public String getDescription()
  {
    return description;
  }
  public MyDate getDate()
  {
    return date;
  }
  public void setTitle(String title)
  {
    this.title=title;
  }
  public void setDescription(String description)
  {
    this.description=description;
  }
  public void setDate(MyDate date)
  {
    this.date=date;
  }
public StudentList getAttenders()
{
  ArrayList<Student> stList = new ArrayList<Student>();
  for (int i = 0; i < attenders.size(); i++)
  {
    stList.add(attenders.getStudent(i));
  }

}
public String toSting()
{

}
}
