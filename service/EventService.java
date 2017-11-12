package com.businesstier.service;

import java.util.List;

import com.businesstier.entity.Event;
import com.businesstier.entity.EventCoordinator;
import com.businesstier.entity.Visitor;
/**
 *  abstract event class for event entity.
 *  
 *  author abhineet.gupta
 */
public interface EventService {
	
	public List<Object[]> getAllEvents();
	
	public List<Object[]> getAllEventsAsc();
	
	public List<Object[]> getAllEventsDesc();
	
	public List<Object[]> getAllEvents(String eventname);
	
	public boolean checkEventsofVisitor(Visitor visitor, int eventid, int sessionid);
	
	public void updateEventDeletions(int eventid,int eventsessionid);
	
	public Event getEvent(int eventId, int sesssionId);
	
	public int updateEvent(Event event);
	
	public int deleteEvent(int eventId, int sesssionId);
	
	public int insertEvent(Event event);
	
	public List<EventCoordinator> getAllEventCoordinators();

}
