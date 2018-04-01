<%@ include file="/WEB-INF/jsp/header.jsp" %>
<%@ include file="/WEB-INF/jsp/header_body.jsp" %>

<script type="text/javascript">
    function setVarz(actionz, paramz)
    {
        var formElements = document.forms['form'].elements;
        formElements['action'].value = actionz;
        formElements['actionparam'].value = paramz;
        document.forms['form'].submit();
    }
    </script>

<form id="form" action="user.htm" method="post">
    <input type="hidden" name="action" />
    <input type="hidden" name="actionparam" />
    
    <tr style="vertical-align: top">
        <td>Delete User</td>
        <td>
            Please Choose a User to Delete<br>
            <select name="user">
                <option value="0">-- Select One --</option>
                <c:forEach items="${userlist}" var="user">
                    <option value="${user.id}" <c:if test="${user.id == command.user}">SELECTED</c:if>><c:out value="${user.lastName}"/>,<c:out value="${user.firstName}"/></option>
                </c:forEach>
            </select>
            
            <input type="button" value="Cancel" onclick="javascript:setVarz('deleteuser', 'cancel')" />
            <input type="button" value="Delete" onclick="javascript:setVarz('deleteuser', 'delete')" />
        </td>
    </tr>
</form>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>