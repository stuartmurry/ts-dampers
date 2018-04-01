
<%--
    Document   : damper
    Created on : Oct 3, 2009, 4:37:23 PM
    Author     : smurry
--%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Damper Test Form</title>
        <!-- Show client menu -->
        <meta name="damper" content="menu">
        <meta name="errors" content="exipres">

        <script type="text/javascript" src="script/calendarDateInput.js">

            /***********************************************
             * Jason's Date Input Calendar- By Jason Moon http://calendar.moonscript.com/dateinput.cfm
             * Script featured on and available at http://www.dynamicdrive.com
             * Keep this notice intact for use.
             ***********************************************/

        </script>

        <script type="text/javascript">
            function areYouSureDamper()
            {
                var answer = confirm('Are you sure you want to delete this damper?');
                if (answer)
                {
                    window.location = "deleteDamper.htm?id=${command.id}&custId=${command.customer_id}&buildingId=${command.building_id}&floorId=${command.buildingfloor_id}";
                }
            }

            function areYouSureDecommission()
            {
                var answer = confirm('Are you sure you want to decommission this damper? ');
                if (answer)
                {
                    window.location = "decommissionDamper.htm?id=${command.id}&custId=${command.customer_id}&buildingId=${command.building_id}&floorId=${command.buildingfloor_id}";
                }
            }

            function areYouSureDeletePicture(id)
            {
                var answer = confirm('Are you sure you want to delete this picture? ');
                if (answer)
                {
                    window.location = "deletePicture.htm?id=" + id;
                }
            }

            function addComment()
            {

                var formElements = document.forms['myforms'].elements;
                var selIndex = formElements['comment'].selectedIndex;
                var currently = formElements['addComments'].value;
                var selText = formElements['comment'][selIndex].text;
                formElements['addComments'].value += selText;
            }

            function addComments()
            {

                var formElements = document.forms['form'].elements;
                var storedComments = formElements['storedComments'];
                var comments = formElements['Comments'];

                var i;
                var count = 0;
                for (i = 0; i < storedComments.options.length; i++) {
                    if (storedComments.options[i].selected) {
                        var valuable = storedComments.options[i].value;
                        var textable = storedComments.options[i].text;
                        comments.value = comments.value + textable + '\n';
                    }
                }
            }

            function addToDatabase()
            {
                var formElements = document.forms['form'].elements;
                formElements['actions'].value = '_add';
                document.forms['form'].submit();
            }

            function removeFromDatabase()
            {
                var formElements = document.forms['form'].elements;
                var selIndex = formElements['comment'].selectedIndex;
                var selValue = formElements['comment'][selIndex].value;
                formElements['params'].value = selValue;
                formElements['actions'].value = '_remove';

                document.forms['form'].submit();
            }

        </script>
    </head>
    <body>

        <form:form name="form" enctype="multipart/form-data">
            <fieldset>
                <legend>
                    ${fn:toUpperCase(command.aliasId)}
                </legend>
                <%--<form:hidden path="refresh" />--%>
                <%--<form:hidden path="id" />--%>
                <!-- We don't need Id, custId, etc because spring form action will place it as a parameter on the url-->
                <form:hidden path="series" />
                <form:hidden path="dampernumber" />
                <form:hidden path="repairHistoryCount" />

                <form:hidden path="customer_id" />
                <form:hidden path="building_id" />
                <form:hidden path="buildingfloor_id" />
                <form:hidden path="dampertype_id" />
                <br>
                <%--<table style="width:100%">
                    <tr>
                        <td></td><td style="border-width:1px;border-style:solid;border-color:black;text-align:center">${fn:toUpperCase(command.aliasId)}</td>
                    </tr>
                </table>--%>
                <spring:hasBindErrors name="command">
                    <span class="error">
                        <p>There were ${errors.errorCount} error(s) in total:</p>
                        <ul><c:forEach var="errMsgObj" items="${errors.allErrors}">
                                <li><spring:message code="${errMsgObj.code}" text="${errMsgObj.defaultMessage}"/></li>
                            </c:forEach></ul>
                    </span>
                </spring:hasBindErrors>
                <table style="width:100%">
                    <tr>
                        <%--<td style="width:10%"><div><!-- Intentionally Left Blank --></div></td>--%>
                        <td style="vertical-align:top"><div><%@include file="leftdamperform.jsp" %></div></td>
                        <td style="vertical-align:top"><div><%@include file="rightdamperform.jsp" %></div></td>
                                <%--<td style="width:10%"><div><!-- Intentionally Left Blank --></div></td>--%>
                    </tr>
                </table>
            </fieldset>
        </form:form>

        <br/>
        <br/>
        <br/>
        <br/>
        Table ID:&nbsp;${command.id}

    </body>
</html>
