<%@ include file="/WEB-INF/jsp/taglibs.jsp" %> 

<div class="jumbotron text-center">
    <h1>Winthrop</h1>
    <p>Damper Report</p>
</div>
<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h3>Main Campus</h3>
            <p>
                <img style="width:90%"  align="top" alt="${mainCampusTotal.customerName}" src="passFailPieChart.htm?pass=${mainCampusTotal.pass}&fail=${mainCampusTotal.fail}&inaccessible=${mainCampusTotal.inaccessible}&customer=${mainCampusTotal.customerName}" />
            </p>

            <div class="row">
                <div class="col-sm-8">
                    Pass 
                </div>
                <div class="col-sm-4">
                    ${mainCampusTotal.pass}
                </div>
            </div>
            <div class="row">
                <div class="col-sm-8">
                    Fail 
                </div>
                <div class="col-sm-4">
                    ${mainCampusTotal.fail}
                </div>
            </div>
            <div class="row">
                <div class="col-sm-8">
                    Inaccessible 
                </div>
                <div class="col-sm-4">
                    ${mainCampusTotal.inaccessible}
                </div>
            </div>
            <div class="row">
                <div class="col-sm-8">
                    Pending 
                </div>
                <div class="col-sm-4">
                    ${mainCampusTotal.pending}
                </div>
            </div>

            <c:forEach items="${winthropMainCampus}" var="mc" varStatus="status">
                <h4>${fn:toUpperCase(mc.buildingName)}</h4>
                <div class="list-group">
                    <a href="listDampers.htm?custId=${mc.custId}&buildingId=${mc.buildingId}&status=1" class="list-group-item">Pass <span class="badge">${mc.pass}</span></a>
                    <a href="listDampers.htm?custId=${mc.custId}&buildingId=${mc.buildingId}&status=2" class="list-group-item">Fail <span class="badge">${mc.fail}</span></a>
                    <a href="listDampers.htm?custId=${mc.custId}&buildingId=${mc.buildingId}&status=3" class="list-group-item">Inaccessible <span class="badge">${mc.inaccessible}</span></a>
                    <a href="listDampers.htm?custId=${mc.custId}&buildingId=${mc.buildingId}&status=7" class="list-group-item">Pending <span class="badge">${mc.pending}</span></a>
                </div>
            </c:forEach>
        </div>
        <div class="col-sm-4">
            <h3>Offsites</h3>
            <p>
                <img style="width:90%"  align="top" alt="${offSitesTotal.customerName}" src="passFailPieChart.htm?pass=${offSitesTotal.pass}&fail=${offSitesTotal.fail}&inaccessible=${offSitesTotal.inaccessible}&customer=${offSitesTotal.customerName}" />
            </p>

            <div class="row">
                <div class="col-sm-8">
                    Pass 
                </div>
                <div class="col-sm-4">
                    ${offSitesTotal.pass}
                </div>
            </div>
            <div class="row">
                <div class="col-sm-8">
                    Fail 
                </div>
                <div class="col-sm-4">
                    ${offSitesTotal.fail}
                </div>
            </div>
            <div class="row">
                <div class="col-sm-8">
                    Inaccessible 
                </div>
                <div class="col-sm-4">
                    ${offSitesTotal.inaccessible}
                </div>
            </div>
            <div class="row">
                <div class="col-sm-8">
                    Pending 
                </div>
                <div class="col-sm-4">
                    ${offSitesTotal.pending}
                </div>
            </div>

            <c:forEach items="${winthropOffsites}" var="mc" varStatus="status">
                <h4>${fn:toUpperCase(mc.buildingName)}</h4>
                <div class="list-group">
                    <a href="listDampers.htm?custId=${mc.custId}&buildingId=${mc.buildingId}&status=1" class="list-group-item">Pass <span class="badge">${mc.pass}</span></a>
                    <a href="listDampers.htm?custId=${mc.custId}&buildingId=${mc.buildingId}&status=2" class="list-group-item">Fail <span class="badge">${mc.fail}</span></a>
                    <a href="listDampers.htm?custId=${mc.custId}&buildingId=${mc.buildingId}&status=3" class="list-group-item">Inaccessible <span class="badge">${mc.inaccessible}</span></a>
                    <a href="listDampers.htm?custId=${mc.custId}&buildingId=${mc.buildingId}&status=7" class="list-group-item">Pending <span class="badge">${mc.pending}</span></a>
                </div>
            </c:forEach>

        </div>
        <div class="col-sm-4">
            <h3>Research and Academic Center</h3>
            <p>
                <img style="width:90%"  align="top" alt="${researchTotal.customerName}" src="passFailPieChart.htm?pass=${researchTotal.pass}&fail=${researchTotal.fail}&inaccessible=${researchTotal.inaccessible}&customer=${researchTotal.customerName}" />
            </p>

            <div class="row">
                <div class="col-sm-8">
                    Pass 
                </div>
                <div class="col-sm-4">
                    ${researchTotal.pass}
                </div>
            </div>
            <div class="row">
                <div class="col-sm-8">
                    Fail 
                </div>
                <div class="col-sm-4">
                    ${researchTotal.fail}
                </div>
            </div>
            <div class="row">
                <div class="col-sm-8">
                    Inaccessible 
                </div>
                <div class="col-sm-4">
                    ${researchTotal.inaccessible}
                </div>
            </div>
            <div class="row">
                <div class="col-sm-8">
                    Pending 
                </div>
                <div class="col-sm-4">
                    ${researchTotal.pending}
                </div>
            </div>
    
            <c:forEach items="${winthropResearch}" var="mc" varStatus="status">
                <h4>${fn:toUpperCase(mc.buildingName)}</h4>
                <div class="list-group">
                    <a href="listDampers.htm?custId=${mc.custId}&buildingId=${mc.buildingId}&status=1" class="list-group-item">Pass <span class="badge">${mc.pass}</span></a>
                    <a href="listDampers.htm?custId=${mc.custId}&buildingId=${mc.buildingId}&status=2" class="list-group-item">Fail <span class="badge">${mc.fail}</span></a>
                    <a href="listDampers.htm?custId=${mc.custId}&buildingId=${mc.buildingId}&status=3" class="list-group-item">Inaccessible <span class="badge">${mc.inaccessible}</span></a>
                    <a href="listDampers.htm?custId=${mc.custId}&buildingId=${mc.buildingId}&status=7" class="list-group-item">Pending <span class="badge">${mc.pending}</span></a>
                </div>
            </c:forEach>
        </div>
    </div>
</div>


