package main;

import model.GenreList;
import utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LoadInitialData
{
  public static void main(String[] args)
  {
    GenreList genreList = new GenreList();
    String fileName = "genres.bin";
    try
    {
      genreList = (GenreList) MyFileHandler.readFromBinaryFile(fileName);
    }
    catch (FileNotFoundException e)
    {
      throw new RuntimeException(e);
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
    catch (ClassNotFoundException e)
    {
      throw new RuntimeException(e);
    }
    System.out.println(genreList);
  }
}
