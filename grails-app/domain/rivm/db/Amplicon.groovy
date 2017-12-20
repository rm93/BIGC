package rivm.db

class Amplicon {

    String amplicon

//    Set relation with amplicon_project
    static hasMany = [project: Amplicon_project]

    static constraints = {
        amplicon nullable: false, blank: false, unique: true
    }
}
