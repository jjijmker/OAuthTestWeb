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
<c:choose>
	<c:when test="${errorCode == ErrorConstants.ERROR_INVALID_ACTION_PATH}">
		Invalid action path
    </c:when>
	<c:when test="${errorCode == 200}">
		Lalalala
	</c:when>
	<c:otherwise>
		Unknown Error
	</c:otherwise>
</c:choose>
</p>

</body>
</html>
