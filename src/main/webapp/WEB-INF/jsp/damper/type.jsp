<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<select name="dampertype_id" >
    <option value="0">-- Select One --</option>
    <c:forEach items="${command.dampertypelist}" var="dampertype">
        <option value="${dampertype.id}" <c:if test="${dampertype.id == command.dampertype_id}">SELECTED</c:if>>&nbsp;${fn:toUpperCase(dampertype.typeName)}</option>
    </c:forEach>
</select>

