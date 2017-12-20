// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'rivm.auth.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'rivm.auth.UserRole'
grails.plugin.springsecurity.authority.className = 'rivm.auth.Role'
grails.plugin.springsecurity.logout.postOnly = false //allows logout to work
grails.gorm.failOnError = true
//    Convert from MB to bytes: MB * 1024 * 1024 = bytes
grails.controllers.upload.maxFileSize=2097152
grails.controllers.upload.maxRequestSize=2097152
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/amplicon/**',      access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],
	[pattern: '/dbconsole/**',   access: ['permitAll']],
	[pattern: '/user/**',     access: ['permitAll']],
	[pattern: '/role/**',     access: ['permitAll']],
	[pattern: '/securityInfo/**',     access: ['permitAll']],
	[pattern: '/registrationCode/**',     access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

