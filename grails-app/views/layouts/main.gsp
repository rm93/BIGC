<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Web interface"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>
<body>

<div class="navbar navbar-default navbar-static-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/#">
                <i class="fa grails-icon">
                    <asset:image src="grails-cupsonly-logo-white.svg"/>
                </i> Genomics & Bioinformatics warehouse
            </a>
        </div>
        <div class="navbar-collapse collapse" aria-expanded="false" style="height: 0.8px;">
            <ul class="nav navbar-nav navbar-right">
            <!--If logged in with ROLE_ADMIN show User system-->
                <sec:ifAllGranted roles="ROLE_ADMIN">
                    <li class="dropdown">
                        <a href="${createLink(uri:'/')}">Home</a>
                    </li>
                    <li class="dropdown">
                        <g:link controller="user">User system</g:link>
                    </li>
                    <li class="dropdown">
                        <g:link controller='logout'>Logout</g:link>
                    </li>
                </sec:ifAllGranted>

            <!--If logged in with ROLE_USER don't show User system-->
                <sec:ifAllGranted roles="ROLE_USER">
                    <li class="dropdown">
                        <a href="${createLink(uri:'/')}">Home</a>
                    </li>
                    <li class="dropdown">
                        <g:link controller='logout'>Logout</g:link>
                    </li>
                </sec:ifAllGranted>
            </ul>
        </div>
    </div>
</div>

<g:layoutBody/>

<div class="footer" role="contentinfo">Copyright &#169; <script>document.write(new Date().getFullYear())</script> Rick Medemblik</div>

<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>

<asset:javascript src="application.js"/>

</body>
</html>

