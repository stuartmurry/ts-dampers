<%-- 
    Document   : generatereport
    Created on : Jan 22, 2010, 9:44:01 AM
    Author     : Stuart
--%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>GenerateReport</title>
    </head>
    <body>

        <table>
            <form:form>
                <script type='text/javascript' src='/eportal/dwr/interface/DamperTest.js'></script>
                <script type='text/javascript' src='/eportal/dwr/engine.js'></script>
                <script type='text/javascript' src='/eportal/dwr/util.js'></script>

                <tr><td>Customer</td><td>


                        <select id="customerIds" name="customer_id" onchange="DamperTest.getBuildings(getCustomerId(), reply0);">
                            <option value="0">-- Select One --</option>
                            <c:forEach items="${command.customerList}" var="customer">
                                <option value="${customer.id}" <c:if test="${customer.id == command.customer_id}">SELECTED</c:if>>&nbsp;${fn:toUpperCase(customer.customerName)}</option>
                            </c:forEach>
                        </select>
                        <script type='text/javascript'>

                            function getCustomerId()
                            {
                                var customerId = document.getElementById('customerIds').value;
                                return customerId;
                            }
                            var reply0 = function(data)
                            {
                                removeOptions('building_id');
                                alert(dwr.util.toDescriptiveString(data, 2));
                                
                                var i;
                                for(i = 0; i < data.length; i++) {
                                    var elSel = document.getElementById('building_id');
                                    var elOptNew = document.createElement('option');
                                    elOptNew.text = data[i].floorName;
                                    elOptNew.value = data[i].id;

                                    try
                                    {
                                        elSel.add(elOptNew, null); // standards compliant; doesn't work in IE
                                    }
                                    catch(ex)
                                    {
                                        elSel.add(elOptNew); // IE only
                                    }
                                }
                            }

                            function removeOptions(optionId)
                            {
                                // Remove
                                var elSel = document.getElementById(optionId);
                                var i;
                                for (i = elSel.length - 1; i>=0; i--)
                                {
                                    elSel.remove(i);
                                }
                                var elOptNew = document.createElement('option');
                                elOptNew.text = '-- Select One --';
                                elOptNew.value = 0;

                                try
                                {
                                    elSel.add(elOptNew, null); // standards compliant; doesn't work in IE
                                }
                                catch(ex)
                                {
                                    elSel.add(elOptNew); // IE only
                                }
                            }
                        </script>
                        <span id='d0' class='reply'></span>

                    </td></tr>
                <tr><td>Building</td><td>
                        <form:select path="building_id" onchange="javascript:submitform()">
                            <form:option value="0" label="-- Select All --"/>
                            <form:options items="${command.buildingList}" itemValue="id" itemLabel="buildingName"/>
                        </form:select>
                        <%--<form:input cssClass="allcaps" size="50" path="building" />--%>
                    </td></tr>
                <tr><td>Floor</td><td>
                        <form:select path="floor_id" onchange="javascript:submitform()" >
                            <form:option value="0" label="-- Select All --"/>
                            <form:options items="${command.floorList}" itemValue="id" itemLabel="floorName"/>
                        </form:select>
                        <%--<form:input cssClass="allcaps" size="50" path="buildingfloor" />--%>
                    </td></tr>
                <tr><td>Status</td><td>
                        <form:select path="damperstatus_id" onchange="javascript:submitform()">
                            <form:option value="0">-- Select One --</form:option>
                            <form:option cssStyle="color:green"  value="1">PASS</form:option>
                            <form:option cssStyle="color:red"  value="2">FAIL</form:option>
                            <form:option cssStyle="color:blue"  value="3">INACCESSIBLE</form:option>
                        </form:select>
                    </td></tr>

                <tr><td><input type="submit" value="Submit"></td></tr>
                    </form:form>
        </table>
    </body>
</html>
