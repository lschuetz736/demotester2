package src.java.dialogs;

public class CmdDialog {
    
    public void printNoArguments(){
        System.out.println("Please enter an argument. Arguments: takeScreenshots, compareScreenshots");
    }

    public void printCreatingDataFile(){
        System.out.println("Creating data file");
    }

    public void printFileCreated(String fileName){
        System.out.println("Data file created: " + fileName);
    }
    
    public void printDataFileExists(){
        System.out.println("Data file does already exist");
    }

    public void printLackOfArguments(){
        System.out.println("An argument that is needed to start the program is missing");
    }

    public void printInvalidCommand(){
        System.out.println("Invalid command. Valid commands: takeScreenshot | compareScreenshots");
    }

    public void printPathDoesNotExist(){
        System.out.println("The Path you entered does not exist");
    }

    public void printInvalidTime(){
        System.out.println("Invalid time. Please enter a time between 1000 and 99999 ms");
    }

    public void printStartingDemo(String name){
        System.out.println("Starting demo " + name + "...");
    }

    public void printFilename(String fileName){
        System.out.println("Filename: " + fileName);
    }

    public void printDemoStarted(){
        System.out.println("Demo started");
    }

    public void printWaitingForSite(int ms){
        System.out.println("Waiting " + ms + " ms for site to load...");
    }
}
