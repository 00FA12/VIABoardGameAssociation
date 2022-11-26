package model;

import java.io.Serializable;

public class Genre implements Serializable
{
  private String name;

  public Genre(String genreName)
  {
    this.name = genreName;
  }
  public void setName(String name)
  {
    this.name = name;
  }

  public String getName()
  {
    return name;
  }

  public String toString()
  {
    return getName();
  }
}
