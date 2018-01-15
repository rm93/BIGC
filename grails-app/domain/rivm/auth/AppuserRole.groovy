package rivm.auth

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.codehaus.groovy.util.HashCodeHelper
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@ToString(cache=true, includeNames=true, includePackage=false)
class AppuserRole implements Serializable {

	private static final long serialVersionUID = 1

	Appuser appuser
	Role role

	@Override
	boolean equals(other) {
		if (other instanceof AppuserRole) {
			other.appuserId == appuser?.id && other.roleId == role?.id
		}
	}

    @Override
	int hashCode() {
	    int hashCode = HashCodeHelper.initHash()
        if (appuser) {
            hashCode = HashCodeHelper.updateHash(hashCode, appuser.id)
		}
		if (role) {
		    hashCode = HashCodeHelper.updateHash(hashCode, role.id)
		}
		hashCode
	}

	static AppuserRole get(long appuserId, long roleId) {
		criteriaFor(appuserId, roleId).get()
	}

	static boolean exists(long appuserId, long roleId) {
		criteriaFor(appuserId, roleId).count()
	}

	private static DetachedCriteria criteriaFor(long appuserId, long roleId) {
		AppuserRole.where {
			appuser == Appuser.load(appuserId) &&
			role == Role.load(roleId)
		}
	}

	static AppuserRole create(Appuser appuser, Role role, boolean flush = false) {
		def instance = new AppuserRole(appuser: appuser, role: role)
		instance.save(flush: flush)
		instance
	}

	static boolean remove(Appuser u, Role r) {
		if (u != null && r != null) {
			AppuserRole.where { appuser == u && role == r }.deleteAll()
		}
	}

	static int removeAll(Appuser u) {
		u == null ? 0 : AppuserRole.where { appuser == u }.deleteAll() as int
	}

	static int removeAll(Role r) {
		r == null ? 0 : AppuserRole.where { role == r }.deleteAll() as int
	}

	static constraints = {
	    appuser nullable: false
		role nullable: false, validator: { Role r, AppuserRole ur ->
			if (ur.appuser?.id) {
				if (AppuserRole.exists(ur.appuser.id, r.id)) {
				    return ['userRole.exists']
				}
			}
		}
	}

	static mapping = {
		id composite: ['appuser', 'role']
		version false
	}
}
