package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.FileHandler;

/**
 * @author Sevastian Bahynskyi & Allan Henriksen (copied one method from his "MyFileHandler")
 */
public class MyFileHandler
{
  /**
   * overwrite the file with the String value
   * @param name filepath
   * @param str String value
   * @throws FileNotFoundException if file is not found
   */
  public static void writeToTextFile(String name, String str)
      throws FileNotFoundException
  {
    PrintWriter write = null;
    try
    {
      FileOutputStream fileOut = new FileOutputStream(name);
      write = new PrintWriter(fileOut);
      write.print(str);
    }
    finally
    {
      if (write != null)
        write.close();
    }
  }

  /**
   *
   * add some text to the file
   * @param name filepath
   * @param str text
   * @throws FileNotFoundException if file is not found
   */
  public static void appendToTextFile(String name, String str)
      throws FileNotFoundException
  {
    PrintWriter write = null;
    try
    {
      FileOutputStream fileOut = new FileOutputStream(name, true);
      write = new PrintWriter(fileOut);
      write.print(str);
    }
    finally
    {
      if (write != null)
        write.close();
    }
  }

  /**
   *
   * @param fileName filepath
   * @return the array of string that was read from the file
   * @throws FileNotFoundException if file is not found
   */
  public static String[] readFromTextFile(String fileName)
      throws FileNotFoundException
  {
    Scanner readFromFile = null;
    ArrayList<String> strs = new ArrayList<String>();

    try
    {
      FileInputStream fileInStream = new FileInputStream(fileName);
      readFromFile = new Scanner(fileInStream);

      while (readFromFile.hasNext())
      {
        strs.add(readFromFile.nextLine());
      }
    }
    finally
    {
      if (readFromFile != null)
      {
        readFromFile.close();
      }
    }

    String[] strsArray = new String[strs.size()];
    return strs.toArray(strsArray);
  }

  /**
   * add object to the binary file
   * @param name filepath
   * @param obj
   * @throws FileNotFoundException if file is not found
   * @throws IOException if file is empty or it is end of file
   */
  public static void writeToBinaryFile(String name, Object obj)
      throws FileNotFoundException, IOException
  {
    ObjectOutputStream write = null;
    System.out.println("Write to binary file was called!");

    try
    {
      FileOutputStream fileOut = new FileOutputStream(name);
      write = new ObjectOutputStream(fileOut);
      write.writeObject(obj);
    }
    finally
    {
      if (write != null)
      {
        try
        {
          write.close();
        }
        catch (IOException e)
        {
          System.out.println("IO Error closing file " + name);
        }
      }
    }
  }

  /**
   *
   * @param fileName filepath
   * @return object that was read from the file
   * @throws FileNotFoundException if file is not found
   * @throws IOException if end of file or it's empty
   * @throws ClassNotFoundException if class is not found
   */
  public static Object readFromBinaryFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException
  {
    Object obj = null;
    ObjectInputStream readFromFile = null;
    try
    {
      FileInputStream fileInStream = new FileInputStream(fileName);
      readFromFile = new ObjectInputStream(fileInStream);
      try
      {
        obj = readFromFile.readObject();
      }
      catch (EOFException eof)
      {
        //Done reading
      }
    }
    finally
    {
      if (readFromFile != null)
      {
        try
        {
          readFromFile.close();
        }
        catch (IOException e)
        {
          System.out.println("IO Error closing file " + fileName);
        }
      }
    }

    return obj;
  }
}
