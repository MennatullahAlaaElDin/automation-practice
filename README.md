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
* Fluent design (method chaining)
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
1. Only with intelliJ IDE:
	* Latest version of [intelliJ Community](https://www.jetbrains.com/idea/download/#section=windows)
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
1. For UI Test Automation Scripts : `Captcha` will be out of scope test automation and need to ask the developers to disable it on test environment.
2. Foe UI and API Manual Test Cases: Please visit [here](https://drive.google.com/drive/folders/1FGuPmZ_7bsGpGO__oDPqopwoElzk7ZwB?usp=sharing).

***
| | | |
|-|-|-|
|Test [ID - Name]|Steps|Expected Result|
|01.01 Check the response when return all items|1- use method GET: http://localhost:3030/categories 2- Check status code|Status code is 200|
| |3- check default data limit|10|
| |4- check default skip|0|
| |5- check response schema|data is retrieved successfully and JSON response schema is valid|
| | | |
|01.02 Check the limit output (pagination) with GET method|1- use method GET: http://localhost:3030/categories?$limit={{limit}} 2- Check status code|Status code is 200|
| |3- Case A: check the response when limit value is between  0 < {{limit}} <= 25 |the number of "data" item should equal limit value length of "data" = {{limit}}|
| |4- Case B: check the response when limit value is zero, {{limit}} = 0 |data item should be empty "data"=[]|
| |5- Case C: check the response when limit value is {{limit}} > 25 |the items in "data" itemis limited to 25 items only length of "data" = 25|
| |6- Case D: check the response when limit value is negative, ex: {{limit}} = -50 |negative value of limit is translated into positive|
| |7- check response schema|data is retrieved successfully and JSON response schema is valid|
| | | |
|01.03 Check the skip output with GET method|1- use method GET: http://localhost:3030/categories?$skip={{skip}} 2- Check status code|Status code is 200|
| |3- check the response when : Case A: skip value is {{skip}} < total number of items && {{limit}} != 0 && {{limit}} < total - {{skip}} ex : total : 4311 , limit = 10 (default), skip = 5|the first 5 {{skip}} records have been skipped and the number of "data" item should equal limit value length of "data" = {{limit}}|
| |4- check the response when : Case B: skip value is {{skip}} < total number of items && {{limit}} != 0 && {{limit}} = total - {{skip}} ex : total : 4310 , limit = 10 (default), skip = 4300|the first 4300 {{skip}} records have been skipped and the number of "data" item should equal limit value  length of "data" = {{limit}}|
| |5- Case C: check the response when : skip value is {{skip}} < total number of items && {{limit}} != 0 && {{limit}} > total - {{skip}} ex : total : 4311 , limit = 10 (default), skip = 4310|the first 4310 {{skip}} records have been skipped and the number of "data" item should equal : length of "data" = total - {{skip}} = 1|
| |6- Case D: check the response when : skip value is {{skip}} >= total number of items ex : total : 4311 , skip = 4312|data item should be empty "data"=[]|
| |7- Case E: check the response when skip value is negative, ex: {{skip}} = -50 |negative value of skip is translated into positive|
| |8- check response schema|data is retrieved successfully and JSON response schema is valid|
| | | |
|01.04 Check error handling with GET method|1- use method GET: http://localhost:3030/categories?$limit={{limit}}&$skip={{skip}} Case A :  set {{limit}} or {{skip}} to any string value  ex: " " Then check the response|internal server error is retrieved: {     "name": "GeneralError",     "message": "SQLITE_ERROR: no such column: NaN",     "code": 500,     "className": "general-error",     "data": {},     "errors": {} }|
| |2- use method GET:  http://localhost:3030/categories?$limit=&$skip= Case B :  leave {{limit}} or {{skip}} empty  Then check the response|internal server error is retrieved: {     "name": "GeneralError",     "message": "SQLITE_ERROR: no such column: NaN",     "code": 500,     "className": "general-error",     "data": {},     "errors": {} }|

