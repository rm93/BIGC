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

//        create new test amplicon
//        new Amplicon(amplicon: "16sv4").save(flush: true)
//        new Amplicon(amplicon: "18sv3").save(flush: true)
//
////        create new test partners
//        new Partner(name: "National Institute for Public Health and the Environment", short_name: "RIVM", country: "The Netherlands").save(flush: true)
//        new Partner(name: "Netherlands Food and Consumer Safety Authority", short_name: "NVWA", country: "The Netherlands").save(flush: true)
//        new Partner(name: "Ministry of Health, Welfare and Sport", short_name: "VWS", country: "The Netherlands").save(flush: true)

    }
    def destroy = {
    }
}
