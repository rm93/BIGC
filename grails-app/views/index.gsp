<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Home page</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>
<div id="content" role="main">
    <section class="row colset-2-its">
        <h1>Welcome <sec:username /></h1>
        <p>
        <table border="1">
            <tr>
                <td><b>Pipeline</b></td>
                <td><b>Discription</b></td>
            </tr>
            <tr>
                <td> <g:link controller="amplicon"><asset:image src="icon_myomics.png"/></g:link></td>
                <td>Our microbial diversity services include taxonomic analysis using our custom data
                analysis pipeline. The data analysis pipeline consists of two major stages, the
                denoising and chimera detection stage and the microbial diversity analysis stage.</td>
            </tr>
        </table>
    </p>
    </section>
</div>
</body>
</html>