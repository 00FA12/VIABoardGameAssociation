package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MyDate
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

  public static MyDate today()
  {
    LocalDate date = LocalDate.now();
    return new MyDate(date.getDayOfMonth(), date.getMonthValue(), date.getYear());
  }

  public int getDay()
  {
    return day;
  }

  public int getYear()
  {
    return year;
  }

  public int getMonth()
  {
    return month;
  }

  public void setYear(int year)
  {
    this.year = year;
  }

  public void setDay(int day)
  {
    this.day = day;
  }

  public void setMonth(int month)
  {
    this.month = month;
  }

  public boolean equals(Object obj)
  {
    if(obj == null || obj.getClass() != getClass())
      return false;

    MyDate date = (MyDate) obj;
    return date.day == day && date.month == month && date.year == year;
  }

  public MyDate copy()
  {
    return new MyDate(day, month, year);
  }

  public static int days(int year, int month, int day)
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

  public static int daysInMonth(int year, int month)
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

  private static boolean isLeapYear(int year)
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

  public String toString()
  {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate date = LocalDate.of(year, month, day);
    return date.format(formatter);
  }
}
