package model;

import java.io.Serializable;
import java.time.LocalDate;

//Kateryna
public class MyDate implements Serializable
{
  private int day;
  private int month;
  private int year;

  public MyDate(int day, int month, int year)
  {
    this.day = day;
    this.month = month;
    this.year = year;
  }

  public MyDate()
  {
    today();
  }

  public void today()
  {
    LocalDate currentDate = LocalDate.now();
    this.day = currentDate.getDayOfMonth();
    this.month = currentDate.getMonthValue();
    this.year = currentDate.getYear();
  }

  public int getDay()
  {
    return day;
  }

  public int getMonth()
  {
    return month;
  }

  public int getYear()

  {
    return year;
  }

  public void setDay(int day)
  {
    this.day = day;
  }

  public void setMonth(int month)
  {
    this.month = month;
  }

  public void setYear(int year)
  {
    this.year = year;
  }

  public boolean isLeapYear()
  {
    return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0);

  }

  public boolean equals(MyDate object)
  {
    if (day == object.day && month == object.month && year == object.year)
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  public MyDate copy()
  {
    return new MyDate(day, month, year);
  }

  public String toString()
  {
    return day + "/" + month + "/" + year;
  }

}
