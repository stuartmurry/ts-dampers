<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<html>
    <head>
        <title>- Damper Test Wizard</title>

        <!-- Dead code: Todo:remove-->
        <script type="text/javascript">
            function checkStatus()
            {
                //Check if pass or fail. If pass then move to page 6 else move to page 7
                var formElements = document.forms['form'].elements;
                var damperStatus = formElements['damperStatus'].value;
            
                if (damperStatus == 'PASS')
                {
                    formElements['_target8'].setAttribute('name', '_target6');
                } else
                {
                    formElements['_target8'].setAttribute('name', '_target7');
                }

                formElements['actions'].value='back';
                document.forms['form'].submit();
            }
        </script>

    </head>
    <body>
        <form method="post" action="" name="form">
            <!-- javascript will add update, delete, etc to the value of this -->
            <input id="actions" type="hidden" name="action" />
            <input id="params" type="hidden" name="param" />
            <input type="hidden" name="_target8" />
            <input type="hidden" id="damperStatus" value="${command.dampertest.damperstatus.status}" name="notImportant" />

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
                    <td style="width:150px">${fn:toUpperCase(command.dampertest.aliasId)}</td>
                    <td style="width:100px">${fn:toUpperCase(command.dampertest.dampertype.typeName)}</td>
                    <td>${fn:toUpperCase(command.dampertest.building.buildingName)}</td>
                    <td>${fn:toUpperCase(command.dampertest.buildingfloor.floorName)}</td>
                    <td>${fn:toUpperCase(command.dampertest.system)}</td>
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
                    <td style="width:100px">${command.dampertest.sizew}W x ${command.dampertest.sizel}H</td>
                    <td>${fn:toUpperCase(command.dampertest.location)}</td>
                    <td>${fn:toUpperCase(command.dampertest.sublocation)}</td>
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
                                <font color="green">
                                </c:when>
                                <c:when test="${command.dampertest.damperstatus.status eq 'FAIL'}" >
                                    <font color="red">
                                    </c:when>
                                    <c:otherwise>
                                        <font color="blue">
                                        </c:otherwise>
                                    </c:choose>
                                    ${command.dampertest.damperstatus.status}

                                </font>

                                </td>
                                <td style="width:100px">${fn:toUpperCase(command.dampertest.dampermaterial.materialName)}</td>
                                <td>
                                    ${fn:toUpperCase(command.comments)}
                                </td>
                                </tr>
                                </table>


                                <p>
                                    <input type="submit" value="Cancel" name="_cancel" />
                                    <input type="button" value="<- Back" onclick="javascript:tarjet('_target8', '_target7')" />
                                    <c:choose>
                                        <c:when test="${command.editDamperMode == true}">
                                            <input type="submit" value="Save Changes" name="_finish" />
                                        </c:when>
                                        <c:when test="${command.editDamperMode == false}">
                                            <input type="submit" value="Create Damper" name="_finish" />
                                        </c:when>
                                    </c:choose>
                                </p>
                                </form>
                                </body>
                                </html>
