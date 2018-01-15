package rivm.db

import rivm.auth.Appuser

class Partner implements Serializable{

    String name
    String short_name
    String country

//    Set relation with user
    static hasMany = [user: Appuser]

    static constraints = {
        name nullable: false, blank: false, unique: true
        short_name nullable: false, blank: false, unique: true
        country nullable: false, blank: false
    }
    static mapping = {
    }
}