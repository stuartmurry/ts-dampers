<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<script type="text/javascript">
    function moveBuildings()
    {
        // Remove
        var elSel = document.getElementById('buildings');
        var i;
        for (i = elSel.length - 1; i>=0; i--) {
            if (elSel.options[i].selected) {

                // Add
                var elOptNew = document.createElement('option');
                elOptNew.text = elSel.options[i].text;
                elOptNew.value = elSel.options[i].value;;
                var lo = document.getElementById('buildingsSelected');

                try {
                    lo.add(elOptNew, null); // standards compliant; doesn't work in IE
                }
                catch(ex) {
                    lo.add(elOptNew); // IE only
                }

                elSel.remove(i);
            }
        }

    }
    
    function removeBuildings()
    {
        // Remove
        var elSel = document.getElementById('buildingsSelected');
        var i;
        for (i = elSel.length - 1; i>=0; i--) {
            if (elSel.options[i].selected) {

                // Add
                var elOptNew = document.createElement('option');
                elOptNew.text = elSel.options[i].text;
                elOptNew.value = elSel.options[i].value;;
                var lo = document.getElementById('buildings');

                try {
                    lo.add(elOptNew, null); // standards compliant; doesn't work in IE
                }
                catch(ex) {
                    lo.add(elOptNew); // IE only
                }

                elSel.remove(i);
            }
        }
    }
    
    function getBuildingArray()
    {
        var formElements = document.forms['form'].elements;
        var buildingsSelected = formElements['buildingsSelected'];
        var buildingsLength = buildingsSelected.length;
        var i;
        var str = '';
        for (i=0; i<buildingsLength; i++)
        {
            var valuable = buildingsSelected.options[i].value;
            var textable = buildingsSelected.options[i].text;
            str += valuable;
            if (i != (buildingsLength -1))
            {
                str += ':';
            }
                    
        }
        formElements['buildingarrays'].value=str;
                
    }
            
    function setVarz(actionz, paramz)
    {
        getBuildingArray();
        var formElements = document.forms['form'].elements;
        formElements['actions'].value = actionz;
        formElements['actionparams'].value = paramz;
        //tarjet('target', '_finish');
    }   
    
</script>

<form method="post" action="" name="form">
    <input id="actions" type="hidden" name="action">
    <input id="params" type="hidden" name="param">
    <input id="buildingarrays" type="hidden" name="buildingarray">
    <input type="hidden" name="target" value="">
        
    <tr style="vertical-align: top">
        <td style="width:30%; text-align:center"><strong>Client Setup</strong></td>
        
        <td>
            <div>
                <table>
                    <tr></tr>
                </table>
                <h2>ATTACH BUILDINGS</h2>
                <table>
                    <tr><td>Global Building List</td><></tr>
                    <tr>
                        <td>
                            <select id="buildings" name="building" style="width:200px; height:150px" multiple="true">
                                <c:forEach items="${command.buildinglist}" var="building">
                                    <option value="<c:out value="${building.id}"/>"><c:out value="${building.buildingName}"/></option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>Attach<br>
                            Here<br>
                            <input id="adding" type="button" value="->" onclick="javascript:moveBuildings()"><br/>
                            <input id="subtracting" type="button" value="<-" onclick="javascript:removeBuildings()">
                        </td>
                        <td>
                            <select id="buildingsSelected" style="width:200px; height:150px" multiple="true">
                                <c:forEach items="${command.selectedbuildinglist}" var="building">
                                    <option value="<c:out value="${building.id}"/>"><c:out value="${building.buildingName}"/></option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
            </div>
            <c:redirect url="project/building" />
            <input type="submit" name="_cancel" value="Cancel" />
            <input type="submit" name="_target2" value="<- Back"  />
            <input type="submit" name="_finish" onclick="javascript:setVarz('save', 'none')" value="Finished"  />
        </td>
    </tr>
</form>