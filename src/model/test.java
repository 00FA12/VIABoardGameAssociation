package model;

public class test
{
  public static void main(String[] args)
  {
    MyDate date1 = MyDate.today();
    MyDate date2 = new MyDate(30, 6, 2011);
    GameAction gameAction = new Borrow(234134, date2, date1);
    System.out.println(gameAction.timeLeft());

    RatingList ratingList = new RatingList();
    ratingList.addRating(3);
    ratingList.addRating(4);
    ratingList.addRating(2);
    ratingList.addRating(5);
    ratingList.addRating(4);
    ratingList.addRating(3);
    ratingList.addRating(5);
    ratingList.addRating(1);
    ratingList.addRating(2);
    System.out.println(ratingList.average());
  }
}
