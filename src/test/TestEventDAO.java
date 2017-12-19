package com.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.businesstier.dao.EventDAO;
import com.businesstier.entity.Event;
import com.businesstier.entity.EventCoordinator;
import com.businesstier.entity.Visitor;
import com.exceptions.FERSGenericException;
import com.helper.FERSDataConnection;

/**
 * Junit test class for EventDAO class
 * 
 */
public class TestEventDAO {

	private static Connection connection = null;
	private static PreparedStatement statement = null;
	private static ResultSet resultSet = null;
	private ArrayList<Object[]> showAllEvents;
	private EventDAO dao;

	/**
	 * Sets up database connection before other methods are executed in this
	 * class
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpDatabaseConnection() throws Exception {
		connection = FERSDataConnection.createConnection();
	}

	/**
	 * Closes the database connection after all the methods are executed
	 * 
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownDatabaseConnection() throws Exception {
		/**
		 * @TODO: Close connection object here  
		 */
	}

	/**
	 * Sets up the objects required in other methods
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		showAllEvents = new ArrayList<Object[]>();
		dao = new EventDAO();
	}

	/**
	 * Deallocate the resources after execution of method
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		/**
		 * @TODO: Release all the objects here by assigning them null  
		 */
	}

	/**
	 * Positive test case to test the method showAllEvents
	 */
	@Test
	public void testShowAllEvents_Positive() {
		/**
		 * @TODO: Call showAllEvents method and assert it for
		 * size of returned type list
		 */	
	}
	
	/**
	 * Junit test case to test positive case for updateEventDeletions
	 */
	@Test
	public void testUpdateEventDeletions_Positive() {
		/**
		 * @TODO: Find out seats available for an event by opening a connection
		 * and calling the query SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = ?
		 * Call the updateEventDeletions for eventId
		 * Again find out the seats available for this event
		 * testSeatsAvailableBefore should be 1 more then testSeatsAvailableAfter
		 */		
	}

	/**
	 * Negative test case for method updateEventDeletions
	 */
	@Test
	public void testUpdateEventDeletions_Negative() {
		/**
		 * @TODO: Call updateEventDeletions for incorrect eventid and it should
		 * throw an exception
		 */
	}

	/**
	 * Positive test case for method updateEventNominations
	 */
	@Test
	public void testUpdateEventNominations_Positive() {
		/**
		 * @TODO: Find out seats available for an event by opening a connection
		 * and calling the query SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = ?
		 * Call the updateEventNominations for eventId
		 * Again find out the seats available for this event
		 * testSeatsAvailableBefore should be 1 less then testSeatsAvailableAfter
		 */	
	}

	/**
	 * Negative test case for method updateEventNominations
	 */
	@Test
	public void testUpdateEventNominations_Negative() {
		/**
		 * @TODO: Call updateEventNominations for incorrect eventid and it should
		 * throw an exception
		 */	
	}

	/**
	 * Positive test case for method checkEventsofVisitor
	 */
	@Test
	public void testCheckEventsOfVisitor_Positive() {
		/**
		 * @TODO: Create visitor object by setting appropriate values
		 * Call checkEventsofVisitor method by passing this visitor object and
		 * valid eventId
		 * Assert the value of return type 
		 */	
	}
	
	/**
	 * Junit test case for getEventCoordinator
	 */
	@Test
	public void testGetEventCoordinator(){
		/**
		 * @TODO: Call getEventCoordinator method
		 * Assert the size of return type arraylist
		 */		
	}
	
	/**
	 * Junit test case for getEvent
	 */
	@Test
	public void testGetEvent(){
		/**
		 * @TODO: Call getEvent method 
		 * Assert the returned Event type with the passed value of event id
		 */		
	}	
	
	/**
	 * Junit test case for updateEvent
	 */
	@Test
	public void testInsertEvent(){
		/**
		 * @TODO: Create Event object by setting appropriate values
		 * Call insertEvent method by passing this event object
		 * Assert the status of return type of this insertEvent method
		 */		
	}
	
	/**
	 * Junit test case for updateEvent
	 */
	@Test
	public void testUpdateEvent(){
		/**
		 * @TODO: Fetch Event object by calling showAllEvents method
		 * Update the values of event object
		 * Call updateEvent method by passing this modified event as object
		 * Assert the status of return type of updateEvent method
		 */			
	}
	
	/**
	 * Junit test case for deleteEvent
	 */
	@Test
	public void testDeleteEvent(){
		/**
		 * @TODO: Fetch Event object by calling showAllEvents method		 * 
		 * Call deleteEvent method by passing this event id and event session id as object
		 * Assert the status of return type of updateEvent method
		 */		
	}

}
