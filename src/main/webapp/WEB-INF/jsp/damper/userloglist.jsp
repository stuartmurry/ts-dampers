<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<html>
    <head>
        <title>Active Session List</title>
        <script type="text/javascript">
            focusElementId='nexts';
        </script>
    </head>

    <body>

        Active Sessions <br><br>

        <c:choose>
            <c:when test="${fn:length(userloglist) != 0}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>SessionId</th>
                            <th>Login Time</th>
                            <th>Project</th>
                            <th>Building</th>
                            <th>Floor</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${userloglist}" var="userlog">
                            <tr>
                                <td><c:out value="${userlog.user.lastName}" />, <c:out value="${userlog.user.firstName}" /></td>
                                <td><c:out value="${userlog.sessionId}" /></td>
                                <td><c:out value="${userlog.loginTs}" /></td>
                                <td>[<c:out value="${userlog.project.projectNum}" />] <c:out value="${userlog.project.customer.customerName}" />-<c:out value="${userlog.project.projectName}" /></td>
                                <td><c:out value="${userlog.building.buildingName}" /> Building</td>
                                <td><c:out value="${userlog.buildingfloor.floorName}" /> Floor</td>
                                <td><a href="useLog.htm?action=endsession&id=<c:out value="${userlog.id}" />">End Floor Session</a></td>
                            </tr>
                        </tbody>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                There are no sessions availble to display
            </c:otherwise>
        </c:choose>

    </body>
</html>

