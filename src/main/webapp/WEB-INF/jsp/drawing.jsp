<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<%-- 
    Document   : drawing
    Created on : Jan 5, 2011, 2:13:50 PM
    Author     : vmurry
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Add or Remove Drawing</title>
    </head>
    <body>
        <form:form name="form" enctype="multipart/form-data">
            <h1>Drawing</h1>
            id: <form:input path="id" /><br>
            drawing: <form:input path="drawingName"  /><br>
            customer: <form:input path="customerId" /><br>
            building: <form:input path="buildingId" /><br>
            floor: <form:input path="floorId" /><br>
            drawing: <input type="file"><br>
            <input type="submit" value="Submit">
        </form:form>

            



    </body>
</html>
