package model.lists;

import model.Event;

import java.io.Serializable;
import java.util.ArrayList;

//Hugo

/**
 * list to operate with events
 * @author Hugo Pennarubia
 */
public class EventList implements Serializable
{
  private ArrayList<Event> events;

  /**
   * constructor with no parameters to create a list
   */
  public EventList()
  {
    events = new ArrayList<Event>();
  }

  /**
   *
   * @param index
   * @return {@link Event} obj by index
   */
  public Event getEvent(int index)
  {
    return events.get(index);
  }

  /**
   * add event to the end of the list
   * @param event
   */
  public void addEvent(Event event)
  {
    events.add(event);
  }

  /**
   *
   * @param title
   * @return {@link Event} title
   */
  public Event getEventByTitle(String title)
  {
    for (int i = 0; i < events.size(); i++)
    {
      if (events.get(i).getTitle().equals(title))
      {
        return events.get(i);
      }
    }
    return null;
  }

  /**
   * set event by index
   * @param event
   * @param index
   */
  public void setEvent(Event event, int index)
  {
    events.set(index, event);
  }

  /**
   *
   * @return the sie of list
   */
  public int getSize()
  {
    return events.size();
  }

  /**
   * removes an event by index
   * @param index
   */
  public void removeEvent(int index)
  {
    events.remove(index);
  }

  /**
   *
   * @return the String representation of list
   */
  public String toString()
  {
    String temp = "";
    for (int i = 0; i < events.size(); i++)
    {
      temp += events.get(i) + "\n";
    }
    return temp;
  }
}
