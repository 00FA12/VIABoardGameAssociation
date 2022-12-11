package model;

import java.io.Serializable;

/**
 * represents borrowing
 * @author Michael Leo
 */
public class Borrow extends GameAction implements Serializable
{
  /**
   * call the constructor of it's super class {@link GameAction}
   * @param ID
   * @param startDate
   * @param endDate
   */
  public Borrow(int ID, MyDate startDate, MyDate endDate)
  {
    super(ID, startDate, endDate);
  }

  /**
   *
   * @return the borrowing String representation
   */
  public String toString()
  {
    return "Borrowed";
  }
}
