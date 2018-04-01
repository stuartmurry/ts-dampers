<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<form method="post" action="" name="form">
    <tr style="vertical-align: top">
        <td style="width:30%; text-align:center"><strong>Client Setup</strong></td>
        <td>
            <spring:hasBindErrors name="command">
                <p><font color="red">Please select a customer. </font></p>  
            </spring:hasBindErrors>
            
            <table><tr>
                    <spring:bind path="command.customer_id">
                        <select name="customer_id" >
                            <option value="0">-- Select One --</option>
                            <c:forEach items="${command.customerlist}" var="customer">
                                <option value="${customer.id}" <c:if test="${customer.id == command.customer.id}">SELECTED</c:if>>&nbsp;${fn:toUpperCase(customer.customerName)}</option>
                            </c:forEach>
                        </select>
                    </spring:bind>
            </tr></table>
            
            <p>
                <input type="submit" name="_cancel" value="Cancel" />
                <input type="submit" name="_target1" value="<- Back"  />
                <input type="submit" name="_target4" value="Next ->"  />
            </p>
        </td>
    </tr>
</form>
