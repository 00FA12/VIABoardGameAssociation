package model;

import java.io.Serializable;
import java.util.ArrayList;

public class GenreList implements Serializable
{
  private ArrayList<Genre> genres;

  public GenreList()
  {
    this.genres = new ArrayList<>();
  }

  public void addGenre(Genre genre)
  {
    this.genres.add(genre);
  }

  public void removeGenre(int index)
  {
    this.genres.remove(index);
  }

  public void setGenre(int index, Genre genre)
  {
    this.genres.set(index, genre);
  }

  public Genre getGenre(int index)
  {
    return this.genres.get(index);
  }

  public String toString()
  {
    String str = "";
    for (int i = 0; i < genres.size() - 1; i++)
    {
      str += genres.get(i) + "\n";
    }
    str += genres.get(genres.size() - 1);
    return str;
  }

  public void addGenre(String genre)
  {
    this.genres.add(new Genre(genre));
  }
}
