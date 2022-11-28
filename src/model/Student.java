package model;

import java.io.Serializable;

//Sevastian
public class Student implements Serializable
{
  private String name;
  private int ID;
  private boolean isMember;

  public Student(String name, int ID)
  {
    this.ID = ID; // todo ID check like in GameCandidate
    this.name = name;
    this.isMember = false;
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

  public Student copy()
  {
    return new Student(name, ID);
  }

  public boolean equals(Object obj)
  {
    if((obj == null || obj.getClass() != getClass()))
      return false;

    Student student = (Student) obj;
    return student.ID == ID;
  }

  public String toString()
  {
    return String.format("Name: %s\nID: %d\nStatus: %s", name, ID, isMember ? "member" : "guest");
  }

}
