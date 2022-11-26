package model;

public abstract class GameAction
{
  private int ID;
  private MyDate startDate;
  private MyDate endDate;

  public GameAction(int ID, MyDate startDate, MyDate endDate)
  {
    this.ID = ID;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public MyDate getEndDate()
  {
    return endDate;
  }

  public MyDate getStartDate()
  {
    return startDate;
  }

  public int getID()
  {
    return ID;
  }

  public int timeLeft()
  {
    int startDays = MyDate.days(startDate.getYear(), startDate.getMonth(),
        startDate.getDay());

    int endDays = MyDate.days(endDate.getYear(), endDate.getMonth(),
        endDate.getDay());

    return endDays - startDays;
  }


}
