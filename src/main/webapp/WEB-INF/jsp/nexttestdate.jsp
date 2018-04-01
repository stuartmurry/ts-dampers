<%-- 
    Document   : nexttestdate
    Created on : Dec 3, 2009, 4:05:44 PM
    Author     : Stuart
--%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Next Test Date Information</title>
        <%--<meta name="damper" content="menu">--%>
    </head>
    <body>

        <%--border:1px solid #98bf21;--%>

    <style type="text/css">

        #customers
        {
            border-collapse:collapse;
        }
        #customers td, #customers th
        {
            border:1px solid #000000;
            padding:3px 7px 2px 7px;
        }
        #customers th
        {
            text-align:left;
            padding-top:5px;
            padding-bottom:4px;
            background-color:#246e9f;
            color:#fff;
        }
        #customers tr.alt td
        {
            color:#000;
            background-color:#EAF2D3;
        }
        .table_header {
            background: url(images/bg_table.gif) repeat-x;
            font-size:11px;
            color:#fff;
            margin:0;
            font-weight:bold;
            text-align:left;
        }

    </style>



    <h1>Test Schedule</h1>
    <table>
        <c:forEach items="${nextTestBeanList}" var="next">
            <tr><td style="text-transform:uppercase; text-align:center; font-weight:bold; border:0">${next.customerName}</td></tr>
            <tr><td>
                    <table border="1"  style="background:transparent; margin:0; width:100%">
                        <tr>
                            <td style="width:30%; border:0px">&nbsp;</td>
                            <c:forEach items="${yearList}" var="year">
                                <th style="text-align:center;background: url(images/bg_table.gif) repeat-x; color:white">${year}</th>
                            </c:forEach>
                        </tr>

                        <c:forEach items="${next.nextTestBuildingBeanList}" var="building">
                            <tr><td style="text-align:center; font-weight:bold; border:0;">${building.buildingName}</td>
                                <c:choose>
                                    <c:when test="${building.yearSum > 0}">
                                        <td style="border:0px"><a href="listDampers.htm?custId=${next.custId}&buildingId=${building.buildingId}&year=${building.baseYear}">${building.yearSum}</a></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td style="border:0px">${building.yearSum}</td>
                                    </c:otherwise>
                                </c:choose>

                                <c:choose>
                                    <c:when test="${building.yearplus1Sum > 0}">
                                        <td style="border:0px"><a href="listDampers.htm?custId=${next.custId}&buildingId=${building.buildingId}&year=${building.baseYear + 1}">${building.yearplus1Sum}</a></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td style="border:0px">${building.yearplus1Sum}</td>
                                    </c:otherwise>
                                </c:choose>

                                <c:choose>
                                    <c:when test="${building.yearplus2Sum > 0}">
                                        <td style="border:0px"><a href="listDampers.htm?custId=${next.custId}&buildingId=${building.buildingId}&year=${building.baseYear + 2}">${building.yearplus2Sum}</a></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td style="border:0px">${building.yearplus2Sum}</td>
                                    </c:otherwise>
                                </c:choose>

                                <c:choose>
                                    <c:when test="${building.yearplus3Sum > 0}">
                                        <td style="border:0px"><a href="listDampers.htm?custId=${next.custId}&buildingId=${building.buildingId}&year=${building.baseYear + 3}">${building.yearplus3Sum}</a></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td style="border:0px">${building.yearplus3Sum}</td>
                                    </c:otherwise>
                                </c:choose>

                                <c:choose>
                                    <c:when test="${building.yearplus4Sum > 0}">
                                        <td style="border:0px"><a href="listDampers.htm?custId=${next.custId}&buildingId=${building.buildingId}&year=${building.baseYear + 4}">${building.yearplus4Sum}</a></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td style="border:0px">${building.yearplus4Sum}</td>
                                    </c:otherwise>
                                </c:choose>

                                <c:choose>
                                    <c:when test="${building.yearplus5Sum > 0}">
                                        <td style="border:0px"><a href="listDampers.htm?custId=${next.custId}&buildingId=${building.buildingId}&year=${building.baseYear + 5}">${building.yearplus5Sum}</a></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td style="border:0px">${building.yearplus5Sum}</td>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${building.yearplus6Sum > 0}">
                                        <td style="border:0px"><a href="listDampers.htm?custId=${next.custId}&buildingId=${building.buildingId}&year=${building.baseYear + 6}">${building.yearplus6Sum}</a></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td style="border:0px">${building.yearplus6Sum}</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td style="text-align:center; font-weight:bold; border:0">TOTAL</td>

                            <c:choose>
                                <c:when test="${next.overallSum > 0}">
                                    <td style="text-align:left; border-left: white; border-bottom: white; border-right: white">
                                        <a href="listDampers.htm?custId=${next.custId}&year=${next.baseYear}">${next.overallSum}</a>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td style="text-align:left; border-left: white; border-bottom: white; border-right: white">0</td>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${next.overallSumPlus1 > 0}">
                                    <td style="text-align:left; border-left: white; border-bottom: white; border-right: white">
                                        <a href="listDampers.htm?custId=${next.custId}&year=${next.baseYear + 1}">${next.overallSumPlus1}</a>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td style="text-align:left; border-left: white; border-bottom: white; border-right: white">0</td>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${next.overallSumPlus2 > 0}">
                                    <td style="text-align:left; border-left: white; border-bottom: white; border-right: white">
                                        <a href="listDampers.htm?custId=${next.custId}&year=${next.baseYear + 2}">${next.overallSumPlus2}</a>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td style="text-align:left; border-left: white; border-bottom: white; border-right: white">0</td>
                                </c:otherwise>
                            </c:choose>


                            <c:choose>
                                <c:when test="${next.overallSumPlus3 > 0}">
                                    <td style="text-align:left; border-left: white; border-bottom: white; border-right: white">
                                        <a href="listDampers.htm?custId=${next.custId}&year=${next.baseYear + 3}">${next.overallSumPlus3}</a>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td style="text-align:left; border-left: white; border-bottom: white; border-right: white">0</td>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${next.overallSumPlus4 > 0}">
                                    <td style="text-align:left; border-left: white; border-bottom: white; border-right: white">
                                        <a href="listDampers.htm?custId=${next.custId}&year=${next.baseYear + 4}">${next.overallSumPlus4}</a>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td style="text-align:left; border-left: white; border-bottom: white; border-right: white">0</td>
                                </c:otherwise>
                            </c:choose>


                            <c:choose>
                                <c:when test="${next.overallSumPlus5 > 0}">
                                    <td style="text-align:left; border-left: white; border-bottom: white; border-right: white">
                                        <a href="listDampers.htm?custId=${next.custId}&year=${next.baseYear + 5}">${next.overallSumPlus5}</a>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td style="text-align:left; border-left: white; border-bottom: white; border-right: white">0</td>
                                </c:otherwise>
                            </c:choose>


                            <c:choose>
                                <c:when test="${next.overallSumPlus6 > 0}">
                                    <td style="text-align:left; border-left: white; border-bottom: white; border-right: white">
                                        <a href="listDampers.htm?custId=${next.custId}&year=${next.baseYear + 6}">${next.overallSumPlus6}</a>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td style="text-align:left; border-left: white; border-bottom: white; border-right: white">0</td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </table>
                </td></tr>
            </c:forEach>
    </table>
</body>
</html>
