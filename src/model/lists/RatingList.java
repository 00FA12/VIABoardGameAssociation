package model.lists;

import model.Student;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

//Hugo

/**
 * A class containing a list of Rating objects.
 * @author Hugo
 * version 1.0
 */
public class RatingList implements Serializable
{
  /**
   * {@link ArrayList}of ratings
   */
  private ArrayList<Integer> ratings;

  /**
   * default constructor
   *
   */
  public RatingList()
  {
    ratings = new ArrayList<>();
  }

  /**
   * add rating to the RatingList
   * @param rating
   */

  public void addRating(int rating)
  {
    if (rating >= 1 && rating <= 5) //We don't want super high or super low values
    {
      ratings.add(rating);
    }
    else
      throw new IllegalArgumentException("Rating has to be between 1 and 5");
  }

  /**
   *
   * @return the String representation average of the list
   */

  public String averageToString()
  {
    DecimalFormat lol = new DecimalFormat("#.#");
    int sum = 0;
    for (int i = 0; i < ratings.size(); i++)
    {
      sum += ratings.get(i);
    }
    if(ratings.size() == 0)
      return lol.format(sum);
    else
      return lol.format((double) sum / ratings.size());
  }

  /**
   *
   * @return the average which calcucated by sum devided by number of ratings.
   */
  public double getAverage()
  {
    int sum = 0;
    for (int i = 0; i < ratings.size(); i++)
    {
      sum += ratings.get(i);
    }
    if(ratings.size() == 0)
      return sum;
    else
      return (double) sum / ratings.size();
  }

  /**
   *
   * @return the list size
   */
  public int getSize()
  {
    return this.ratings.size();
  }

  /**
   *
   * @param index the position in the list of the Rating object
   * @return the Rating at index
   */

  public int getRating(int index)
  {
    return ratings.get(index);
  }

  /**
   * Replaces the Rating object at index with ratings.
   * @param index the position in the list that will be replaced
   * @param rating the rating to replace with
   */
  public void setRating(int index, int rating)
  {
    ratings.set(index, rating);
  }

  /**
   *removes rating object by index
   * @param index the position in the list that will be replaced
   */
  public void removeRating(int index)
  {
    ratings.remove(index);
  }

  /**
   *
   * @return the String representation of the list
   */
  public String toString()
  {
    String temp = "";
    for (int i = 0; i < ratings.size(); i++)
    {
      temp += "Rating number " + i + " is " + ratings.get(i) + "\n";
    }
    return temp;
  }
}
