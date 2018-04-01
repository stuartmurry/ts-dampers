<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<script type="text/javascript">

</script>
<form method="post" action="" name="form">
    <input type="hidden" name="target" value="">
    <tr style="vertical-align: top">
        <td style="width:30%; text-align:center"><strong>Client Setup</strong></td>
        <td>
            <div>
                <table>
                    <tr><td>Client</td><td><input type="text" name="customerName" value="${command.customer.customerName}" /></td></tr>
                </table>
                <input type="submit" name="_target4" value="Next" />
            </div>
        </td>
    </tr>
</form>
