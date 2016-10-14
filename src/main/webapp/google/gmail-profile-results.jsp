<%@ include file="/init.jsp" %>

<c:set var="title" value="Google GMail Profile Results" />

<html>

<jsp:include page="/includes/header.jsp">
	<jsp:param name="title" value="${title}" />
</jsp:include>

<body>

<jsp:include page="/includes/body_header.jsp">
	<jsp:param name="title" value="${title}" />
</jsp:include>

<h3>GMail Profile</h3>

<table>
	<tr>
		<th>
			Email Address:
		</th>
		<td>
			<c:out value="${sessionScope[AttrConstants.ATTR_RESPONSE].emailAddress}" />
		</td>
	</tr>
	<tr>
		<th>
			Messages Total:
		</th>
		<td>
			<c:out value="${sessionScope[AttrConstants.ATTR_RESPONSE].messagesTotal}" />
		</td>
	</tr>
	<tr>
		<th>
			Threads Total:
		</th>
		<td>
			<c:out value="${sessionScope[AttrConstants.ATTR_RESPONSE].threadsTotal}" />
		</td>
	</tr>
	<tr>
		<th>
			History ID:
		</th>
		<td>
			<c:out value="${sessionScope[AttrConstants.ATTR_RESPONSE].historyId}" />
		</td>
	</tr>
</table>

</body>
</html>