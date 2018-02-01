package bigc

import rivm.auth.*
import rivm.db.Amplicon
import rivm.db.Partner

class BootStrap {

//    Create admin user by the first startup.
    def init = { servletContext ->
        if(Appuser.count() == 0) {
            def adminRole = new Role(authority: "ROLE_ADMIN").save(flush: true)
            def user = new Appuser(username: "admin", password: "password").save(flush: true)
            if (!user.authorities.contains(adminRole)) {
                AppuserRole.create(user, adminRole, true)
            }
        }
    }
    def destroy = {
    }
}
