<%-- 
    Document   : dampersuccess
    Created on : Oct 3, 2009, 2:56:20 PM
    Author     : smurry
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Successfully Saved Damper</title>

        <link rel="stylesheet" type="text/css" href="css/thumbnail.css">
        
        <link rel="stylesheet" type="text/css" href="css/stripes.css">
        <script type="text/javascript" src="script/stripes.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                //Even-Odd table striping 
                stripe('stripes', '#fff', '#edf3fe');
            });
        </script>
    </head>
    <body>
        <p>Successfully saved damper. <br><br></p>

        <c:choose>
            <c:when test="${not empty results}">
                <table id="stripes" style="width:100%; ">
                    <thead>
                        <tr>
                            <th style="width:10px">&nbsp;</th>
                            <th style="width:10px">&nbsp;</th>
                            <th style="border:1px solid #000000;width:150px;text-align:center">ID</th>
                            <th style="border:1px solid #000000;width:150px;text-align:center">Customer</th>
                            <th style="border:1px solid #000000;width:150px;text-align:center">Building</th>
                            <th style="border:1px solid #000000;width:75px;text-align:center">Floor</th>

                            <th style="border:1px solid #000000;width:75px;text-align:center">Date</th>
                            <th style="border:1px solid #000000;width:35px;text-align:center">Size</th>
                            <th style="border:1px solid #000000;width:50px;text-align:center">System</th>
                            <th style="border:1px solid #000000;width:20px;text-align:center">Status</th>
                            <th style="border:1px solid #000000;width:20px;text-align:center"></th>
                            <th style="border:1px solid #000000;text-align:center">Location</th>
                            <th style="border:1px solid #000000;text-align:center">Sub Location</th>
                            <th style="width:10px;text-align:center">&nbsp;</th>
                        </tr>
                    </thead>

                    <tbody id="damperTable">
                        <c:forEach items="${results}" var="damper" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td>
                                    <c:if test="${damper.occupancy =='NEW_CONSTRUCTION'}"><img src="images/under_construction.gif"></c:if>
                                    <c:if test="${role.role == 'admin' || role.role == 'employee'}">
                                        <c:if test="${damper.series > 0}" >
                                            <a href="editDamper.htm?id=${damper.id}&custId=${damper.customerId}&buildingId=${damper.buildingId}&floorId=${damper.floorId}&addInSeries=2&level=${level}&pageNum=${pageNum}" >(add)</a>
                                        </c:if>
                                    </c:if>
                                </td>
                                <td>
                                    <a href="editDamper.htm?id=${damper.id}&custId=${damper.customerId}&buildingId=${damper.buildingId}&floorId=${damper.floorId}&level=${level}&pageNum=${pageNum}">${fn:toUpperCase(damper.aliasId)} </a>
                                </td>
                                <td>${fn:toUpperCase(damper.customer)}</td>
                                <td>${fn:toUpperCase(damper.building)}</td>
                                <td>${fn:toUpperCase(damper.floor)}</td>
                                <td style="width:75px"><fmt:formatDate pattern="M/dd/yyyy" value="${damper.date}" /></td>
                                <td style="width:35px">${damper.dimensions}</td>
                                <td style="width:75px">${fn:toUpperCase(damper.system)}</td>
                                <td style="font-family: arial;font-weight: bold">
                                    <!-- We are going to give them a reason for this failure-->
                                    <c:choose>
                                        <c:when test="${damper.status eq 'PASS'}">
                                            <c:choose>
                                                <c:when test="${damper.comment == ''}" >
                                                    <span style="color:green; font-weight:bold">PASSED</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <a class="thumbnail" href="#thumb">
                                                        <img src="images/passed.gif"  border="0" />
                                                        <span><img width="200px" height="1px" src="images/yellow.gif" /><br />${fn:toUpperCase(damper.comment)}</span>
                                                    </a>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                        <c:when test="${damper.status eq 'FAILED'}">
                                            <a class="thumbnail" href="#thumb">
                                                <img src="images/fail.gif"  border="0" />
                                                <span><img width="200px" height="1px" src="images/yellow.gif" /><br />${fn:toUpperCase(damper.comment)}</span>
                                            </a>
                                        </c:when>
                                        <c:when test="${damper.status eq 'INACCESSIBLE'}">
                                            <a class="thumbnail" href="#thumb">
                                                <img src="images/inaccessible.gif"  border="0" />
                                                <span><img width="200px" height="1px" src="images/yellow.gif" /><br />${fn:toUpperCase(damper.comment)}</span>
                                            </a>
                                        </c:when>
                                        <c:when test="${damper.status eq 'FAIL:REPAIRED'}">
                                            <a class="thumbnail" href="#thumb">
                                                <img src="images/failed_repaired.gif"  border="0" />
                                                <span><img width="200px" height="1px" src="images/yellow.gif" /><br />${fn:toUpperCase(damper.comment)}</span>
                                            </a>
                                        </c:when>
                                        <c:when test="${damper.status eq 'INACCESSIBLE:REPAIRED'}">
                                            <a class="thumbnail" href="#thumb">
                                                <img src="images/inaccessible_repaired.gif"  border="0" />
                                                <span><img width="200px" height="1px" src="images/yellow.gif" /><br />${fn:toUpperCase(damper.comment)}</span>
                                            </a>
                                        </c:when>
                                        <c:when test="${damper.status eq 'FAIL:INACCESSIBLE'}">
                                            <a class="thumbnail" href="#thumb">
                                                <img src="images/fail_inaccessible.gif"  border="0" />
                                                <span><img width="200px" height="1px" src="images/yellow.gif" /><br />${fn:toUpperCase(damper.comment)}</span>
                                            </a>
                                        </c:when>
                                        <c:when test="${damper.status eq 'DECOMMISSIONED'}">
                                            <span style="color:blue; font-weight:bold"> ** DECOMMISSIONED ** </span>
                                        </c:when>
                                        <c:when test="${damper.status eq 'PENDING'}">
                                            <span style="color:purple; font-weight:bold"> PENDING </span>
                                        </c:when>
                                        <%--When a user starts a new test cycle, the status field should be null.
                                        This will force the user to fill in the damper status.
                                        Show the user "Awaiting Testing" showing the client that we still need to test the damper.--%>
                                        <c:otherwise>
                                            <span style="width:200px; color:red; font-weight:bold">&lt;Awaiting Testing&gt;</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:if test="${damper.repaired}">
                                        <a href="listRepairHistoryFromDamperList.htm?damperId=${damper.id}&custId=${damper.customerId}&buildingId=${damper.buildingId}&floorId=${damper.floorId}&level=${level}&pageNum=${pageNum}">Repair History</a>
                                    </c:if>
                                    <c:if test="${damper.picture}">
                                        <a href="editDamper.htm?id=${damper.id}&custId=${damper.customerId}&buildingId=${damper.buildingId}&floorId=${damper.floorId}&level=${level}&pageNum=${pageNum}">
                                            <img alt="Picture Available"  src="images/camera.png"  border="0" />
                                        </a>
                                    </c:if>
                                </td>
                                <td>${fn:toUpperCase(damper.location)}</td>
                                <td>${fn:toUpperCase(damper.sublocation)}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <b><p style="text-align:center">No records found.</p></b>
            </c:otherwise>
        </c:choose>

        <c:if test="${role.role == 'admin' || role.role == 'employee'}">
            <div style="width:100%; text-align:left">
                <c:if test="${custId != null}">
                    <c:if test="${buildingId != null}">
                        <c:if test="${floorId != null}">
                            <input type="button" name="single" onclick="parent.location = 'selectDamperType.htm?custId=${custId}&buildingId=${buildingId}&floorId=${floorId}&singleUnit=1'" value="ADD SINGLE UNIT DAMPER" />
                            <input type="button" name="multi"  onclick="parent.location = 'selectDamperType.htm?custId=${custId}&buildingId=${buildingId}&floorId=${floorId}&singleUnit=2'" value="ADD MULTI UNIT DAMPER" />
                            <input type="button" name="single" onclick="parent.location = 'listDampers.htm?custId=${custId}&buildingId=${buildingId}&floorId=${floorId}&level=3'" value="RETURN HOME" />
                        </c:if>
                    </c:if>
                </c:if>
            </div>
            <br>
        </c:if>

    </body>
</html>
