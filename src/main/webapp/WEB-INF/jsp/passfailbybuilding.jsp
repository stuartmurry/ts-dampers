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
        <title>Damper Statistics By Building</title>
    </head>
    <body>
        <div style="width: 700px; margin-left: auto; margin-right: auto ;">
            <p style="text-align: center"><strong>${buildingName}</strong></p>
            <br>

            <table border="1" id="passFail">
                <tr>
                    <th style="text-align:center">Floor</th>
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
                <c:forEach items="${passFailList}" var="passFail">
                    <tr>
                        <td>${passFail.floorName}</td>


                        <c:choose>
                            <c:when test="${passFail.pass > 0}">
                                <td style="text-align:center"><a href="listDampers.htm?custId=${custId}&buildingId=${buildingId}&floorId=${passFail.floorId}&status=1"><span style="color:green">${passFail.pass}</span></a></td>
                            </c:when>
                            <c:otherwise>
                                <td style="text-align:center"><span style="color:green">0</span></td>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${passFail.fail > 0}">
                                <td style="text-align:center"><a href="listDampers.htm?custId=${custId}&buildingId=${buildingId}&floorId=${passFail.floorId}&status=2"><span style="color:red">${passFail.fail}</span></a></td>
                            </c:when>
                            <c:otherwise>
                                <td style="text-align:center"><span style="color:red">0</span></td>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${passFail.inaccessible > 0}">
                                <td style="text-align:center"><a href="listDampers.htm?custId=${custId}&buildingId=${buildingId}&floorId=${passFail.floorId}&status=3"><span style="color:blue">${passFail.inaccessible}</span></a></td>
                            </c:when>
                            <c:otherwise>
                                <td style="text-align:center"><span style="color:blue">0</span></></td>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${(passFail.pass + passFail.fail + passFail.inaccessible) > 0}">
                                <td style="text-align:center"><a href="listDampers.htm?custId=${custId}&buildingId=${buildingId}&floorId=${passFail.floorId}">${passFail.pass + passFail.fail + passFail.inaccessible}</a></td>
                            </c:when>
                            <c:otherwise>
                                <td style="text-align:center">0</td>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${passFail.failedRepaired > 0}">
                                <td style="text-align:center"><a href="listDampers.htm?custId=${custId}&buildingId=${buildingId}&floorId=${passFail.floorId}&status=4"><span style="color:red">${passFail.failedRepaired}</span></a></td>
                            </c:when>
                            <c:otherwise>
                                <td style="text-align:center"><span style="color:red">0</span></td>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${passFail.inaccessibleRepaired > 0}">
                                <td style="text-align:center"><a href="listDampers.htm?custId=${custId}&buildingId=${buildingId}&floorId=${passFail.floorId}&status=5"><span style="color:blue">${passFail.inaccessibleRepaired}</span></a></td>
                            </c:when>
                            <c:otherwise>
                                <td style="text-align:center"><span style="color:blue">0</span></></td>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${passFail.failInaccessible > 0}">
                                <td style="text-align:center"><a href="listDampers.htm?custId=${custId}&buildingId=${buildingId}&floorId=${passFail.floorId}&status=8"><span style="color:red">${passFail.failInaccessible}</span></a></td>
                            </c:when>
                            <c:otherwise>
                                <td style="text-align:center"><span style="color:blue">0</span></></td>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${passFail.newConstruction > 0}">
                                <td style="text-align:center"><a href="listDampers.htm?custId=${custId}&buildingId=${buildingId}&floorId=${passFail.floorId}&occupancy=NEW_CONSTRUCTION"><span style="color:blue">${passFail.newConstruction}</span></a></td>
                            </c:when>
                            <c:otherwise>
                                <td style="text-align:center"><span style="color:blue">0</span></></td>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${passFail.pending > 0}">
                                <td style="text-align:center"><a href="listDampers.htm?custId=${custId}&buildingId=${buildingId}&floorId=${passFail.floorId}&status=7"><span style="color:purple">${passFail.pending}</span></a></td>
                            </c:when>
                            <c:otherwise>
                                <td style="text-align:center"><span style="color:blue">0</span></></td>
                            </c:otherwise>
                        </c:choose>


                    </tr>

                </c:forEach>
            </table>
        </div>
    </body>
</html>
