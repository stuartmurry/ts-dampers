<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<%@page import="com.thermalstrategies.eportal.security.EPortalSecurityContext" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Damper Test Form</title>
        <!-- Show client menu -->
        <meta name="damper" content="menu">
        <meta name="errors" content="exipres">

        <script type="text/javascript" src="script/calendarDateInput.js">

            /***********************************************
             * Jason's Date Input Calendar- By Jason Moon http://calendar.moonscript.com/dateinput.cfm
             * Script featured on and available at http://www.dynamicdrive.com
             * Keep this notice intact for use.
             ***********************************************/

        </script>
        <script type="text/javascript">
            $(document).ready(function () {

            });
        </script>

        <style>
            .allcaps {
                text-transform: uppercase;
            }
            div input, textarea, select {
                background: none repeat scroll 0 0 white;
                border: 1px solid #18658c;
                color: #4d4141;
            }
            .main-left{
                float:left;
                width:600px;
            }
            .left {
                float:left;
                width:100px;
            }
            .outter {
                padding:5px;
            }
            .input {
                width:325px;
            }

            .location{
                height:100px;
            }
            .okbutton{
                padding-top:20px;
            }
            .button{
                width:100px;
                height:25px;
                border: 1px solid #18658c !important;
                color: #4d4141;
                background-color: #F8F8F8 !important;
                
            }
        </style>
    </head>
    <body>
        <form><fieldset><legend>${command.aliasId} (ARCHIVED)</legend>

                <div class="main-left">
                    <div class="outter">
                        <div class="left">Date Tested</div><div class="allcaps">${command.datetested}</div>
                    </div>
                    <div class="outter"><div class="left">Status</div><div>
                            <c:choose>
                                <c:when test="${command.damperstatus_id == 1}"><Input type="Text" style="color:green" value="PASS" /></c:when>
                                <c:when test="${command.damperstatus_id == 2}"><Input type="Text" style="color:red" value="FAIL" /></c:when>
                                <c:when test="${command.damperstatus_id == 3}"><Input type="Text" style="color:blue" value="INACCESSIBLE" /></c:when>
                                <c:when test="${command.damperstatus_id == 4}"><Input type="Text" style="color:red" value="FAIL:REPAIRED" /></c:when>
                                <c:when test="${command.damperstatus_id == 5}"><Input type="Text" style="color:blue" value="INACCESSIBLE:REPAIRED" /></c:when>
                                <c:when test="${command.damperstatus_id == 6}"><Input type="Text" style="color:blue" value="** DECOMMISSIONED **" /></c:when>
                                <c:when test="${command.damperstatus_id == 7}"><Input type="Text" style="color:yellow" value="PENDING" /></c:when>
                                <c:when test="${command.damperstatus_id == 8}"><Input type="Text" style="color:red" value="FAIL:INACCESSIBLE" /></c:when>
                                <c:otherwise>
                                    <Input type="Text" style="color:red" value="<**UnRecognized Status**>" />
                                </c:otherwise>
                            </c:choose></div></div>

                    <div class="outter"><div class="left">Alias Id</div><div><Input class="input allcaps" type="Text" value="${command.aliasId}" /></div></div>
                    <div class="outter"><div class="left">Customer</div><div><Input class="input allcaps" type="Text" value="${command.customer}" /></div></div>
                    <div class="outter"><div class="left">Building</div><div><Input class="input allcaps" type="Text" value="${command.building}" /></div></div>
                    <div class="outter"><div class="left">Floor</div><div><Input class="input allcaps" type="Text" value="${command.buildingfloor}" /></div></div>
                    <div class="outter"><div class="left">Damper Type</div><div><Input class="input allcaps" type="Text" value="${command.dampertype}" /></div></div>
                    <div class="outter"><div class="left">Material</div><div>
                            <select name="dampermaterial_id">
                                <c:forEach items="${command.damperMaterialList}" var="dampermaterial">
                                    <option class="allcaps" value="${dampermaterial.id}" <c:if test="${dampermaterial.id == command.dampermaterial_id}">SELECTED</c:if>>${dampermaterial.materialName}</option>
                                </c:forEach>
                            </select>
                        </div></div>
                    <div class="outter"><div class="left">Width</div><div><Input class="input allcaps" type="Text" value="${command.sizew}" /></div></div>
                    <div class="outter"><div class="left">Height</div><div><Input class="input allcaps" type="Text" value="${command.sizel}" /></div></div>
                    <div class="outter"><div class="left">System</div><div><Input class="input allcaps" type="Text" value="${command.system}" /></div></div>
                    <div class="outter"><div class="left">Location</div><div><textarea class="input location allcaps">${command.location}</textarea></div></div>
                    <div class="outter"><div class="left">Sublocation</div><div><textarea class="input location allcaps">${command.sublocation}</textarea></div></div>
                            <c:forEach items="${command.pictureIdList}" var="ids">
                        <div><div><img style="width: 400px;border: 1px black solid" src="pictures.htm?id=${ids}"></div></div>
                            </c:forEach>
                    <div class="outter"><div class="left">Table ID</div><div>${command.id}</div></div>
                </div>
                <div>
                    <!--                    <div class="outter">
                                            <div class="left">Date Tested</div><div>${command.nextTestDate}</div>
                                        </div>-->
                    <div class="outter"><div class="left">Occupancy</div><div class="allcaps">${command.occupancy}</div></div>
                    <div class="outter"><div class="left">Comments</div><div><textarea class="allcaps" style="width:400px" id="Comments" cols="10" rows="10" name="comments"><c:out value="${command.comments}" /></textarea></div></div>
                    <div class="outter"><div class="left">Special Procedures</div><div><textarea class="allcaps" style="width:400px" id="specialProcedures" cols="10" rows="10" name="specialProcedures">${command.specialProcedures}</textarea></div></div>

                    <div class="outter okbutton"><div class="left">&nbsp;</div>
                        <div>
                            <input class="button" type="button" onClick="history.back()" value="Ok"/>
                        </div>
                    </div>
                </div>
            </fieldset></form>
    </body>
</html>

