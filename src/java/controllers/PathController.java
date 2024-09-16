package src.java.controllers;

public class PathController{

    private String projectDirectory = "C:\\Projects\\webforj-docs-samples";
    private String demosPath;
    private String[] demoPaths; 

    public String getProjectDirectory(){
        return projectDirectory;
    }

    public void setProjectDirectory(String directory){
        this.projectDirectory = directory;
    }

    public void setDemosPath(String path){
        this.demosPath = path;
    }

    public String[] getDemoPaths(){
        demosPath = projectDirectory + "/src/main/java";
    
        String path1 = demosPath + "/addondemos/tabledemos";
        String path2 = demosPath + "/componentdemos/buttondemos";
        String path3 = demosPath + "/componentdemos/checkboxdemos";
        String path4 = demosPath + "/componentdemos/choiceboxdemos";
        String path5 = demosPath + "/componentdemos/comboboxdemos";
        String path6 = demosPath + "/componentdemos/dateeditboxdemos";
        String path7 = demosPath + "/componentdemos/dialogdemos";
        String path8 = demosPath + "/componentdemos/drawerdemos";
        String path9 = demosPath + "/componentdemos/fielddemos";
        String path10 = demosPath + "/componentdemos/labeldemos";
        String path11 = demosPath + "/componentdemos/listboxdemos";
        String path12 = demosPath + "/componentdemos/login";
        String path13 = demosPath + "/componentdemos/navigatordemos";
        String path14 = demosPath + "/componentdemos/numericboxdemos";
        String path15 = demosPath + "/componentdemos/optiondialog/confirm";
        String path16 = demosPath + "/componentdemos/optiondialog/filechooser";
        String path17 = demosPath + "/componentdemos/optiondialog/fileupload";
        String path18 = demosPath + "/componentdemos/optiondialog/input";
        String path19 = demosPath + "/componentdemos/optiondialog/message";
        String path20 = demosPath + "/componentdemos/progressbar";
        String path21 = demosPath + "/componentdemos/radiobuttondemos";
        String path22 = demosPath + "/componentdemos/sliderdemos";
        String path23 = demosPath + "/componentdemos/stringeditdemos";
        String path24 = demosPath + "/componentdemos/tabbedpanedemos";
        String path25 = demosPath + "/componentdemos/textareademos/radiobuttondemos";
        String path26 = demosPath + "/componentdemos/textareademos/sliderdemos";
        String path27 = demosPath + "/componentdemos/textareademos/stringeditdemos";
        String path28 = demosPath + "/componentdemos/textboxdemos";
        String path29 = demosPath + "/demos/webcomponents/element";
        String path30 = demosPath + "/demos/webcomponents/elementcomposite";
        String path31 = demosPath + "/layout_demos/applayout";
        String path32 = demosPath + "/layout_demos/container";
        String path33 = demosPath + "/layout_demos/flex";
        String path34 = demosPath + "/layout_demos/helper";
        String path35 = demosPath + "/layout_demos/item";
        String path36 = demosPath + "/layout_demos/splitter";
        String path37 = demosPath + "/addondemos/chartdemos";
        String path38 = demosPath + "/demos";
        String path39 = demosPath + "/layout_demos";
        
        String[] paths = {path1,path2,path3,path4,path5,path6,path7,path8,path9,path10,path11,path12,path13,path14,path15,path16,path17,path18,path19,path20,path21,path22,path23,path24,path25,path26,path27,path28,path29,path30,path31,path32,path33,path34,path35,path36,path37,path38,path39};
        
        return paths;
    }

}
