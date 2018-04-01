<%-- 
    Document   : repairhistory
    Created on : Dec 1, 2009, 6:39:18 PM
    Author     : Stuart
--%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%--window.location = "editRepairHistory.htm?Delete=true&id=" + repair;--%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Edit Repair History</title>
        
        <script type="text/javascript">
            function areYouSure(repair)
            {
                var answer = confirm('Are you sure you want to delete this repair history note?');
                if (answer)
                {
                    window.location = "deleteRepairHistory.htm?damperId=${command.damperId}&id=${command.id}";
                }
            }
        </script>
    </head>
    <body>
        
        <form:form>
            <%--<form:hidden path="id" />--%>
            <%--<form:hidden path="damperId" />--%>
            <table>
                <tr><td>Date</td><td><script>DateInput('date', false, 'DD-MON-YYYY', '${command.date}')</script></td></tr>
                <tr><td>Description</td><td><form:textarea cssClass="allcaps" cols="50" rows="20" path="description" /></td></tr>
                <tr>
                    <td></td>
                    <td>
                        <c:if test="${role.role == 'admin'}">
                            <input type="submit" name="Save" value="Save"/>
                            <input onclick="javascript:areYouSure(${command.id})" type="button" name="Delete" value="Delete"/>
                        </c:if>
                        <input type="button" onClick="history.back()" value="Cancel"/></td>
                    <td></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>
