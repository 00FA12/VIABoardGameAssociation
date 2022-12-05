package model.lists;

import model.AssociationModelManager;
import model.Student;

import java.io.Serializable;
import java.util.ArrayList;

// Sevastian
public class StudentList implements Serializable
{
  private ArrayList<Student> students;

  public StudentList()
  {
    this.students = new ArrayList<>();
  }

  public Student getStudentById(int ID)
  {
    Student student = new Student("Bob", ID);
    for (int i = 0; i < students.size(); i++)
    {
      if (students.get(i).equals(student))
        return students.get(i);
    }
    return null;
  }

  public void addStudent(Student student)
  {
    boolean repeat = false;
    StudentList studentList = AssociationModelManager.getAssociation().getStudentList();
    for (int i = 0; i < studentList.getSize(); i++)
    {
      if (studentList.getStudent(i).getID() == student.getID())
      {
        repeat = true;
        break;
      }
    }
    if (!repeat)
    {
      this.students.add(student);
    }
    else
    {
      throw new IllegalArgumentException("The ID introduced is already in use");
    }
  }

  public Student getStudent(int index)
  {
    return this.students.get(index);
  }

  public void setStudent(int index, Student student)
  {
    this.students.set(index, student);
  }

  public int getSize()
  {
    return students.size();
  }

  public void removeStudent(Student student)
  {
    students.remove(student);
  }

  public void removeStudent(int index)
  {
    students.remove(index);
  }

  public Student[] getArrayOfStudents()
  {
    return students.toArray(new Student[students.size()]);
  }

  public String toString()
  {
    String str = "";
    if(students.size() == 0)
      return "Empty";
    for (int i = 0; i < students.size(); i++)
    {
      str += students.get(i) + "\n";
    }
    return str;
  }
}
