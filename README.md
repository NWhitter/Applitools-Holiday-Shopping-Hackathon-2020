# Applitools Holiday Shopping Hackathon 2020

## Prerequisites

- **Programming Language:** Java 15
- **Build Tool:** Maven 3.6.3 
- **Test Runner:** TestNG 7.1.0
- **Other Dependencies:**
    - Selenium 3.141.59
    - WebDriverManager 4.2.2
    - Applitools Eyes 3.182.0
    
## Instructions
### Execute Tests on UltraFast Grid
1. Download repo using ```git clone```
2. Navigate to project folder
3. Add Applitools API key to the 'EyesConfig' class
4. Run one of the commands below:
  - Run Part 1: ```test -Dsurefire.suiteXmlFiles=AppliFashion-V1-Production.xml```
  - Run Part 2: ```test -Dsurefire.suiteXmlFiles=AppliFashion-Dev.xml```
  - Run Part 3: ```test -Dsurefire.suiteXmlFiles=AppliFashion-Final-Production.xml```
