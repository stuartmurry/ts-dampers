<%-- 
    Document   : index
    Created on : Oct 3, 2009, 4:35:08 PM
    Author     : smurry
--%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dli">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Welcome</title>
        <link rel="stylesheet" type="text/css" href="css/index.css">
        <link rel="stylesheet" type="text/css" href="css/stripes.css">
        <script type="text/javascript" src="script/stripes.js"></script>
        <link rel="stylesheet" type="text/css" href="css/thumbnail.css">
        <script type="text/javascript">
            $(document).ready(function () {
                //Even-Odd table striping 
                stripe('stripes', '#fff', '#edf3fe');
            });

            // Progess bar widget, by Matthew Harvey (matt at smallish.com) -->
            // Licensed under a Creative Commons Attribution-Share Alike 2.5 License (http://creativecommons.org/licenses/by-sa/2.5/) -->
            function drawProgressBar(color, width, percent) {
                var pixels = width * (percent / 100);
                document.write('<div class="smallish-progress-wrapper" style="width: ' + width + 'px">');
                document.write('<div class="smallish-progress-bar" style="width: ' + pixels + 'px; background-color: ' + color + ';"></div>');
                document.write('<div class="smallish-progress-text" style="width: ' + width + 'px">' + percent + '%</div>');
                document.write('</div>');
            }
        </script>
    </head>
    <body>

        <p>Welcome <b>${user.firstName} ${user.lastName}</b> to <span style="color: red">Thermal Strategies'</span> damper database portal. <p>

        <table>
            <tr>
                <td style="text-align: center" width="100px"><a href="damper.htm" ><img border="0" align="top" alt="Damper DB" src="images/damperdb92.gif" /></a><b>Damper Database</b></td>
                <td>This function allows you to view dampers within a specific site, building, or floor.
                </td>
                <c:if test="${role.role != 'employee'}">
                    <td style="text-align: center" width="100px"><a href="listRepairHistoryFromIndex.htm?back=index" ><img border="0" align="top" alt="Repairs" src="images/repairs92.gif" /></a><b>Repairs</b></td>
                    <td>This function allows you to see real-time repairs of failed or inaccessible dampers.
                    </td>
                    <td style="text-align: center" width="100px"><a href="passFail.htm" ><img border="0" align="top" alt="Statistics" src="images/statistics.gif" /></a><b>Statistics</b></td>
                    <td>This function provides you with an over-all breakdown of dampers in all of your buildings based on a
                        pass, fail, and inaccessible status.</td>
                    </c:if>

                <c:if test="${role.role != 'employee'}">

                    <td style="text-align: center" width="100px"><a href="nextTestDate.htm" ><img border="0" align="top" alt="Test Schedule" src="images/schedule92.gif" /></a><b>Test Schedule</b></td>
                    <td>This function allows you to see the exact of number of dampers that must be tested within a specific year.
                    </td>
                    <td style="text-align: center" width="100px"><a href="reports.htm" ><img border="0" align="top" alt="Reports" src="images/reports.gif" /></a><b>Reports</b></td>
                    <td>This function allows you to generate detailed reports based on individual criteria.
                    </td>

                    <%--<tr><td>Links</td><td><a href="dampertest_report.xls?custId=2">Sample Report</a></td></tr>--%>
                </c:if>
            </tr>
        </table>

        <c:choose>
            <c:when test="${not empty buildingUnderTestList}">
                <hr/>
                <p style="text-align:center; font-weight: bold; color: blue">BUILDINGS CURRENTLY UNDER TEST</p>
                <hr/>
                <table id="stripes" style="width:100%">
                    <c:forEach items="${buildingUnderTestList}" var="rh">
                        <tr>
                            <td style="width:100px">${rh.startDate}</td>
                            <td>${rh.customer}</td>
                            <td>${rh.building}</td>
                            <td>
                                <script type="text/javascript">drawProgressBar('#ff0000', 200, ${rh.percentComplete});</script>
                            </td>
                            <c:if test="${role.role != 'customer'}">
                                <c:choose>
                                    <c:when test="${rh.percentComplete == 100}">
                                        <td><a href="confirmTestCycle.htm?id=${rh.testCycleId}">Confirm</a></td> 
                                    </c:when>
                                    <c:otherwise>
                                        <td>Confirm (Must be 100% to confirm)</td>
                                    </c:otherwise>

                                </c:choose>

                            </c:if>


                        </tr>
                        <%--<c:forEach items="${rh.floorUnderTestList}" var="f">
                            <tr>
                                <td></td>
                                <td></td>
                                <td>${f.floor}</td>
                                <td>
                                    <script type="text/javascript">drawProgressBar('#ff0000', 200, ${f.percentComplete});</script>
                                </td>
                            </tr>
                        </c:forEach>--%>
                    </c:forEach>
                </table>
            </c:when>
        </c:choose>


    </body>
</html>
