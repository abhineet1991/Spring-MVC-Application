package com.helper;

/**
 * Helper class for externalization of queries
 */
public class FERSDbQuery {
	
	// SQL queries for EVENTDAO
	private String searchEvent = "";
	private String searchByEventName = "";
	private String searchEventAsc = "";
	private String searchEventDesc = "";
	private String updateEvent = "";
	private String checkEvent = "";
	private String updateDeleteEvent = "";
	private String getEvent = "";
	private String updateTEvent = "";
	private String updateEventSession = "";
	private String insertEvent = "";
	private String selectEventCoordinator = "";
	private String insertEventSession = "";
	private String selectMaxEventId = "";
	private String selectMaxEventSessionId = "";
	private String deleteEvent = "";
	private String deleteEventSession = "";
	private String deleteEventSessionSignup = "";

    // TO DO 

	public void setSearchEvent(String searchEvent) {
		this.searchEvent = searchEvent;
	}

	public String getUpdateEvent() {
		return updateEvent;
	}

	public void setUpdateEvent(String updateEvent) {
		this.updateEvent = updateEvent;
	}

	public String getCheckEvent() {
		return checkEvent;
	}

	public void setCheckEvent(String checkEvent) {
		this.checkEvent = checkEvent;
	}

	public String getUpdateDeleteEvent() {
		return updateDeleteEvent;
	}

	public void setUpdateDeleteEvent(String updateDeleteEvent) {
		this.updateDeleteEvent = updateDeleteEvent;
	}

	public String getInsertQuery() {
		return insertQuery;
	}

	public void setInsertQuery(String insertQuery) {
		this.insertQuery = insertQuery;
	}

	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}

	public String getRegisterQuery() {
		return registerQuery;
	}

	public void setRegisterQuery(String registerQuery) {
		this.registerQuery = registerQuery;
	}

	public String getStatusQuery() {
		return statusQuery;
	}

	public void setStatusQuery(String statusQuery) {
		this.statusQuery = statusQuery;
	}

	public String getUpdateQuery() {
		return updateQuery;
	}

	public void setUpdateQuery(String updateQuery) {
		this.updateQuery = updateQuery;
	}

	public String getDeleteEventQuery() {
		return deleteEventQuery;
	}

	public void setDeleteEventQuery(String deleteEventQuery) {
		this.deleteEventQuery = deleteEventQuery;
	}

	public String getValidateVisitor() {
		return validateVisitor;
	}

	public void setValidateVisitor(String validateVisitor) {
		this.validateVisitor = validateVisitor;
	}

	public String getSearchByEventName() {
		return searchByEventName;
	}

	public void setSearchByEventName(String searchByEventName) {
		this.searchByEventName = searchByEventName;
	}

	public String getSearchEventAsc() {
		return searchEventAsc;
	}

	public void setSearchEventAsc(String searchEventAsc) {
		this.searchEventAsc = searchEventAsc;
	}

	public String getSearchEventDesc() {
		return searchEventDesc;
	}

	public void setSearchEventDesc(String searchEventDesc) {
		this.searchEventDesc = searchEventDesc;
	}

	public String getGetEvent() {
		return getEvent;
	}

	public void setGetEvent(String getEvent) {
		this.getEvent = getEvent;
	}
	
	public String getUpdateTEvent() {
		return updateTEvent;
	}

	public void setUpdateTEvent(String updateTEvent) {
		this.updateTEvent = updateTEvent;
	}

	public String getInsertEventSession() {
		return insertEventSession;
	}

	public void setInsertEventSession(String insertEventSession) {
		this.insertEventSession = insertEventSession;
	}

	public String getSelectMaxEventId() {
		return selectMaxEventId;
	}

	public void setSelectMaxEventId(String selectMaxEventId) {
		this.selectMaxEventId = selectMaxEventId;
	}

	public String getSelectMaxEventSessionId() {
		return selectMaxEventSessionId;
	}

	public void setSelectMaxEventSessionId(String selectMaxEventSessionId) {
		this.selectMaxEventSessionId = selectMaxEventSessionId;
	}

	public String getDeleteEvent() {
		return deleteEvent;
	}

	public void setDeleteEvent(String deleteEvent) {
		this.deleteEvent = deleteEvent;
	}

	public String getDeleteEventSession() {
		return deleteEventSession;
	}

	public void setDeleteEventSession(String deleteEventSession) {
		this.deleteEventSession = deleteEventSession;
	}

	public String getDeleteEventSessionSignup() {
		return deleteEventSessionSignup;
	}

	public void setDeleteEventSessionSignup(String deleteEventSessionSignup) {
		this.deleteEventSessionSignup = deleteEventSessionSignup;
	}
	
	public String getChangePWDQuery() {
		return changePWDQuery;
	}

	public void setChangePWDQuery(String changePWDQuery) {
		this.changePWDQuery = changePWDQuery;
	}
	
	public String getVerifyPWDQuery() {
		return verifyPWDQuery;
	}

	public void setVerifyPWDQuery(String verifyPWDQuery) {
		this.verifyPWDQuery = verifyPWDQuery;
	}

}
