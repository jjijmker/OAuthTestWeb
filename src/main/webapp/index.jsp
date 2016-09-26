<%@ include file="/init.jsp" %>

<c:set var="title" value="Main Page" />

<html>

<jsp:include page="/includes/header.jsp">
	<jsp:param name="title" value="${title}" />
</jsp:include>

<body>

<jsp:include page="/includes/body_header.jsp">
	<jsp:param name="title" value="${title}" />
</jsp:include>

<p>
This App uses LinkedIn to explore the workings of OAuth.
It retrieves user details from LinkedIn using LinkedIn API calls.
It authorises the retrieval using OAuth access tokens.
</p>

<p>
This App supports <a href="oauth1/">OAuth1</a> and <a href="oauth2/">OAuth2</a>.
</p>

<form name="actionForm" action="${URLUtil.getActionFormAction(pageContext.request)}" method="get">

<input type="hidden" name="executeAction" value="false" />

<select name="resourceServer" onchange="actionForm.submit()">
	<option value="empty">Select</option>
<c:forEach var="resourceServer" items="${ConfigUtil.getResourceServers()}">
	<c:if test="${param.resourceServer == resourceServer}">
		<c:set var="isSelected" value="selected"/>
	</c:if>
	<option value="${resourceServer}" ${isSelected}>
		<c:out value="${resourceServer}"/>
	</option>
</c:forEach>
</select>

<select name="resourceServerAction">
	<option value="empty">Select</option>
<c:forEach var="resourceServerAction" items="${ConfigUtil.getResourceServerActions(param.resourceServer)}">
	<c:if test="${param.resourceServerAction == resourceServerAction}">
		<c:set var="isSelected" value="selected"/>
	</c:if>
	<option value="${resourceServerAction}" ${isSelected}>
		<c:out value="${resourceServerAction}"/>
	</option>
</c:forEach>
</select>

<input type="submit" value="Execute" onclick="actionForm.executeAction.value = true" />

</form>

</body>
</html>
