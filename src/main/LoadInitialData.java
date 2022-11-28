package main;

import model.Genre;
import model.lists.GenreList;
import model.lists.StudentList;
import utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LoadInitialData
{
  public static void setGenreList(GenreList genreList)
  {
    String fileName = "genres.bin";
    try
    {
      MyFileHandler.writeToBinaryFile(fileName, genreList);
    }
    catch (FileNotFoundException e)
    {
      System.err.println("File is not found!");
    }
    catch (IOException e)
    {
      System.err.println("End of file, or file is empty");
    }
  }

  public static void setStudentList()
  {
    StudentList studentList = new StudentList();
    String fileName = "students.bin";
    try
    {
      MyFileHandler.writeToBinaryFile(fileName, studentList);
    }
    catch (FileNotFoundException e)
    {
      System.err.println("File is not found!");
    }
    catch (IOException e)
    {
      System.err.println("End of file, or file is empty");
    }
  }

  public static GenreList getGenreList()
  {
    GenreList genreList = new GenreList();
    String fileName = "genres.bin";
    try
    {
      genreList = (GenreList) MyFileHandler.readFromBinaryFile(fileName);
    }
    catch (FileNotFoundException e)
    {
      System.err.println("File is not found!");
    }
    catch (IOException e)
    {
      System.err.println("End of file, or file is empty");
    }
    catch (ClassNotFoundException e)
    {
      System.err.println("Class is not found!");
    }
    return genreList;
  }

  public static StudentList getStudentList()
  {
    StudentList studentList = new StudentList();
    String fileName = "students.bin";
    try
    {
      studentList = (StudentList) MyFileHandler.readFromBinaryFile(fileName);
    }
    catch (FileNotFoundException e)
    {
      System.err.println("File is not found!");
    }
    catch (IOException e)
    {
      System.err.println("End of file, or file is empty");
    }
    catch (ClassNotFoundException e)
    {
      System.err.println("Class is not found!");
    }
    return studentList;
  }
}
