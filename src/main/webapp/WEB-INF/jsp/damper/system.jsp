<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<select name="dampersystem_id">
    <option value="0">-- Select One --</option>
    <c:forEach items="${command.dampersystemlist}" var="dampersystem">
        <option value="${dampersystem.id}" <c:if test="${dampersystem.id == command.dampertest.dampersystem.id}">SELECTED</c:if>>&nbsp;${fn:toUpperCase(dampersystem.systemName)}</option>
    </c:forEach>
</select>

