<%@ include file="/init.jsp" %>

<c:set var="title" value="OAuth1 Test Page" />

<html>

<jsp:include page="/includes/header.jsp">
	<jsp:param name="title" value="${title}" />
</jsp:include>

<body>

<jsp:include page="/includes/body_header.jsp">
	<jsp:param name="title" value="${title}" />
</jsp:include>

<p>
Toe maar!
</p>

<input
	type="button"
	onclick="location.href = '${URLUtil.getActionPathOAuth1Init(pageContext.request)}'"
	value="Start OAuth1 Authorisation" />

</body>
</html>
