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

import com.businesstier.entity.Visitor;
import com.businesstier.service.EventService;
import com.businesstier.service.EventServiceImpl;
import com.businesstier.service.VisitorService;
import com.businesstier.service.VisitorServiceImpl;
import com.exceptions.FERSGenericException;

/**
 * <br/>
 * CLASS DESCRIPTION: <br/>
 * 
 * A controller class for receiving and handling all visitor related
 * transactions from the User Interface including visitor account access,
 * visitor account maintenance, and visitor event registration requests. <br/>
 * 
 * @author abhineet.gupta
 * 
 */

@Controller
public class VisitorController {

	private static Logger log = Logger.getLogger(VisitorController.class);

	/**
	 * <br/>
	 * METHOD DESCRIPTION: <br/>
	 * This method will register new Visitor into system by accepting his
	 * details and load into database <br/>
	 * 
	 * @return ModelAndView
	 * 
	 * @param request
	 * @param response
	 * 
	 * @throws Exception
	 * 
	 */
	@RequestMapping("/newVistor.htm")
	public ModelAndView newVisitor(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (request == null || response == null) {
			log.info("Request or Response failed for NEWVISITOR METHOD..");
			throw new FERSGenericException(
					"Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder",
					new NullPointerException());
		}
		String username = request.getParameter("USERNAME");
		String password = request.getParameter("PASSWORD");
		String firstname = request.getParameter("FIRSTNAME");
		String lastname = request.getParameter("LASTNAME");
		String email = request.getParameter("EMAIL");
		String phoneno = request.getParameter("PHONENO");
		String place = request.getParameter("ADDRESS");

		log.info("creating new visitor with UserName :" + username);

		Visitor visitor = new Visitor();
		visitor.setUserName(username);
		visitor.setPassword(password);
		visitor.setFirstName(firstname);
		visitor.setLastName(lastname);
		visitor.setEmail(email);
		visitor.setPhoneNumber(phoneno);
		visitor.setAddress(place);

		VisitorService vServiceImpl = new VisitorServiceImpl();
		boolean insertStatus = vServiceImpl.createVisitor(visitor);

		ModelAndView mv = new ModelAndView();
		if (insertStatus == true) {
			mv.addObject("REGISTRATIONSTATUSMESSAGE",
					"User Registered Succesfully !!!");
			log.info("Succesfully created visitor " + username);
			mv.setViewName("/registration.jsp");
		} else {
			mv.addObject("REGISTRATIONSTATUSMESSAGE",
					"USERNAME already exists.. please register again with different USERNAME..");
			log.info("Username " + username
					+ " already exists and visitor creation failed..");
			mv.setViewName("/registration.jsp");
		}
		return mv;
	}

	/**
	 * <br/>
	 * METHOD DESCRIPTION: <br/>
	 * This method is for validating visitor in the first page and redirects to
	 * visitor homepage based on credentials. if validation fails, error message
	 * is printed on same screen <br/>
	 * 
	 * @return ModelAndView
	 * 
	 * @param request
	 * @param response
	 * 
	 * @throws Exception
	 */

