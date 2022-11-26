package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MyFileHandler
{
  public static void writeToTextFile(String name, String str) throws FileNotFoundException
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
      if(write != null)
        write.close();
    }
  }

  public static void appendToTextFile(String name, String str) throws FileNotFoundException
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
      if(write != null)
        write.close();
    }
  }

  public static void writeArrayToTextFile(String name, String[] str) throws FileNotFoundException
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
      if(write != null)
        write.close();
    }
  }

  public static void appendArrayToTextFile(String name, String[] str) throws FileNotFoundException
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
      if(write != null)
        write.close();
    }
  }

  public static String readFromTextFile(String fileName) throws FileNotFoundException
  {
    Scanner read = null;
    String str = "";
    try
    {
      FileInputStream fileIn = new FileInputStream(fileName);
      read = new Scanner(fileIn);
      str = read.nextLine();
    }
    finally
    {
      if(read != null)
        read.close();
      return str;
    }
  }

  public static String[] readArrayFromTextFile(String fileName) throws FileNotFoundException
  {
    Scanner read = null;
    ArrayList<String> temp = new ArrayList<>();

    try
    {
      FileInputStream fileIn = new FileInputStream(fileName);
      read = new Scanner(fileIn);
      while(read.hasNext())
      {
        temp.add(read.nextLine());
      }
    }
    finally
    {
      if(read != null)
        read.close();
      return temp.toArray(new String[temp.size()]);
    }
  }

  public static void writeToBinaryFile(String name, Object obj) throws FileNotFoundException, IOException
  {
    ObjectOutputStream write = null;

    try
    {
      FileOutputStream fileOut = new FileOutputStream(name);
      write = new ObjectOutputStream(fileOut);
      write.writeObject(obj);
    }
    finally
    {
      if(write != null)
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

  public static void writeArrayToBinaryFile(String name, Object[] obj) throws FileNotFoundException, IOException
  {
    ObjectOutputStream write = null;

    try
    {
      FileOutputStream fileOut = new FileOutputStream(name);
      write = new ObjectOutputStream(fileOut);
      for (int i = 0; i < obj.length; i++)
      {
        write.writeObject(obj[i]);
      }
    }
    finally
    {
      if(write != null)
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

  public static Object readFromBinaryFile(String name) throws FileNotFoundException, IOException, ClassNotFoundException
  {
    ObjectInputStream read = null;
    Object obj = null;
    try
    {
      FileInputStream fileIn= new FileInputStream(name);
      read = new ObjectInputStream(fileIn);
      obj = read.readObject();
    }
    finally
    {
      if(read != null)
      {
        try
        {
          read.close();
        }
        catch (IOException e)
        {
          System.out.println("IO Error closing file " + name);
        }
      }
      return obj;
    }
  }

  public static Object[] readArrayFromBinaryFile(String name) throws FileNotFoundException, IOException, ClassNotFoundException
  {
    ObjectInputStream read = null;
    ArrayList<Object> obj = null;
    try
    {
      FileInputStream fileIn = new FileInputStream(name);
      read = new ObjectInputStream(fileIn);
      while(true)
      {
        try
        {
          obj.add(read.readObject());
        }
        catch (EOFException e)
        {
          break;
        }
      }
    }
    finally
    {
      if(read != null)
      {
        try
        {
          read.close();
        }
        catch (IOException e)
        {
          System.out.println("IO Error closing file " + name);
        }
      }
      return obj.toArray(new Object[obj.size()]);
    }
  }
}
