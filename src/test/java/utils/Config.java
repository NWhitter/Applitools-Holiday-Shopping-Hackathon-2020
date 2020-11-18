package utils;


import exceptions.InvalidBrowserProviderException;
import org.openqa.selenium.Dimension;

public class Config {
    public static final Dimension WINDOW_SIZE = new Dimension(1200, 800);

    public enum BrowserProvider {
        ALL_BROWSERS,
        ONLY_CHROME;

        public static BrowserProvider fromString(String browser) {
            for (BrowserProvider selection : BrowserProvider.values()) {
                if (selection.toString().equals(browser)) {
                    return selection;
                }
            }
            throw new InvalidBrowserProviderException("Invalid browser provider", browser);
        }
    }
}
