<%@ include file="/init.jsp" %>

<%@ page import="nl.ijmker.test.action.linkedin.jsphelpers.ActionsPageHelper" %>

<a href="${ActionsPageHelper.getRetrieveProfileXMLURL(pageContext.request)}">
	${ActionsPageHelper.getRetrieveProfileXMLURL(pageContext.request)}
</a><br/>

<a href="${ActionsPageHelper.getRetrieveProfileJSONURL(pageContext.request)}">
	${ActionsPageHelper.getRetrieveProfileJSONURL(pageContext.request)}
</a><br/>