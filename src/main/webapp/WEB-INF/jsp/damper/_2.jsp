<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<html>
    <head>
        <title>- Damper Test Wizard</title>
        <script type="text/javascript">

            function setIdAndDelete(ids)
            {
                var answer = confirm('Are you sure you want to delete this damper?');
                if (answer)
                {
                    var formElements = document.forms['form'].elements;
                    formElements['actions'].value='delete';
                    formElements['params'].value=ids;
                    tarjet('_target2','_target3');
                }
            }
    
            function saveRepair()
            {
                var formElements = document.forms['form'].elements;
                formElements['actions'].value='repair';
                tarjet('_target2','_target3');
            }
        </script>
    </head>

    <body>
        <form method="post" id="form" action="" name="form">
            <!-- javascript will add update, delete, etc to the value of this -->
            <input id="actions" type="hidden" name="action">
            <input id="params" type="hidden" name="param">
            <input type="hidden" name="_target2">


            <table style="width:90%">
                <tr><td><img src="images/pdficon_small.gif"/><a href="generateReport.htm?damperId=${command.dampertest.id}">Print this report</a></td></tr>
                <tr>
                    <td style="border-width:1px;border-style:solid;border-color:black;text-align:center; width:100%"><c:out value="${fn:toUpperCase(command.dampertest.aliasId)}" /></td>
                </tr>
            </table>

            <table style="width:90%;">
                <tr><td></td><td></td></tr>
                <tr>
                    <th style="border-width:1px;border-style:solid;border-color:black;">ID</th>
                    <th style="border-width:1px;border-style:solid;border-color:black;">TYPE</th>
                    <th style="border-width:1px;border-style:solid;border-color:black;">BUILDING</th>
                    <th style="border-width:1px;border-style:solid;border-color:black;">FLOOR</th>
                    <th style="border-width:1px;border-style:solid;border-color:black;">SYSTEM</th>
                </tr>
                <tr>
                    <td style="width:150px"><c:out value="${fn:toUpperCase(command.dampertest.aliasId)}" /></td>
                    <td style="width:100px"><c:out value="${fn:toUpperCase(command.dampertest.dampertype.typeName)}" /></td>
                    <td><c:out value="${fn:toUpperCase(command.dampertest.building.buildingName)}" /></td>
                    <td><c:out value="${fn:toUpperCase(command.dampertest.buildingfloor.floorName)}" /></td>
                    <td><c:out value="${fn:toUpperCase(command.dampertest.system)}" /></td>
                </tr>
                <tr><td>&nbsp;</td><td></td><td></td><td></td></tr>
            </table>
            <table style="width:90%; text-align:center">
                <tr>
                    <th style="border-width:1px;border-style:solid;border-color:black;">DATE TESTED</th>
                    <th style="border-width:1px;border-style:solid;border-color:black;">SIZE</th>
                    <th style="border-width:1px;border-style:solid;border-color:black;">LOCATION</th>
                    <th style="border-width:1px;border-style:solid;border-color:black;">SUBLOCATION</th>
                </tr>
                <tr>
                    <td style="width:150px"><fmt:formatDate pattern="MM-dd-yyyy" value="${command.dampertest.dateTestedTs}" /></td>
                    <td style="width:100px"><c:out value="${command.dampertest.sizew}" />W x <c:out value="${command.dampertest.sizel}" />H</td>
                    <td><c:out value="${fn:toUpperCase(command.dampertest.location)}" /></td>
                    <td><c:out value="${fn:toUpperCase(command.dampertest.sublocation)}" /></td>
                </tr>
                <tr><td>&nbsp;</td><td></td><td></td><td></td></tr>
            </table>
            <table style="width:90%; text-align:center">
                <tr>
                    <th style="border-width:1px;border-style:solid;border-color:black;">STATUS</th>
                    <th style="border-width:1px;border-style:solid;border-color:black;">MATERIAL</th>
                    <th style="border-width:1px;border-style:solid;border-color:black;">COMMENTS</th>
                </tr>
                <tr>
                    <td style="width:150px">
                        <c:choose>
                            <c:when test="${command.dampertest.damperstatus.status eq 'PASS'}">
                                <font color="green"/>
                            </c:when>
                            <c:when test="${command.dampertest.damperstatus.status eq 'FAIL'}" >
                                <font color="red"/>
                            </c:when>
                            <c:otherwise>
                                <font color="blue"/>
                            </c:otherwise>
                        </c:choose>
                        ${fn:toUpperCase(command.dampertest.damperstatus.status)}:${fn:toUpperCase(command.dampertest.damperstatus.substatus)}

                    </td>
                    <td style="width:100px">${fn:toUpperCase(command.dampertest.dampermaterial.materialName)}</td>
                    <td>
                        <c:out value="${fn:toUpperCase(command.comments)}" />
                    </td>
                </tr>
            </table>
            <!-- Inaccessable only notes -->
            <c:if test="${command.repairHistory != ''}" >
                <br/>
                <table style="width:100%">
                    <tr><td style="text-align:left;"><strong>REPAIR HISTORY</strong></td></tr>
                    <tr><td style="text-align:left">
                            <textarea cols="77%" rows="5" readonly="true">
                                ${command.repairHistory}
                            </textarea>
                        </td></tr>
                </table>
            </c:if>

            <c:if test="${command.dampertest.damperstatus.status eq 'INACCESSABLE' || command.dampertest.damperstatus.status eq 'FAILED'}" >
                <br/>
                <table style="width:100%">

                    <tr><td></td><td style="text-align:left;"><strong>REPAIR NOTES</strong></td></tr>
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
                        <td style="width:30px">Date Repaired</td><td><script>DateInput('repairDate', true, 'DD-MON-YYYY')</script></td><td style="text-align:right"></td>
                    </tr>
                    <tr>
                        <td>Status</td><td>
                            <select name="repairState">
                                <option value="NOT REPAIRED" <c:if test="${command.dampertest.damperstatus.substatus eq 'NOT REPAIRED'}">SELECTED</c:if>>NOT REPAIRED</option>
                                <option value="REPAIRED" <c:if test="${command.dampertest.damperstatus.substatus eq 'REPAIRED'}">SELECTED</c:if>>REPAIRED</option>
                            </select>
                        </td>
                    </tr>
                    <tr><td></td><td style="text-align:left">
                            <spring:bind path="command.repair">
                                <textarea name="repair" cols="70%" rows="5"></textarea>
                            </spring:bind>
                        </td></tr>
                    <tr><td></td><td><input type="button" value="Enter Repair" onclick="javascript:saveRepair()"/> </td></tr>
                </table>
            </c:if>
            <p>
                <input type="button" value="Cancel" onclick="javascript:tarjet('_target2','_cancel')" />
                <input type="button" value="<- Back" onclick="javascript:backAPage('_target2','_target3')" />

                <c:choose>
                    <c:when test="${command.floorStatus == 1}">
                        <input type="button" value="Edit" onclick="javascript:tarjet('_target2','_target4')" />
                        <input type="button" value="Delete" onclick="javascript:setIdAndDelete(${command.dampertest.id})" />
                    </c:when>
                </c:choose>
            </p>
        </form>
    </body>
</html>
