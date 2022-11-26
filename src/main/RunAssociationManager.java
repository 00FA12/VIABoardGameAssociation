package main;

import model.GenreList;
import utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

public class RunAssociationManager
{
  public static void main(String[] args)
  {
    LoadInitialData.main(new String[] {"0", "1"});
  }
}
