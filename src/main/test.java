package main;

import model.Association;
import model.AssociationModelManager;

public class test
{
  public static void main(String[] args)
  {
    LoadInitialData.load();
    Association association = AssociationModelManager.getAssociation();
    System.out.println(association.getGenreList());
  }
}
