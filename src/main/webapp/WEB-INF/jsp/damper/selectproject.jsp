<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<select name="project_id">
        <option value="0">-- Select One --</option>
        <c:forEach items="${command.projectlist}" var="project">
            <option value="${project.id}" <c:if test="${project.id == command.project_id}">SELECTED</c:if>>&nbsp;${project.projectNum} -- ${fn:toUpperCase(project.projectName)}</option>
        </c:forEach>
</select>
