<%-- 
    Document   : utilities
    Created on : Dec 25, 2009, 9:14:20 AM
    Author     : Stuart
--%>
<%@include file="taglibs.jsp" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Admin Utilities</title>
        <link rel="stylesheet" type="text/css" href="css/simpletree.css">
        <script type="text/javascript" src="script/simpletreemenu.js">

            /***********************************************
             * Simple Tree Menu- © Dynamic Drive DHTML code library (www.dynamicdrive.com)
             * This notice MUST stay intact for legal use
             * Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
             ***********************************************/

        </script>

        <script type="text/javascript" src="script/stripes.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                //Even-Odd table striping 
                stripe('stripes', '#fff', '#edf3fe');
            });
            
            function areYouSureDamper(buildingId)
            {
                var answer = confirm('Are you sure you want to create a new test cycle?\nThis will archive all comments, dates, and status\' \n which you will not be able to undo.  Still proceed? ');
                if (answer)
                {
                    window.location = "newTestCycle.htm?buildingId=" + buildingId;
                }

            }
        </script>
    </head>
    <body>
        

        <h1>Utilities</h1>
        <form:form>
            <table id="stripes">
                <tr><td valign="top" style="width:400px"><p><u>Recalculate Next Test Dates</u></p>
                        Recalculates the next test date based on occupancy.
                        If the user chooses "Business Occupancy" then the next
                        test date will be 4 years + the date tested.  If the user
                        chooses "Health Care" then the next test date will be 6 years
                        + the date tested. If the user chooses "New Construction" then
                        the next test date will be 1 year + date tested. (Recalculates entire database so be sure to use extreme
                        caution before pressing this button!)
                    </td><td valign="top"><br><input type="submit" name="recalculateNextTestDate" value="Submit"></td></tr>
                <tr><td valign="top" style="width:400px"><p><u>Start New Test Cycle</u></p>
                        Please choose a building you wish to start a new test.  All status and comments will
                        be converted to the comments history. <br>
                        <div>
                            <ul style="color:blue" id="treemenu" class="treeview">
                                <c:forEach items="${customerBuildingFloorBeanList}" var="cust">
                                    <li><b style="color: blue">${cust.customerName}</b>
                                        <ul>
                                            <c:forEach items="${cust.buildingList}" var="building">
                                                <li style="color:blue">${building.buildingName}
                                                    <c:choose>
                                                        <c:when test="${building.test}">
                                                            <span style="color:red">(currently under test)</span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <a href="#" onclick="javascript:areYouSureDamper(${building.id})" >(start new test)</a>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>

                    </td><td valign="top"></td></tr>
            </table>

        </form:form>

        <script type="text/javascript">

            //ddtreemenu.createTree(treeid, enablepersist, opt_persist_in_days (default is 1))

            //ddtreemenu.createTree("treemenu1", true)
            //ddtreemenu.createTree("treemenu2", false)
            ddtreemenu.createTree("treemenu", false);

        </script>


    </body>
</html>
