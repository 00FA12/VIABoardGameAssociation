package model.lists;

import model.Event;

import java.io.Serializable;
import java.util.ArrayList;

//Hugo
public class EventList implements Serializable
{
  private ArrayList<Event> events;
  public EventList()
  {
    events = new ArrayList<Event>();
  }

  public Event getEvent(int index)
  {
    return events.get(index);
  }

  public void addEvent(Event event)
  {
    events.add(event);
  }
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

  public void setEvent(Event event, int index)
  {
    events.set(index, event);
  }

  public int getSize()
  {
    return events.size();
  }

  public void removeEvent(int index)
  {
    events.remove(index);
  }

  public String toString()
  {
    String temp = "";
    for (int i = 0; i < events.size(); i++)
    {
      temp+= events.get(i) + "\n";
    }
    return temp;
  }
}
