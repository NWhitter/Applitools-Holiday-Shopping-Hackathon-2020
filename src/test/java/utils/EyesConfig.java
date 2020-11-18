package utils;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.visualgrid.model.*;

import static utils.Config.*;
import static utils.Config.WINDOW_SIZE;

public class EyesConfig {

    private static final String API_KEY = "u39ThTsWwbNpYYwSW4LgYkg41I4rCTp8YhGUF45JAfU110";
    public static final String APP_NAME = "AppliFashion";
    private final String batchName;

    public EyesConfig(String batchName) {
        this.batchName = batchName;
    }

    public void setUp(Eyes eyes, String browsers) {

        // Initialise eyes configuration
        Configuration config = new Configuration();

        // Set Applitool's API key from dashboard
        config.setApiKey(API_KEY);

        // Create and set new batch info instance to the configuration
        config.setBatch(new BatchInfo(batchName));

        // Add browsers based on TestNG .xml file
        config.addBrowsers(setEyeBrowsers(browsers));

        // Set the configuration object to Eyes
        eyes.setConfiguration(config);
    }

    // Add browsers based on TestNG .xml file
    public IRenderingBrowserInfo[] setEyeBrowsers(String browsers) {
        int windowHeight = WINDOW_SIZE.getHeight();
        int windowWidth = WINDOW_SIZE.getWidth();

        IRenderingBrowserInfo[] browserList;
        BrowserProvider selectedBrowsers = BrowserProvider.fromString(browsers);

        browserList = switch (selectedBrowsers) {
            case ONLY_CHROME -> new IRenderingBrowserInfo[]{
                    // Add chrome browser
                    new DesktopBrowserInfo(windowWidth, windowHeight, BrowserType.CHROME)
            };
            case ALL_BROWSERS -> new IRenderingBrowserInfo[]{
                    // Add multiple browsers with same viewport
                    new DesktopBrowserInfo(windowWidth, windowHeight, BrowserType.CHROME),
                    new DesktopBrowserInfo(windowWidth, windowHeight, BrowserType.FIREFOX),
                    new DesktopBrowserInfo(windowWidth, windowHeight, BrowserType.EDGE_CHROMIUM),
                    new DesktopBrowserInfo(windowWidth, windowHeight, BrowserType.SAFARI),
                    // Add mobile emulated iPhone in Portrait mode
                    new IosDeviceInfo(IosDeviceName.iPhone_X, ScreenOrientation.PORTRAIT)
            };
        };
        // Return browsers
        return browserList;
    }
}
