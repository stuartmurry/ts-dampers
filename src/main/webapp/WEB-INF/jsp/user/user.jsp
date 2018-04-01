<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Edit a User</title>
        <!-- Show client menu -->
    </head>

    <body>
        <script type="text/javascript">
            function showhide(what,obj) {
                obj1 = document.getElementById(obj);
                if(what) {
                    obj1.style.visibility = 'visible';
                } else {
                    obj1.style.visibility = 'hidden';
                }
            }

        </script>
    <style type="text/css">
        .textbox {
            border: 1px solid #6297BC;
            width: 230px;
        }
        .errorBox {
            color:red;
        }
        .checkbox{
            border: 1px solid #6297BC;
        }

    </style>

    <form:form>
        <spring:hasBindErrors name="command">
            <span class="error">
                <p>There were ${errors.errorCount} error(s) in total:</p>
                <ul><c:forEach var="errMsgObj" items="${errors.allErrors}">
                        <li><spring:message code="${errMsgObj.code}" text="${errMsgObj.defaultMessage}"/></li>
                    </c:forEach></ul>
            </span>
        </spring:hasBindErrors>

        <table>
            <tr><td>
                    <table>
                        <tr><td>Role</td><td>
                                <c:if test="${role.role == 'admin'}">
                                    <form:select path="role_id">
                                        <form:option value="0">-- Select One --</form:option>
                                        <form:option cssStyle="color:black"  value="400">Customer</form:option>
                                        <form:option cssStyle="color:black"  value="100">Administrator</form:option>
                                        <%--<form:option cssStyle="color:black"  value="200">Manager</form:option>--%>
                                        <form:option cssStyle="color:black"  value="300">Employee</form:option>
                                        <%--<form:option cssStyle="color:blue"   value="500">Guest</form:option>--%>
                                    </form:select>
                                </c:if>
                            </td>
                        </tr>

                        <tr><td>Enabled</td><td><form:checkbox path="isenabled" cssClass="checkbox" /></td></tr>
                        <tr><td>UserName</td><td><form:input path="userName" cssClass="textbox" /></td></tr>
                        <tr><td>Email Address</td><td><form:input path="email" cssClass="textbox" /></td></tr>
                        <tr><td>Password</td><td><form:password path="password" cssClass="textbox" showPassword="true" /></td></tr>
                        <tr><td>Confirm</td><td><form:password path="passwordconfirm" cssClass="textbox" showPassword="true" /></td></tr>
                        <tr><td>First Name</td><td><form:input path="firstName" cssClass="textbox"/></td></tr>
                        <tr><td>Last Name</td><td><form:input path="lastName" cssClass="textbox"  /></td></tr>
                        <tr><td>Address</td><td><form:input path="address1" cssClass="textbox" /></td></tr>
                        <tr><td>Address</td><td><form:input path="address2" cssClass="textbox" /></td></tr>
                        <tr><td>City</td><td><form:input path="city" cssClass="textbox" /></td></tr>
                        <tr><td>State</td><td><form:input path="state" cssClass="textbox" /></td></tr>
                        <tr><td>Zip</td><td><form:input path="zip" cssClass="textbox" /></td></tr>
                        <tr><td>Phone</td><td><form:input path="phone" size="25" cssClass="textbox" /></td></tr>
                        <tr><td>Include Building IDs</td><td><form:input path="includebuildingids" size="25" cssClass="textbox" /></td></tr>

                        <tr><td></td><td>
                                <c:if test="${role.role == 'admin'}">
                                    <input type="Submit" name ="Delete" value="Delete" />
                                </c:if>
                                <input type="Submit" name="Save" value="Save"/>
                                <input type="button" onClick="history.back()" value="Cancel"/>
                            </td></tr>

                    </table>

                </td><td>

                    <c:forEach items="${command.customerSelectList}" var="csl">
                        <input type="checkbox" name="${csl.id}" <c:if test="${csl.checked}">CHECKED</c:if>>  ${csl.name}<br>
                    </c:forEach>

                </td></tr></table>

    </form:form>
</body>
</html>


