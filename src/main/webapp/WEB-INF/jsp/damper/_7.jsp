<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<html>
    <head>
        <title>- Damper Test Wizard</title>
        <script language="JavaScript" type="text/javascript" src="richtext_compressed.js"></script>
        <script type="text/javascript">
            focusElementId='storedComments';
        </script>
        <script type="text/javascript">
    
            function addComment()
            {

                var formElements = document.forms['myforms'].elements;
                var selIndex = formElements['comment'].selectedIndex;
                var currently = formElements['addComments'].value;
                var selText = formElements['comment'][selIndex].text;
                formElements['addComments'].value += selText;
            }
    
            function addComments()
            {

                var formElements = document.forms['form'].elements;
                var storedComments = formElements['storedComments'];
                var comments = formElements['Comments'];
       
                var i;
                var count=0;
                for (i=0; i<storedComments.options.length; i++) {
                    if (storedComments.options[i].selected) {
                        var valuable = storedComments.options[i].value;
                        var textable = storedComments.options[i].text;
                        comments.value = comments.value + textable + '\n';
                    }
                }
            }
    
            function addToDatabase()
            {
                var formElements = document.forms['form'].elements;
                formElements['actions'].value='_add';
                document.forms['form'].submit();
            }
    
            function removeFromDatabase()
            {
                var formElements = document.forms['form'].elements;
                var selIndex = formElements['comment'].selectedIndex;
                var selValue = formElements['comment'][selIndex].value;
                formElements['params'].value = selValue;
                formElements['actions'].value='_remove';
        
                document.forms['form'].submit();
            }
    
        </script>
    </head>

    <form method="post" action="" name="form">
        <!-- javascript will add update, delete, etc to the value of this -->
        <input id="actions" name="action" type="hidden" value="refresh" >
        <input id="params" name="param" type="hidden" >
        <input type="hidden" name="_target7">

        <table>
            <tr><td>
                    <spring:hasBindErrors name="command">
                        <span style="color:red">
                            <p>There were ${errors.errorCount} error(s) in total:</p>
                            <ul>
                                <c:forEach var="errMsgObj" items="${errors.allErrors}">
                                    <li>
                                        <spring:message code="${errMsgObj.code}" text="${errMsgObj.defaultMessage}"/>
                                    </li>
                                </c:forEach>
                            </ul>
                        </span>
                    </spring:hasBindErrors>
                </td></tr>
            <tr>
                <td style="border-width:1px;border-style:solid;border-color:black;text-align:center"><c:out value="${fn:toUpperCase(command.dampertest.aliasId)}" /></td>
            </tr>
            <tr><td></td><td></td></tr>
            <tr>

                <td style="width:400px;">
                    <div style="overflow-y:scroll; width:400px; height:100px">

                        <select id="storedComments" size="10" style="width:390px" name="comment"  multiple="true">
                            <c:forEach items="${command.dampercommentlist}" var="dampercomment">
                                <option value="${dampercomment.id}">&nbsp;${fn:toUpperCase(dampercomment.comment)}</option>
                            </c:forEach>
                        </select>
                    </div>
                </td>
                <td><a href="#"><img border="0" src="images/add.gif" onclick="javascript:addComments()"></a></td>
                <td><a href="#"><img border="0" src="images/add_database.gif" onclick="javascript:addToDatabase()"></a></td>
                <td><a href="#"><img border="0" src="images/remove_database.gif" onclick="javascript:removeFromDatabase()"></a></td>

            <tr>
                <spring:bind path="command.comments">
                    <td>
                        <c:choose>
                            <c:when test="${status.error}">
                                <span style="color:red">Comments</span>
                            </c:when>
                            <c:otherwise>
                                <span>Comments</span>
                            </c:otherwise>
                        </c:choose>
                    </td></tr>
                <tr>

                    <td><textarea style="width:400px" id="Comments" cols="10" rows="10" name="comments"><c:out value="${command.comments}" /></textarea></td>
                </spring:bind>
            </tr><td>&nbsp;</td><td>&nbsp;</td>

            </tr>

            <tr><td>
                    <input type="submit" value="Cancel" name="_cancel" />
                    <input type="button" value="<- Back" onclick="javascript:backAPage('_target7','_target6')" />
                    <input type="submit" value="Next ->" onclick="javascript:tarjet('_target7', '_target8')" />
                </td></tr>
        </table>
    </form>
</body>
</html>

