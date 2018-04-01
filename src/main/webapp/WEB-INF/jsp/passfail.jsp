<%-- 
    Document   : passfail
    Created on : Dec 17, 2009, 6:52:59 AM
    Author     : Stuart
--%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Damper Statistics</title>
    </head>
    <body>
        <table width="100%">
            <tr>
                <td valign="top">
                    <c:forEach items="${passFailList}" var="passFail">
                        <div style="height: 500px">
                            <table align="center"><tr><td align="left">
                                        <img  align="top" alt="${passFail.customerName}" src="passFailPieChart.htm?pass=${passFail.pass}&fail=${passFail.fail}&inaccessible=${passFail.inaccessible}&customer=${passFail.customerName}" />
                                        <br><br><br><br>
                                        <span style="color:green">PASS</span>:
                                        <c:choose>
                                            <c:when test="${passFail.pass > 0}">
                                                <a href="listDampers.htm?custId=${passFail.custId}&status=1">${passFail.pass}</a>
                                            </c:when>
                                            <c:otherwise>0</c:otherwise>
                                        </c:choose><br>
                                        <span style="color:red">FAIL</span>:
                                        <c:choose>
                                            <c:when test="${passFail.fail > 0}">
                                                <a href="listDampers.htm?custId=${passFail.custId}&status=2">${passFail.fail}</a>
                                            </c:when>
                                            <c:otherwise>0</c:otherwise>
                                        </c:choose><br>
                                        <span style="color:blue">INACCESSIBLE</span>:
                                        <c:choose>
                                            <c:when test="${passFail.inaccessible > 0}">
                                                <a href="listDampers.htm?custId=${passFail.custId}&status=3">${passFail.inaccessible}</a>
                                            </c:when>
                                            <c:otherwise>0</c:otherwise>
                                        </c:choose><br>
                                        <span style="color:purple">PENDING</span>:
                                        <c:choose>
                                            <c:when test="${passFail.pending > 0}">
                                                <a href="listDampers.htm?custId=${passFail.custId}&status=7">${passFail.pending}</a>
                                            </c:when>
                                            <c:otherwise>0</c:otherwise>
                                        </c:choose><br>

                                    </td></tr></table>
                        </div>
                    </c:forEach>
                </td>
                <td valign="top">

                    <table align="center"><tr><td>&nbsp;</td><td>
                                <!-- Pass fail counters -->

                                <c:forEach items="${passFailManagerList}" var="passFail">
                                    <div style="height: 500px">
                                        <table>
                                            <%--<tr><td align="center"><h1>${passFail.customerName}</h1></td></tr>--%>
                                            <tr><td style="font-size: 12pt">&nbsp;</td></tr>
                                            <tr><td><table border="1">
                                                        <tr>
                                                            <th>&nbsp;</th>
                                                            <th style="text-align:center">Pass</th>
                                                            <th style="text-align:center">Fail</th>
                                                            <th style="text-align:center">Inaccessible</th>
                                                            <th style="text-align:center">Total</th>
                                                            <th style="text-align:center">Fail:Repaired</th>
                                                            <th style="text-align:center">Inaccessible:Repaired</th>
                                                            <th style="text-align:center">Fail:Inaccessible</th>
                                                            <th style="text-align:center">New Construction</th>
                                                            <th style="text-align:center">Pending</th>
                                                        </tr>
                                                        <c:forEach items="${passFail.buildingList}" var="building">
                                                            <tr>
                                                                <td style="text-align:center"><b><a href="passFailByBuilding.htm?id=${building.buildingId}&custId=${passFail.custId}">${building.buildingName}</a></b></td>
                                                                <c:choose>
                                                                    <c:when test="${building.pass > 0}">
                                                                        <td style="text-align:center"><a href="listDampers.htm?custId=${passFail.custId}&buildingId=${building.buildingId}&status=1"><span style="color:green">${building.pass}</span></a></td>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <td style="text-align:center"><span style="color:green">0</span></td>
                                                                    </c:otherwise>
                                                                </c:choose>

                                                                <c:choose>
                                                                    <c:when test="${building.fail > 0}">
                                                                        <td style="text-align:center"><a href="listDampers.htm?custId=${passFail.custId}&buildingId=${building.buildingId}&status=2"><span style="color:red">${building.fail}</span></a></td>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <td style="text-align:center"><span style="color:red">0</span></td>
                                                                    </c:otherwise>
                                                                </c:choose>

                                                                <c:choose>
                                                                    <c:when test="${building.inaccessible > 0}">
                                                                        <td style="text-align:center"><a href="listDampers.htm?custId=${passFail.custId}&buildingId=${building.buildingId}&status=3"><span style="color:blue">${building.inaccessible}</span></a></td>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <td style="text-align:center"><span style="color:blue">0</span></></td>
                                                                    </c:otherwise>
                                                                </c:choose>

                                                                <c:choose>
                                                                    <c:when test="${(building.pass + building.fail + building.inaccessible) > 0}">
                                                                        <td style="text-align:center"><a href="listDampers.htm?custId=${passFail.custId}&buildingId=${building.buildingId}">${building.pass + building.fail + building.inaccessible}</a></td>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <td style="text-align:center">0</td>
                                                                    </c:otherwise>
                                                                </c:choose>

                                                                <c:choose>
                                                                    <c:when test="${building.failedRepaired > 0}">
                                                                        <td style="text-align:center"><a href="listDampers.htm?custId=${passFail.custId}&buildingId=${building.buildingId}&status=4"><span style="color:red">${building.failedRepaired}</span></a></td>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <td style="text-align:center"><span style="color:red">0</span></td>
                                                                    </c:otherwise>
                                                                </c:choose>

                                                                <c:choose>
                                                                    <c:when test="${building.inaccessibleRepaired > 0}">
                                                                        <td style="text-align:center"><a href="listDampers.htm?custId=${passFail.custId}&buildingId=${building.buildingId}&status=5"><span style="color:blue">${building.inaccessibleRepaired}</span></a></td>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <td style="text-align:center"><span style="color:blue">0</span></></td>
                                                                    </c:otherwise>
                                                                </c:choose>

                                                                <c:choose>
                                                                    <c:when test="${building.failInaccessible > 0}">
                                                                        <td style="text-align:center"><a href="listDampers.htm?custId=${passFail.custId}&buildingId=${building.buildingId}&status=8"><span style="color:blue">${building.failInaccessible}</span></a></td>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <td style="text-align:center"><span style="color:red">0</span></></td>
                                                                    </c:otherwise>
                                                                </c:choose>

                                                                <c:choose>
                                                                    <c:when test="${building.newConstruction > 0}">
                                                                        <td style="text-align:center"><a href="listDampers.htm?custId=${passFail.custId}&buildingId=${building.buildingId}&occupancy=NEW_CONSTRUCTION"><span style="color:blue">${building.newConstruction}</span></a></td>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <td style="text-align:center"><span style="color:blue">0</span></></td>
                                                                    </c:otherwise>
                                                                </c:choose>

                                                                <c:choose>
                                                                    <c:when test="${building.pending > 0}">
                                                                        <td style="text-align:center"><a href="listDampers.htm?custId=${passFail.custId}&buildingId=${building.buildingId}&status=7"><span style="color:purple">${building.pending}</span></a></td>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <td style="text-align:center"><span style="color:purple">0</span></td>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </tr>
                                                        </c:forEach>
                                                    </table></td></tr>
                                        </table>
                                    </div>
                                </c:forEach>

                            </td><td></td></tr></table>


                </td>
            </tr>

        </table>

    </body>
</html>
