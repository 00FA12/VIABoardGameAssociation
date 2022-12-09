package model;

import model.lists.StudentList;

import java.io.Serializable;


//Sevastian
public class Student implements Serializable
{
  private String name;
  private int ID;
  private boolean isMember;


  /**
   * first class constructor, validate both parameters and throw an exception if found a mistake
   * @param name which include first name and second name (Name can consist of middle names, max possible numbers of sub-names is 4)
   * @param ID should be unique
   */
  public Student(String name, int ID)
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
      this.isMember = false;

  }

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

  public String getName()
  {
    return this.name;
  }

  public int getID()
  {
    return ID;
  }

  public void setID(int ID)
  {
    this.ID = ID;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void makeMember()
  {
    this.isMember = true;
  }

  public void makeGuest()
  {
    this.isMember = false;
  }

  public String getStatus()
  {
    return isMember ? "Member" : "Guest";
  }

  public Student copy()
  {
    return new Student(name, ID, isMember);
  }

  public boolean equals(Object obj)
  {
    if ((obj == null || obj.getClass() != getClass()))
      return false;

    Student student = (Student) obj;
    return student.ID == ID;
  }

  public String toString()
  {
    return name + "," + ID;
  }

}
