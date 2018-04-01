<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<html>
    <head>
        <title>- Damper Test Wizard</title>
        <script type="text/javascript">
            focusElementId='locations';
        </script>
    </head>
    <body>
        <form method="post" action="" name="form">
            <!-- javascript will add update, delete, etc to the value of this -->
            <input id="actions" type="hidden" name="action">
            <input id="params" type="hidden" name="param">
            <input type="hidden" name="_target5">

            <table>
                <tr><td></td><td>
                        <spring:hasBindErrors name="command">
                            <span style="color:red">
                                <p>There were ${errors.errorCount} error(s) in total:</p>
                                <ul>
                                    <c:forEach var="errMsgObj" items="${errors.allErrors}">
                                        <li>
                                            <spring:message code="${errMsgObj.code}" text="${errMsgObj.defaultMessage}"/>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </span>
                        </spring:hasBindErrors>
                    </td></tr>
                <tr>
                    <td></td><td style="border-width:1px;border-style:solid;border-color:black;text-align:center"><c:out value="${fn:toUpperCase(command.dampertest.aliasId)}" /></td>
                </tr>
                <tr><td></td><td></td></tr>
                <tr>
                    <spring:bind path="command.location">
                        <td>
                            <c:choose>
                                <c:when test="${status.error}">
                                    <span style="color:red">Location</span>
                                </c:when>
                                <c:otherwise>
                                    <span>Location</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td><textarea id="locations" name="location" cols="20" rows="5"><c:out value="${command.dampertest.location}" /></textarea></td>
                    </spring:bind>
                </tr>
                <tr>
                    <spring:bind path="command.sublocation">
                        <td>
                            <c:choose>
                                <c:when test="${status.error}">
                                    <span style="color:red">SubLocation</span>
                                </c:when>
                                <c:otherwise>
                                    <span>SubLocation</span>
                                </c:otherwise>
                            </c:choose>
                        </td><td><textarea name="sublocation" cols="20" rows="5"><c:out value="${command.dampertest.sublocation}" /></textarea></td>
                    </spring:bind>
                </tr>

                <tr><td></td><td>
                        <input type="button" value="Cancel" onclick="javascript:tarjet('_target5','_cancel')" />
                        <input type="button" value="<- Back" onclick="javascript:backAPage('_target5','_target4')" />
                        <input type="button" value="Next ->" onclick="javascript:tarjet('_target5','_target6')" />
                    </td></tr>
            </table>


        </form>
    </body>
</html>

