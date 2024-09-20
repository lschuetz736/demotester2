# WebForJDemoTester

## What does this project do?

This is a tool to test the demos for WebForJ. It navigates into the project which contains the demo files and automatically starts each demo by opening it in the Browser. It then either takes a screenshot of every site when given the argument "takeScreenshots" or compares every saved screenshot to the site and declares wether it was successfull (if the screenshot matches the site) or not (when it does not) and saves the results in the data.txt when given the argument "compareScreenshots".  

### Getting started

1. Make sure the "webforj-docs-example"-project is running in its docker container 
2. Download this project and place it at any location of your folder system
3. Open a terminal and navigate to the project 
4. After that the following commands need to be executed in this particular order:
    - mvn install
    - mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install --with-deps"
5. Start the program with this command: mvn compile exec:java -D exec.mainClass="src.java.tester.DemoTester" -D exec.args="your arguments"<br>

## Arguments
    Arguments syntax: command path address time
    Command: "takeScreenshots" or "compareScreenshots"
    Path: The path of the project which contains the demos
    Address: The address (HTTP) of the main site, for example: "http://localhost:8888/webapp/controlsamples/". 
    Time: Time in ms the program has to wait between opening a site and taking/comparing a screenshot (between 1000 or 99999 ms)

    Address and/or Time can be set to "default" which sets the address to "http://localhost:8888/webapp/controlsamples/" and time to 3000 ms 

    Example: mvn compile -e exec:java -D exec.mainClass="src.java.tester.DemoTester" -D exec.args="takeScreenshots C:\Projects\webforj-docs-samples default 5000"

## Further useful information

    This program was designed to run in the GitHub ubuntu runner for automatically testing the WebForJ demos every time someone pushes to the webforj-docs-samples project which you can find here: https://github.com/webforj/webforj-docs-samples. A yml was created in a forked version which you can find here: https://github.com/lschuetz736/webforj-docs-samples/tree/master. However you can also run this program on your machine to take or compare screenshots but make sure that the webforj-docs-samples project is running in its docker container, it is not recommended to run the project without its docker container if you run it locally.

