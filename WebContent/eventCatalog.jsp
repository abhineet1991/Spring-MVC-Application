<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/include.jsp"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Welcome to Festival Event Registration System</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="StyleSheet" href="css/struts2.css" type="text/css" />
<script language="JavaScript">
<!--
var nHist = window.history.length;
if(window.history[nHist] != window.location)
  window.history.forward();
//-->
</script>
<script type="text/javascript">
function previousPage()
{
	history.go(-1);
}
function validateForm()
{
var ename=document.forms["logForm"]["eventname"].value;
if (ename==null || ename=="")
{
alert("Please provide Eventname to Search..");
return false;
}
}
</script>
<style type="text/css">
<!--
.style1 {
	font-size: 12px
}
-->
</style>
</head>
<body>
<form name="logForm" method="post" action="searchEventByNameCatalog.htm">
<br/><br/><br/>
	<table width="80%" align="center" border="2">
		<tr>
			<td>
			<div id="header">&nbsp;
			<div align="center">Festival
			Registration System</div>
			</div>

			<table>
				<tr>
					<td width="100%">
					<table align="right" cellpadding="2">
						<tr>
							<td width="90">
							<div id="menu" align="center"><a href="<jstlcore:url value="/index.jsp"/>">
							Logout </a></div>
							</td>
							<td width="160">
							<div id="menu" ><a href="<jstlcore:url value="/searchVisitor.htm"/>">
							My_Portal </a></div>
							</td>
							<jstlcore:if test="${visitor.admin == true}">	
								<td width="160">
									<div id="menu" >
										<a href="<jstlcore:url value="/displayEvent.htm?eventId=-1"/>">Add_Event </a>								
									</div>
								</td>
							</jstlcore:if>	
							<td width="90">
							<div id="menu" align="center"><a href="<jstlcore:url value="/about.jsp"/>">
							About</a><br />
							</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td width="900">
					<div align="center"><img src="images/greenhorizontalline.jpg"
						height="5" width="100%" /></div>
					<br />
					<div id="content" align="center">
					<h3>Up-coming Events</h3>
					<p class="content">Enter eventname to search <input type="text" name="eventname"></input>
					<input type="submit" value="Search"></input>
					</p>
					<table class="content" width="96%" border="1" align="center">
				<tr bgcolor="#669966">
					<th scope="col">Event id</th>
					<th scope="col">Event name<br/>
					<a href="<jstlcore:url value="/displaycatalogasc.htm"/>"><img src="images/upointer.jpg"/></a>
					<a href="<jstlcore:url value="/displaycatalogdesc.htm"/>"><img src="images/dpointer.jpg"/></a>
					</th>
					<th scope="col">description</th>
					<th scope="col">Places</th>
					<th scope="col">Duration</th>
					<th scope="col">Event type</th>
					<th scope="col">Event Session</th>
					<th scope="col">Available Tickets</th>
					<jstlcore:if test="${visitor.admin == true}">	
						<th scope="col">Update</th>
						<th scope="col">Delete</th>
					</jstlcore:if>	
				</tr>
				<jstlcore:forEach items="${allEvents}" var="event" >
					<tr>
					<td align="center"><jstlcore:out value="${event[0]}"></jstlcore:out></td>
					<td align="center"><jstlcore:out value="${event[1]}"></jstlcore:out></td>
					<td align="center"><jstlcore:out value="${event[2]}"></jstlcore:out></td>
					<td align="center"><jstlcore:out value="${event[5]}"></jstlcore:out></td>
					<td align="center"><jstlcore:out value="${event[3]}"></jstlcore:out></td>
					<td align="center"><jstlcore:out value="${event[4]}"></jstlcore:out></td>
					<td align="center"><jstlcore:out value="${event[7]}"></jstlcore:out></td>
					
				<jstlcore:choose>
					  <jstlcore:when test="${event[6] != 0}">
							<td width="100px" bgcolor="#CCCC99" align="center">
							<jstlcore:out value="${event[6]}"></jstlcore:out>
							<strong> seats left.</strong>						 	   						 	 
							</td>
						</jstlcore:when>		
						<jstlcore:otherwise>
							<td width="100px" bgcolor="#CCCC99" align="center"><strong>No
							seats left</strong></td>
						</jstlcore:otherwise>						
						</jstlcore:choose>
						<jstlcore:if test="${visitor.admin == true}">	
							<td align="center">
								<a href="<jstlcore:url value="/displayEvent.htm?eventId=${event[0]}&sessionId=${event[7]}"/>"> Update </a>								
							</td>						
							<td align="center">
								<a href="<jstlcore:url value="/deleteEvent.htm?eventId=${event[0]}&sessionId=${event[7]}"/>"> Delete </a>								
							</td>	
						</jstlcore:if>						
					</tr>
				</jstlcore:forEach>
			
				
			</table>
	
			</div>
			</td>
		</tr>
	</table>		
	</table>
</form>
</body>

</html>
