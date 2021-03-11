# LongestCommonStringWebApp

Dependencies:
Maven 3.6.3
javax.servlet-api 3.0.1
json-20201115
Tomcat 7 maven plugin 2.2

Test tools:
junit 3.8.1
mockito 3.8.0

DIRECTIONS TO RUN:
At top level of project(should see request.json file) enter command:

mvn tomcat7:run

Let servlet begin running(should see 'Starting ProtocolHandler')

In another terminal, at the same directory level,
enter the following into the commandline:

curl -d @request.json -H "Content-Type: application/json" -X POST  http://localhost:8080/LongestCommonStringApp/LCS

You can observe the results of the LCS in json format in the response on the console.
The Unit Tests can be run with the command:

mvn clean test