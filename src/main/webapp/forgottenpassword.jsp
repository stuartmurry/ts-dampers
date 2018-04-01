<%-- 
    Document   : forgottenpassword
    Created on : Feb 17, 2010, 2:39:34 PM
    Author     : Stuart
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Forgotten Password</title>
        <meta name="login" content="login">
    </head>
    <body>
        <h1>Forgotten Password</h1>
        <p>To obtain your password please type in your email address.  You password will be sent to your email address.</p>
        <p>
        <form action="ForgottenPassword" method="post">
            Email: <input type="text" name="email" />
            <input type="submit" value="Submit">
        </form>

        </p>
    </body>
</html>
