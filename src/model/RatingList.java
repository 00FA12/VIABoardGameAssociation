package model;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class RatingList
{
  private ArrayList<Integer> ratings;

  public RatingList()
  {
    this.ratings = new ArrayList<>();
  }

  public void addRating(int rating)
  {
    this.ratings.add(rating);
  }

  public String average()
  {
    double res = 0;
    for (int i = 0; i < ratings.size(); i++)
    {
      res += ratings.get(i);
    }
    res /= ratings.size();
    DecimalFormat df = new DecimalFormat("#.#");
    return df.format(res);
  }

  public int getRating(int index)
  {
    return ratings.get(index);
  }

  public void setRating(int index, int rating)
  {
    this.ratings.set(index, rating);
  }

  public void removeRating(int index)
  {
    this.ratings.remove(index);
  }

}
