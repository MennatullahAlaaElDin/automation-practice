# automation-practice
Tests are using the best practice coding standards, design patterns for maintainability.
### This Test Automation Project Uses: 
* Open source Test Automation Engine [SHAFT-Engine](https://github.com/MohabMohie/SHAFT_ENGINE) which is impilicitly built of :

	* Java Language.
	* Selenium Webdriver.
	* RestAssured.
	* TestNG.
	* Allure Reports.
	* Extent Reports.
	* Maven build management tool `and much more..`
 
### Project Design:
* GUI/API Object Model design pattern.
* Fluent design `method chaining`.
* Data driven design , eg: external `JSON` file.
* Keyword driven design.
 

### Explanation of the setup:
#### With Eclipse IDE:

1. [JDK-17](https://www.oracle.com/java/technologies/downloads/#jdk17-windows)
2. Latest version of [Eclipse](http://www.eclipse.org/downloads/eclipse-packages/)
3. Latest version of [TestNG](https://testng.org/doc/download.html)
4. Clone the project then open it with IDE and wait until all maven dependencies are download from `pom.xml` file.
5. Configure JDK version to version 17 through: 
	+ Right Click on the project > Build path > Configure build path
	
#### With intelliJ IDE:
1. Latest version of [intelliJ Community](https://www.jetbrains.com/idea/download/#section=windows)
2. Clone the project then open it with IDE and wait until all maven dependencies are download from `pom.xml` file.
3. Add Listeners as following:
	- Run/Debug Configurations > Edit Configurations... > Templates > TestNG > Configuration > Listeners > Add all SHAFT_Engine Listeners [AlterSuite, InvokedMethod, Suite] 
		###### for more info please visit [here](https://github.com/MohabMohie/using_SHAFT_ENGINE)
	
### Additional Setup Before Executing API Tests:

Make sure you have [NodeJS](https://nodejs.org/) installed (we require version 4 or newer).

```bash
git clone https://github.com/bestbuy/api-playground/
cd api-playground
npm install
npm start
# Best Buy API Playground started at http://localhost:3030
```

Now open http://localhost:3030 in your browser to begin exploring the API. From there we'll guide you on using tools such as Swagger and Postman to get meaningful experience interacting with APIs.
###### for more info please visit [here](https://github.com/BestBuy/api-playground)
	
### Getting Started with Running the Test Cases:
* All needed configuration are placed in `properties` folder, can be found in `src\main\resources`.
* Test cases are in  the `src/test/java` folder mainly in the `phpTravelsUITests` and `apiPlaygroundTests` packages.
* Test suites for all test cases are placed in the `src/test/resources/testSuits` folder mainly in the `phpTravels.xml` and `apiPlayground.xml` files.
* To start the execution right click on the test suite xml file and click Run.
* After execution, `Allure Report` will be easily generated and opened on the default browser on your machine, Also you can find the `Extent Report` HTML file generated.

### Notes:
1. For UI Test Automation Scripts : `Captcha` will be out of test automation scope and need to ask the developers to disable it on test environment.
2. For UI and API Manual Test Cases: Please visit [Automation Practice Documents](https://drive.google.com/drive/folders/1FGuPmZ_7bsGpGO__oDPqopwoElzk7ZwB?usp=sharing).

***