	@RequestMapping("/searchVisitor.htm")
	public ModelAndView searchVisitor(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (request == null || response == null) {
			log.info("Request or Response failed for SEARCHVISITOR METHOD..");
			throw new FERSGenericException(
					"Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder",
					new NullPointerException());
		}
		String username = request.getParameter("USERNAME");
		String password = request.getParameter("PASSWORD");
		HttpSession hs = request.getSession();
		if (hs.isNew()) {
			hs.setAttribute("USERNAME", username);
			hs.setAttribute("PASSWORD", password);
		} else {
			username = hs.getAttribute("USERNAME").toString();
			password = hs.getAttribute("PASSWORD").toString();
		}

		log.info("Logging into FERS using username :" + username
				+ " and password :" + password);

		Visitor visitor = new Visitor();
		VisitorService vServiceImpl = new VisitorServiceImpl();
		visitor = vServiceImpl.searchVisitor(username, password);

		ModelAndView mv = new ModelAndView();

		if (visitor.getVisitorId() == 0) {
			mv.addObject("ERROR", "Invalid Username / Password.");
			mv.setViewName("/index.jsp");
			return mv;
		} else {

			log.info("Visitor details available for the username :" + username);

			HttpSession session = request.getSession();

			List<Object[]> eventList = new ArrayList<Object[]>();
			EventService serviceImpl = new EventServiceImpl();
			eventList = serviceImpl.getAllEvents();

			log.info("All events listed for the visitor :" + eventList);

			List<Object[]> regList = new ArrayList<Object[]>();
			regList = vServiceImpl.showRegisteredEvents(visitor);

			log.info("All Registered events listed for the visitor :" + regList);

			session.setAttribute("VISITOR", visitor);

			mv.addObject("visitor", visitor);
			mv.addObject("allEvents", eventList);
			mv.addObject("regEvents", regList);
			mv.setViewName("/visitormain.jsp");
			return mv;
		}
	}

	/**
	 * <br/>
	 * METHOD DESCRIPTION: <br/>
	 * This method is used to register specific event by visitor and maintains
	 * list of all the events visitor selected. if user already registered for
	 * event then displays relavent error message <br/>
	 * 
	 * @return ModelAndView
	 * 
	 * @param request
	 * @param response
	 * 
	 * @throws Exception
	 */

	@RequestMapping("/eventreg.htm")
	public ModelAndView registerVisitor(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request == null || response == null) {
			log.info("Request or Response failed for REGISTERVISITOR METHOD..");
			throw new FERSGenericException(
					"Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder",
					new NullPointerException());
		}

		HttpSession session = request.getSession();
		Visitor visitor = (Visitor) session.getAttribute("VISITOR");
		int eventid = Integer.parseInt(request.getParameter("eventId"));
		int sessionid = Integer.parseInt(request.getParameter("sessionId"));

		log.info("Visitor registered for the event :" + eventid);

		ModelAndView mv = new ModelAndView();

		VisitorService vServiceImpl = new VisitorServiceImpl();
		EventService serviceImpl = new EventServiceImpl();

		boolean regStatus = serviceImpl.checkEventsofVisitor(visitor, eventid,
				sessionid);

		log.info("Status of the visitor for the event :" + regStatus);

		if (regStatus == false) {
			vServiceImpl.RegisterVisitor(visitor, eventid, sessionid);
			log.info("Visitor succesfully registed for event :" + eventid);
		} else {
			mv.addObject("RegError",
					"User already Registered for the EVENT for same Session !!");
		}

		List<Object[]> regList = new ArrayList<Object[]>();
		regList = vServiceImpl.showRegisteredEvents(visitor);

		List<Object[]> eventList = new ArrayList<Object[]>();

		eventList = serviceImpl.getAllEvents();

