package model.lists;

import model.Student;

import java.util.ArrayList;

// Sevastian
public class StudentList
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
      if(students.get(i).equals(student))
        return students.get(i);
    }
    return null;
  }

  public void addStudent(Student student)
  {
    this.students.add(student);
  }

  public Student getStudent(int index)
  {
    return this.students.get(index);
  }

  public void setStudent(int index, Student student)
  {
    this.students.set(index, student);
  }

  public String toString()
  {
    String str = "";
    for (int i = 0; i < students.size() - 1; i++)
    {
      str += students.get(i) + "\n";
    }
    str += students.get(students.size() - 1);
    return str;
  }
}
