package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class containing a data implementation
 * @author Kateryna Sokolova
 * @version 1.0
 */
public class MyDate implements Serializable
{
  private int day;
  private int month;
  private int year;

  /**
   * first class constructor
   * @param day
   * @param month
   * @param year
   * @throws IllegalArgumentException if the entered date is before the current date.
   */
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

  }

  public void today()
  {
    LocalDate currentDate = LocalDate.now();
    this.day = currentDate.getDayOfMonth();
    this.month = currentDate.getMonthValue();
    this.year = currentDate.getYear();
  }

  /**
   * 
   * @return {@link MyDate#day}
   */
  public int getDay()
  {
    return day;
  }

  /**
   *
   * @return {@link MyDate#month}
   */
  public int getMonth()
  {
    return month;
  }

  /**
   *
   * @return {@link MyDate#year}
   */
  public int getYear()

  {
    return year;
  }

  /**
   *
   * @param day
   */
  public void setDay(int day)
  {
    this.day = day;
  }

  /**
   *
   * @param month
   */
  public void setMonth(int month)
  {
    this.month = month;
  }

  /**
   *
   * @param year
   */
  public void setYear(int year)
  {
    this.year = year;
  }

  /**
   *
   * @return boolean value if this a leap year or not
   */
  public boolean isLeapYear()
  {
    return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0);

  }

  /**
   *
   * @param object
   * @return true if {@param object }  equals to MyDate object
   */
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

  /**
   *
   * @return the copy of object
   */
  public MyDate copy()
  {
    return new MyDate(day, month, year);
  }

  /**
   *
   * @return the String that represents formatted date
   */
  public String toString()
  {
    LocalDate myDateObj = LocalDate.of(year, month, day);
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    String formattedDate = myDateObj.format(myFormatObj);
    return formattedDate;
  }

}
