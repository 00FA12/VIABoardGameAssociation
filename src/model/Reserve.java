package model;

import java.io.Serializable;

/**
 * represents the reservation
 * @author Michael Leo
 */
public class Reserve extends GameAction implements Serializable
{
  /**
   * call the constructor of it's super class {@link GameAction}
   * @param ID
   * @param startDate
   * @param endDate
   */
  public Reserve(int ID, MyDate startDate, MyDate endDate)
  {
    super(ID, startDate, endDate);
  }

  public String toString()
  {
    return "Reserved";
  }
}
