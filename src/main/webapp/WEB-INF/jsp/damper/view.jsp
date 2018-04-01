<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<h1>VIEW DAMPERS</h1>
<!-- fmt:formatNumber value="${paymentAmount}" type="currency" currencyCode="USD"/-->
<table width="100%">
<tr class="projectheader">
    <td>ID</td>
    <td>DATE</td>
    <td>DAMPER ID</td>
    <td>TYPE</td>
    <td>SIZE</td>
    <td>MATERIAL</td>
    <td>FLOOR</td>
    <td>SYSTEM</td>
    <td>STATUS</td>
    <td>LOCATION</td>
    <td>SUB LOCATION</td>
    <td>COMMENTS</td>
</tr>
<c:forEach items="${model.damperlist}" var="damper">
    <tr class="projectlist">
        <td><c:out value="${damper.id}"/></td>
        <td><c:out value="${damper.date}"/></td>
        <td><c:out value="${damper.building}"/></td>
        <td><c:out value="${damper.type}"/></td>
        <td><c:out value="${damper.size}"/></td>
        <td><c:out value="${damper.material}"/></td>
        <td><c:out value="${damper.floor}"/></td>
        <td><c:out value="${damper.system}"/></td>
        <td><c:out value="${damper.status}"/></td>
        <td><c:out value="${damper.location}"/></td>
        <td><c:out value="${damper.sublocation}"/></td>
        <td><c:out value="${damper.comments}"/></td>
    </tr>     
</c:forEach>
</table>
<p>
    
</p>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
