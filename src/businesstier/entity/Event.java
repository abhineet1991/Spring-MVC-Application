package com.businesstier.entity;

import java.util.HashSet;
import java.util.Set;
/**
 * POJO class for Event
 */
public class Event {
	
	private int eventid;
	private String name="";
	private String description="";
	private String place="";
	private String duration;
	private String eventtype="";
	private String seatsavailable;
	private int sessionId;
	private int eventCoordinatorId;
	private boolean add = false;
	private int eventSession=1;

	private Set<Visitor> visitors=new HashSet<Visitor>();
	
	// Getter and Setter methods for encapsulated fields
	
	public int getEventid() {
		return eventid;
	}
	
	public void setEventid(int eventid) {
		this.eventid = eventid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getEventtype() {
		return eventtype;
	}
	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}
	public String getSeatsavailable() {
		return seatsavailable;
	}
	public void setSeatsavailable(String seatsavailable) {
		this.seatsavailable = seatsavailable;
	}
	public Set<Visitor> getVisitors() {
		return visitors;
	}
	public void setVisitors(Set<Visitor> visitors) {
		this.visitors = visitors;
	}
	
	public int getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	
	public int getEventCoordinatorId() {
		return eventCoordinatorId;
	}
	public void setEventCoordinatorId(int eventCoordinatorId) {
		this.eventCoordinatorId = eventCoordinatorId;
	}

	public boolean getAdd() {
		return add;
	}

	public void setAdd(boolean add) {
		this.add = add;
	}

	public int getEventSession() {
		return eventSession;
	}

	public void setEventSession(int eventSession) {
		this.eventSession = eventSession;
	}


}
