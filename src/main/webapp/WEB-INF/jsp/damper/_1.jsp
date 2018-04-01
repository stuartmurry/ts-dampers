<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<html>
    <head>
        <title>- Damper Test Wizard</title>
    </head>

    <body>

        <form method="post" action="dampertest" name="form" >
            <input type="hidden" name="target" >
            <input id="customerIdz" type="hidden" name="customer_id" >

            <tr style="vertical-align: top">
                <td>
                    <spring:hasBindErrors name="command">
                        <p><font color="red">Please select a project. </font></p>
                    </spring:hasBindErrors>

                    <table>
                        <tr><td>ID</td><td>Customer Name</td><td>Customer</td></tr>
                        <c:forEach items="${command.customerlist}" var="customer">
                            <tr>
                                <td><a href="client.htm?id=${customer.id}">${customer.id}</a></td>
                                <td><a href="#" onclick="javascript:setIdAndSubmit(${customer.id})">${customer.customerName}</a></td>
                                <td>${customer.customerName}</td>
                            </tr>
                        </c:forEach>
                    </table>

                    <input type="submit" name="_cancel" value="Cancel" />
                    <input type="submit" name="_target0" value="<- Back"  />
                    <input type="submit" id="nexts" name="_target3" value="Next ->"  />
                </td>
            </tr>

        </form>
    </body>
</html>