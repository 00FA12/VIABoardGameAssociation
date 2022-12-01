package main;

import model.Association;
import model.AssociationModelManager;
import model.Genre;
import model.lists.GenreList;
import utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LoadInitialData
{
  public static void load()
  {
    String fileNameTxt = "genres.txt";
    String fileNameBin = "association.bin";
    GenreList genreList = null;
    try
    {
      String[] genresArray = MyFileHandler.readFromTextFile(fileNameTxt);
      genreList = new GenreList();
      for (int i = 0; i < genresArray.length; i++)
      {
        genreList.addGenre(new Genre(genresArray[i]));
      }
      Association association = AssociationModelManager.getAssociation();
      association.setGenreList(genreList);
      AssociationModelManager.saveAssociation(association);
    }
    catch (FileNotFoundException e)
    {
      System.err.println("File is not found!");
    }
    catch (IOException e)
    {
      System.err.println("End of file");
    }

    System.out.println("Load of initial data is done!");
  }
}
