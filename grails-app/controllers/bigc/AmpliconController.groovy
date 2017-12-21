package bigc

import rivm.db.Amplicon_project

class AmpliconController {
    def springSecurityService

    def index() {
    }

    def upload(){
        def projectName = params.projectName
        def Private = params.pri
        def usr = springSecurityService.getCurrentUserId()
        def amplicon = params.amplicon
        def fileUpload = params.fileUpload

//        create project
//        When login works test it
//        new Amplicon_project(name: projectName, amplicon: amplicon, privat: Private, user: usr, start_date: new Date(), end_date: new Date(), status: "Running", pipeline_version: new Integer(1))

//        Maybe there is a cleaner way to find the newest project ID?
//        This is not the cleanest way but it works

//        Create the upload path for the zip file
        def path = new File("/home/rm93/Documents/Git/BIGC_test_upload/amplicon_pipeline/${rivm.db.Amplicon_project.getAll().id[0]}/upload/").mkdirs()
//        Transfer the zip file to the create path
        fileUpload.transferTo(new File("/home/rm93/Documents/Git/BIGC_test_upload/amplicon_pipeline/${rivm.db.Amplicon_project.getAll().id[0]}/upload/data.zip"))

        redirect action: 'index'
    }
}
