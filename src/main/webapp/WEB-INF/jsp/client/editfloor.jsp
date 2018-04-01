<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<form:form>
    <form:hidden path="buildId"  />
    <form:hidden path="id"/>
    <table>
        <tr><td>Floor  Name</td><td><form:input path="floorName"/></td></tr>
        <tr><td>Sequence Number<br>(Used for ordering: use increments of 100)</td><td><form:input path="sequenceNum" /></td></tr>
        <tr><td></td><td>
                <input type="submit" name="save" value="Save"  />
                <input type="submit" name="cancel" value="Cancel"  />
            </td></tr>
    </table>



</form:form>

