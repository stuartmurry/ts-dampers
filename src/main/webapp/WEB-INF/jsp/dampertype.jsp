<%-- 
    Document   : dampertype
    Created on : Nov 10, 2009, 9:58:00 PM
    Author     : Stuart
--%>

<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Please select a damper type</title>
    </head>
    <body>

        <form:form>
            <spring:hasBindErrors name="command">
                <span class="error">
                    <p>There were ${errors.errorCount} error(s) in total:</p>
                    <ul><c:forEach var="errMsgObj" items="${errors.allErrors}">
                            <li><spring:message code="${errMsgObj.code}" text="${errMsgObj.defaultMessage}"/></li>
                    </c:forEach></ul>
                </span>
            </spring:hasBindErrors>
            <%--            <input type="hidden" name="custId" value="${custId}">
                        <input type="hidden" name="buildingId" value="${buildingId}">
                        <input type="hidden" name="floorId" value="${floorId}">
                        <input type="hidden" name="singleUnit" value="${singleUnit}">--%>
            <div align="center">
                <spring:bind path="dampertype_id">
                    <c:choose>
                        <c:when test="${status.error}">
                            <span style="color:red">Type</span>
                        </c:when>
                        <c:otherwise>
                            <span>Type</span>
                        </c:otherwise>
                    </c:choose>
                </spring:bind>:

                <select id="dampertypeids" name="dampertype_id" >
                    <option value="0">-- Select One --</option>
                    <c:forEach items="${dampertypelist}" var="dampertype">
                        <option value="${dampertype.id}" <c:if test="${dampertype.id == damperTypeId}">SELECTED</c:if>>${fn:toUpperCase(dampertype.typeName)}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="Submit">
            </div>
        </form:form>
    </body>
</html>
