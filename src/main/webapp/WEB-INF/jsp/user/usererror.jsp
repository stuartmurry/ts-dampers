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
    
<div style="text-align:center;">
    <p>ERROR</p>
    <p style="color:red;">
        <c:out value="${appMsg}" />
    </p>
    <p><input type="button" value="OK" onclick="javascript:document.location.href='redirect.htm?view=project/user'" /></p>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
