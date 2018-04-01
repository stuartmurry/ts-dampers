<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<form id="form" action="editTestCycle.htm" method="post">

    <%--<input type="hidden" name="id" value="${command.id}">--%>
    <input type="hidden" name="custId" value="${command.customer.id}">
    <input type="hidden" name="buildingId" value="${command.building.id}">

    Client: ${command.customer.customerName}<br>
    Buiding: ${command.building.buildingName}<br>
    Description: <input name="description" type="text" value="${command.description}"><br>
    Start Date:<script>DateInput('sDate', false, 'DD-MON-YYYY', '${command.sDate}')</script><br>
    Finish Date:<script>DateInput('fDate', false, 'DD-MON-YYYY', '${command.fDate}')</script><br>


    <c:if test="${role.role == 'admin'}">
        <input type="submit" name="save" value="Save"  />
        <input type="submit" name="cancel" value="Cancel"  />
    </c:if>


</form>

