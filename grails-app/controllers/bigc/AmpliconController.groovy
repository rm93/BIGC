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
        new Amplicon_project(name: projectName, amplicon: amplicon, privat: Private, user: usr, start_date: new Date(), end_date: new Date(), status: "Running", pipeline_version: new Integer(1)).save(flush: true)

//        Create the upload path
        new File("/home/rm93/Documents/Git/BIGC_test_upload/amplicon_pipeline/${rivm.db.Amplicon_project.last().id}/upload/").mkdirs()

//        Create the output path
        new File("/home/rm93/Documents/Git/BIGC_test_upload/amplicon_pipeline/${rivm.db.Amplicon_project.last().id}/output/").mkdirs()

//        Transfer the zip file to the upload folder
        fileUpload.transferTo(new File("/home/rm93/Documents/Git/BIGC_test_upload/amplicon_pipeline/${rivm.db.Amplicon_project.last().id}/upload/data.zip"))

//        pipeline(amplicon)
        redirect action: 'index'
    }

    def pipeline(amplicon){
//        Start python script

//          in de zelfde map lukt wel
//        println("python test.py".execute().text)

//        Dit lukt ook
//        println("ls -l".execute().text)

//        def amp = rivm.db.Amplicon.findById(amplicon).amplicon

//        amplicon_pipeline.py -i '/home/rm93/Documents/Git/BIGC_test_upload/amplicon_pipeline/${rivm.db.Amplicon_project.last().id}/upload/' -o '/home/rm93/Documents/Git/BIGC_test_upload/amplicon_pipeline/${rivm.db.Amplicon_project.last().id}/output/' -a 'amp'
    }
}
