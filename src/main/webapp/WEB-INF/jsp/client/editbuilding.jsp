<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<html>
<head></head>
<body>
    <form:form>
    <form:hidden path="id" />
    <table>

        <c:if test="${role.role == 'admin'}">
            <tr><td>Site</td><td>
                    <div>
                        <select name="site_id">
                            <option value="0">-- Select One --</option>
                            <c:forEach items="${command.siteList}" var="site">
                                <option value="${site.id}" <c:if test="${site.id == command.site.id}">SELECTED</c:if>>${site.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </td><td><a href="editSite.htm?custId=${custId}&buildId=${buildId}">(create new site)</a></td></tr>
            </c:if>
        <tr><td></td><td>
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
            </td></tr>
        <tr><td style="width:75px">Alias</td><td><form:input path="aliasId" /></td></tr>
        <tr><td style="width:75px">Name</td><td><form:input path="buildingName" /></td></tr>
        <tr><td style="width:75px">Address1</td><td><form:input path="address1" /></td></tr>
        <tr><td style="width:75px">Address1</td><td><form:input path="address2" /></td></tr>
        <tr><td style="width:75px">City</td><td><form:input path="city" /></td></tr>
        <tr><td style="width:75px">State</td><td><form:input path="state" /></td></tr>
        <tr><td style="width:75px">Zip</td><td><form:input path="zip" /></td></tr>
        <tr><td style="width:75px">Contact</td><td><form:input path="poc" /></td></tr>
        <tr><td style="width:75px">Phone</td><td><form:input path="pocPhone" /></td></tr>
    </table>
    <input type="submit" name="save" value="Save" />
    <input type="button" onClick="history.back()" value="Cancel"/>
</form:form>
</body>
</html>



