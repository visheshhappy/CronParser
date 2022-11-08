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

## This project has three main modules

### Validator
This module validates the cron expression passed by the client. If the expression is invalid, it throws an exception.

### Descriptor
This module describe the various cron expression into valid values.

### Service
This module has the services such as parser which parses the client input, the view service which creates the required 
view and a cron service which acts like a facade.


## Testing
The service can be tested either by using the CLI option provided above.

## Limitations
In Windows passing a wild card expression via CLI reads all the files within that folder and pass it along in the 
command arguments making the cron invalid. To test these scenarios, use the main class `CronJobTester` directly.

