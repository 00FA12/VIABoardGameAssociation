package model.lists;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

//Hugo
public class RatingList implements Serializable
{
  private ArrayList<Integer> ratings;

  public RatingList()
  {
    ratings = new ArrayList<>();
  }

  public void addRating(int rating)
  {
    if (rating >= 1 && rating <= 5) //We don't want super high or super low values
    {
      ratings.add(rating);
    }
    else
      throw new IllegalArgumentException("Rating has to be between 1 and 5");
  }

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
  public int getRating(int index)
  {
    return ratings.get(index);
  }

  public void setRating(int index, int rating)
  {
    ratings.set(index, rating);
  }

  public void removeRating(int index)
  {
    ratings.remove(index);
  }

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
