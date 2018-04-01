<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<%@page import="com.thermalstrategies.eportal.security.EPortalSecurityContext" %>
<table>
    <tr>
        <td>Date Tested</td><td><script>DateInput('datetested', false, 'DD-MON-YYYY', '${command.datetested}')</script></td>
    </tr>
    <tr>
        <td>
            <spring:bind path="damperstatus_id">
                <c:choose>
                    <c:when test="${status.error}">
                        <span style="color:red">Status</span>
                    </c:when>
                    <c:otherwise>
                        <span>Status</span>
                    </c:otherwise>
                </c:choose>
            </spring:bind>
        </td><td>
            <c:choose>
                <c:when test="${command.damperstatus_id == 6}">
                    <form:select path="damperstatus_id">
                        <form:option cssStyle="color:blue"  value="6">DECOMMISSIONED</form:option>
                    </form:select>
                </c:when>
                <c:otherwise>
                    <form:select path="damperstatus_id">
                        <form:option value="0">-- Select One --</form:option>
                        <form:option value="1">PASS</form:option>
                        <form:option value="2">FAIL</form:option>
                        <form:option value="3">INACCESSIBLE</form:option>
                        <form:option value="4">FAIL:REPAIRED</form:option>
                        <form:option value="5">INACCESSIBLE:REPAIRED</form:option>
                        <form:option value="8">FAIL:INACCESSIBLE</form:option>
                        <form:option value="7">PENDING</form:option>
                    </form:select>
                </c:otherwise>
            </c:choose>


        </td>
    </tr>

    <tr><td>Alias Id</td><td><form:input cssClass="allcaps" path="aliasId" /></td></tr>
    <tr><td>Customer</td><td>
            <%--<form:select path="customer_id" disabled="true">
                <form:option value="-" label="--Please Select"/>
                <form:options items="${customerlist}" itemValue="id" itemLabel="customerName"/>
            </form:select>--%>
            <form:input cssClass="allcaps" size="50" path="customer" />
        </td></tr>
    <tr><td>Building</td><td>
            <%--<form:select path="building_id" disabled="true">
                <form:option value="-" label="--Please Select"/>
                <form:options items="${buildinglist}" itemValue="id" itemLabel="buildingName"/>
            </form:select>--%>
            <form:input cssClass="allcaps" size="50" path="building" />
        </td></tr>
    <tr><td>Floor</td><td>
            <%--<form:select path="buildingfloor_id" disabled="true">
                <form:option value="-" label="--Please Select"/>
                <form:options items="${buildingfloorlist}" itemValue="id" itemLabel="floorName"/>
            </form:select>--%>
            <form:input cssClass="allcaps" size="50" path="buildingfloor" />
        </td></tr>
    <tr><td>Damper Type</td><td>
            <%--<form:select path="dampertype_id" disabled="true">
                <form:option value="-" label="--Please Select"/>
                <form:options items="${dampertypelist}" itemValue="id" itemLabel="typeName"/>
            </form:select>--%>
            <form:input cssClass="allcaps" size="50" path="dampertype" />
        </td></tr>
    <tr>

        <spring:bind path="dampermaterial_id">
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
                    <c:forEach items="${command.damperMaterialList}" var="dampermaterial">
                        <option value="${dampermaterial.id}" <c:if test="${dampermaterial.id == command.dampermaterial_id}">SELECTED</c:if>>${fn:toUpperCase(dampermaterial.materialName)}</option>
                    </c:forEach>
                </select>
            </td>
        </spring:bind>
    </tr>

    <tr>
        <td>
            <spring:bind path="sizew">
                <c:choose>
                    <c:when test="${status.error}">
                        <span style="color:red">Width</span>
                    </c:when>
                    <c:otherwise>
                        <span>Width</span>
                    </c:otherwise>
                </c:choose>
            </spring:bind>
        </td>
        <td>
            <form:input size="5" maxlength="5" path="sizew" />
            <spring:bind path="sizel">
                <c:choose>
                    <c:when test="${status.error}">
                        <span style="color:red">Height</span>
                    </c:when>
                    <c:otherwise>
                        <span>Height</span>
                    </c:otherwise>
                </c:choose>
            </spring:bind>
            <form:input size="5" maxlength="5" path="sizel" />
        </td>
    </tr>
    <tr>
        <td>
            <spring:bind path="system">
                <c:choose>
                    <c:when test="${status.error}">
                        <span style="color:red">System</span>
                    </c:when>
                    <c:otherwise>
                        <span>System</span>
                    </c:otherwise>
                </c:choose>
            </spring:bind>
        </td><td>
            <form:input cssClass="allcaps"  path="system" />
        </td>
    </tr>
    <tr>
        <td>
            <spring:bind path="location">
                <c:choose>
                    <c:when test="${status.error}">
                        <span style="color:red">Location</span>
                    </c:when>
                    <c:otherwise>
                        <span>Location</span>
                    </c:otherwise>
                </c:choose>
            </spring:bind>
        </td>
        <td><form:textarea cssClass="allcaps" path="location" cols="20" rows="5" /></td>
    </tr>
    <tr>
        <td>
            <spring:bind path="sublocation">
                <c:choose>
                    <c:when test="${status.error}">
                        <span style="color:red">Sublocation</span>
                    </c:when>
                    <c:otherwise>
                        <span>Sublocation</span>
                    </c:otherwise>
                </c:choose>
            </spring:bind>
        </td>
        <td><form:textarea cssClass="allcaps" path="sublocation" cols="20" rows="5" /></td>
    </tr>

</table>

<c:forEach items="${command.pictureIdList}" var="ids">
    <%
                if ("admin".equals(EPortalSecurityContext.getRole().getRole()) || "employee".equals(EPortalSecurityContext.getRole().getRole())) {
    %>
    <a href="#" onclick="javascript:areYouSureDeletePicture(${ids})">Delete this picture?</a><br>
    <%                    }
    %>
    <img style="width: 400px;border: 1px black solid" src="pictures.htm?id=${ids}"><br>

</c:forEach>
