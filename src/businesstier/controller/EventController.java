package com.businesstier.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.businesstier.entity.Event;
import com.businesstier.entity.EventCoordinator;
import com.businesstier.entity.Visitor;
import com.businesstier.service.EventService;
import com.businesstier.service.EventServiceImpl;
import com.exceptions.FERSGenericException;

/**
 * 
 * @author abhineet.gupta This controller will handle all event related
 *         activities for a new visitor 
 * 
 */

@Controller
public class EventController {

	private static Logger log = Logger.getLogger(EventController.class);

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             The method will display all the events available in the
	 *             catalog to the visitor
	 */
	@RequestMapping("/catalog.htm")
	public ModelAndView getAvailableEvents(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request == null || response == null) {
			log.info("request or response not valid in GETAVAILABLEEVENTS METHOD ");
			throw new FERSGenericException(
					"Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder",
					new NullPointerException());
		}

		List<Object[]> eventList = new ArrayList<Object[]>();
		EventService serviceImpl = new EventServiceImpl();
		eventList = serviceImpl.getAllEvents();

		log.info("All Events are listed :" + eventList);
		
		HttpSession hs = request.getSession();
		Visitor visitor = (Visitor)hs.getAttribute("VISITOR");
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("allEvents", eventList);
		mv.addObject("visitor",visitor);
		mv.setViewName("/eventCatalog.jsp");
		return mv;
	}

	/**
	 * This method deletes the event from database
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteEvent.htm")
	public ModelAndView deleteEvent(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request == null || response == null) {
			log.info("request or response not valid in DELTEEVENT METHOD ");
			throw new FERSGenericException(
					"Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder",
					new NullPointerException());
		}

		String eventId = request.getParameter("eventId");
		String sessionId = request.getParameter("sessionId");
		List<Object[]> eventList = new ArrayList<Object[]>();
		EventService serviceImpl = new EventServiceImpl();
		serviceImpl.deleteEvent(Integer.parseInt(eventId), Integer.parseInt(sessionId));	
		eventList = serviceImpl.getAllEvents();
		
		HttpSession hs = request.getSession();
		Visitor visitor = (Visitor)hs.getAttribute("VISITOR");
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("allEvents", eventList);
		mv.addObject("visitor",visitor);
		mv.setViewName("/eventCatalog.jsp");
		return mv;
	}

	/**
	 * This method displays the add/update event page
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/displayEvent.htm")
	public ModelAndView displayEvent(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request == null || response == null) {
			log.info("request or response not valid in DISPLACEVENT METHOD ");
			throw new FERSGenericException(
					"Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder",
					new NullPointerException());
		}

		String eventId = request.getParameter("eventId");
		String sessionId = request.getParameter("sessionId");	
		
		EventService serviceImpl = new EventServiceImpl();
		List<EventCoordinator> eventCoordinator = serviceImpl.getAllEventCoordinators();
		Event event;
		if (eventId.equals("-1")) {
			event = new Event();
			event.setAdd(true);
		} else {
			event = serviceImpl.getEvent(Integer.parseInt(eventId),
					Integer.parseInt(sessionId));
		}

		log.info("Displaying event for eventId:" + eventId);
		
		HttpSession hs = request.getSession();
		Visitor visitor = (Visitor)hs.getAttribute("VISITOR");

		ModelAndView mv = new ModelAndView();
		mv.addObject("event", event);
		mv.addObject("eventCoordinator", eventCoordinator);
		mv.addObject("visitor",visitor);
		mv.setViewName("/addEvent.jsp");
		return mv;
	}

	/**
	 * This method updates the event
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateEvent.htm")
	public ModelAndView updateEvent(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String statusString = "";
		if (request == null || response == null) {
			log.info("request or response not valid in UPDATEEVENT METHOD ");
			throw new FERSGenericException(
					"Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder",
					new NullPointerException());
		}

		String eventId = request.getParameter("eventId");
		String sessionId = request.getParameter("sessionId");
		String eventName = request.getParameter("eventName");
		String desc = request.getParameter("desc");
		String place = request.getParameter("place");
		String duration = request.getParameter("duration");
		String eventType = request.getParameter("eventType");
		String ticket = request.getParameter("ticket");
		String isAdd = request.getParameter("isAdd");			
		
		Event updatedEvent = new Event();
		updatedEvent.setEventid(Integer.parseInt(eventId));
		updatedEvent.setEventtype(eventType);
		updatedEvent.setName(eventName);
		updatedEvent.setDescription(desc);
		updatedEvent.setPlace(place);
		updatedEvent.setDuration(duration);
		updatedEvent.setSeatsavailable(ticket);
		updatedEvent.setSessionId(Integer.parseInt(sessionId));
		
		EventService serviceImpl = new EventServiceImpl();
		int status = -99;
		if(isAdd.equals("true")){
			String eventCoordinatorId = request.getParameter("coordinator");
			String eventSession = request.getParameter("eventSession");
			updatedEvent.setEventCoordinatorId(Integer.parseInt(eventCoordinatorId));
			updatedEvent.setEventSession(Integer.parseInt(eventSession));	
			updatedEvent.setAdd(true);
			status = serviceImpl.insertEvent(updatedEvent);
			statusString = "Event added Succesfully !!!";
		} 
		else{
			status = serviceImpl.updateEvent(updatedEvent);
			statusString = "Event updated Succesfully !!!";
		}	
		
		List<EventCoordinator> eventCoordinatorList = serviceImpl.getAllEventCoordinators();
		ModelAndView mv = new ModelAndView();

		if (status > 0) {
			mv.addObject("REGISTRATIONSTATUSMESSAGE",statusString);
			log.info(statusString);

		} else if (status == 0) {
			mv.addObject("REGISTRATIONSTATUSMESSAGE",
					"There was error while saving the event. Please see log file for more details..");
			log.info("There was error while saving the event. Please see log file for more details..");
		}

		mv.addObject("event", updatedEvent);
		mv.addObject("eventCoordinator", eventCoordinatorList);
		mv.setViewName("/addEvent.jsp");
		return mv;
	}

}
