# CRON PARSER

### Description of Project
This project read a cron command and displays the human read-able output format.

## Steps to set up project and run
1. Checkout the project from git
2. Run ```mvn clean install``` to build the project
3. Run ```mvn clean package``` to build a jar file.
4. Go to the target drectory where the jar file is build.
5. Run the following command to execute

   ```java -cp CronJobParser-1.0-SNAPSHOT.jar org.vishesh.cronjob.main.CronJobTester */15 0 1,15 1 1-5 /usr/bin/find```
