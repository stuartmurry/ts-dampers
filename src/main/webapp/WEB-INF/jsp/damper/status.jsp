<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<select id="damperStatus" name="damperstatus_id">
    <option value="0">-- Select One --</option>
    <!--c:forEach items="${command.damperstatuslist}" var="damperstatus"-->
    <option value="1" style="color:green" <c:if test="${command.dampertest.damperstatus.status eq 'PASS'}"> SELECTED</c:if>>PASS</option>
    <option value="2" style="color:red" <c:if test="${command.dampertest.damperstatus.status eq 'FAIL'}"> SELECTED</c:if>>FAIL</option> 
    <option value="3" style="color:blue" <c:if test="${command.dampertest.damperstatus.status eq 'INACCESSABLE'}"> SELECTED</c:if>>INACCESSABLE</option> 
    <!--/c:forEach-->
</select>

