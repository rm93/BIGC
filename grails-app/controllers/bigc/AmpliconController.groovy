package bigc

import rivm.db.Amplicon_project

class AmpliconController {
    def springSecurityService

    def index() {
    }

    def upload(){
//        Get the data from the upload form
        def projectName = params.projectName
        def Private = params.pri
        def usr = springSecurityService.getCurrentUserId()
        def amplicon = params.amplicon
        def fileUpload = params.fileUpload

//        Set default path to store the data
        String Path = System.getProperty("user.home")+"/Documents/web_interface/pipeline/amplicon_pipeline/"

//        create project
        new Amplicon_project(name: projectName, amplicon: amplicon, privat: Private, user: usr, start_date: new Date(), end_date: new Date(), status: "Running", pipeline_version: new Integer(1)).save(flush: true)

//        Create the upload path
        new File(Path+"${rivm.db.Amplicon_project.last().id}/upload/").mkdirs()

//        Create the output path
        new File(Path+"${rivm.db.Amplicon_project.last().id}/output/").mkdirs()

//        Transfer the zip file to the upload folder
        fileUpload.transferTo(new File(Path+"${rivm.db.Amplicon_project.last().id}/upload/data.zip"))

//        Start the "startPipeline" function
        startPipeline(amplicon, Path)

//        Redirect to the history page
        redirect action: 'index'
    }

    def startPipeline(amplicon, Path){
//        Unzip data.zip file in output folder.
        def getZipData = ["unzip", "-j", Path+"${rivm.db.Amplicon_project.last().id}/upload/data.zip", "-d", Path+"${rivm.db.Amplicon_project.last().id}/upload/"].execute()

//        Remove data.zip file in output folder.
        def rmDataZipFile = ["rm", "-f", Path+"${rivm.db.Amplicon_project.last().id}/upload/data.zip"].execute()

//        Start pipeline
        def amp = rivm.db.Amplicon.findById(amplicon).amplicon
        def pipeline = ["amplicon_pipeline.py", "-i", Path+"${rivm.db.Amplicon_project.last().id}/upload/", "-o", Path+"${rivm.db.Amplicon_project.last().id}/output/", "-a", amp, "-n", "results"].execute()
        println(pipeline.err.text)

//        Start the "updateDateStatus" function
        updateDateStatus(Path)
    }

    def updateDateStatus(Path){
//        Update date and status *first attempt*.
        def project = Amplicon_project.get(rivm.db.Amplicon_project.last().id)

//        Set new data.
        project.end_date = new Date()

//        Check if output folder is empty or not.
//        If output folder is empty then the pipeline is not starting or gives a error.
//        Not empty status Complete.
//        Empty status Error.
        File parentDir = new File(Path+"${rivm.db.Amplicon_project.last().id}/output/results/output/")
        if (parentDir.isDirectory() && parentDir.list().length == 0){
            project.status = "Error"
        } else{
            project.status = "Complete"
        }

//        Saves the changes
        project.save(flush: true)
    }

    def deleteRecord(){
//        Set path variable
        String Path = System.getProperty("user.home")+"/Documents/web_interface/pipeline/amplicon_pipeline/"

        if (Amplicon_project.get(params.idp).userId.toString().equals((params.idu).toString() == true)){
//            If the project status is "Error" or "Complete" delete the project else show message.
            if (Amplicon_project.get(params.idp).status == "Error" || Amplicon_project.get(params.idp).status == "Complete"){
//                Delete selected project from the database
                Amplicon_project.get(params.idp).delete(flush: true)

//                Delete selected project data
                ["rm", "-rf", Path+params.idp+"/"].execute()
            }else {
//                Error message
                flash.message = """Project "${Amplicon_project.get(params.idp).name}" can not be deleted because it is still in use."""
            }
        }else{
//             Error message
            flash.message = """Project "${Amplicon_project.get(params.idp).name}" can not be deleted because it is not your project."""
        }

//        Redirect to the history page
        redirect action: 'index'
    }
}