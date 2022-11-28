package model;

import java.io.Serializable;

//MICHAEL
public class Genre implements Serializable
{
  private String name;

  public Genre(String name){this.name = name;}

  public String getName(){return name;}
  public void setName(String name){this.name = name;}

  public String toString(){return String.format("Genre name: %s\n", this.getName());}
}
