<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test="${testDamperOkMetaData == 'true'}" >
    <c:if test="${role.role == 'admin' || role.role == 'employee'}">
        <div style="width:100%; text-align:center" align="center">
            <c:if test="${role != 'customer'}">
                <c:if test="${custId != null}">
                    <c:if test="${buildingId != null}">
                        <c:if test="${floorId != null}">
                            <input type="button" name="single" onclick="parent.location='selectDamperType.htm?custId=${custId}&buildingId=${buildingId}&floorId=${floorId}&singleUnit=1'" value="ADD SINGLE UNIT DAMPER" />
                            <input type="button" name="multi"  onclick="parent.location='selectDamperType.htm?custId=${custId}&buildingId=${buildingId}&floorId=${floorId}&singleUnit=2'" value="ADD MULTI UNIT DAMPER" />
                        </c:if>
                    </c:if>
                </c:if>
            </c:if>
        </div>
        <br>
    </c:if>
</c:if>

