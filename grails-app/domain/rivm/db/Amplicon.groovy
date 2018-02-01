package rivm.db

class Amplicon {

//    Create database table to store available amplicons.
    String amplicon

//    Set relation with amplicon_project
    static hasMany = [project: Amplicon_project]

    static constraints = {
        amplicon nullable: false, blank: false, unique: true
    }
}
