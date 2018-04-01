<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<form action="editbuilding.htm" method="post">
    <c:choose>
        <c:when test="${fn:length(buildinglist) != 0}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Name</th>
                    </tr>
                </thead>
                
                <tbody>
                    <c:forEach items="${buildinglist}" var="building" varStatus="status">
                        <tr>
                            <td>${status.count}.</td>
                            <td>${building.buildingName}</td>
                            <td><a href="editbuilding.htm?action=edit&id=<c:out value="${building.id}" />">Edit</a></td>
                        </tr>
                    </tbody>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            There are no buildings to display for ${customer.customerName}.  <a href="editbuilding.htm">Click here to create a new Building</a>
        </c:otherwise> 
    </c:choose>
    
    <br>
    
    <input type="submit" value="Cancel" name="cancel" />
    <input type="submit" value="Create Building" name="new" />
</form>

<br><br>

<h4>Client</h4>

<ul id="treemenu" class="treeview">
    <li>add new client...</li>
    <c:choose>
        <c:when test="${fn:length(clientlist) != 0}"> 
            <c:forEach items="${clientlist}" var="customer" varStatus="status">
                <li>${customer.customerName}
                    <ul>
                        <li>add new building</li>
                        <c:choose>
                            <c:when test="${fn:length(customer.customerbuildingLks) != 0}"> 
                                
                                    <c:forEach items="${customer.customerbuildingLk}" var="lk" varStatus="status">
                                        <li>${lk.building.buildingName}
                                            <ul>
                                                <li>add new floors</li>
                                            </ul>    
                                        </li>
                                    </c:forEach>
                                    
                            </c:when>
                            <c:otherwise>
                                There are no buildings to display for ${customer.customerName}.  <a href="editbuilding.htm">Click here to create a new Building</a>
                            </c:otherwise> 
                        </c:choose>
                    </ul>
                </li>
            </c:forEach>
            
        </c:when>
        <c:otherwise>
            There are no clients to display for ${customer.customerName}.  <a href="editbuilding.htm">Click here to create a new Building</a>
        </c:otherwise>
    </c:choose>
</ul>

<a href="javascript:ddtreemenu.flatten('treemenu2', 'expand')">Expand All</a> | <a href="javascript:ddtreemenu.flatten('treemenu2', 'contact')">Contact All</a>

<!--
<ul id="treemenu2" class="treeview">

    <li>Sloan Kettering
        <ul>
            <li>add new building...</li>
            <li>Folder 1.1
                <ul rel="open">
                    <li>Sub Item 1.1.1</li>
                    <li>Sub Item 1.1.2</li>
                    <li>Folder 1.1.1
                        <ul>
                            <li>Sub item 1.1.1.1</li>
                            <li>Sub item 1.1.1.2</li>
                            <li>Sub item 1.1.1.3</li>
                            <li>Sub item 1.1.1.4</li>
                        </ul>
                    </li>
                </ul>
            </li>
        </ul>
    </li>
    
    <li>Item 2</li>
</ul>
-->
<script type="text/javascript">

    //ddtreemenu.createTree(treeid, enablepersist, opt_persist_in_days (default is 1))

    //ddtreemenu.createTree("treemenu1", true)
    //ddtreemenu.createTree("treemenu2", false)
    ddtreemenu.createTree("treemenu", false); 
     
</script>

