<!-- 
	External HTTP Error:
	sessionScope[AttrConstants.ATTR_ERROR_STATUS_CODE]
	sessionScope[AttrConstants.ATTR_ERROR_DESCRIPTION]
	
	Internal Technical Error (exception or HTTP error):
	requestScope[AttrConstants.ATTR_SERVLET_ERROR_STATUS_CODE]
	requestScope[AttrConstants.ATTR_SERVLET_ERROR_MESSAGE]
	requestScope[AttrConstants.ATTR_SERVLET_ERROR_EXCEPTION]
	
	Functional Errors:
	sessionScope[AttrConstants.ATTR_ERROR]
	sessionScope[AttrConstants.ATTR_ERROR_DESCRIPTION]
	
		OAuth2 - Authorization Errors:
			error = user_cancelled_login (user aborts before login)
			error = user_cancelled_authorize (user aborts before grant)
 -->

<%@ include file="/init.jsp" %>

<c:set var="title" value="Error" />

<html>

<jsp:include page="/includes/header.jsp">
	<jsp:param name="title" value="${title}" />
</jsp:include>

<body>

<jsp:include page="/includes/body_header.jsp">
	<jsp:param name="title" value="${title}" />
</jsp:include>

<p>
<a href="/index.jsp">Home</a> | <a href="/status.jsp">Status</a>
</p>

<table>
	<tr>
		<th align="right" valign="top" width="15%">
			Status:
		</th>
		<td width="85%">
			<c:out value="${sessionScope[AttrConstants.ATTR_DISPLAY_ERROR].getStatusCode()}" />
		</td>
	</tr>
	<tr>
		<th align="right" valign="top">
			Origin:
		</th>
		<td>
			<c:out value="${FormattingUtil.formatOrigin(sessionScope[AttrConstants.ATTR_DISPLAY_ERROR].getOrigin())}" />
		</td>
	</tr>
	<tr>
		<th align="right" valign="top">
			Message:
		</th>
		<td>
			<c:out value="${sessionScope[AttrConstants.ATTR_DISPLAY_ERROR].getMessage()}" />
		</td>
	</tr>
	<c:if test="${not empty sessionScope[AttrConstants.ATTR_DISPLAY_ERROR].getStackTrace()}">
		<tr>
			<th align="right" valign="top">
				Stack Trace:
			</th>
			<td>
				<c:out value="${sessionScope[AttrConstants.ATTR_DISPLAY_ERROR].getStackTrace()}" />
			</td>
		</tr>
	</c:if>
	<c:if test="${not empty sessionScope[AttrConstants.ATTR_DISPLAY_ERROR].getExtraInfo()}">
		<tr>
			<th align="right" valign="top">
				Extra Info:
			</th>
			<td>
				<c:out value="${sessionScope[AttrConstants.ATTR_DISPLAY_ERROR].getExtraInfo()}" />
			</td>
		</tr>
	</c:if>
</table>

</body>
</html>
