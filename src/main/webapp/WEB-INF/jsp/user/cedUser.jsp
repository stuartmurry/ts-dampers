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
    function areYouSure(actionz, paramz)
    {
        var answer = confirm('Are you sure you want to delete this user?');
        if (answer)
        {
            setVarz(actionz, paramz);
        }

    }
    </script>

<form id="form" action="user.htm" method="post">
    <input type="hidden" name="action" />
    <input type="hidden" name="actionparam" />
    <tr style="vertical-align: top">
        <td>USER</td>
        <td>
            Please select from one of the options listed below.<br>
            <select name="ced">
                <option value="0">-- Select One --</option>
                <option value="create" <c:if test="${command.action == 'create'}">SELECTED</c:if>>Add a user</option>
                <option value="edit" <c:if test="${command.action == 'edit'}">SELECTED</c:if>>Edit a user</option>
                <option value="delete" <c:if test="${command.action == 'delete'}">SELECTED</c:if>>Delete a user</option>
            </select>
            <br>
            
            <input type="button" value="Cancel" onclick="javascript:setVarz('ceduser','cancel')"/>
            <input type="button" value="Submit" onclick="javascript:setVarz('ceduser','submit')"/>
        </td>
    </tr>
</form>



<%@ include file="/WEB-INF/jsp/footer.jsp" %>
