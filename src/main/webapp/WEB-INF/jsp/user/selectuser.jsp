<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Select a User</title>
        <link rel="stylesheet" type="text/css" href="css/stripes.css">
        <script type="text/javascript" src="script/stripes.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                //Even-Odd table striping 
                stripe('stripes', '#fff', '#edf3fe');
            });
            
            function createNewUser(){
                window.location = "edituser.htm";
            }
        </script>
    </head>

    <body>
        <input type="hidden" name="action" />
        <input type="hidden" name="actionparam" />

        <h1>Users</h1>
        <table id="stripes">
            <c:forEach items="${userCommandBeanList}" var="user" varStatus="stat">
                <tr><td>${stat.count}</td><td><a href="edituser.htm?id=${user.id}">${user.lastName}, ${user.firstName}</a></td></tr>
            </c:forEach>
            <tr>
                <td></td><td>
                    <c:if test="${role.role == 'admin'}">
                        <input type="button" onclick="javascript:createNewUser()" value="Create New User" />
                    </c:if>
                </td>
            </tr>
        </table>
    </body>
</html>
