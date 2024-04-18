
plugins {
    // id("net.ivoa.vo-dml.vodmltools") version "0.4.5"    << requires gradle version 8.0+
    id("net.ivoa.vo-dml.vodmltools") version "0.3.14"
}

group = "org.javastro.ivoa.dm"     // "net.ivoa.vo-dml"
version = "0.4.1-SNAPSHOT"         // "0.2-SNAPSHOT"

// Settings for all vodml tools
vodml {
     // where to find the vodml XML files (using 'project' structure)
     //  - defaults to src/main/vo-dml
     //vodmlDir.set( file("<path to vo-dml files>") )

     // identify the vodml XML files 
     //   - defaults to all "*.vo-dml.xml" files in vodmlDir
     //vodmlFiles.setFrom( project.files( vodmlDir.file("Trans-v1.0.vo-dml.xml") ))

     // where to find the VODSL files
     //  - defaults to src/main/vodsl
     //vodslDir.set( file("./src/main/vodsl") )

     // identify the VODSL files (using 'project' structure)
     //   - defaults to all "*.vodsl" files in vodslDir
     //vodslFiles.setFrom( project.files( vodslDir.file("Trans-v1.0.vodsl") ))

     // where to find the binding file(s)
     //   - finds them by pattern in the project file tree. (eg: binding_proposal_model.xml )
     bindingFiles.setFrom( project.files(
                             layout.projectDirectory.asFileTree.matching( PatternSet().include("binding*model.xml") )
                           ))

     // where to put output from vodmlDoc task
     outputDocDir.set(layout.projectDirectory.dir("std/generated"))

     // where to put output from vodmlSite task ( toolkit 0.4.5 )
     // outputSiteDir.set(layout.projectDirectory.dir("docs/generated"))

     // which models to process ( for repos containing multiple models? )
     modelsToDocument.set("transform")
}

// Register task to execute UML converter which is very custom
tasks.register( "UmlToVodml", net.ivoa.vodml.gradle.plugin.XmiTask::class.java){
    // ----------------------------------------------------------
    // creates build/tmp/UmlToVodml tree to write temporary files
    // ----------------------------------------------------------
    description = "convert UML to VO-DML"

    // the conversion script to use
    xmiScript.set("xmi2vo-dml_Modelio3.7_UML2.4.1.xsl")

    // where to find the UML XMI file
    xmiFile.set(file("../model/trans_1.0_uml2p4p1.xmi"))
    
    // output VO-DML/XML file to 'project' tree structure
    //  - this lets us use a lot of default settings in the vodml block
    vodmlFile.set(file("./src/main/vo-dml/Trans-v1.0.vo-dml.xml"))
}
