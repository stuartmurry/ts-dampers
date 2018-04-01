<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<select name="addedit">
    <option value="0">-- Select One --</option>
    <c:forEach items="${command.addeditlist}" var="addedit">
        <option value="${addedit.id}" <c:if test="${addedit.id == command.addedit}">SELECTED</c:if>>&nbsp;${addedit.addedit}</option>
    </c:forEach>
</select>