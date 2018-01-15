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
    <button class="tablinks" onclick="openTab(event, 'faq')">FAQ</button>
</div>
%{--end--}%

%{--First tab with the pipeline history table--}%
<div id="hist" class="tabcontent">
    <h3><strong>${controllerName}</strong></h3>
    <br>
    <div class="body">
        <table>
            <thead>
                <th>Name project</th>
                <th>User</th>
                <th>Short Name</th>
                <th>Start date</th>
                <th>End date</th>
                <th>Status</th>
            </thead>
            <g:each var="i" in="${rivm.db.Amplicon_project.getAll()}">
                <g:if test="${i.privat == true}">
                    <g:if test="${i.userId.toString().equals(sec.loggedInUserInfo(field:"id").toString())}">
                        <tr>
                            <td><a href="/home/rm93/Documents/Git/BIGC_test_upload/amplicon_pipeline/${i.id}/output/">${i.name}</a></td>
                            <td>${rivm.auth.Appuser.findById(i.userId).username}</td>
                            <td>${rivm.db.Partner.findById(rivm.auth.Appuser.findById(i.userId).partnerId).short_name}</td>
                            <td>${i.start_date}</td>
                            <td>${i.end_date}</td>
                            <td>${i.status}</td>
                        </tr>
                    </g:if>
                </g:if>

                <g:else test="${i.privat == false}">
                    <tr>
                        <td><a href="/home/rm93/Documents/Git/BIGC_test_upload/amplicon_pipeline/${i.id}/output/">${i.name}</a></td>
                        <td>${rivm.auth.Appuser.findById(i.userId).username}</td>
                        <td>${rivm.db.Partner.findById(rivm.auth.Appuser.findById(i.userId).partnerId).short_name}</td>
                        <td>${i.start_date}</td>
                        <td>${i.end_date}</td>
                        <td>${i.status}</td>
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
                <td>Upload fastq files as zip</td>
                <td><input type="file" name="fileUpload" accept=".zip"/></td>
            </tr>
        </table>
        <g:submitButton name="upload" value="Run pipeline"></g:submitButton>
    </g:form>
</div>

%{--Third tab with FAQ--}%
<div id="faq" class="tabcontent">
    <h3><strong>${controllerName}</strong></h3>
    <br>
    <div class="body">
        <table>
            <thead>
                <th>Question</th>
                <th>Answer</th>
            </thead>
            <tr>
                <td>Name project</td>
                <td>Answer</td>
            </tr>
            <tr>
                <td>Project private</td>
                <td>Answer</td>
            </tr>
            <tr>
                <td>Chose amplicon</td>
                <td>Answer</td>
            </tr>
            <tr>
                <td>Upload</td>
                <td>Answer</td>
            </tr>
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