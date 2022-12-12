package model;

import model.lists.StudentList;
import model.lists.VotingList;
import utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

/**
 * association model manager works with {@link MyFileHandler} and {@link Association},
 * it can read {@link Association} from the file and write it to the file
 */
public class AssociationModelManager implements Serializable
{
  /**
   * binary filepath
   */
  private static final String fileName = "association.bin";

  /**
   * overwrite file with new Object
   * @param association
   */
  public static void saveAssociation(Association association)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(fileName, association);
    }
    catch (FileNotFoundException e)
    {
      System.err.println(String.format("File %s is not found", fileName));
    }
    catch (IOException e)
    {
      System.err.println(
          String.format("End of file %s, or it is empty", fileName));
    }
  }

  /**
   *
   * @return {@link Association} object, which was read from the file
   */
  public static Association getAssociation()
  {
    Association association = null;
    try
    {
      association = (Association) MyFileHandler.readFromBinaryFile(fileName);
    }
    catch (FileNotFoundException e)
    {
      System.err.println(String.format("File %s is not found", fileName));
    }
    catch (IOException e)
    {
      System.err.println(
          String.format("End of file %s, or it is empty", fileName));
    }
    catch (ClassNotFoundException e)
    {
      System.err.println(
          String.format("Class is not found in %s file", fileName));
    }
    catch (Exception e){
      System.err.println("some other mistakes");
    }
    return association;
  }

  /**
   * call {@link AssociationModelManager#getVotingList()} than call {@link Association#getVotingList()} from {@link Association}
   * @return voting list
   */
  public static VotingList getVotingList()
  {
    return getAssociation().getVotingList();
  }

  /**
   * call {@link AssociationModelManager#getStudentList()} ()} than call {@link Association#getStudentList()} from {@link Association}
   * @return student list
   */
  public static StudentList getStudentList()
  {
    return getAssociation().getStudentList();
  }
}
