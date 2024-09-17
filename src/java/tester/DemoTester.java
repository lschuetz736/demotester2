package src.java.tester;

import src.java.controllers.*;
import src.java.dialogs.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.nio.file.Path;

import java.time.LocalDate;
import java.time.LocalTime;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.microsoft.playwright.*;

public class DemoTester{

    private PathController pathController;

    private Playwright playwright;

    private String demoDirectory;
    private String command;
    private String addressBegin;
    private String time;

    private int ms;
    
    private String[] paths;

    public static void main(String[] args){
        new DemoTester().start(args);
    }

    public void start(String[] args){
        
        int exitCode = init(args);     
        
        switch(exitCode){
            case 0:
                
            break;

            case 2:
                new CmdDialog().printNoArguments();
            break;

            case 3:
                new CmdDialog().printLackOfArguments();
            break;

            case 4:
                new CmdDialog().printInvalidCommand();
            break;

            case 5:
                new CmdDialog().printPathDoesNotExist();
            break;

            case 6:
                new CmdDialog().printInvalidTime();
            break;
        }

        if (exitCode != 0){
            System.exit(exitCode);
        }

        try{
            playwright = Playwright.create();
        } catch(Exception e){
            e.printStackTrace(); 
        }

        switch(command){
            case "takeScreenshots":
                try{
                    takeScreenshots();
                } catch(Exception e){
                    e.printStackTrace();
                }
            break;

            case "compareScreenshots":
                try{
                    compareScreenshots();
                } catch(Exception e){
                    e.printStackTrace();
                }
            break;
        }
    }

    public int init(String[] args){
        // command pattern: command path address time
        
        if (args.length == 0){
            new CmdDialog().printNoArguments();
            return 2;
        } else if (args.length < 4){
            new CmdDialog().printLackOfArguments();
            return 3;
        }

        command = args[0];
        if (command.matches("takeScreenshots") == false && command.matches("compareScreenshots") == false){
            new CmdDialog().printInvalidCommand();
            return 4;
        }
        
        demoDirectory= args[1];
        Path path = Paths.get(demoDirectory);
        if (Files.exists(path) == false){
            new CmdDialog().printPathDoesNotExist();
            return 5;
        }
        
        addressBegin = args[2]; 
        if (addressBegin.matches("default")){
            addressBegin = "http://localhost:8888/webapp/demos/";
        }

        time = args[3];
        if (time.matches("default")){
            ms = 3000;
        } else if (time.matches("[0-9]{1,5}") && time.matches("default") == false){
            return 6;
        } else {
            ms = Integer.parseInt(time);
        }
        
        pathController = new PathController();
        pathController.setProjectDirectory(demoDirectory);
        paths = pathController.getDemoPaths();

        String name;

        return 0;
    }

    public void takeScreenshots() throws InterruptedException{
        String name;

        for (int i = 0; i < paths.length; i++){
            File folderPath = new File(paths[i]);
            String[] files = folderPath.list();
            
            if (files == null){
                continue;
            }

            for (int j = 0; j < files.length; j++){
                
                String fileName = files[j];
                
                boolean containsJava = fileName.contains(".java");
                
                if (containsJava = false){
                    continue;
                } else {
                    name = fileName.replace(".java","");
                }
                
                new CmdDialog().printStartingDemo(name);
                new CmdDialog().printFilename(fileName);

                String address = addressBegin + name;
                
                Browser browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(true).setSlowMo(50));
                BrowserContext context = browser.newContext();
                Page page = context.newPage();
                page.navigate(address);

                new CmdDialog().printDemoStarted();
                new CmdDialog().printWaitingForSite(ms);
                
                Thread.sleep(ms);

                System.out.println("Taking Screenshot...");
                page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("./Screenshots/screenshot_" + name + ".png")));
                System.out.println("Screenshot taken");
                browser.close();
                
