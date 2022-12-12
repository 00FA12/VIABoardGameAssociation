package model.lists;

import model.Genre;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * class that describes the genre list which consist of {@link Genre}
 * @author Michael Leo
 */
public class GenreList implements Serializable
{
  /**
   * list to store genres
   */
  private ArrayList<Genre> genres = new ArrayList<Genre>();

  /**
   * add genre to the end of the list
   * @param genre
   */
  public void addGenre(Genre genre)
  {
    genres.add(genre);
  }

  /**
   * removes the genre from the list
   * @param index
   */
  public void removeGenre(int index)
  {
    genres.remove(index);
  }

  /**
   * set genre by index
   * @param genre
   * @param index
   */
  public void setGenre(Genre genre, int index)
  {
    genres.set(index, genre);
  }

  /**
   *
   * @return an array representation of the list
   */
  public Genre[] getArrayOfGenres()
  {
    return genres.toArray(new Genre[genres.size()]);
  }

  /**
   *
   * @param index
   * @return {@link Genre} by index
   */
  public Genre getGenre(int index)
  {
    return genres.get(index);
  }

  /**
   *
   * @return String representation of genre list
   */
  public String toString()
  {
    String temp = "";
    for (int i = 0; i < genres.size(); i++)
    {
      temp += i + " " + genres.get(i).toString();
    }
    return temp;
  }
}
