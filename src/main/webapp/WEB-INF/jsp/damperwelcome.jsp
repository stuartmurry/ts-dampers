<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Damper Database</title>
        <link rel="stylesheet" type="text/css" href="css/damper.css">
        
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
                
                function areYouSureCustomer(client)
                {
                    var answer = confirm('Are you sure you want to delete this client?');
                    if (answer)
                    {
                        window.location = "deleteClient.htm?id=" + client;
                    }

                }
                function areYouSureBuilding(building)
                {
                    var answer = confirm('Are you sure you want to delete this building?');
                    if (answer)
                    {
                        window.location = "deleteBuilding.htm?id=" + building;
                    }

                }
                function areYouSureFloor(floor)
                {
                    var answer = confirm('Are you sure you want to delete this floor?');
                    if (answer)
                    {
                        window.location = "deleteFloor.htm?id=" + floor;
                    }

                }
            });
        </script>

    </head>
    <body>

        <h1>Welcome to the Damper Database</h1>

        <table>
            <tr><td valign="top" width="300px">
                    <div id="cool">
                        <ul style="color:blue" id="treemenu" class="treeview">
                            <c:choose>
                                <c:when test="${fn:length(customerBuildingFloorBeanList) == 1}">
                                    <c:forEach items="${customerBuildingFloorBeanList}" var="cust">
                                        <c:forEach items="${cust.buildingList}" var="building">
                                            <li><b>${building.buildingName}</b>
                                                <a href="listDampers.htm?custId=${cust.id}&buildingId=${building.id}&level=2" >(list all)</a>
                                                <ul>
                                                    <c:forEach items="${building.buildingFloorList}" var="floor">
                                                        <li><a href="listDampers.htm?custId=${cust.id}&buildingId=${building.id}&floorId=${floor.id}&level=3" ><b>${floor.floorName}</b></a></li>
                                                                </c:forEach>
                                                </ul>
                                            </li>
                                        </c:forEach>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach items="${customerBuildingFloorBeanList}" var="cust">
                                        <li><b style="color: blue">${cust.customerName}</b>
                                            <a href="listDampers.htm?custId=${cust.id}&level=1">(list all)</a>
                                            <ul>
                                                <c:forEach items="${cust.buildingList}" var="building">
                                                    <li style="color:blue">${building.buildingName}
                                                        <a href="listDampers.htm?custId=${cust.id}&buildingId=${building.id}&level=2" >(list all)</a>
                                                        <ul>
                                                            <c:forEach items="${building.buildingFloorList}" var="floor">
                                                                <li><a href="listDampers.htm?custId=${cust.id}&buildingId=${building.id}&floorId=${floor.id}&level=3" ><b>${floor.floorName}</b></a></li>
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

    </body>
</html>

