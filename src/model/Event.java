package model;

import model.lists.StudentList;

import java.io.Serializable;
import java.util.List;

//Kateryna

/**
 * this class describes events of association
 * @author Kateryna Sokolova
 */
public class Event implements Serializable
{
  /**
   * event name
   */
  private String title;
  /**
   * event description, for some details
   */
  private String description;
  /**
   * the date when event start's
   */
  private MyDate date;
  /**
   * the list of attenders that are invited on the event
   */
  private StudentList attenders;

  /**
   * constructor which validate {@link Event#title} and initialize
   * {@link Event#date} and {@link Event#description} if didn't find any mistakes. List of event attenders will be created
   * @param title
   * @param description
   * @param date
   */
  public Event(String title, String description, MyDate date)
  {
    if(title.isEmpty() || date == null || description.isEmpty())
    {
      throw new IllegalArgumentException("Some field has wrong data or it is empty");
    }

    for (Character ch:title.toCharArray())
    {
      if(!Character.isLetterOrDigit(ch) && ch != ' ')
        throw new IllegalArgumentException("Field \"Title\" can only consist of digits and letters!");
    }

    this.title = title;
    this.description = description;
    this.date = date;
    attenders = new StudentList();
  }

  /**
   *
   * @return {@link Event#title}
   */
  public String getTitle()
  {
    return title;
  }

  /**
   *
   * @return {@link Event#description}
   */
  public String getDescription()
  {
    return description;
  }

  /**
   *
   * @return {@link Event#date}
   */
  public MyDate getDate()
  {
    return date;
  }

  /**
   *
   * @param title
   */
  public void setTitle(String title)
  {
    this.title = title;
  }

  /**
   * adds an attender to list of event attenders
   * @param student
   */
  public void addAttender(Student student)
  {
    attenders.addStudentAsAttender(student);
  }

  /**
   * initialize attender list with {@param students}
   */
  public void addAttenderList(List<Student> students)
  {
    for (Student student:students)
    {
      attenders.addStudentAsAttender(student);
    }
  }

  /**
   *
   * @param description
   */
  public void setDescription(String description)
  {
    this.description = description;
  }

  /**
   *
   * @param date
   */
  public void setDate(MyDate date)
  {
    this.date = date;
  }

  /**
   *
   * @return {@link Event#attenders}
   */
  public StudentList getAttenders()
  {
    return attenders;
  }

  /**
   *
   * @return String representation of event information
   */
  public String toString()
  {
    return "Title: " + title + "\nDescription: " + description + "\nDate: "
        + date + "\nAttenders: " + attenders;
  }
}
