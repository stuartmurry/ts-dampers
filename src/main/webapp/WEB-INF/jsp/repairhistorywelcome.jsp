<%-- 
    Document   : listrepairhistorywelcome
    Created on : Feb 10, 2010, 5:39:11 PM
    Author     : Stuart
--%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Repair History</title>
        
        <link rel="stylesheet" type="text/css" href="css/simpletree.css">
        <script type="text/javascript" src="script/simpletreemenu.js">

            /***********************************************
             * Simple Tree Menu- © Dynamic Drive DHTML code library (www.dynamicdrive.com)
             * This notice MUST stay intact for legal use
             * Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
             ***********************************************/

        </script>
        <script type="text/javascript">
            $(document).ready(function () {
                /* Initialize tree menu here. */
                ddtreemenu.createTree("treemenu", false);
            });
        </script>
    </head>
    <body>

        <h1>Repair History</h1>

        <table>
            <tr><td valign="top" width="300px">
                    <div>
                        <a href="listRepairHistory.htm">Show All</a>
                        <ul style="color:blue" id="treemenu" class="treeview">
                            <c:choose>
                                <c:when test="${fn:length(customerBuildingFloorBeanList) == 1}">
                                    <c:forEach items="${customerBuildingFloorBeanList}" var="cust">
                                        <c:forEach items="${cust.buildingList}" var="building">
                                            <li><b>${building.buildingName}</b>
                                                <a href="listRepairHistory.htm?custId=${cust.id}&buildingId=${building.id}&level=2" >(list all)</a>
                                                <ul>
                                                    <c:forEach items="${building.buildingFloorList}" var="floor">
                                                        <li><a href="listRepairHistory.htm?custId=${cust.id}&buildingId=${building.id}&floorId=${floor.id}&level=3" ><b>${floor.floorName}</b></a></li>
                                                                </c:forEach>
                                                </ul>
                                            </li>
                                        </c:forEach>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach items="${customerBuildingFloorBeanList}" var="cust">
                                        <li><b style="color: blue">${cust.customerName}</b>
                                            <a href="listRepairHistory.htm?custId=${cust.id}&level=1">(list all)</a>
                                            <ul>
                                                <c:forEach items="${cust.buildingList}" var="building">
                                                    <li style="color:blue">${building.buildingName}
                                                        <a href="listRepairHistory.htm?custId=${cust.id}&buildingId=${building.id}&level=2" >(list all)</a>
                                                        <ul>
                                                            <c:forEach items="${building.buildingFloorList}" var="floor">
                                                                <li><a href="listRepairHistory.htm?custId=${cust.id}&buildingId=${building.id}&floorId=${floor.id}&level=3" ><b>${floor.floorName}</b></a></li>
                                                                        </c:forEach>
                                                        </ul>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </li>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </ul>

                    </div>
                </td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td valign="top">
                    To select a specific floor within a building, simply click the folder icon next to the building.
                    To select a specific building,
                    simply click the folder icon next to the building.  Once the building has expanded
                    click the floor you wish to view.  You will then see a list of all dampers located on that floor.
                    If you wish to view all dampers within a specific building click view all link next to the building.
                    You will then see a list of all dampers located in that building.
                </td></tr>

        </table>

        <script type="text/javascript">

            //ddtreemenu.createTree(treeid, enablepersist, opt_persist_in_days (default is 1))

            //ddtreemenu.createTree("treemenu1", true)
            //ddtreemenu.createTree("treemenu2", false)
            ddtreemenu.createTree("treemenu", false);

        </script>

    </body>
</html>
