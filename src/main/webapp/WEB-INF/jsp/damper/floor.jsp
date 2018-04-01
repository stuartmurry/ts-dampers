<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<select name="buildingfloor_id"  onchange="javascript:update()">
    <option value="0">-- Select One --</option>
    <c:forEach items="${command.buildingfloorlist}" var="buildingfloor">
        <option value="${buildingfloor.id}" <c:if test="${buildingfloor.id == command.buildingfloor_id}">SELECTED</c:if>>&nbsp;${fn:toUpperCase(buildingfloor.floorName)}</option>
    </c:forEach>
</select>

