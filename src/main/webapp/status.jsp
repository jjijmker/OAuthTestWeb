<%@ include file="/init.jsp" %>

<!-- Utils -->
<%@ page import="nl.ijmker.test.jsphelpers.StatusPageHelper" %>

<c:set var="title" value="Status Page" />

<html>

<jsp:include page="/includes/header.jsp">
	<jsp:param name="title" value="${title}" />
</jsp:include>

<body>

<jsp:include page="/includes/body_header.jsp">
	<jsp:param name="title" value="${title}" />
</jsp:include>

<h3>General</h3>

<table>
	<tr>
		<th>
			Server:
		</th>
		<td>
			<c:out value="${StatusPageHelper.getServerName(pageContext.request)}" />
		</td>
	</tr>
</table>

<h3>OAuth1</h3>

<h3>OAuth2</h3>

<table>
	<tr>
		<th>
			Access Token:
		</th>
		<td>
			<c:out value="${StatusPageHelper.getOAuth2AccessToken(pageContext.request)}" />
		</td>
	</tr>
	<tr>
		<th>
			Token Type:
		</th>
		<td>
			<c:out value="${StatusPageHelper.getOAuth2TokenType(pageContext.request)}" />
		</td>
	</tr>
	<tr>
		<th>
			Scope:
		</th>
		<td>
			<c:out value="${StatusPageHelper.getOAuth2Scope(pageContext.request)}" />
		</td>
	</tr>
	<tr>
		<th>
			Expires on:
		</th>
		<td>
			<c:out value="${StatusPageHelper.getOAuth2Expiration(pageContext.request)}" />
		</td>
	</tr>
	<tr>
		<th>
			Refresh Token
		</th>
		<td>
			<c:out value="${StatusPageHelper.getOAuth2RefreshToken(pageContext.request)}" />
		</td>
	</tr>
</table>

<h3><c:out value="${StatusPageHelper.getServerName(pageContext.request)}" /> Actions</h3>

<table>
	<tr>
		<th>
			Selected Action
		</th>
		<td>
			<a href="<c:out value="${StatusPageHelper.getSelectedResourceActionURL(pageContext.request)}" />">
				<c:out value="${StatusPageHelper.getSelectedResourceActionName(pageContext.request)}" />
			</a>
		</td>
	</tr>
</table>

<table>
	<tr>
		<th>
			Other Actions for <c:out value="${StatusPageHelper.getServerName(pageContext.request)}" />
		</th>
		<td>
			<ul>
				<c:forEach var="resourceAction" items="${StatusPageHelper.getOtherResourceActions(pageContext.request)}">
					<a href="${StatusPageHelper.getResourceActionSwitchURL(pageContext.request, resourceAction)}">
						<c:out value="${ConfigUtil.getResourceActionName(param.server, resourceAction)}"/>
					</a>
				</c:forEach>
			</ul>
		</td>
	</tr>
</table>

</body>
</html>
