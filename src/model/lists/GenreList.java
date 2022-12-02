package model.lists;

import model.Genre;

import java.io.Serializable;
import java.util.ArrayList;

//MICHAEL
public class GenreList implements Serializable
{
  private ArrayList<Genre> genres = new ArrayList<Genre>();

  public void addGenre(Genre genre)
  {
    genres.add(genre);
  }

  public void removeGenre(int index)
  {
    genres.remove(index);
  }

  public void setGenre(Genre genre, int index)
  {
    genres.set(index, genre);
  }

  public Genre[] getArrayOfGenres()
  {
    return genres.toArray(new Genre[genres.size()]);
  }

  public Genre getGenre(int index)
  {
    return genres.get(index);
  }

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
