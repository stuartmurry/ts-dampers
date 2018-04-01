<%--
    Document   : damperlistbycustomer
    Created on : Oct 3, 2009, 10:59:00 PM
    Author     : Stuart
--%>

<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Search Results</title>
        <link rel="stylesheet" type="text/css" href="css/jquery.highlight-3.js" />
    </head>
    <body>
        <c:choose>
            <c:when test="${fn:length(resultList) != 0}">
                Found ${showing} out of ${hits} that match your query <br>
                <c:forEach items="${totalPageList}" var="page" varStatus="status">
                    <a href="search.htm?query=${queryUrl}&page=${page}">${page + 1}</a>
                </c:forEach>
                <table>
                    <tr><td>Relavance</td><td>Location</td><td>Description</td></tr>
                    <c:forEach items="${resultList}" var="result" varStatus="status">
                        <tr><td>${(pageSize*currentPage) + status.count}</td><td valign="top">${result.score}</td><td valign="top"><a href="${result.link}">${result.title}</a></td><td valign="top">${result.description}</td><tr>
                        </c:forEach>
                </table>
            </c:when>
            <c:otherwise>No Results Match Your description</c:otherwise>
        </c:choose>

    </body>
</html>
