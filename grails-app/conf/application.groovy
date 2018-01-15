// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'rivm.auth.Appuser'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'rivm.auth.AppuserRole'
grails.plugin.springsecurity.authority.className = 'rivm.auth.Role'
//allows logout to work
grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['ROLE_ADMIN', 'ROLE_USER']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['ROLE_ADMIN', 'ROLE_USER']],
	[pattern: '/index.gsp',      access: ['ROLE_ADMIN', 'ROLE_USER']],
	[pattern: '/amplicon/**',      access: ['ROLE_ADMIN', 'ROLE_USER']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],
	[pattern: '/user/**',     access: ['ROLE_ADMIN']],
	[pattern: '/role/**',     access: ['ROLE_ADMIN']],
	[pattern: '/securityInfo/**',     access: ['ROLE_ADMIN']],
	[pattern: '/registrationCode/**',     access: ['ROLE_ADMIN']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

