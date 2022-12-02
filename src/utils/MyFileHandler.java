package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MyFileHandler
{
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

  public static void appendArrayToTextFile(String name, String[] str)
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

//  public static Object readFromBinaryFile(String name)
//      throws FileNotFoundException, IOException, ClassNotFoundException
//  {
//    ObjectInputStream read = null;
//    Object obj = null;
//    try
//    {
//      FileInputStream fileIn = new FileInputStream(name);
//      read = new ObjectInputStream(fileIn);
//      obj = read.readObject();
//    }
//    finally
//    {
//      if (read != null)
//      {
//        try
//        {
//          read.close();
//        }
//        catch (IOException e)
//        {
//          System.out.println("IO Error closing file " + name);
//        }
//      }
//      return obj;
//    }
  }
}
