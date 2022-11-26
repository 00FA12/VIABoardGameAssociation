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
    int startDays = days(startDate.getYear(), startDate.getMonth(),
        startDate.getDay());

    int endDays = days(endDate.getYear(), endDate.getMonth(),
        endDate.getDay());

    return endDays - startDays;
  }

  private int days(int year, int month, int day)
  {
    int val = 0;
    for(int i = 1; i <= year; i++)
    {
      if(isLeapYear(i))
      {
        val += 366;
      }
      else
      {
        val += 365;
      }
    }
    for(int i = 1; i <= month; i++)
    {
      val += daysInMonth(year, i);
    }
    val += day;
    return val;
  }

  private int daysInMonth(int year, int month)
  {
    if(month == 1 || month == 3 || month == 5 || month == 7
        || month == 8 || month == 10 || month == 12)
      return 31;
    else if(month == 2 && isLeapYear(year))
      return 29;
    else if(month == 2 && !isLeapYear(year))
      return 28;
    else
      return 30;
  }

  private boolean isLeapYear(int year)
  {
    if(year % 4 == 0)
    {
      if(year % 100 == 0 && year % 400 == 0)
        return true;
      else if(year % 100 != 0)
        return true;
      else
        return false;
    }
    else
      return false;
  }
}
