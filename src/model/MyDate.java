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

  public String toString()
  {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate date = LocalDate.of(year, month, day);
    return date.format(formatter);
  }
}
