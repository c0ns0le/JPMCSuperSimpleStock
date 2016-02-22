

**1. Solution**

**Design**

Simple stock application is implemented with Java 8 spring 4, Maven 3,
Junit 4 and Log4j. Spring will take care of dependency injection and it
will make the singleton object of repository objects and Service object.
Implemented test cases in junit to design the application with TDD
approach. Maven to build the application and automate the test cases.
Integrated the Log4j framework for the complete logging of the
application.

Designed the application with 3 different layers, Model with constants,
Business and Repository. The Layered approach is to maintain the code as
loosely coupled and to achieve the service oriented architecture. The
repository instance can be implemented as a different way of retrieving
data from different back end system,

All the business logic for the calculations of stocks are implemented
in **SimpleStockServiceImpl.java**. The**StockRepositoryImpl.java** is
implemented as a repository object. For the current implementation the
data are hardcoded inside the StockRepositoryImpl.java as provided in
the Test Doc. **SimpleStockAppConfiguration.java** has the configuration
of generating the SimpleStockService and stockRepository instance. By
default the spring will generate the instance as singleton, the spring
container will take care of generating singleton
object. **SimpleStockServicesTest.java** has all the test cases. The
SimpleStockService and stockRepository instance are injected using auto
wired annotation. Also this Junit test case has the support of
SpringJUnit4ClassRunner. So we can easily make use of spring in this
test case class.

**Application Layer structure**

![](https://github.com/sureshrajasun/JPMCSuperSimpleStock/blob/master/SuperSimpleStockArch.png)

**Unit Test Cases - TDD**

To test the code of the technical test, it has been developed with Test
Driven approach provided by Junit frame work combined with maven, coded
with junit test cases for each of the requirement.

**Try it yourself**

The code for the application is built using Eclipse IDE with an embedded
version of Maven 3. To compile the code, download the folder
**JPMCSuperSimpleStock** and import the project into Eclipse IDE as a
maven project.

Alternatively, Import the application into a folder and run the below
command. Provided the maven is installed and the maven repositories
completely configured in the system.

$&gt; mvn clean install

This will compile the code and will start execute the unit test cases.
