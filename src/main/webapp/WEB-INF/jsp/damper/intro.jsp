<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<html>
    <head>
        <title>- Damper Test Wizard - Intro</title>
        <script type="text/javascript">
            focusElementId='nexts';
        </script>
    </head>

    <body>

        <form method="post" action="damperTestWizard.htm" name="damperTest">
            <input type="hidden" name="target" value="">
            <table>
                <tr>
                    <td style="width:10%; text-align:center"></td>

                    <td style="text-align:center">

                        <strong>DAMPER TEST WIZARD</strong><br><br>

                        Please fill out the appropriate information.&nbsp;
                        When you are finished, please select continue.  When&nbsp;
                        You are finished entering in the damper test infomation,&nbsp;
                        please click finish...

                        <p>
                            <input id="nexts" type="submit" name="_target1" value="NEXT ->" >
                        </p>

                    </td>
                    <td style="width:10%"></td>
                </tr>
            </table>
        </form>
    </body>
</html>

