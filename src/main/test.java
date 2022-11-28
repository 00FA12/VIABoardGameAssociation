package main;

import model.Genre;
import model.lists.GenreList;

public class test
{
  public static void main(String[] args)
  {
    GenreList genreList = new GenreList();
    genreList.addGenre(new Genre("Monopoly"));
    genreList.addGenre(new Genre("526372"));
    genreList.addGenre(new Genre("dhgfsh"));
    genreList.addGenre(new Genre("2356"));
    genreList.addGenre(new Genre("sjgt"));
    genreList.addGenre(new Genre("athh"));
    genreList.addGenre(new Genre("asgdj"));
    LoadInitialData.setGenreList(genreList);
    System.out.println(LoadInitialData.getGenreList());
  }
}
