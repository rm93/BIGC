package bigc

import rivm.db.Amplicon_project

class AmpliconController {
    def springSecurityService

    def index() {
    }

    def upload(){
        def projectName = params.projectName
        def Private = params.private
//        Wat komt hier uit
        def usr = springSecurityService.getCurrentUser()
        def amplicon = params.amplicon
//      Maak een zip upload
        def fileUpload = request.getFile(params.fileUpload)




//        new Amplicon_project(name: projectName, privat: Private, usr: usr, start_date: new Date(), end_date: new Date(), status: "Running", pipeline_version: new Integer(1)).save(flush: true)


        def path = new File("/home/rm93/Documents/Git/GABCE_Test/amplicon_pipeline/${rivm.db.Amplicon_project.getAll().id[0]}/upload/").mkdirs()

//      Test print info in text file
//      verander /home/rm93/Documents/Git/GABCE_Test/ for ${basedir}



        fileUpload.transferTo(path)


        redirect action: 'index'
    }
}
