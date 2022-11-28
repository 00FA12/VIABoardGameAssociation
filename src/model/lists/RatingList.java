package model.lists;

import java.text.DecimalFormat;
import java.util.ArrayList;
//HUGO
public class RatingList
{
  private ArrayList<Integer> ratings;
  public RatingList()
  {
    ratings = new ArrayList<Integer>();
  }

  public void addRating(int rating)
  {
    if (rating<=5 && rating>=0) //We don't want super high or super low values
    {
      ratings.add(rating);
    }
    else
      throw new IllegalArgumentException("Rating has to be between 0 and 5");
  }

  public String average()
  {
    DecimalFormat lol = new DecimalFormat("0.00");
    int sum = 0;
    for (int i = 0; i < ratings.size(); i++)
    {
      sum+=ratings.get(i);
    }
    return "" + lol.format(sum/ratings.size());
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
      temp+= "Rating number " + i + " is " + ratings.get(i) + "\n";
    }
    return temp;
  }
}
