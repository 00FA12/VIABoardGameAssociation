package main;

import model.Association;
import model.AssociationModelManager;
import model.Genre;
import model.lists.GenreList;
import utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * class that load initial whenever the program starts
 */
public class LoadInitialData
{
  /**
   * static method which actually loads the genres from the text file to the association binary file
   */
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

      if(association == null)
        association = new Association();

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
