<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<html>
    <head>
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
        </script>

    </head>
    <body>
        <h1>Clients</h1>

        <ul id="treemenu" class="treeview">

            <c:choose>
                <c:when test="${fn:length(customerBuildingFloorBeanList) != 0}">
                    <c:forEach items="${customerBuildingFloorBeanList}" var="customer" varStatus="status">
                        <li>${customer.customerName} - (<a href="#" onclick="javascript:areYouSureCustomer(${customer.id});">delete</a>)
                            <ul>
                                <c:choose>
                                    <c:when test="${fn:length(customer.buildingList) > 0}">
                                        <c:forEach items="${customer.buildingList}" var="clk" varStatus="status">
                                            <li>${clk.buildingName} (<a href="#" onclick="javascript:areYouSureBuilding(${clk.id});">delete</a>)
                                                <c:if test="${clk.siteId != null}" >
                                                    ${clk.siteName} <a href="editSite.htm?id=${clk.siteId}&custId=${customer.id}" >(edit site)</a>
                                                </c:if>
                                                <ul>
                                                    <c:forEach items="${clk.buildingFloorList}" var="blk" varStatus="status">
                                                        <li>${blk.floorName} - ${blk.sequenceNum}(<a href="#" onclick="javascript:areYouSureFloor(${blk.id});">delete</a>)
                                                            <ul>
                                                                <li><a href="editfloor.htm?action=edit&buildId=${clk.id}&custId=${customer.id}&floorId=${blk.id}">click here to edit floor ${blk.floorName}...</a></li>

                                                                <!--<li><a href="editDrawing.htm?buildingId=${clk.id}&custId=${customer.id}&floorId=${blk.id}">Add Drawing...</a> </li>-->
                                                            </ul>
                                                        </li>
                                                    </c:forEach>
                                                    <li><a href="editfloor.htm?action=new&buildId=${clk.id}&custId=${customer.id}">add a floor ...</a> </li>
                                                    <li><a href="editbuilding.htm?action=edit&buildId=${clk.id}&custId=${customer.id}">edit building ${clk.buildingName}...</a> </li>

                                                </ul>    
                                            </li>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        There are no buildings to display for ${customer.customerName}.
                                    </c:otherwise>
                                </c:choose>
                                <li><a href="editbuilding.htm?action=new&custId=${customer.id}">add building to ${customer.customerName}</a> </li>
                                <li><a href="editclient.htm?action=edit&custId=${customer.id}">edit client ${customer.customerName}...</a> </li>
                            </ul>
                        </li>

                    </c:forEach>

                </c:when>
                <c:otherwise>
                    There are no clients to display
                </c:otherwise>
            </c:choose>
            <li><a href="editclient.htm?action=new">add new client...</a> </li>
        </ul>
    </body>
</html>

