<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<spring:bind path="command.building_id">
<select name="building_id" onchange="javascript:update()">
    <option value="0">-- Select One --</option>
    <c:forEach items="${command.buildinglist}" var="building">
        <option value="${building.id}" <c:if test="${building.id == command.building_id}">SELECTED</c:if>>&nbsp;${fn:toUpperCase(building.buildingName)}</option>
    </c:forEach>
</select>
</spring:bind>