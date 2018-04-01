<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Damper Database</title>
        <!-- Show client menu -->
        <meta name="damper" content="menu">

    </head>
    <body>

        <script type="text/javascript">
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
        <h1>Welcome to the Damper Database</h1>

        <table>
            <tr><td valign="top" width="300px">
                    <div>
                        <table>
                        <c:forEach items="${damperDBViewList}" var="cust">
                            <tr><td>${cust.customerName}</td><td>${cust.siteName}</td></tr>
                        </c:forEach>
                        </table>
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

