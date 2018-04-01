<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--<c:if test="${damperMetaData == 'menu'}" >
    <div id="myslidemenu" class="jqueryslidemenu">
        <ul class="navigation-1">
            <c:choose>
                <c:when test="${fn:length(customerBuildingFloorBeanList) == 1}">
                    <c:forEach items="${customerBuildingFloorBeanList}" var="cust">
                        <c:forEach items="${cust.buildingList}" var="building">
                            <li><a href="listDampers.htm?custId=${cust.id}&buildingId=${building.id}&level=2" >${building.buildingName}</a>
                                <ul>
                                    <c:forEach items="${building.buildingFloorList}" var="floor">
                                        <li><a href="listDampers.htm?custId=${cust.id}&buildingId=${building.id}&floorId=${floor.id}&level=3" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${floor.floorName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
                                    </c:forEach>
                                </ul>
                            </li>
                        </c:forEach>
                    </c:forEach>

                </c:when>
                <c:otherwise>
                    <c:forEach items="${customerBuildingFloorBeanList}" var="cust">
                        <li><a href="listDampers.htm?custId=${cust.id}&level=1" >${cust.customerName}</a>
                            <ul>
                                <c:forEach items="${cust.buildingList}" var="building">
                                    <li><a href="listDampers.htm?custId=${cust.id}&buildingId=${building.id}&level=2" >${building.buildingName}</a>
                                        <ul>
                                            <c:forEach items="${building.buildingFloorList}" var="floor">
                                                <li><a href="listDampers.htm?custId=${cust.id}&buildingId=${building.id}&floorId=${floor.id}&level=3" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${floor.floorName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
                                            </c:forEach>
                                        </ul>
                                    </li>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:forEach>
                </c:otherwise>
            </c:choose>


            <li><a href="listDampers.htm">SHOW ALL</a></li>
        </ul>
    </div>
    <br><br><br>

</c:if>--%>