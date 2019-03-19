# Logging Tutorial

* log4j log levels are: Trace < Debug < Info < Warn < Error < Fatal
* A good logging should provides us with the exact answers for *WHO, WHAT, WHEN* questions. Otherwise it's simple useless logging. You can learn about this on [DZONE](https://dzone.com/articles/application-logging-what-when) and [OWASP](https://www.owasp.org/index.php/Logging_Cheat_Sheet) websites.

# Steps

**TASK 1**: The log4j setting file is located inside *src/main/java* and is named [log4j2.xml](https://github.com/mirsaeedi/SoftwareTestingTurorials/tree/logging/src/main/java/log4j2.xml) by convention. You can learn about file naming convention on [log4j documents](https://logging.apache.org/log4j/2.x/manual/configuration.html). In this hands-on, we are going to improve the logging inside the [CoreService class](https://github.com/mirsaeedi/SoftwareTestingTurorials/blob/logging/src/main/java/tutorial/core/banking/services/CoreService.java).

**TASK 2**: We want to see the logs on Console.So, Modify log4j2.xml and set the Root Logger to use ConsoleAppender.

**TASK 3**: In real-world scenarios, we need a way more information to make our logs helpful. You should enrich the logs by adding additional fields to the PatternLayout of ConsoleAppender. So, we should be able to answer following questions.

* At what time has the event occured?
* In which method/class has the event occured?
* In which line has the event occured?
* In which thread/process the event occured?

So, we can diagnose our application more easily. Add the following fields to the pattern layout of ConsoleAppender. You can get help from [log4j documents](https://logging.apache.org/log4j/2.x/manual/layouts.html#PatternLayout) for finding relevant pattern codes.
  - Log Level %p
  - Date %d{HH:mm:ss,SSS}
  - Method Name %M (it's slow)
  - Class Name %C (it's slow)
  - Line Number %L (it's slow)
  - Thread Name %t
  - Thread Id %tid
  - Process Id %pid

**TASK 4**: In web applications, we need to have some information about the user (username,sessionsId,IP,URL) to be able to deal with problems in production environment. Use [*Thread Context Map* (key/value)](https://logging.apache.org/log4j/2.x/manual/thread-context.html) to provide log4j with contextual data. So, the contextual data will be logged alongside the message and other parameters. You can get help from [here](https://howtodoinjava.com/log4j2/threadcontext-fish-tagging/).

**TASK 5**: make the program to use both Consoler and RollingFileAppender at the same time. Make sure you know what are the used policies. You can read about them [here](https://logging.apache.org/log4j/2.x/manual/appenders.html#RollingFileAppender).  
