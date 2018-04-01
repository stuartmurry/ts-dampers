<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<decorator:usePage id="thePage" />



<% String selection = thePage.getProperty("meta.selection");%>

<% if (selection != null && "login".equals(selection)) {%>
<%

}
else
{

%>
<a href='index.htm'>Home</a>

<sec:authorize ifAllGranted="ROLE_USER">
    <a href='reportIndex.htm'>Report Wizard</a>
</sec:authorize>

<sec:authorize ifAllGranted="ROLE_ADMIN">
    <a href='user.htm'>User Setup</a>
    <a href='client.htm'>Client Setup</a>
    <a href='searchProject.htm'>Project Setup</a>
    <a href='useLog.htm?action=list'>Session Monitor</a>
</sec:authorize>

<sec:authorize ifAllGranted="ROLE_TELLER">
    <a href='testDamper.htm'>Damper Test Wizard</a>
    <a href='client.htm'>Client Setup</a>
    <a href='searchProject.htm'>Project Setup</a>
    <a href='useLog.htm?action=list'>Session Monitor</a>
</sec:authorize>
<%
}

%>









