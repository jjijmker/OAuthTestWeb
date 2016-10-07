<%@ include file="/init.jsp" %>

<c:set var="title" value="LinkedIn Retrieve JSON Results" />

<html>

<jsp:include page="/includes/header.jsp">
	<jsp:param name="title" value="${title}" />
</jsp:include>

<body>

<jsp:include page="/includes/body_header.jsp">
	<jsp:param name="title" value="${title}" />
</jsp:include>

<h3>Person</h3>

<table>
	<tr>
		<th>
			ID:
		</th>
		<td>
			<c:out value="${sessionScope[AttrConstants.ATTR_RESPONSE].id}" />
		</td>
	</tr>
	<tr>
		<th>
			First Name:
		</th>
		<td>
			<c:out value="${sessionScope[AttrConstants.ATTR_RESPONSE].firstName}" />
		</td>
	</tr>
	<tr>
		<th>
			Last Name:
		</th>
		<td>
			<c:out value="${sessionScope[AttrConstants.ATTR_RESPONSE].lastName}" />
		</td>
	</tr>
	<tr>
		<th>
			Headline:
		</th>
		<td>
			<c:out value="${sessionScope[AttrConstants.ATTR_RESPONSE].headline}" />
		</td>
	</tr>
	<tr>
		<th>
			URL:
		</th>
		<td>
			<a href="${sessionScope[AttrConstants.ATTR_RESPONSE].siteStandardProfileRequest.url}">
				<c:out value="${sessionScope[AttrConstants.ATTR_RESPONSE].siteStandardProfileRequest.url}" />
			</a>
		</td>
	</tr>
</table>

</body>
</html>