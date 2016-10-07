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

<p><c:out value="${param.server}"/></p>

<form name="actionForm" action="${URLUtil.getActionFormAction(pageContext.request)}" method="get">

<input type="hidden" name="executeAction" value="false" />

<select name="server" onchange="actionForm.submit()">
	<option value="empty">Select</option>
<c:forEach var="server" items="${ConfigUtil.getServers()}">
	<option value="${server}" <c:if test="${param.server == server}">selected</c:if>>
		<c:out value="${server}"/>
	</option>
</c:forEach>
</select>

<select name="resource">
	<option value="empty">Select</option>
<c:forEach var="resource" items="${ConfigUtil.getResources(param.server)}">
	<option value="${resource}" <c:if test="${param.resource == resource}">selected</c:if>>
		<c:out value="${resource}"/>
	</option>
</c:forEach>
</select>

<select name="securityAction">
	<option value="empty">Select</option>
<c:forEach var="securityAction" items="${ConfigUtil.getSecurityActions(param.server)}">
	<option value="${securityAction}" <c:if test="${param.securityAction == securityAction}">selected</c:if>>
		<c:out value="${securityAction}"/>
	</option>
</c:forEach>
</select>

<input type="submit" value="Execute" onclick="actionForm.executeAction.value = true" />

</form>

</body>
</html>
