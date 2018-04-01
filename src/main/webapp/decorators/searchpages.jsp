<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${searchResultsMetaData == 'on'}">
    <div>
        
        <table style="width:100%">
            <tr><td>
                    <span style="font-size:8pt">${customer.customerName} showing page <strong>${page.page + 1}</strong> out of <strong>${page.pageCount}</strong> (<strong>${page.fromRecords} - ${page.toRecords} </strong> out of <strong>${page.totalRecords} Dampers</strong>)</span>
                    <a href="dampertest_report.xls?custId=${custId}&buildingId=${buildingId}&floorId=${floorId}&level=${level}&status=${status}"><img border="0" alt="Export to Excel" src="images/excel-logo25.jpg"></a>
                </td></tr>
            <tr><td>
                    <c:if test="${page.pageCount > 1}" >
                        <c:forEach var="i" begin="1" end="${page.pageCount}">
                            <a href="listDampers.htm?custId=${custId}&pageNum=${i-1}&buildingId=${buildingId}&floorId=${floorId}&year=${year}&status=${status}">&nbsp;${i}&nbsp;</a>
                        </c:forEach>
                    </c:if>
                </td></tr></table>
    </div>
</c:if>

