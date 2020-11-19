package tests;

import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.HomePage;
import pages.ProductDetailsPage;
import utils.DriverManager;
import utils.EyesConfig;
import utils.EnvSelector;

import static utils.Config.WINDOW_SIZE;

public class AppliFashionTest {

    private VisualGridRunner runner;
    private Eyes eyes;
    private WebDriver driver;
    private DriverManager driverManager;
    private String urlUnderTest;

    @BeforeClass(alwaysRun = true)
    @Parameters({"browsers", "env"})
    public void setBatch(String browsers, @Optional final String env) {
        EyesConfig eyeConfig = new EyesConfig("Holiday Shopping");
        // Create a runner with 10 concurrency tests
        runner = new VisualGridRunner(10);
        // Create Eyes object with the runner
        eyes = new Eyes(runner);
        eyeConfig.setUp(eyes, browsers);
        // Set the Environment Under Test URL from the TestNG .xml file
        urlUnderTest = EnvSelector.getEnvironment(env);
    }

    /*
    Test main page
     */
    @BeforeMethod(alwaysRun = true)
    public void initDriver() {
        // Setup and launch browser
        driverManager = new DriverManager();
        driver = driverManager.initDriver();
    }

    /*
    Test filtered product grid
     */
    @Test
    void test1MainPageTest() {
        try {
            // Navigate to url under test
            driverManager.openUrl(urlUnderTest);

            // Initialize a test session
            eyes.open(driver, EyesConfig.APP_NAME, "Test 1", new RectangleSize(WINDOW_SIZE.getWidth(), WINDOW_SIZE.getHeight()));

            // Check the whole page
            eyes.checkWindow("main page", true);
            //eyes.check(Target.window().fully().withName("main page"));

            // Call close on eyes so server knows it should display the results
            eyes.closeAsync();
        } finally {
            eyes.abortAsync();
        }
    }

    /*
    Test product details
     */
    @Test
    public void test2FilterProductGridTest() {
        try {
            // Navigate to url under test
            driverManager.openUrl(urlUnderTest);

            HomePage homePage = new HomePage(driver);
            homePage.filterShoesByColour("Black");

            // Initialize a test session
            eyes.open(driver, EyesConfig.APP_NAME, "Test 2", new RectangleSize(WINDOW_SIZE.getWidth(), WINDOW_SIZE.getHeight()));

            // Check a page element
            eyes.checkRegion(homePage.productGrid, "filter by color", true);
            //eyes.check(Target.region(homePage.productGrid).fully().withName("filter by color"));

            // Call close on eyes so server knows it should display the results
            eyes.closeAsync();
        } finally {
            eyes.abortAsync();
        }
    }

    @Test
    public void test3ProductDetailsTest() {
        try {
            // Navigate to url under test
            driverManager.openUrl(urlUnderTest);

            // Click on shoe
            HomePage homePage = new HomePage(driver);
            homePage.clickShoe("Appli Air x Night");

            // Initialize a test session
            eyes.open(driver, EyesConfig.APP_NAME, "Test 3", new RectangleSize(WINDOW_SIZE.getWidth(), WINDOW_SIZE.getHeight()));

            // Check the whole page
            eyes.checkWindow("product details", true);
            // eyes.check(Target.window().fully().withName("product details"));

            // Call close on eyes so server knows it should display the results
            eyes.closeAsync();
        } finally {
            eyes.abortAsync();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver() {
        // Close the browser
        driver.quit();
    }

    @AfterClass
    private void printTestResults() {
        // We pass false to this method to suppress the exception that is thrown if we find visual differences
        TestResultsSummary allTestResults = runner.getAllTestResults(false);
        System.out.println(allTestResults);
    }
}
