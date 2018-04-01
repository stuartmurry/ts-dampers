<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<c:if test="${loginMetaData != 'login'}" >
    <table style="width:100%">
        <tr>
            <td style="width:242px"><a href="index.htm" name="top"><img border="0" src="images/thermal_strategies.png" style="width:242px;"></a></td>
                    <%--                    <td class="MainLogo"></td>--%>
            <td style="width:52px"><img src="images/a_thermal_bluep_1_gradient.jpg" style="height:75px"></td>
            <td style="width:104px"><img src="images/a_thermal_blue_5.jpg" style="height:75px"></td>
            <td style="text-align:right; vertical-align:bottom; font-size:8pt">
                <table style="width:100%">
                    <tr><td style="font-size:10pt; font-weight:bold">
                            You are currently logged in. <a href="j_spring_security_logout" ><span style="color:green">LOG OUT</span></a> <%--:: DWR Test Utility: <a href="dwr/index.html">Click here</a>--%>
                        </td></tr>
                    <tr><td>
                            <form action="search.htm" METHOD="post">
                                Fuzzy Search(BETA)<input type="text" name="query" value="${query}">
                                <input type="submit" value="Search">
                            </form>
                        </td></tr>
                    <tr>
                        <td align="right"><!-- Old Menu --></td>
                    </tr>
                </table>

            </td>
        </tr>
        <%--implement later--%>

    </table>

    <script type="text/javascript" src="ddtabmenufiles/ddtabmenu.js">

        /***********************************************
         * DD Tab Menu script- © Dynamic Drive DHTML code library (www.dynamicdrive.com)
         * This notice MUST stay intact for legal use
         * Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
         ***********************************************/

    </script>

    <link rel="stylesheet" type="text/css" href="ddtabmenufiles/ddcolortabs.css" />
    <script type="text/javascript">
        //SYNTAX: ddtabmenu.definemenu("tab_menu_id", integer OR "auto")
        ddtabmenu.definemenu("ddtabs1", 0) //initialize Tab Menu #1 with 1st tab selected
        ddtabmenu.definemenu("ddtabs2", 1) //initialize Tab Menu #2 with 2nd tab selected
        ddtabmenu.definemenu("ddtabs3", 1) //initialize Tab Menu #3 with 2nd tab selected
        ddtabmenu.definemenu("ddtabs4", 2) //initialize Tab Menu #4 with 3rd tab selected
        ddtabmenu.definemenu("ddtabs5", -1) //initialize Tab Menu #5 with NO tabs selected (-1)

    </script>
    <div id="ddtabs4" class="ddcolortabs">
        <ul>
            <li><a href="index.htm" rel="ct2"><span>HOME</span></a></li>
            <li><a href="damper.htm" rel="ct2"><span>DAMPERS</span></a></li>
            <c:if test="${role.role != 'employee'}">
                <li><a href="repair.htm" rel="ct3"><span>REPAIRS</span></a></li>
                <li><a href="passFail.htm" rel="ct4"><span>STATISTICS</span></a></li>
                <li><a href="nextTestDate.htm" rel="ct1"><span>TEST SCHEDULE</span></a></li>
                <li>
                    <a href="reports.htm" rel="ct1"><span>REPORTS</span></a>
                </li>
            </c:if>
            <c:if test="${role.role == 'admin'}">
                <li><a href="listuser.htm" rel="ct4"><span>USERS</span></a></li>
                <li><a href="client.htm" rel="ct4"><span>CLIENTS</span></a></li>
                <li><a href="utilities.htm" rel="ct4"><span>UTILITIES</span></a></li>
            </c:if>
        </ul>
    </div>
    <div class="ddcolortabsline">&nbsp;</div>

    <br><br>
</c:if>
