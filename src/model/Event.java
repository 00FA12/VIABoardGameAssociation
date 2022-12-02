package model;

import model.lists.StudentList;
import org.controlsfx.control.CheckComboBox;

import java.io.Serializable;
import java.util.List;

//Kateryna
public class Event implements Serializable
{
  private String title;
  private String description;
  private MyDate date;
  private StudentList attenders;

  public Event(String title, String description, MyDate date)
  {
    if(title.isEmpty() || date == null || description.isEmpty())
    {
      throw new IllegalArgumentException("Some field has wrong data or it is empty");
    }

    for (Character ch:title.toCharArray())
    {
      if(!Character.isLetterOrDigit(ch))
        throw new IllegalArgumentException("Field \"Title\" can only consist of digits and letters!");
    }

    this.title = title;
    this.description = description;
    this.date = date;
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
    this.title = title;
  }

  public void addAttender(Student student)
  {
    attenders.addStudent(student);
  }

  public void addAttenderList(List<Student> students)
  {
    for (Student student:students)
    {
      attenders.addStudent(student);
    }
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public void setDate(MyDate date)
  {
    this.date = date;
  }

  public StudentList getAttenders()
  {
    return attenders;
  }

  public String toSting()
  {
    return "Title: " + title + "\nDescription: " + description + "\nDate: "
        + date + "\nAttenders: " + attenders;
  }
}
