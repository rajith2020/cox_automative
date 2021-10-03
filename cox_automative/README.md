# Finra Test
## Install Java
1. Download Java SDK 1.8 
        https://www.oracle.com/java/technologies/javase-jdk8-downloads.html
2. Install Java 
3. Set environment variable with JAVA_HOME with jdk Location and Add also JAVA_HOME/bin to the $PATH

## How to Run Testcases
 
        1.Install Maven https://maven.apache.org/download.cgi
        2. set MAVEN_HOME and add MAVEN_HOME to the $PATH
        3. check if maven is installed properly mvn -version
        4. clone the code using git clone https://github.com/HarinAutomation/FinraTest.git
        5. navigate to the FinraTest folder and make sure you see pom.xml file  (ls -al pom.xml )
        6. run "mvn test" via command prompt
  
        
## Implementation of Testcases 

        We are utilizing the API "https://api.coxauto-interview.com/api" and created testcases 
        for the following Operations:

     Test case 1 : Generate a new datasetid
     Test case 2 : Generate a new invalid datasetid
     Test case 3 : Generate a new vechicle with datasetid
     Test case 4 : Generate a new vechicle without datasetid error message
     Test case 5 : Print  a new vechicles with details  
     Test case 6 : 404 when you try with invalid vechicleid 


## Results:

![image](https://user-images.githubusercontent.com/71590616/135758554-3902ce11-efb2-42da-b28f-9445cbb83056.png)


