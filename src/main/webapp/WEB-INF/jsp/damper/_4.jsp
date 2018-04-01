<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<html>
    <head>
        <title>- Damper Test Wizard</title>
        <script type="text/javascript">
            focusElementId='datetested';
        </script>

    </head>
    <body>
        <form method="post" action="" name="form">
            <!-- javascript will add update, delete, etc to the value of this -->
            <input id="actions" type="hidden" name="action">
            <input id="params" type="hidden" name="param">

            <input type="hidden" name="_target4">


            <table>
                <tr>
                    <td></td>
                    <td>
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
                    </td>
                </tr>

                <tr><td>&nbsp;</td></tr>
                <tr>
                    <td></td><td style="border-width:1px;border-style:solid;border-color:black;text-align:center">${fn:toUpperCase(command.dampertest.aliasId)}</td>
                </tr>
                <tr><td></td><td></td></tr>
                <tr>
                    <td>Date Tested</td><td><script>DateInput('datetested', false, 'DD-MON-YYYY', '<fmt:formatDate pattern="dd-MMM-yyyy" type="date" value="${command.dampertest.dateTestedTs}" />')</script></td>
                </tr>
                <tr>
                    <spring:bind path="command.dampermaterial_id">
                        <td>
                            <c:choose>
                                <c:when test="${status.error}">
                                    <span style="color:red">Material</span>
                                </c:when>
                                <c:otherwise>
                                    <span>Material</span>
                                </c:otherwise>
                            </c:choose>
                        </td><td>
                            <select name="dampermaterial_id">
                                <option value="0">-- Select One --</option>
                                <c:forEach items="${command.dampermateriallist}" var="dampermaterial">
                                    <option value="${dampermaterial.id}" <c:if test="${dampermaterial.id == command.dampertest.dampermaterial.id}">SELECTED</c:if>>&nbsp;${fn:toUpperCase(dampermaterial.materialName)}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </spring:bind>
                </tr>
                <tr>
                    <spring:bind path="command.sizew">
                        <td>
                            <c:choose>
                                <c:when test="${status.error}">
                                    <span style="color:red">Width</span>
                                </c:when>
                                <c:otherwise>
                                    <span>Width</span>
                                </c:otherwise>
                            </c:choose>
                        </td>

                        <td>
                            <input type="text" size=5 maxlength=5 name="sizew" value="<c:out value="${command.dampertest.sizew}"/>"/>
                        </spring:bind>
                        <spring:bind path="command.sizel">
                            <c:choose>
                                <c:when test="${status.error}">
                                    <span style="color:red">Height</span>
                                </c:when>
                                <c:otherwise>
                                    <span>Height</span>
                                </c:otherwise>
                            </c:choose>
                            <input type="text" size=5 maxlength=5 name="sizel" value="<c:out value="${command.dampertest.sizel}"/>">
                        </td>
                    </spring:bind>
                </tr>
                <tr>
                    <spring:bind path="command.dampersystem">
                        <td>
                            <c:choose>
                                <c:when test="${status.error}">
                                    <span style="color:red">System</span>
                                </c:when>
                                <c:otherwise>
                                    <span>System</span>
                                </c:otherwise>
                            </c:choose>
                        </td><td>
                            <input type="text" name="dampersystem" value="${command.dampertest.system}"/>
                        </td>
                    </spring:bind>
                </tr>

            </table>
            <p>
                <input type="button" value="Cancel" onclick="javascript:tarjet('_target4','_cancel')" />
                <input type="button" value="<- Back" onclick="javascript:backAPage('_target4','_target3')" />
                <input type="button" value="Next ->" onclick="javascript:tarjet('_target4','_target5')" />
            </p>
        </form>
    </body>
</html>