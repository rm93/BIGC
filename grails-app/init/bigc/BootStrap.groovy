package bigc

import rivm.auth.*
import rivm.db.Amplicon
import rivm.db.Partner

class BootStrap {

    def init = { servletContext ->
        if(Appuser.count() == 0) {
            def adminRole = new Role(authority: "ROLE_ADMIN").save(flush: true)
            def user = new Appuser(username: "admin", password: "password").save(flush: true)
            if (!user.authorities.contains(adminRole)) {
                AppuserRole.create(user, adminRole, true)
            }
        }

//        create new test amplicon
//        new Amplicon(amplicon: "16sv4").save(flush: true)

//        create new test partners
//        new Partner(name: "National Institute for Public Health and the Environment", short_name: "RIVM", country: "The Netherlands").save(flush: true)

    }
    def destroy = {
    }
}
