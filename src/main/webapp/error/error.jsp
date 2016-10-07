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

<h3>Details</h3>

<table>
	<tr>
		<th>
			Status:
		</th>
		<td>
			<c:choose>
				<c:when test="${not empty requestScope[AttrConstants.ERROR_STATUS_CODE]}">
					<c:out value="${requestScope[AttrConstants.ERROR_STATUS_CODE]}" />
				</c:when>
				<c:otherwise>
					200
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr>
		<th>
			Code:
		</th>
		<td>
			<c:choose>
				<c:when test="${not empty requestScope[AttrConstants.ERROR_EXCEPTION]}">
					<c:out value="${ErrorConstants.ERROR_EXCEPTION}" />
				</c:when>
				<c:otherwise>
					<c:out value="${sessionScope[AttrConstants.ATTR_ERROR]}" />
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr>
		<th>
			Description:
		</th>
		<td>
			<c:choose>
				<c:when test="${not empty requestScope[AttrConstants.ERROR_EXCEPTION]}">
					<c:out value="${requestScope[AttrConstants.ERROR_MESSAGE]}" />
				</c:when>
				<c:otherwise>
					<c:out value="${sessionScope[AttrConstants.ATTR_ERROR_DESCRIPTION]}" />
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<c:if test="${not empty sessionScope[AttrConstants.ATTR_ERROR_URI]}">
		<tr>
			<th>
				URI:
			</th>
			<td>
				<c:out value="${sessionScope[AttrConstants.ATTR_ERROR_URI]}" />
			</td>
		</tr>
	</c:if>
	<c:if test="${not empty requestScope[AttrConstants.ERROR_EXCEPTION]}">
		<tr>
			<th>
				Exception:
			</th>
			<td>
				<c:out value="${requestScope[AttrConstants.ERROR_EXCEPTION]}" />
			</td>
		</tr>
	</c:if>
</table>

</body>
</html>
