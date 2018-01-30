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
        String Path = System.getProperty("user.home")+"/Documents/web_interface/pipeline/amplicon_pipeline/"

//        create project
        new Amplicon_project(name: projectName, amplicon: amplicon, privat: Private, user: usr, start_date: new Date(), end_date: new Date(), status: "Running", pipeline_version: new Integer(1)).save(flush: true)

//        Create the upload path
        new File(Path+"${rivm.db.Amplicon_project.last().id}/upload/").mkdirs()

//        Create the output path
        new File(Path+"${rivm.db.Amplicon_project.last().id}/output/").mkdirs()

//        Transfer the zip file to the upload folder
        fileUpload.transferTo(new File(Path+"${rivm.db.Amplicon_project.last().id}/upload/data.zip"))

        startPipeline(amplicon, Path)
        redirect action: 'index'
    }

    def startPipeline(amplicon, Path){
//        Unzip data.zip file in output folder.
        def getZipData = ["unzip", "-j", Path+"${rivm.db.Amplicon_project.last().id}/upload/data.zip", "-d", Path+"${rivm.db.Amplicon_project.last().id}/upload/"].execute()
        println(getZipData.err.text)

//        Remove data.zip file in output folder.
        def rmDataZipFile = ["rm", "-f", Path+"${rivm.db.Amplicon_project.last().id}/upload/data.zip"].execute()
        println(rmDataZipFile.err.text)

//        Start pipeline
        def amp = rivm.db.Amplicon.findById(amplicon).amplicon
        def pipeline = ["amplicon_pipeline.py", "-i", Path+"${rivm.db.Amplicon_project.last().id}/upload/", "-o", Path+"${rivm.db.Amplicon_project.last().id}/output/", "-a", amp].execute()
        println(pipeline.err.text)

//        Update date and status *first attempt*.
        def project = Amplicon_project.get(rivm.db.Amplicon_project.last().id)
        project.end_date = new Date()

//        Still need work!
//        if (!(Path+"${rivm.db.Amplicon_project.last().id}/output/".isEmpty())){
//            project.status = "Complete"
//        }else{
//            project.status = "Error"
//        }
        project.save(flush: true)
    }
}
