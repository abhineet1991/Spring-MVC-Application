<%@ include file="/include.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<html>
<head>
<title>Welcome to Festival Registration System</title>
<script language="JavaScript">
<!--
var nHist = window.history.length;
if(window.history[nHist] != window.location)
  window.history.forward();
//-->
</script>
<script type="text/javascript">
function isNumeric(value) {
	  if (value=="" || value == null || !value.toString().match(/^[-]?\d*\.?\d*$/))
	  { return false;
	  }
	  return true;
	}		
	function echeck(str) {
		   var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;   
			   return emailPattern.test(str);   							
	}
	function validateForm()
	{
	var eventName=document.forms["regform"]["eventName"].value;
	var desc=document.forms["regform"]["desc"].value;
	var place=document.forms["regform"]["place"].value;
	var duration=document.forms["regform"]["duration"].value;	
	var eventType=document.forms["regform"]["eventType"].value;
	var ticket=document.forms["regform"]["ticket"].value;	
	if (eventName==null || eventName=="")
	  {
	  alert("Please provide event name");
	  return false;
	  }
	if (desc==null || desc=="")
	  {
	  alert("Please provide event description");
	  return false;
	  }
	if (place==null || place=="")
	  {
	  alert("Please provide place");
	  return false;
	  }
	if (duration==null || duration=="")
	  {
	  alert("Please provide duration");
	  return false;
	  }
	if (eventType==null || eventType=="")
	  {
	  alert("Please provide event type");
	  return false;
	  }
	if (ticket==null || ticket=="" || ticket==0)
	  {
	  alert("Please provide ticket");
	  return false;
	  }
	
	if(isNumeric(ticket)==false)
	{
		alert("Invalid ticket");
		return false;
	}
}
	function cancelRegistration()
	{
		history.go(-1);
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="StyleSheet" href="css/struts2.css" type="text/css" />
<style type="text/css">
<!--
.style1 {
	font-size: 12
}
-->
</style>
<style>
.error {
	color: red;
	font-weight: bold;
	font-size: 20px;
}
</style>
</head>

<body>	
	<br/><br/>
	<form action="updateEvent.htm" name="regform" method="post" onsubmit="return validateForm()">
	<input type="hidden" name="eventId" value="${event.eventid}"/>	
	<input type="hidden" name="sessionId" value="${event.sessionId}"/>	
	<input type="hidden" name="isAdd" value="${event.add}"/>
	<table width="80%" align="center"  border="2">
		<tbody><tr>
			<td>
			<div id="header">&nbsp;
			<div align="center">Festival Registration System</div>
			</div>
			
			<table>
				<tbody><tr>
					<td width="100%">
					<table align="right" cellpadding="2">
						<tbody><tr>
							<td width="90">
							<div id="menu" align="center"><a href="<jstlcore:url value="/catalog.htm"/>">
							EventCatalog_Page </a></div>
							</td>							
						</tr>
					</tbody></table>
					</td>
				</tr>
				<tr>
					<td width="900">
					<div id="content">
					
					<table align="center" border="0">
						
						<tbody><tr>
							<td align="center" colspan="2">
							<h3>New Event Registration Page</h3>
							</td>
						</tr>
						<tr><td align="center" colspan="2" style="font-style: italic;">Fields marked (<span style="font-weight: bold;color: red; font-size: 15px;">*</span>) are Mandatory</td></tr>
						<tr><td></td><td></td></tr>
						<tr><td></td><td></td></tr>
						<tr>	
										
							<td><span style="font-weight: bold;color: red;font-size: 15px;">*</span>
							Event Name:</td><td> 
							   <input type="text" name="eventName" size="25" value="${event.name}"></input></td> 							
					   </tr>
					   <tr>			
							<td><span style="font-weight: bold;color: red;font-size: 15px;">*</span>
							Event Description: </td><td><input type="text" name="desc" size="25" value="${event.description}"></input></td>							
					   </tr>
					   <tr>
							<td><span style="font-weight: bold;color: red;font-size: 15px;">*</span>
							Places:</td><td><input type="text" name="place" size="25" value="${event.place}"></input></td> 							
					   </tr>
					   <tr>
						    <td><span style="font-weight: bold;color: red;font-size: 15px;">*</span>
						    Duration:</td><td><input type="text" name="duration" size="27" value="${event.duration}"></input></td>						   
					   </tr>
					   <tr>
						    <td><span style="font-weight: bold;color: red;font-size: 15px;">*</span>
						    Event Type:</td><td><input type="text" name="eventType" size="27" value="${event.eventtype}"></input></td>						   
					   </tr>
					   <tr>
						   <td><span style="font-weight: bold;color: red;font-size: 15px;">*</span>
						    Available Tickets: </td><td> <input type="text" name="ticket" size="25" value="${event.seatsavailable}"></input></td> 						    
					   </tr>
					   <jstlcore:if test="${event.add == true}">					   
					   <tr>
                           <td><span style="font-weight: bold;color: red;font-size: 15px;">*</span>Event Coordinator:</td>
                            <td>
                            <Select name="coordinator" id="coordinator">  
                            	<jstlcore:forEach items="${eventCoordinator}" var="eventCoordinator" >
                            		<option value="${eventCoordinator.eventcoordinatorid}"><jstlcore:out value="${eventCoordinator.userName}"></jstlcore:out></option>                           		
                            	</jstlcore:forEach>
                            </Select>
                            </td>                           
					   </tr>
					   <tr>
                           <td><span style="font-weight: bold;color: red;font-size: 15px;">*</span>Number of Event Sessions:</td>
                            <td>
                            <Select name="eventSession" id="eventSession">                              	
                            		<option value="1"><jstlcore:out value="1"></jstlcore:out></option>   
                            		<option value="2"><jstlcore:out value="2"></jstlcore:out></option> 
                            		<option value="3"><jstlcore:out value="3"></jstlcore:out></option> 
                            		<option value="4"><jstlcore:out value="4"></jstlcore:out></option> 
                            		<option value="5"><jstlcore:out value="5"></jstlcore:out></option> 		
                            </Select>
                            </td>                           
					   </tr>	
					  </jstlcore:if>					  			    	 
					   <tr>	
						   <td colspan="2" align="right">	
						   <jstlcore:if test="${event.add == true}">									
						   	 <input value="Add New Event" type="submit">
						    </jstlcore:if>
						     <jstlcore:if test="${event.add == false}">									
						   	 <input value="Update Event" type="submit">
						    </jstlcore:if>
						   </td>
						</tr>
						<tr>						
						</tr>
					</tbody></table>
					</div>
					</td>
					<!-- content end -->
				</tr>
			</tbody>
			</table>
			</td>			
		</tr>
		<tr><td colspan="4" align="center"><div style="font-size: 15px; color: red; font-weight: bold;">${REGISTRATIONSTATUSMESSAGE}</div></td></tr>
	</tbody></table>
	
	</form>
</body>

</html>
