<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<decorator:usePage id="thePage" />
<% String loginMetaData = thePage.getProperty("meta.login");%>
<% String damperMetaData = thePage.getProperty("meta.damper");%>
<% String searchResultsMetaData = thePage.getProperty("meta.searchResults");%>
<% String testDamperOkMetaData = thePage.getProperty("meta.testDamperOk");%>
<% String errorsExpiresMetaData = thePage.getProperty("meta.errors");%>

<c:set var="loginMetaData" scope="page"><%=loginMetaData%></c:set>
<c:set var="damperMetaData" scope="page"><%=damperMetaData%></c:set>
<c:set var="searchResultsMetaData" scope="page"><%=searchResultsMetaData%></c:set>
<c:set var="testDamperOkMetaData" scope="page"><%=testDamperOkMetaData%></c:set>
<c:set var="errorsExpiresMetaData" scope="page"><%=errorsExpiresMetaData%></c:set>


    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
    <html xmlns="http://www.w3.org/1999/xhtml" lang="en">
        <head>

            <title>Thermal Strategies - <decorator:title default="Welcome!" /></title>
        <!--        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
                <script src="http://code.jquery.com/jquery-migrate-1.1.0.js"></script>-->

        <link href="//netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="//cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.7/css/bootstrap-dialog.min.css" rel="stylesheet"/>

        <script src="//code.jquery.com/jquery-2.2.4.min.js"></script>
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>


        <link rel="stylesheet" type="text/css" href="css/body.css"/>
        <link rel="stylesheet" type="text/css" href="css/logo.css" />
        <decorator:head />

    </head>
    <body>

        <%@include file="banner.jsp" %>

        <!-- Damper Menu -->
        <%@include file="dampermenu.jsp" %> 

        <%@include file="addnewdamper.jsp" %>


        <!-- Wrap html with page search results if search meta data is on-->
        <%@include file="searchpages.jsp" %>
        <decorator:body />
        <%@include file="searchpages.jsp" %>

        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <hr style="width:100%; color:ffa700" />

    </body>
</html>



