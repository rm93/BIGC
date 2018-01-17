<g:set var='securityConfig' value='${applicationContext.springSecurityService.securityConfig}'/>
<html>
<head>
	<meta name="layout" content="main"/>
	<s2ui:title messageCode='spring.security.ui.login.title'/>
	<asset:stylesheet src='spring-security-ui-auth.css'/>
</head>
<body>
<p/>
<div class="login s2ui_center ui-corner-all" style='text-align:center;'>
	<div class="login-inner">
		<s2ui:form type='login' focus='username'>
			<div class="sign-in">
				<h2><g:message code='spring.security.ui.login.signin'/></h2>
				<table>
					<tr>
						<td><label for="username"><g:message code='spring.security.ui.login.username'/></label></td>
						<td><input type="text" name="${securityConfig.apf.usernameParameter}" id="username" class='formLogin' size="20"/></td>
					</tr>
					<tr>
						<td><label for="password"><g:message code='spring.security.ui.login.password'/></label></td>
						<td><input type="password" name="${securityConfig.apf.passwordParameter}" id="password" class="formLogin" size="20"/></td>
					</tr>
					<tr>
						<td colspan='2'>
							<g:submitButton name="Login"></g:submitButton>
						</td>
					</tr>
				</table>
			</div>
		</s2ui:form>
	</div>
</div>
</body>
</html>
