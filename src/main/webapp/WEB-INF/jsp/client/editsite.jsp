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
        <title>Site</title>
        <script type="text/javascript">
            function areYouSure(repair)
            {
                var answer = confirm('Are you sure you want to delete this site note?');
                if (answer)
                {
                    window.location = "deleteSite.htm?id=${command.id}";
                }
            }
        </script>
    </head>
    <body>

        <form:form>
            <%--<input type="hidden" name="id" value="${command.id}">--%>
            <%--<form:hidden path="damperId" />--%>
            <table>
                <tr><td>Alias</td><td><input type="text" name="alias" value="${command.alias}" ></td></tr>
                <tr><td>Name</td><td><input type="text" name="name" value="${command.name}" ></td></tr>
                <tr><td>Description</td><td><input type="text" name="description" value="${command.description}" ></td></tr>
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
            </select>
        </table>
    </form:form>
</body>
</html>