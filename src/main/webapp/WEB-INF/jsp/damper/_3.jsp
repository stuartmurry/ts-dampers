<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<html>
    <head>
        <title>Damper Test Wizard</title>
    </head>
    <body>
        <script type='text/javascript' src='/eportal_rewrite/dwr/interface/userFloorTracker.js'></script>
        <script type='text/javascript' src='/eportal_rewrite/dwr/interface/damperTest.js'></script>

        <form method="post" action="" name="form" >
            <div style="font-size:200%;" id="text"></div>

            <!-- javascript will add update, delete, etc to the value of this -->
            <input id="actions" type="hidden" name="action">
            <input id="params" type="hidden" name="param">
            <input id="projectids" type="hidden" value="${command.customer.id}" >

            <input type="hidden" name="_target3" >

            <table>
                <tr><td style="width:10%">Customer:</td><td style="font-weight:bold;color:blue">${command.customer.customerName}</td></tr>
            </table>

            <div class="section">

                <!-- 0: Floor is not being used -->
                <!-- 1: Floor is being used by this person -->
                <!-- 2: Floor is being used by another person -->
                <!-- -1: Otherwise is assumed -->
                <spring:bind path="command.dummy">
                    <c:choose>
                        <c:when test="${status.error}"><h2 style="font-weight:bold; text-align:center; color: #ff0000">CONCURRENT LOGIN ERROR</h2></c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${command.floorStatus == 0}"><h2 style="font-weight:bold; text-align:center;"><a href="#" onclick="javascript:floorLogin()" >(click here to logon to this floor)</a></h2></c:when>
                                <c:when test="${command.floorStatus == 1}"><h2 style="font-weight:bold; text-align:center; background-color: #00ff00"><a href="#" onclick="javascript:floorLogout()" >YOU ARE CURRENTLY LOGGED ONTO THIS FLOOR(Click here to logout)</a></h2></c:when>
                                <c:when test="${command.floorStatus == 2}"><h2 style="font-weight:bold; text-align:center; background-color: #ff0000">SOMEONE ELSE IS LOGGED ONTO THIS FLOOR</h2></c:when>
                                <c:otherwise></c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </spring:bind>

            </div>

            <table> <!--style="border:1px solid #000000;width:80%;"-->
                <tr><td style="width:10px"></td><td>
                        <spring:hasBindErrors name="command">
                            <span style="color:red">
                                <p>There were ${errors.errorCount} error(s) in total:</p>
                                <ul><c:forEach var="errMsgObj" items="${errors.allErrors}">
                                        <li><spring:message code="${errMsgObj.code}" text="${errMsgObj.defaultMessage}"/></li>
                                </c:forEach></ul>
                            </span>
                        </spring:hasBindErrors>
                    </td></tr>

                <tr><td></td></tr>
                <tr><td></td></tr>

                <tr>
                    <spring:bind path="command.building_id">
                        <td>
                            <c:choose>
                                <c:when test="${status.error}">
                                    <span style="color:red">Building</span>
                                </c:when>
                                <c:otherwise>
                                    <span>Building</span>
                                </c:otherwise>
                            </c:choose>
                        <td>
                            <select id="buildingIds" name="building_id" onchange="javascript:getBuildingFloors()">
                                <option value="0">-- Select One --</option>
                                <c:forEach items="${command.buildinglist}" var="building">
                                    <option value="${building.id}" <c:if test="${building.id == command.building_id}">SELECTED</c:if>>&nbsp;${fn:toUpperCase(building.buildingName)}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </spring:bind>
                </tr>
                <tr>
                    <spring:bind path="command.buildingfloor_id">
                        <td>
                            <c:choose>
                                <c:when test="${status.error}">
                                    <span style="color:red">Floor</span>
                                </c:when>
                                <c:otherwise>
                                    <span>Floor</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <select id="buildingfloorids" name="buildingfloor_id"  onchange="javascript:update()">
                                <option value="0">-- Select One --</option>
                                <c:forEach items="${command.buildingfloorlist}" var="buildingfloor">
                                    <option value="${buildingfloor.id}" <c:if test="${buildingfloor.id == command.buildingfloor_id}">SELECTED</c:if>>&nbsp;${fn:toUpperCase(buildingfloor.floorName)}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </spring:bind>
                </tr>
                <tr>
                    <spring:bind path="command.dampertype_id">
                        <td>
                            <c:choose>
                                <c:when test="${status.error}">
                                    <span style="color:red">Type</span>
                                </c:when>
                                <c:otherwise>
                                    <span>Type</span>
                                </c:otherwise>
                            </c:choose>
                        </td><td>
                            <select id="dampertypeids" name="dampertype_id" >
                                <option value="0">-- Select One --</option>
                                <c:forEach items="${command.dampertypelist}" var="dampertype">
                                    <option value="${dampertype.id}" <c:if test="${dampertype.id == command.dampertype_id}">SELECTED</c:if>>&nbsp;${fn:toUpperCase(dampertype.typeName)}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </spring:bind>
                </tr>
                <tr><td><img id="loadingindicator" src="images/blank.gif"/></td>
                        <c:choose>
                            <c:when test="${command.floorStatus == 1}">
                            <td><input onclick="javascript:changeButtonName('Add Single-Unit Damper')" checked="true" type="radio" name="addedit" value="1" >Single-Unit Damper &nbsp;
                                <input onclick="javascript:changeButtonName('Add Multi-Unit Damper')" type="radio" name="addedit" value="2" > Multi-Unit Damper</td>
                            </c:when>
                        </c:choose>
                </tr>

                <tr><td></td><td><input type="button" value="Cancel" onclick="javascript:tarjet('_target3','_cancel')" />
                        <input type="button" value="<- Back" onclick="javascript:backAPage('_target3','_target1')" />
                        <c:choose>
                            <c:when test="${command.floorStatus == 1}"><input id="nextbutton" type="button" value="Add Single-Unit Damper" onclick="javascript:addNew()" /></c:when>
                            <c:otherwise></c:otherwise>
                        </c:choose>
                    </td></tr>

                <tr>
                    <td></td><td></td>
                </tr>

            </table>

            <br><br>

            <table width="100%">
                <thead class="projectheader">
                    <tr>
                        <th style="width:10px">&nbsp;</th>
                        <th style="width:10px">&nbsp;</th>
                        <th style="border:1px solid #000000;width:150px;text-align:center">ID</th>
                        <th style="border:1px solid #000000;width:75px;text-align:center">DATE</th>
                        <th style="border:1px solid #000000;width:50px;text-align:center">SYSTEM</th>
                        <th style="border:1px solid #000000;width:20px;text-align:center">STATUS</th>
                        <th style="border:1px solid #000000;text-align:center">LOCATION</th>
                        <th style="border:1px solid #000000;text-align:center">SUB LOCATION</th>
                        <th style="width:10px;text-align:center">&nbsp;</th>
                    </tr>
                </thead>

                <tbody id="damperTable">
                    <c:forEach items="${command.dampertestlist}" var="damper" varStatus="status">
                        <tr class="projectlist">
                            <td style="width:10px">${status.count}</td>
                            <td style="width:10px">
                                <c:if test="${damper.series > 0}" >
                                    <c:choose>
                                        <c:when test="${command.floorStatus == 1}">
                                            <a href="#" onclick="javascript:addToSeries(${damper.id})">Add</a>
                                        </c:when>
                                    </c:choose>
                                </c:if>
                            </td>

                            <td>
                                <a href="#" onclick="javascript:setIdAndSubmit(${damper.id})">${fn:toUpperCase(damper.aliasId)}</a>
                            </td>

                            <td style="width:75px"><fmt:formatDate pattern="M/dd/yyyy" value="${damper.dateTestedTs}" /></td>
                            <td style="width:75px">${fn:toUpperCase(damper.system)}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${damper.damperstatus.status eq 'PASS'}"><span style="color:green; font-weight:bold"><c:out value="${damper.damperstatus.status}"/></span></c:when>
                                    <c:when test="${damper.damperstatus.status eq 'FAIL'}"><span style="color:red; font-weight:bold"><c:out value="${damper.damperstatus.status}"/></span></c:when>
                                    <c:otherwise><span style="color:blue; font-weight:bold"><c:out value="${damper.damperstatus.status}"/></span></c:otherwise>
                                </c:choose>
                            </td>
                            <td>${fn:toUpperCase(damper.location)}</td>
                            <td>${fn:toUpperCase(damper.sublocation)}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </form>

    </body>
</html>
