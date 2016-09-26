<%@ include file="/init.jsp" %>

<c:set var="title" value="OAuth1 Authorised Page" />

<html>

<jsp:include page="/includes/header.jsp">
	<jsp:param name="title" value="${title}" />
</jsp:include>

<body>

<jsp:include page="/includes/body_header.jsp">
	<jsp:param name="title" value="${title}" />
</jsp:include>

<table>
	<tr>
		<th>
			Token
		</th>
		<td>
			<c:out value="${sessionScope[AttrConstants.ATTR_ACCESS_TOKEN].token}" />
		</td>
	</tr>
	<tr>
		<th>
			Token Secret
		</th>
		<td>
			<c:out value="${sessionScope[AttrConstants.ATTR_ACCESS_TOKEN].tokenSecret}" />
		</td>
	</tr>
</table>

<p>
Toe maar!
</p>

<input
	type="button"
	onclick="location.href = '${URLUtil.getActionPathLinkedInRetrieve(pageContext.request)}'"
	value="Start LinkedIn Retrieval" />

</body>
</html>