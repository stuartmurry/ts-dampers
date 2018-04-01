<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>


<%

    Object msg  = request.getAttribute("client.delete.msg");
    if (msg !=null)
    {
        %><%= msg %> <%
    }

%>



