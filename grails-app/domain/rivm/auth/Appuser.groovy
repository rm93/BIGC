package rivm.auth

import rivm.db.Amplicon_project
import rivm.db.Partner
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class Appuser implements Serializable {

    private static final long serialVersionUID = 1

    String username
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    static belongsTo = [partner: Partner]
    static hasMany = [project: Amplicon_project]

    Set<Role> getAuthorities() {
        (AppuserRole.findAllByAppuser(this) as List<AppuserRole>)*.role as Set<Role>
    }

    static constraints = {
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true
        partner nullable: true
    }

    static mapping = {
        password column: '`password`'
    }
}
