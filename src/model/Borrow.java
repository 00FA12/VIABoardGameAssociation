package model;

import java.io.Serializable;

//MICHAEL
public class Borrow extends GameAction implements Serializable
{
  public Borrow(int ID, MyDate startDate, MyDate endDate)
  {
    super(ID, startDate, endDate);
  }
}
