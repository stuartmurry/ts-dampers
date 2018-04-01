<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<select id="dampercomment_id" name="comment" style="width:200px;" multiple="true" size="5">
    <c:forEach items="${command.dampercommentlist}" var="dampercomment">
        <option value="${dampercomment.id}"> SELECTED>&nbsp;${fn:toUpperCase(dampercomment.comment)}</option>
    </c:forEach>
</select>