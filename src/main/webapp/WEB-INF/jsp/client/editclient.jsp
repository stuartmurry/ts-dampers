<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<form id="form" action="editclient.htm" method="post">
    <input type="hidden" name="action" />
    <input type="hidden" name="actionparam" />
    <input type="hidden" name="id" value="${customer.id}">

    Client's Name <input name="customerName" type="text" value="${customer.customerName}"/>
    <c:if test="${role.role == 'admin'}">
        <input type="submit" name="save" value="Save"  />
        <input type="submit" name="cancel" value="Cancel"  />
    </c:if>


</form>

