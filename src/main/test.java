package main;

import model.Association;
import model.AssociationModelManager;

public class test
{
  public static void main(String[] args)
  {
    Association association = AssociationModelManager.getAssociation();
    System.out.println("Agua con pan");
  }
}
