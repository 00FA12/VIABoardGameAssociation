package model;

import model.lists.StudentList;
import model.lists.VotingList;

import java.io.Serializable;

//Sevastian
public class Student implements Serializable
{
  private String name;
  private int ID;
  private boolean isMember;

  public Student(String name, int ID)
  {
   // StudentList studentList = AssociationModelManager.getStudentList();
   // if (studentList.getStudentById(ID) == null)
   // {
      this.ID = ID;
      this.name = name;
      this.isMember = false;
   // }
   // else {
    //  throw new IllegalArgumentException("ID is not unique!");
  //  }
    // todo ID check!
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
