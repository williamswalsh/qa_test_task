
### Objective:

Create a test strategy for this e-commerce application: https://www.saucedemo.com/  

### Setup:
- Java 17
- Set PATH/JAVA_HOME with java version - sdkman does this automatically

### Selenium Setup:
- Need a driver executable to interact with Browser.
- Need a driver manager to get the correct driver executable. 
  (Can manually do this by setting webdriver.chrome.driver) System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/chromedriver-mac-arm64/chromedriver");
- Use the property to avoid constantly downloading the driver

### Test Execution:

1) Check JDK version to execute tests: `java -version`
2) Set the JDK version to 17 by using SDKman: `sdk use java 17.0.6-amzn`
3) Execute the command:  `mvn clean test`


### Features to cover: 
- log in - partially done 
- log out - tested basic login-logout sequence
- search for products
- filtering
- product listing page
- product details page
- cart
- checkout

Create the test plan for the feature under test
Choose a framework from your preference and explain why  

**NB:** We use Python as the programming language 

most important scenarios
Report bugs with severity and priorities
Add the instructions to install and how to run the tests

README.MD
EXERCISE-1.MD
TEST-STRATEGY
TEST-PLANS
DECISIONS AND REASONS
BUGREPORT-1.MD ????
