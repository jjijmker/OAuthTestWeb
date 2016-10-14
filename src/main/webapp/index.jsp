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

<form name="actionForm" action="${URLUtil.getActionFormAction(pageContext.request)}" method="get">

<input type="hidden" name="executeAction" value="false" />

<select name="server" onchange="actionForm.submit()" style="width: 100px;">
	<option value="empty">Select</option>
<c:forEach var="server" items="${ConfigUtil.getServers()}">
	<option value="${server}" <c:if test="${param.server == server}">selected</c:if>>
		<c:out value="${ConfigUtil.getServerName(server)}"/>
	</option>
</c:forEach>
</select>

<select name="resourceAction" style="width: 100px;">
	<option value="empty">Select</option>
<c:forEach var="resourceAction" items="${ConfigUtil.getResourceActions(param.server)}">
	<option value="${resourceAction}" <c:if test="${param.resourceAction == resourceAction}">selected</c:if>>
		<c:out value="${ConfigUtil.getResourceActionName(param.server, resourceAction)}"/>
	</option>
</c:forEach>
</select>

<select name="securityAction" style="width: 100px;">
	<option value="empty">Select</option>
<c:forEach var="securityAction" items="${ConfigUtil.getSecurityActions(param.server)}">
	<option value="${securityAction}" <c:if test="${param.securityAction == securityAction}">selected</c:if>>
		<c:out value="${ConfigUtil.getSecurityActionName(securityAction)}"/>
	</option>
</c:forEach>
</select>

<input type="submit" value="Execute" onclick="actionForm.executeAction.value = true" />

</form>

</body>
</html>
