package model.lists;

import model.AssociationModelManager;
import model.Student;

import java.io.Serializable;
import java.util.ArrayList;

// Sevastian

/**
 * class that describes student list and consist of {@link Student}
 */
public class StudentList implements Serializable
{
  private ArrayList<Student> students;

  /**
   * empty constructor which creates the list
   */
  public StudentList()
  {
    this.students = new ArrayList<>();
  }

  /**
   *
   * @param ID
   * @return either {@link Student} object if found one, or null if didn't
   */
  public Student getStudentById(int ID)
  {
    Student student = new Student("Bob Bobster", ID);
    for (int i = 0; i < students.size(); i++)
    {
      if (students.get(i).equals(student))
        return students.get(i);
    }
    return null;
  }

  /**
   * adds {@link Student} to the end of the list if introduced Student has unique ID
   * @param student
   * @throws IllegalArgumentException if the list already has introduced {@link Student}
   */
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

  /**
   * adds student without checking if the list already has this student
   * @param student
   */
  public void addStudentAsAttender(Student student){
    this.students.add(student);
  }

  /**
   *
   * @param index
   * @return {@link Student} object by index
   */
  public Student getStudent(int index)
  {
    return this.students.get(index);
  }

  /**
   * set student in the list by index
   * @param index
   * @param student
   */
  public void setStudent(int index, Student student)
  {
    this.students.set(index, student);
  }

  /**
   *
   * @return the list size
   */
  public int getSize()
  {
    return students.size();
  }

  /**
   * remove student by {@link Student} object
   * @param student
   */
  public void removeStudent(Student student)
  {
    students.remove(student);
  }

  /**
   * removes {@link Student} object by index
   * @param index
   */
  public void removeStudent(int index)
  {
    students.remove(index);
  }

  /**
   *
   * @return an array representation of the list
   */
  public Student[] getArrayOfStudents()
  {
    return students.toArray(new Student[students.size()]);
  }

  /**
   *
   * @return the String representation of the list
   */
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
