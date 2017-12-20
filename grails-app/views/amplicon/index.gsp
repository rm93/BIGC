<!doctype html>
<html>
<head>
<meta name="layout" content="main"/>
<title>Amplicon pipeline</title>

<asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
<asset:javascript src="uploadr.manifest.js"/>
<asset:stylesheet href="uploadr.manifest.css"/>
%{--Style code for the tabs--}%
<style>
    /* Style the tab */
    div.tab {
        overflow: hidden;
        border: 1px solid #ccc;
        background-color: #f1f1f1;
    }

    /* Style the buttons inside the tab */
    div.tab button {
        background-color: inherit;
        float: left;
        border: none;
        outline: none;
        cursor: pointer;
        padding: 14px 16px;
        transition: 0.3s;
        font-size: 17px;
    }

    /* Change background color of buttons on hover */
    div.tab button:hover {
        background-color: #ddd;
    }

    /* Create an active/current tablink class */
    div.tab button.active {
        background-color: #ccc;
    }

    /* Style the tab content */
    .tabcontent {
        display: none;
        padding: 6px 12px;
        border: 1px solid #ccc;
        border-top: none;
    }
</style>
</head>
<body>
%{--Tab names--}%
<div class="tab">
    <button class="tablinks" onclick="openTab(event, 'pipeline')" id="defaultOpen">Pipeline</button>
    <button class="tablinks" onclick="openTab(event, 'hist')">View history</button>
</div>
%{--end--}%

%{--First tab with the pipeline input fields--}%
<div id="pipeline" class="tabcontent">
    %{--Name of controller--}%
    <h3><strong>${controllerName}</strong></h3>
    <br>
    <g:form action="upload" method="post" useToken="true" enctype="multipart/form-data">
        %{--<table style="width: 800px">--}%
            %{--<tr>--}%
                %{--<td colspan="2"><label for="projectName">Name project</label><g:textField name="projectName" required="required" maxlength="100" minlength="5"></g:textField></td>--}%
            %{--</tr>--}%
            %{--<tr>--}%
                %{--<td><label for="private">Project private</label><g:select name="private" from="['True', 'False']" value="False"></g:select></td>--}%
                %{--<td><label for="amplicon">Chose amplicon</label><g:select name="amplicon" from="${rivm.db.Amplicon.all.amplicon}" noSelection="${['null':'Select..']}" value="63"></g:select></td>--}%
                %{--<g:select name="amplicon" from="${rivm.db.Amplicon.getAll()}" value="63" optionKey="id" optionValue="amplicon"></g:select>--}%
            %{--</tr>--}%
            %{--<tr>--}%
                %{--<td><label for="fileUpload">Drag & drop to upload or click on browse to upload</label></td>--}%
                %{--<td><input type="file" name="fileUpload"/></td>--}%
            %{--</tr>--}%
            %{--<tr>--}%
                %{--<input type="hidden" name="usr" value="<sec:username/>"><td colspan="2"><g:submitButton name="upload" value="Run pipeline"></g:submitButton></td>--}%
            %{--</tr>--}%
        %{--</table>--}%

        <span class="button">
            <input type="file" name="fileUpload"/>
            <input type="submit" class="upload" value="upload"/>

        </span>
    </g:form>

    <table>
        <tr>
            <td>Name project</td>
            <td><g:textField name="projectName" required="required" maxlength="100" minlength="5"></g:textField></td>
        </tr>
        <tr>
            <td>Project private</td>
            <td>False <input type="radio" name="pri" value="False" checked> True <input type="radio" name="pri" value="True"></td>
        </tr>
        <tr>
            <td>Chose amplicon</td>
            <td><g:select name="amplicon" from="${rivm.db.Amplicon.getAll()}" value="63" optionKey="id" optionValue="amplicon"></g:select></td>
        </tr>
        <tr>
            <td>Upload fastq files as zip</td>
            <td><input type="file" name="fileUpload" multiple/></td>
        </tr>
    </table>
</div>

%{--Second tab with the pipeline history table--}%
<div id="hist" class="tabcontent">
    <h3><strong>${controllerName}</strong></h3>
    <br>
    <div class="body">
        <table>
            <tr>
                <th>Name project</th>
                <th>User</th>
                <th>Short Name</th>
                <th>Start date</th>
                <th>End date</th>
                <th>Status</th>
            </tr>
        <g:each var="i" in="${rivm.db.Amplicon_project.getAll()}">
            ${i.id}
            <tr>
                <td><a href="${createLink(uri:'#')}">${i.name}</a></td>
                <td>${i.usr}</td>
                <td>${rivm.db.Partner.getAll().short_name[0]} [hardcoded]</td>
                <td>${i.start_date}</td>
                <td>${i.end_date}</td>
                <td>${i.status}</td>
            </tr>
        </g:each>
        </table>
    </div>
</div>

    %{--Start tab script code--}%
<script>
    function openTab(evt, name) {
        var i, tabcontent, tablinks;
        tabcontent = document.getElementsByClassName("tabcontent");
        for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablinks");
        for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
        }
        document.getElementById(name).style.display = "block";
        evt.currentTarget.className += " active";
    }

    // Get the element with id="defaultOpen" and click on it
    document.getElementById("defaultOpen").click();
</script>
    %{--End tab script code--}%
</body>
</html>