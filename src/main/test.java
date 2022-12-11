package main;

import model.Association;
import model.AssociationModelManager;
import model.lists.EventList;

public class test
{
  public static void main(String[] args)
  {
    Association association = AssociationModelManager.getAssociation();
    EventList eventList = association.getEventList();
    for (int i = 0; i < eventList.getSize(); i++)
    {
      eventList.removeEvent(i);
      i--;
    }
    AssociationModelManager.saveAssociation(association);
  }
}
