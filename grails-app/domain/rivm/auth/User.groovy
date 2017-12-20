package rivm.auth

import grails.compiler.GrailsCompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import rivm.db.Amplicon_project
import rivm.db.Partner

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

    private static final long serialVersionUID = 1

    String username
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

//    Set relations
    static belongsTo = [partner: Partner]
    static hasMany = [project: Amplicon_project]

    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

    static constraints = {
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true
//        Set partner to nullable
        partner nullable: true
    }

    static mapping = {
        table '`appuser`' //user is properity for postgresql insted i use now User as table name
	    password column: '`password`'
    }
}
