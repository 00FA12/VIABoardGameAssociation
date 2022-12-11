package model;

import model.lists.StudentList;

import java.io.Serializable;


/**
 * describes the student
 * @author Sevastian Bahynskiy
 */
public class Student implements Serializable
{
  /**
   * represents the name of the user, including his first name and second name (Name can consist of middle names, max possible numbers of sub-names is 4)
   */
  private String name;

  /**
   * should be unique
   */
  private int ID;

  /**
   * represents if student is member or user of the association
   */
  private boolean isMember;


  /**
   * first class constructor, validate both parameters and throw an exception if found a mistake, if there is no mistake, initialize the class
   * @param name
   * @param ID
   * @throws IllegalArgumentException for all possible mistakes
   */
  public Student(String name, int ID)
  {
    try
    {
      String[] splitString = name.split(" ");
      if(splitString.length < 2 || splitString.length > 4)
        throw new IllegalArgumentException("You can ony have a name of 2 to 4 words.");

      for (int i = 0; i < splitString.length; i++)
      {
        if (!Character.isUpperCase(splitString[i].charAt(0)))
          throw new IllegalArgumentException("Both name and surname should start with capital letter");
        for (int j = 1; j < splitString[i].length(); j++)
        {

          if (!Character.isLowerCase(splitString[i].charAt(j)))
            throw new IllegalArgumentException("Only the first letters of each word have to be in upper case, the rest have to be in lowe case");
        }
      }
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException("Both name and surname should start with capital letter");
    }

     if (ID < 100000 || ID > 999999)
     {
       throw new IllegalArgumentException("Wrong ID format");
     }
      this.ID = ID;
      this.name = name;
      this.isMember = false;

  }
  /**
   * second class constructor, validate both name and ID, initialize isMember and throw an exception if found a mistake or initialize class fields if not
   * @param name
   * @param ID
   * @param isMember
   * @throws IllegalArgumentException for all possible mistakes
   */
  public Student(String name, int ID, boolean isMember)
  {
    try
    {
      String[] splitString = name.split(" ");
      if(splitString.length < 2 || splitString.length > 4)
        throw new IllegalArgumentException("We are so restrictive that you can ony have a name of 2 to 4 words, we don't really care about your name");

      for (int i = 0; i < splitString.length; i++)
      {
        if (!Character.isUpperCase(splitString[i].charAt(0)))
          throw new IllegalArgumentException("Both name and surname should start with capital letter");
        for (int j = 1; j < splitString[i].length(); j++)
        {

          if (!Character.isLowerCase(splitString[i].charAt(j)))
            throw new IllegalArgumentException("Only the first letters of each word have to be in upper case, the rest have to be in lowe case");
        }
      } // bad HUGO, angry, furious, toxic, he is forcing me to cry
    } // I wish I made him cry
    catch (Exception e)
    {
      throw new IllegalArgumentException("Both name and surname should start with capital letter");
    }


    if (ID < 100000 || ID > 999999)
    {
      throw new IllegalArgumentException("Wrong ID format");
    }
    this.ID = ID;
    this.name = name;
    this.isMember = isMember;
  }

  /**
   * @return {@link Student#name}
   */
  public String getName()
  {
    return this.name;
  }

  /**
   * @return {@link Student#ID}
   */
  public int getID()
  {
    return ID;
  }

  /**
   * @param ID
   */
  public void setID(int ID)
  {
    this.ID = ID;
  }

  /**
   *
   * @param name
   */
  public void setName(String name)
  {
    this.name = name;
  }

  /**
   * {@link Student#isMember} becomes true
   */
  public void makeMember()
  {
    this.isMember = true;
  }

  /**
   * {@link Student#isMember} becomes false
   */
  public void makeGuest()
  {
    this.isMember = false;
  }

  /**
   *
   * @return the String value of {@link Student#isMember}
   */
  public String getStatus()
  {
    return isMember ? "Member" : "Guest";
  }

  /**
   *
   * @return the copy object
   */
  public Student copy()
  {
    return new Student(name, ID, isMember);
  }

  /**
   *
   * @param obj
   * @return true if {@param obj} equals to Student object
   */
  public boolean equals(Object obj)
  {
    if ((obj == null || obj.getClass() != getClass()))
      return false;

    Student student = (Student) obj;
    return student.ID == ID;
  }

  /**
   *
   * @return the String version of class
   */
  public String toString()
  {
    return name + "," + ID;
  }

}
