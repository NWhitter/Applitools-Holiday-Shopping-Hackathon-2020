package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    private final By filterButton = By.id("filterBtn");
    public final By productGrid = By.id("product_grid");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void filterShoesByColour(String colour) {
        String colourFilterLabel = "//label[contains(text(), '%s')]";
        WebElement blackCheckBox = driver.findElement(By.xpath(String.format(colourFilterLabel, colour)));

        if (!blackCheckBox.isSelected()) {
            blackCheckBox.click();
        }

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(filterButton))).click();
    }

    public ProductDetailsPage clickShoe(String shoeName) {
        String shoeHeading = "//h3[contains(text(), '%s')]/../../figure";
        driver.findElement(By.xpath(String.format(shoeHeading, shoeName))).click();

        return new ProductDetailsPage(driver);
    }
}
