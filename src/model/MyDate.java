package model;

import javax.swing.text.DateFormatter;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

//Kateryna
public class MyDate implements Serializable
{
  private int day;
  private int month;
  private int year;

  public MyDate(int day, int month, int year)
  {
    if(LocalDate.of(year, month, day).isBefore(LocalDate.now()))
    {
      throw new IllegalArgumentException("Start date should not be before the current date.");
    }

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
    LocalDate myDateObj = LocalDate.of(year, month, day);
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    String formattedDate = myDateObj.format(myFormatObj);
    return formattedDate;
  }

}
