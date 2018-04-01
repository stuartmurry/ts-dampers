<%-- 
    Document   : user-employee-setup
    Created on : Feb 6, 2010, 6:20:16 PM
    Author     : Stuart
--%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Setup your employee</title>
    </head>
    <body>
        <form:form>
            <form:checkboxes items="" path="${items}"/>
        </form:form>
    </body>
</html>
