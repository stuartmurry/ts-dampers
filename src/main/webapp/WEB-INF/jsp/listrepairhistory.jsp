<%-- 
    Document   : listrepairhistory
    Created on : Nov 28, 2009, 3:01:04 PM
    Author     : Stuart
--%>

<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%--window.location = "deleteRepairHistory.htm?damperId=${command.damperId}&id=${command.id}";--%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Repair History List</title>
        <%--<meta name="damper" content="menu" />--%>
        <%--<meta name="searchResults" content="on" />--%>
        <%--<meta name="testDamperOk" content="true" />--%>
        <link rel="stylesheet" type="text/css" href="css/stripes.css">
        <script type="text/javascript" src="script/stripes.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                //Even-Odd table striping 
                stripe('stripes', '#fff', '#edf3fe');

                function backAPage()
                {

                    window.location = "listDampers.htm?pageNum=${pageNum}&custId=${custId}&buildingId=${buildingId}&floorId=${floorId}&level=${level}";

                }
            });
        </script>

    </head>
    <body>
        <p>Repair History</p>

        <c:choose>
            <c:when test="${not empty repairHistoryList}">
                <table id="stripes">
                    <tr>
                        <th></th>
                        <th>Repair Date</th>
                        <th>Description</th>
                        <th>Building</th>
                        <th>Damper</th>
                        <th>Date Tested</th>
                        <th>Damper Status</th>
                        <th>Damper Comment</th>
                    </tr>
                    <c:forEach items="${repairHistoryList}" var="repair" varStatus="stat">
                        <tr>
                            <td>${stat.count}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${role.role == 'admin'}">
                                        <a href="editRepairHistoryFromRepairHistoryList.htm?id=${repair.id}&damperId=${repair.damperId}&custId=${damper.customerId}&buildingId=${damper.buildingId}&floorId=${damper.floorId}&level=${level}&pageNum=${pageNum}&back=${back}">
                                            ${repair.date}
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        ${repair.date}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                ${fn:toUpperCase(repair.description)}
                            </td>
                            <td>${fn:toUpperCase(repair.building)}</td>
                            <td><a href="editDamper.htm?id=${repair.damperId}&back=repairHistory">${fn:toUpperCase(repair.aliasId)}</a></td>
                            <td>
                                <c:choose>
                                    <c:when test="${repair.dateTestedTs != '<Undefined>'}">${repair.dateTestedTs}</c:when>
                                    <c:otherwise>
                                        <span style="color:red">&lt;Under Test&gt;</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${repair.dateTestedTs != '<Undefined>'}">${fn:toUpperCase(repair.status)}</c:when>
                                    <c:otherwise>
                                        <span style="color:red">&lt;Under Test&gt;</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${repair.dateTestedTs != '<Undefined>'}">${fn:toUpperCase(repair.comments)}</c:when>
                                    <c:otherwise>
                                        <span style="color:red">&lt;Under Test&gt;</span>
                                    </c:otherwise>
                                </c:choose>


                            </td>
                        </tr>


                    </c:forEach>
                    <tr><th></th><th>
                            <%--<form:form>
                                <input type="submit" value="Ok" />
                            </form:form>--%>
                        </th><th></th><th></th></tr>
                </table>
            </c:when>
            <c:otherwise>
                No Records Found
            </c:otherwise>
        </c:choose>

    </body>
</html>
