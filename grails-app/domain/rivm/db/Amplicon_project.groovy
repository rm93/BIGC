package rivm.db

import rivm.auth.Appuser

class Amplicon_project {

//    Create database table to store project data.
    String name
    Boolean privat
    Date start_date
    Date end_date
    String status
    Integer pipeline_version

//    Set relations with amplicon and user
    static belongsTo = [amplicon: Amplicon, user: Appuser]

    static constraints = {
        name nullable: false, blank: false
        privat nullable: false, blank: false
        start_date nullable: false, blank: false
        status nullable: false, blank: false
        pipeline_version nullable: false, blank: false
    }

    static mapping = {
//        Sort on start_date
        sort start_date: "desc"
    }
}
