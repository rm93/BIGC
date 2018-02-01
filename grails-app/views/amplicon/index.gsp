<!doctype html>
<html>
<head>
<meta name="layout" content="main"/>
<title>Amplicon pipeline</title>

<asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
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
    <button class="tablinks" onclick="openTab(event, 'hist')" id="defaultOpen">History</button>
    <button class="tablinks" onclick="openTab(event, 'pipeline')">New project</button>
    <button class="tablinks" onclick="openTab(event, 'manual')">Manual</button>
</div>

%{--First tab with the pipeline history table--}%
<div id="hist" class="tabcontent">
    <h3><strong>${controllerName}</strong></h3>
    <br>
    <div class="body">
        %{--Show error message if a project with status "running" is deleted--}%
        <g:if test="${flash.message}">
            <div class="message" style="display: block"><b>${flash.message}</b></div>
        </g:if>

        <table>
            <thead>
                <th>Name project</th>
                <th>User</th>
                <th>Department</th>
                <th>Start date</th>
                <th>End date</th>
                <th>Status</th>
                <th>Delete</th>
            </thead>

            <g:each var="i" in="${rivm.db.Amplicon_project.getAll()}">
                <g:if test="${i.privat == true}">
                    <g:if test="${i.userId.toString().equals(sec.loggedInUserInfo(field:"id").toString())}">
                        <tr>
                            <td><a href="${System.getProperty("user.home")}/Documents/web_interface/pipeline/amplicon_pipeline/${i.id}/output/results/output/">${i.name}</a></td>
                            <td>${rivm.auth.Appuser.findById(i.userId).username}</td>
                            <td>${rivm.db.Partner.findById(rivm.auth.Appuser.findById(i.userId).partnerId).short_name}</td>
                            <td>${i.start_date}</td>
                            <td>${i.end_date}</td>
                            <td>${i.status}</td>
                            <td><g:link params="${[id: i.id]}" action="deleteRecord"><asset:image src="trash.png" height="23" width="23" title="This action removes all data and can not be reversed!"/></g:link></td>
                        </tr>
                    </g:if>
                </g:if>

                <g:else test="${i.privat == false}">
                    <tr>
                        <td><a href="${System.getProperty("user.home")}/Documents/web_interface/pipeline/amplicon_pipeline/${i.id}/output/results/output/">${i.name}</a></td>
                        <td>${rivm.auth.Appuser.findById(i.userId).username}</td>
                        <td>${rivm.db.Partner.findById(rivm.auth.Appuser.findById(i.userId).partnerId).short_name}</td>
                        <td>${i.start_date}</td>
                        <td>${i.end_date}</td>
                        <td>${i.status}</td>
                        <td><g:link params="${[idp: i.id, idu: i.userId]}" action="deleteRecord"><asset:image src="trash.png" height="23" width="23" title="This action removes all data and can not be reversed!"/></g:link></td>
                    </tr>
                </g:else>
            </g:each>
        </table>
    </div>
</div>

%{--Second tab with the pipeline input fields--}%
<div id="pipeline" class="tabcontent">
    %{--Name of controller--}%
    <h3><strong>${controllerName}</strong></h3>
    <br>
    %{--Form with the necessary input fields to run the pipeline--}%
    <g:form action="upload" method="post" useToken="true" enctype="multipart/form-data">
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
                <td><g:select name="amplicon" from="${rivm.db.Amplicon.getAll()}" value="3" optionKey="id" optionValue="amplicon"></g:select></td>
            </tr>
            <tr>
                <td>Upload fastq files as zip (maximum file size 10GB)</td>
                <td><input type="file" name="fileUpload" accept=".zip" required/></td>
            </tr>
        </table>
        %{--Button that gives the input data to the amplicon_controller--}%
        <g:submitButton name="upload" value="Run pipeline"></g:submitButton>
    </g:form>
</div>

%{--Third tab with manual--}%
<div id="manual" class="tabcontent">
    <h3><strong>${controllerName}</strong></h3>
    <br>
    <div class="body">
        <h1>This is a temporary text. Here comes a manual specifically for this pipeline.</h1><br>

        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus tristique sed<br>
        lacus at lobortis. Duis at tincidunt velit, vel volutpat nibh. Etiam posuere nunc<br>
        id laoreet rhoncus. Sed molestie placerat sem sollicitudin maximus. Fusce pulvinar<br>
        ligula odio, sed placerat velit aliquam eget. Aenean id cursus lacus, quis congue<br>
        arcu. Etiam tincidunt mi vitae orci pretium, in cursus massa condimentum. In eu<br>
        sem enim. Phasellus sed diam dapibus, interdum diam at, consequat magna.<br>
        <br>
        Curabitur id tristique dui, at porttitor ipsum. Sed non risus non quam ornare<br>
        placerat. Quisque vestibulum nisi sed sem consequat, eget accumsan nunc eleifend.<br>
        In placerat nunc at diam suscipit ultricies. Sed eget placerat lorem. Vestibulum<br>
        et pulvinar erat. Nunc gravida dignissim condimentum. Maecenas ut arcu porttitor,<br>
        rhoncus nibh sit amet, tincidunt turpis. Suspendisse hendrerit porta justo nec<br>
        ornare. Ut tincidunt mi ac diam dignissim aliquam. Donec quis blandit magna.<br>
        Vivamus tincidunt dui eget condimentum congue. Suspendisse venenatis mollis<br>
        vehicula. Mauris turpis justo, volutpat ac pretium vitae, ullamcorper consectetur<br>
        neque. Sed scelerisque neque sit amet tempor porta.<br>
        <br>

        <asset:image src="amplicon_pipeline_flow.png" height="800" width="800"/>
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