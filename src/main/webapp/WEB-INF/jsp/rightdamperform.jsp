<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<%@page import="com.thermalstrategies.eportal.security.EPortalSecurityContext" %>


<table>
    <tr>
        <td>Next Test Date</td><td><script>DateInput('nextTestDate', false, 'DD-MON-YYYY', '${command.nextTestDate}')</script></td>
    </tr>

    <%
                if ("admin".equals(EPortalSecurityContext.getRole().getRole()) || "employee".equals(EPortalSecurityContext.getRole().getRole())) {
    %>
    <tr>
        <td><spring:bind path="picture">
                <c:choose>
                    <c:when test="${status.error}">
                        <span style="color:red">Picture</span>
                    </c:when>
                    <c:otherwise>
                        <span>Picture</span>
                    </c:otherwise>
                </c:choose>
            </spring:bind>
        </td>
        <td><input type="file" name="picture"></td>
    </tr>
    <% }%>

    <%--<c:if test="${true}">
        <tr><td>Drawings</td><td>
                <form:select path="drawing_id">
                    <form:option value="0">-- Select One --</form:option>
                    <c:forEach items="${command.drawingList}" var="drawing">
                        <form:option value="drawing.id">PASS</form:option>
                    </c:forEach>
                </form:select>
                <a href="#">place damper on drawing</a>
            </td></tr>
    </c:if> --%>

    <tr>
        <td>Occupancy</td>
        <td>
            <select id="healthCareIds" name="occupancy">
                <c:choose>
                    <c:when test="${command.occupancy == 'BUSINESS'}">
                        <option value="HEALTH_CARE" >HEALTH CARE OCCUPANCY</option>
                        <option value="BUSINESS" SELECTED>BUSINESS OCCUPANCY</option>
                        <option value="NEW_CONSTRUCTION" >NEW CONSTRUCTION</option>
                    </c:when>
                    <c:when test="${command.occupancy == 'NEW_CONSTRUCTION'}">
                        <option value="HEALTH_CARE" >HEALTH CARE OCCUPANCY</option>
                        <option value="BUSINESS" >BUSINESS OCCUPANCY</option>
                        <option value="NEW_CONSTRUCTION" SELECTED>NEW CONSTRUCTION</option>
                    </c:when>
                    <c:otherwise>
                        <option value="HEALTH_CARE" SELECTED>HEALTH CARE OCCUPANCY</option>
                        <option value="BUSINESS" >BUSINESS OCCUPANCY</option>
                        <option value="NEW_CONSTRUCTION" >NEW CONSTRUCTION</option>
                    </c:otherwise>
                </c:choose>

            </select>
        </td>
    </tr>


    <tr>
        <td><spring:bind path="comments">
                <c:choose>
                    <c:when test="${status.error}">
                        <span style="color:red">Comments</span>
                    </c:when>
                    <c:otherwise>
                        <span>Comments</span>
                    </c:otherwise>
                </c:choose>
            </spring:bind>
        </td>
        <td>
            <textarea class="allcaps" style="width:400px" id="Comments" cols="10" rows="10" name="comments"><c:out value="${command.comments}" /></textarea>
        </td>
    </tr>

    <tr>
        <td><spring:bind path="specialProcedures">
                <c:choose>
                    <c:when test="${status.error}">
                        <span style="color:red">Special Procedures</span>
                    </c:when>
                    <c:otherwise>
                        <span>Special Procedures</span>
                    </c:otherwise>
                </c:choose>
            </spring:bind>
        </td>
        <td>
            <textarea class="allcaps" style="width:400px" id="specialProcedures" cols="10" rows="10" name="specialProcedures">${command.specialProcedures}</textarea>
        </td>
    </tr>

    <%
                if ("admin".equals(EPortalSecurityContext.getRole().getRole())) {
    %>
    <tr>
        <td><spring:bind path="repairHistory">
                <c:choose>
                    <c:when test="${status.error}">
                        <span style="color:red">Repair History</span>
                    </c:when>
                    <c:otherwise>
                        <span>Repair History</span>
                    </c:otherwise>
                </c:choose>
            </spring:bind></td>
        <td><form:input cssClass="allcaps" path="repairHistory" size="50" />
        </td>
    </tr>
    <% }%>
    <!-- Repair Histories-->
    <tr><td></td><td>
            <c:if test="${not empty command.repairHistoryList}">
                <hr/>
                <span style="text-align:center">REPAIR HISTORY</span>
                <hr/>
                <table id="stripes" style="width:100%">
                    <c:forEach items="${command.repairHistoryList}" var="rh">
                        <tr>

                            <td style="width:100px">
                                <c:if test="${role.role == 'admin'}">
                                    <a href="editRepairHistoryFromRepairHistoryList.htm?id=${rh.id}&damperId=${rh.damperId}&custId=${rh.custId}&buildingId=${rh.buildingId}&floorId=${rh.floorId}&level=${level}&pageNum=${pageNum}&back=${back}">
                                        ${rh.date}
                                    </a>
                                </c:if>
                            </td>
                            <td>
                                ${rh.status}
                            </td>
                            <td>
                                ${rh.description}
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </td></tr>

    <!-- Comment Histories-->
    <tr><td></td><td>
            <c:if test="${not empty command.commentHistoryBeanList}">
                <hr/>
                <span style="text-align:center">DAMPER HISTORY</span>
                <hr/>
                <table id="stripes" style="width:100%">
                    <c:forEach items="${command.commentHistoryBeanList}" var="rh">
                        <tr>
                            <td style="width:100px"><a href="editDamperArchive.htm?id=${rh.damperId}">${rh.dateTestedTs}</a></td>
                            <td>
                                ${rh.status}
                            </td>
                            <td>
                                ${rh.comments}
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </td></tr>

    <tr><td></td>
        <td>
            <%
                        if ("admin".equals(EPortalSecurityContext.getRole().getRole()) || "employee".equals(EPortalSecurityContext.getRole().getRole())) {
            %>

            <input type="submit" name="Save" value="Save"/>
            <c:if test="${command.showDecommissionedButton}">
                <c:choose>
                    <c:when test="${command.damperstatus_id == 6}">

                    </c:when>
                    <c:otherwise>
                        <input onclick="javascript:areYouSureDecommission()" type="button" name="decommission" value="Decommission"/>
                    </c:otherwise>
                </c:choose>

                <%
                                            if ("admin".equals(EPortalSecurityContext.getRole().getRole())) {
                %>
                <input onclick="javascript:areYouSureDamper()" type="button" name="delete" value="Delete"/>
                <% }%>
                
            </c:if>
                <input type="submit" name="Clone" value="Clone"/>
            <% }%>
            
            <input type="button" onClick="history.back()" value="Cancel"/>
        </td>
    </tr>
</table>
