<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<select name="dampermaterial_id">
    <option value="0">-- Select One --</option>
    <c:forEach items="${command.dampermateriallist}" var="dampermaterial">
        <option value="${dampermaterial.id}" <c:if test="${dampermaterial.id == command.dampertest.dampermaterial.id}">SELECTED</c:if>>&nbsp;${fn:toUpperCase(dampermaterial.materialName)}</option>
    </c:forEach>
</select>