                /* 
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
                context = browser.newContext();
                page = context.newPage();
                page.navigate("http://desktop-fp4op26:8888/webapp/ProcessRemover");

                Thread.sleep(1000);
                
                browser.close();
                
                Code of the ProcessRemover, you need to deploy this bbjprogram in the dwc first

                sessions! = BBjAPI().getSessionInfos()

                for i = 0 to sessions!.size() -1
                    session! = sessions!.getItem(i)
                    BBjAPI().killSession(session!,"admin","admin123")
                next i

                bye

                    */

            }
        }
    }

    public void compareScreenshots() throws Exception{
        boolean testSuccessfull = true;
        String name;

            new CmdDialog().printCreatingDataFile();

            File file = new File("data.txt");

            if (file.createNewFile()) {
                new CmdDialog().printFileCreated(file.getName());
            } else {
                new CmdDialog().printDataFileExists();
            }

            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now().withNano(0);

            FileWriter dataWriter = new FileWriter("data.txt",true);

            dataWriter.write("**********NEW TEST SESSION**********\n");
            dataWriter.write("Date: " + date + " Time: " + time + "\n");
            dataWriter.write("************************************\n");
            dataWriter.flush();
        
        for (int i = 0; i < paths.length; i++){
            File folderPath = new File(paths[i]);
            String[] files = folderPath.list();
            
            if (files == null){
                continue;
            }

            for (int j = 0; j < files.length; j++){
                
                String fileName = files[j];
                
                boolean containsJava = fileName.contains(".java");
                
                if (containsJava = false){
                    continue;
                } else {
                    name = fileName.replace(".java","");
                }
                
                System.out.println("Starting demo " + name + "...");
                System.out.println("Filename: " + fileName);
                
                String address = addressBegin + name;
                
                Browser browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(true).setSlowMo(50));
                BrowserContext context = browser.newContext();
                Page page = context.newPage();
                page.navigate(address);

                System.out.println("Demo started");

                System.out.println("Waiting " + ms + " ms for site to load...");
                Thread.sleep(ms);

                System.out.println("Comparing screenshots for demo " + name + "...");
                // byte[] nowState = page.screenshot(new Page.ScreenshotOptions());
                page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("./tmpScreenshots/screenshot_" + name + ".png")));
                byte[] nowState = Files.readAllBytes(Paths.get("./tmpScreenshots/screenshot_" + name + ".png"));
                byte[] shouldState = Files.readAllBytes(Paths.get("./Screenshots/screenshot_" + name + ".png"));

                StringBuilder sb = new StringBuilder();
                for (byte b : nowState) {
                    sb.append(String.format("%02X", b));
                }
                String byteString = sb.toString();
                
                sb = new StringBuilder();
                for (byte b : shouldState) {
                    sb.append(String.format("%02X", b));
                }
                String byteString2 = sb.toString();

                boolean equals1 = false;
                boolean equals2 = false;

                if (byteString2.contains(byteString)){
                    equals1 = true;
                }

                if (byteString.contains(byteString2)){
                    equals2 = true;
                }

                // The lines above are not the most beautiful code i know but i couldnt get it to work otherwise (yes tried other methods like Array.equals, String comparison doesnt fire either) 
                browser.close();

                boolean equals = java.util.Arrays.equals(nowState, shouldState);

                String status = "Failed";

                if (equals1 == true && equals2 == true){
                    status = "Success";
                } else {
                    status = "Failed";
                }

                if (status == "Success"){
                    System.out.println("****************************************************");
                    System.out.println("***********************SUCCESS**********************");
                    System.out.println("****************************************************");
                } else {
                    System.out.println("****************************************************");
                    System.out.println("***********************FAILED***********************");
                    System.out.println("****************************************************");
                    testSuccessfull = false;
                }

                dataWriter.write("Demo: " + name + " Status: " + status + "\n");
                dataWriter.flush();
                /* 
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
                context = browser.newContext();
                page = context.newPage();
                page.navigate("http://desktop-fp4op26:8888/webapp/ProcessRemover");

                Thread.sleep(1000);
                
                browser.close();
                
                /* Code of the ProcessRemover, you need to deploy this bbjprogram in the dwc first

                sessions! = BBjAPI().getSessionInfos()

                for i = 0 to sessions!.size() -1
                    session! = sessions!.getItem(i)
                    BBjAPI().killSession(session!,"admin","admin123")
                next i

                bye

                    */

            }
        }
        
        if (testSuccessfull = true){
            dataWriter.write("*****Test was successfull*****");
        } else {
            dataWriter.write("*****Test was not successfull*****");
        }
        dataWriter.close();
    }

}
