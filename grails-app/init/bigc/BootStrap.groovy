package bigc

import rivm.auth.*
import rivm.db.*

class BootStrap {

    def init = { servletContext ->
//        Make admin user
        if(User.count() == 0) {
            def adminRole = new Role(authority: "ROLE_ADMIN").save(flush: true)
            def user = new User(username: "admin", password: "password").save(flush: true)
            if (!user.authorities.contains(adminRole)) {
                UserRole.create(user, adminRole, true)
            }
        }
//        Make new partner
//        new Partner(name: "National Institute for Public Health and the Environment", short_name: "RIVM", country: "The Netherlands").save(flush: true)

//        create new test amplicon
//        new Amplicon(amplicon: "16sv4").save(flush: true)
    }
    def destroy = {
    }
}
