# Logging Tutorial

* log4j log levels are: Trace < Debug < Info < Warn < Error < Fatal
* A good logging should provides us with the exact answers for *WHO, WHAT, WHEN* questions. Otherwise it's simple useless logging. You can learn about this on [DZONE](https://dzone.com/articles/application-logging-what-when) and [OWASP](https://www.owasp.org/index.php/Logging_Cheat_Sheet) websites.

# Steps

0. The log4j setting file is located inside *src/main/java* and is named [log4j2.xml](https://github.com/mirsaeedi/MockingDependencies/blob/logging/MockingDependencies/src/main/java/log4j2.xml) by convention. You can learn about file naming convention on [log4j documents](https://logging.apache.org/log4j/2.x/manual/configuration.html). In this hands-on, we are going to improve the logging inside the [CoreService class](https://github.com/mirsaeedi/MockingDependencies/blob/logging/MockingDependencies/src/main/java/tutorial/core/banking/CoreService.java).

1. Modify the log4j2.xml and set the Root Logger to use the defined ConsoleAppender. So, you can see the logs inside the console. Then, run the main method inside tutorial.core.banking package to see the logs.

2. In real-world scenarios, we need a way more information to make our logs helpful. You should enrich the logs by adding additional fields to the PatternLayout of ConsoleAppender. So, we can diagnose our application more easily. Add the following fields to the pattern layout of ConsoleAppender. You can get help from [log4j documents](https://logging.apache.org/log4j/2.x/manual/layouts.html) for finding relevant pattern codes.
  - Log Level
  - Date
  - Method Name
  - Class Name
  - Line Number
  - Thread Name 
  - Thread Id
  - Process Id

3. A good Logging practice should help us to answer What, When, Who questions. So, usually, we need to enrich our logs with some information about the context of the request/operation. For example, in web applications, we need to know the username, IP address, requested URL and session id in order to diagnose the problem. 
In our sample application, we have an HTTP context filled with fake information. You should implement the [*IntializeContextualLogging*](https://github.com/mirsaeedi/MockingDependencies/blob/logging/MockingDependencies/src/main/java/tutorial/core/banking/Main.java) method by using the *HttpContext* class and *Thread Context Map* (key/value) to provide log4j with contextual data. So, the contextual data will be logged alongside the message and other parameters. You can get help from [log4j documents](https://logging.apache.org/log4j/2.x/manual/thread-context.html) and [here](https://howtodoinjava.com/log4j2/threadcontext-fish-tagging/).

4. Change the appender to RollingFileAppender. Run the program again and take a look at the created file. Then, run the program once more. Take a look at the log folder again. Make sure you know what are the used policies. You can read about them [here](https://logging.apache.org/log4j/2.x/manual/appenders.html#RollingFileAppender).  
