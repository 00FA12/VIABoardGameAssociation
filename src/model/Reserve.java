package model;

import java.io.Serializable;

//Michael
public class Reserve extends GameAction implements Serializable
{
  public Reserve(int ID, MyDate startDate, MyDate endDate)
  {
    super(ID, startDate, endDate);
  }

  public String toString()
  {
    return "Reserved";
  }
}
