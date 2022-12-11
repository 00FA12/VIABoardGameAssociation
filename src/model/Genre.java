package model;

import java.io.Serializable;

//MICHAEL

/**
 * represents game genre
 * @author Michael Leo
 */
public class Genre implements Serializable
{
  /**
   * represents genre name
   */
  private String name;

  /**
   * the only class constructor
   * @param name
   */
  public Genre(String name)
  {
    this.name = name;
  }

  /**
   *
   * @return {@link Genre#name}
   */
  public String getName()
  {
    return name;
  }

  /**
   * to initialize {@link Genre#name}
   * @param name
   */
  public void setName(String name)
  {
    this.name = name;
  }

  /**
   *
   * @return the {@link Genre#name}
   */
  public String toString()
  {
    return name;
  }
}
