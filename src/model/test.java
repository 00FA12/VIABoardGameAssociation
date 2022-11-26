package model;

public class test
{
  public static void main(String[] args)
  {
    MyDate date1 = MyDate.today();
    MyDate date2 = new MyDate(30, 6, 2011);
    GameAction gameAction = new Borrow(234134, date2, date1);
    System.out.println(gameAction.timeLeft());
  }
}