		mv.addObject("visitor", visitor);
		mv.addObject("allEvents", eventList);
		mv.addObject("regEvents", regList);
		mv.setViewName("/visitormain.jsp");
		return mv;

	}

	/**
	 * <br/>
	 * METHOD DESCRIPTION: <br/>
	 * This method will update details of the visitor and enables visitor to
	 * logout and re-login with updated details <br/>
	 * 
	 * @return ModelAndView
	 * 
	 * @param request
	 * @param response
	 * 
	 * @throws Exception
	 * 
	 */

	@RequestMapping("/updatevisitor.htm")
	public ModelAndView updateVisitor(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request == null || response == null) {
			log.info("Request or Response failed for UPDATEVISITOR METHOD..");
			throw new FERSGenericException(
					"Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder",
					new NullPointerException());
		}

		HttpSession session = request.getSession();
		Visitor visitor = (Visitor) session.getAttribute("VISITOR");

		log.info("Updating visitor details with VisitorID :"
				+ visitor.getVisitorId());

		String username = request.getParameter("username");
		// String password = request.getParameter("password");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String phoneno = request.getParameter("phoneno");
		String place = request.getParameter("address");

		visitor.setFirstName(firstname);
		visitor.setLastName(lastname);
		visitor.setUserName(username);
		// visitor.setPassword(password);
		visitor.setEmail(email);
		visitor.setPhoneNumber(phoneno);
		visitor.setAddress(place);

		VisitorService vServiceImpl = new VisitorServiceImpl();
		int status = vServiceImpl.updateVisitorDetails(visitor);

		log.info("Number of Visitor records updated is :" + status);

		ModelAndView mv = new ModelAndView();

		if (status > 0) {
			mv.addObject("status", "success");
			mv.setViewName("/updatevisitor.jsp");
		} else {
			mv.addObject("updatestatus",
					"Error in updation.. Please Check fields and retry");
			mv.setViewName("/updatevisitor.jsp");
		}
		return mv;
	}

	@RequestMapping("/changePWD.htm")
	public ModelAndView changePassword(HttpServletRequest request,
			HttpServletResponse response) {
		int status = -1;

		HttpSession session = request.getSession();
		Visitor visitor = (Visitor) session.getAttribute("VISITOR");

		if (visitor != null) {
			log.info("Changing visitor password with VisitorID :"
					+ visitor.getVisitorId());

			String password = request.getParameter("password");

			if (password != null) {
				visitor.setPassword(password);

				VisitorService vServiceImpl = new VisitorServiceImpl();

				try {
					status = vServiceImpl.changePassword(visitor);
				} catch (FERSGenericException e) {
					status = -5;
					log.error(e.getMessage(), e);
				}
			} else {
				log.error("Password cannot be blank");
			}

			log.info("Visitor password changed :" + status);
		} else {
			log.error("Visitor details are invalid");
		}

		ModelAndView mv = new ModelAndView();

		if (status > 0) {
			mv.addObject("status", "success");
			mv.setViewName("/changePWD.jsp");
		} else if (status == -5) {
			mv.addObject("status", "error");
			mv.addObject("errorMsg",
					"System error occurred, Please verify log file for more details");
			mv.setViewName("/changePWD.jsp");
		} else if (status == -10) {
			mv.addObject("status", "error");
			mv.addObject(
					"errorMsg",
					"New password must be different from current password, please choose a different password and retry");
			mv.setViewName("/changePWD.jsp");
		} else {
			mv.addObject("status", "error");
			mv.addObject(
					"errorMsg",
					"Error while changing password.. Please verify visitor and password details and retry again");
			mv.setViewName("/changePWD.jsp");
		}
		return mv;
	}

	/**
	 * <br/>
	 * METHOD DESCRIPTION: <br/>
	 * This method is to unregister event by the visitor and tickets will be
	 * freed to other visitors<br/>
	 * 
	 * @return ModelAndView
	 * 
	 * @param request
	 * @param response
	 * 
	 * @throws Exception
	 */

	@RequestMapping("/eventunreg.htm")
	public ModelAndView unregisterEvent(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request == null || response == null) {
			log.info("Request or Response failed for UNREGISTEREVENT METHOD..");
			throw new FERSGenericException(
					"Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder",
					new NullPointerException());
		}

		HttpSession session = request.getSession();
		Visitor visitor = (Visitor) session.getAttribute("VISITOR");
		int eventid = Integer.parseInt(request.getParameter("eventId"));
		int eventsessionid = Integer.parseInt(request
				.getParameter("eventsessionid"));

		log.info("Unregistering for the event :" + eventid);

		VisitorService vServiceImpl = new VisitorServiceImpl();
		vServiceImpl.unregisterEvent(visitor, eventid, eventsessionid);

		List<Object[]> regList = new ArrayList<Object[]>();
		regList = vServiceImpl.showRegisteredEvents(visitor);

		List<Object[]> eventList = new ArrayList<Object[]>();
		EventService serviceImpl = new EventServiceImpl();

		serviceImpl.updateEventDeletions(eventid, eventsessionid);

		log.info("Seats allocated for the event are released :" + eventid);

		eventList = serviceImpl.getAllEvents();

		ModelAndView mv = new ModelAndView();
		mv.addObject("visitor", visitor);
		mv.addObject("allEvents", eventList);
		mv.addObject("regEvents", regList);
		mv.setViewName("/visitormain.jsp");
		return mv;
	}

	/**
	 * <br/>
	 * METHOD DESCRIPTION: <br/>
	 * This method is for searching for events based on an event name entered on
	 * the visitor portal page by the user <br/>
	 * 
	 * @return ModelAndView
	 * 
	 * @param request
	 * @param response
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/searchEventByName.htm")
	public ModelAndView searchEventsByName(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request == null || response == null) {
			log.info("Request or Response failed for UNREGISTEREVENT METHOD..");
			throw new FERSGenericException(
					"Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder",
					new NullPointerException());
		}

		HttpSession session = request.getSession();
		Visitor visitor = (Visitor) session.getAttribute("VISITOR");
		String eventname = request.getParameter("eventname");

		VisitorService vServiceImpl = new VisitorServiceImpl();

		log.info("Search event by name called..." + eventname);

		List<Object[]> eventList = new ArrayList<Object[]>();
		EventService serviceImpl = new EventServiceImpl();

		eventList = serviceImpl.getAllEvents(eventname);

		List<Object[]> regList = new ArrayList<Object[]>();
		regList = vServiceImpl.showRegisteredEvents(visitor);

		ModelAndView mv = new ModelAndView();
		mv.addObject("visitor", visitor);
		mv.addObject("allEvents", eventList);
		mv.addObject("regEvents", regList);
		mv.setViewName("/visitormain.jsp");
		return mv;
	}

	/**
	 * <br/>
	 * METHOD DESCRIPTION: <br/>
	 * This method is for searching for events based on the event name entered
	 * on the Event Catalog page by the Visitor <br/>
	 * 
	 * @return ModelAndView
	 * 
	 * @param request
	 * @param response
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/searchEventByNameCatalog.htm")
	public ModelAndView searchEventsByNameCatalog(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request == null || response == null) {
			log.info("Request or Response failed for UNREGISTEREVENT METHOD..");
			throw new FERSGenericException(
					"Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder",
					new NullPointerException());
		}

		HttpSession session = request.getSession();
		Visitor visitor = (Visitor) session.getAttribute("VISITOR");
		String eventname = request.getParameter("eventname");

		log.info("Search event by name called in catalog..." + eventname);

		List<Object[]> eventList = new ArrayList<Object[]>();
		EventService serviceImpl = new EventServiceImpl();

		eventList = serviceImpl.getAllEvents(eventname);

		ModelAndView mv = new ModelAndView();
		mv.addObject("visitor", visitor);
		mv.addObject("allEvents", eventList);
		mv.setViewName("/eventCatalog.jsp");
		return mv;
	}

	/**
	 * <br/>
	 * METHOD DESCRIPTION: <br/>
	 * This method is for displaying data of events to be displayed in Ascending
	 * order <br/>
	 * 
	 * @return ModelAndView
	 * 
	 * @param request
	 * @param response
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/displayasc.htm")
	public ModelAndView showEventsAsc(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request == null || response == null) {
			log.info("Request or Response failed for UNREGISTEREVENT METHOD..");
			throw new FERSGenericException(
					"Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder",
					new NullPointerException());
		}

		HttpSession session = request.getSession();
		Visitor visitor = (Visitor) session.getAttribute("VISITOR");

		VisitorService vServiceImpl = new VisitorServiceImpl();

		log.info("Show events in Ascending order...");

		List<Object[]> eventList = new ArrayList<Object[]>();
		EventService serviceImpl = new EventServiceImpl();

		eventList = serviceImpl.getAllEventsAsc();

		List<Object[]> regList = new ArrayList<Object[]>();
		regList = vServiceImpl.showRegisteredEvents(visitor);

		ModelAndView mv = new ModelAndView();
		mv.addObject("visitor", visitor);
		mv.addObject("allEvents", eventList);
		mv.addObject("regEvents", regList);
		mv.setViewName("/visitormain.jsp");
		return mv;
	}

	/**
	 * <br/>
	 * METHOD DESCRIPTION: <br/>
	 * This method is for displaying data of events to be displayed on the
	 * visitor's home page in descending order <br/>
	 * 
	 * @return ModelAndView
	 * 
	 * @param request
	 * @param response
	 * 
	 * @throws Exception
	 * 
	 */
	@RequestMapping("/displaydesc.htm")
	public ModelAndView showEventsDesc(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request == null || response == null) {
			log.info("Request or Response failed for UNREGISTEREVENT METHOD..");
			throw new FERSGenericException(
					"Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder",
					new NullPointerException());
		}

		HttpSession session = request.getSession();
		Visitor visitor = (Visitor) session.getAttribute("VISITOR");

		VisitorService vServiceImpl = new VisitorServiceImpl();

		log.info("Show events in Descending order...");

		List<Object[]> eventList = new ArrayList<Object[]>();
		EventService serviceImpl = new EventServiceImpl();

		eventList = serviceImpl.getAllEventsDesc();

		List<Object[]> regList = new ArrayList<Object[]>();
		regList = vServiceImpl.showRegisteredEvents(visitor);

		ModelAndView mv = new ModelAndView();
		mv.addObject("visitor", visitor);
		mv.addObject("allEvents", eventList);
		mv.addObject("regEvents", regList);
		mv.setViewName("/visitormain.jsp");
		return mv;
	}

	/**
	 * <br/>
	 * METHOD DESCRIPTION: <br/>
	 * This method is for displaying data of events to be displayed in ascending
	 * order on the event catalog page. <br/>
	 * 
	 * @return ModelAndView
	 * 
	 * @param request
	 * @param response
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/displaycatalogasc.htm")
	public ModelAndView showEventsCatalogAsc(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request == null || response == null) {
			log.info("Request or Response failed for UNREGISTEREVENT METHOD..");
			throw new FERSGenericException(
					"Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder",
					new NullPointerException());
		}

		HttpSession session = request.getSession();
		Visitor visitor = (Visitor) session.getAttribute("VISITOR");

		log.info("Show events of catalogue in Ascending order...");

		List<Object[]> eventList = new ArrayList<Object[]>();
		EventService serviceImpl = new EventServiceImpl();

		eventList = serviceImpl.getAllEventsAsc();

		ModelAndView mv = new ModelAndView();
		mv.addObject("visitor", visitor);
		mv.addObject("allEvents", eventList);
		mv.setViewName("/eventCatalog.jsp");
		return mv;
	}

	/**
	 * <br/>
	 * METHOD DESCRIPTION: <br/>
	 * This method is for displaying data of events to be displayed in
	 * Descending order on the Event Catalog page. <br/>
	 * 
	 * @return ModelAndView
	 * 
	 * @param request
	 * @param response
	 * 
	 * @throws Exception
	 * 
	 */
	@RequestMapping("/displaycatalogdesc.htm")
	public ModelAndView showEventsCatalogDesc(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request == null || response == null) {
			log.info("Request or Response failed for UNREGISTEREVENT METHOD..");
			throw new FERSGenericException(
					"Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder",
					new NullPointerException());
		}

		HttpSession session = request.getSession();
		Visitor visitor = (Visitor) session.getAttribute("VISITOR");

		log.info("Show events of catalogue in Descending order...");

		List<Object[]> eventList = new ArrayList<Object[]>();
		EventService serviceImpl = new EventServiceImpl();

		eventList = serviceImpl.getAllEventsDesc();

		ModelAndView mv = new ModelAndView();
		mv.addObject("visitor", visitor);
		mv.addObject("allEvents", eventList);
		mv.setViewName("/eventCatalog.jsp");
		return mv;
	}
}
