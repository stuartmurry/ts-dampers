<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
    <head>
        <title>Login Page</title>
        <!--<link href="style/main.css" rel="stylesheet" type="text/css" />-->
        <meta name="login" content="login">
    </head>

    <style>
        A, BODY, FORM, H1, H2, H3, LI, P, PRE, SPAN, UL {
            margin: 0px;
            padding: 0px;
        }
        BODY {
            background-color: #eeeeee;
            font-family: Arial, Helvetica, sans-serif;
            text-align: center;
        }

        INPUT, SELECT {
            font-size: 12px;
        }

        PRE {
            font-size: 12px;
            margin-top: 5px;
        }

        #body {
            background: white;
            border-left: 2px solid #aaaaaa;
            border-right: 2px solid #aaaaaa;
            width: 100%;
            margin: 0 auto;
            padding: 10px;
        }

        #head {
            background: url(../images/thermal_strategies.png) no-repeat left top;
            text-align: left;
            height: 25px; padding-left:205px; padding-right:0; padding-top:20px; padding-bottom:0
        }
        #head H1 {
            color: #444444;
            font-size: 22px;
        }
        #head H2 {
            color: #888888;
            font-size: 18px;
            margin-left: 20px;
        }

        #menu {
            background-color: #666666;
            font-size: 12px;
        }
        #menu A {
            color: white;
            font-weight: bold;
            text-decoration: none;
            padding: 0 10px 0 10px;
        }
        #menu A:hover {
            background-color: white;
            color: #666666;
        }

        #content {
            min-height: 250px;
            text-align: left;
        }
        #content .ajax {
            color: #dd2222;
            font-size: 22px;
            font-weight: bold;
        }

        #foot {
            color: #aaaaaa;
            font-size: 10px;
        }

        .section {
            border-bottom: 1px solid #dddddd;
            color: #444444;
            font-size: 14px;
            margin-top: 10px;
            padding-bottom: 10px;
        }
        .section A {
            color: #664444;
        }
        .section A:hover {
            color: #aa2222;
            text-decoration: none;
        }
        .section CODE {
            color: black;
            background-color: #f2ebdc;
            font-size: 12px;
        }
        .section H1 {
            font-size: 20px;
            margin: 0 0 0 20px;
        }
        .section H2 {
            font-size: 16px;
            margin-top: 10px;
        }
        .section LI {
            margin: 0 0 0 20px;
        }
        .section PRE {
            color: black;
            background-color: #f2ebdc;
            font-size: 12px;
            margin: 5px 0 10px 0;
            border-left: 1px solid #aaa499;
            margin-left: 10px;
            padding-left: 10px;
            width: 500px;
        }

        .section2 {
            border-left: 4px solid #dddddd;
            text-align: left;
            margin: 20px 0 0 20px;
            padding-left: 5px;
        }

        .damperColA td {
            width:20px
        }
    </style>

    <body onload='document.f.j_username.focus();' style="background-color:white">
        <form name='f' action='j_spring_security_check' method='POST'>
            <table width="100%"  border=0 cellpadding=0 cellspacing=0>

                <tr><td align="center" width="100%" valign="middle">
                        <img style="width:200px;" src="images/thermal_strategies.png" alt="Thermal Logo" />
                        <table>
                            <%
                                String login_error = request.getParameter("login_error");
                                try {
                                    int err = Integer.parseInt(login_error);
                                    if (err > 0) {
                                        String loginOut = "<tr><td></td><td><font color=\"RED\">LOGIN ERROR.  PLEASE TRY AGAIN.</FONT></td></tr>";
                            %>
                            <%= loginOut%>
                            <%
                                    }
                                } catch (Throwable t) {
                                }
                            %>
                            <tr><td>User:</td><td><input type='text' name='j_username' ></td></tr>
                            <tr><td>Password:</td><td><input type='password' name='j_password'/></td></tr>
                            <tr><td><input type='checkbox' name='_spring_security_remember_me'/></td><td>Remember me on this computer.</td></tr>
                            <tr><td></td><td colspan='2'><input value="Login" type="submit"/><input name="reset" type="reset"/></td></tr>
                            <tr><td></td><td><a href="forgottenpassword.jsp">forgotten password?</a></td></tr>
                        </table>
                    </td>
                    <td style="width: 1px">
                        <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
                        <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

                    <td></tr>
            </table>
        </form>
    </body>
</html>
