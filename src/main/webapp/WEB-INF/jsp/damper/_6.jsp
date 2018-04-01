<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<html>
    <head>
        <title>- Damper Test Wizard</title>

        <script type="text/javascript">
            focusElementId='damperStatus';
        </script>

        <!-- Dead code.  To do: Remove at a later date-->
        <script type="text/javascript">
            function checkStatus()
            {
                var formElements = document.forms['form'].elements;
                formElements['actions'].value='update';
                var damperStat = formElements['damperStatus'].value;
        
                if (damperStat == 1)
                {
                    formElements['_target6'].setAttribute('name', '_target8');
                    document.forms['form'].submit();
                }
                else {
                    formElements['_target6'].setAttribute('name', '_target7');
                    document.forms['form'].submit();
                }
            }
        </script>

    </head>
    <body>

        <form method="post" action="" name="form">
            <!-- javascript will add update, delete, etc to the value of this -->
            <input id="actions" type="hidden" name="action">
            <input id="params" type="hidden" name="param">
            <input type="hidden" name="_target6">

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
                    <spring:bind path="command.damperstatus_id">
                        <td>
                            <c:choose>
                                <c:when test="${status.error}">
                                    <span style="color:red">Status</span>
                                </c:when>
                                <c:otherwise>
                                    <span>Status</span>
                                </c:otherwise>
                            </c:choose>
                        </td><td>
                            <select id="damperStatus" name="damperstatus_id">
                                <option value="0">-- Select One --</option>
                                <!--c:forEach items="${command.damperstatuslist}" var="damperstatus"-->
                                <option value="1" style="color:green" <c:if test="${command.dampertest.damperstatus.status eq 'PASS'}"> SELECTED</c:if>>PASS</option>
                                <option value="2" style="color:red" <c:if test="${command.dampertest.damperstatus.status eq 'FAIL'}"> SELECTED</c:if>>FAIL</option> 
                                <option value="3" style="color:blue" <c:if test="${command.dampertest.damperstatus.status eq 'INACCESSABLE'}"> SELECTED</c:if>>INACCESSABLE</option> 
                                <!--/c:forEach-->
                            </select>
                        </td>
                    </spring:bind>
                </tr>

                <tr><td></td><td>
                        <input type="button" value="Cancel" onclick="javascript:tarjet('_target6','_cancel')" />
                        <input type="button" value="<- Back" onclick="javascript:backAPage('_target6','_target5')" />
                        <input type="button" value="Next ->" onclick="javascript:tarjet('_target6','_target7')" />
                    </td></tr>
            </table>
        </form>
    </body>
</html>
