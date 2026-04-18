package demo.wrappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {

    public static WebElement waitForElement(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        // Ensure element is in view to prevent Intercepted Click errors
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }

    public static void enterText(WebDriver driver, By locator, String text) {
        try {
            WebElement element = waitForElement(driver, locator);
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            System.out.println("Error entering text: " + e.getMessage());
        }
    }

    public static void radioButton(WebDriver driver, String radioButtonText) {
        try {
            By locator = By.xpath("//span[contains(@class, 'OvPDhc') and contains(text(),'" 
                    + radioButtonText + "')]/../../..//div[@class = 'vd3tt']");
            waitForElement(driver, locator).click();
        } catch (Exception e) {
            System.out.println("Error clicking radio button: " + e.getMessage());
        }
    }

    public static void checkBox(WebDriver driver, String checkboxText) {
        try {
            By locator = By.xpath("//span[contains(@class, 'n5vBHf') and contains(text(),'" 
                    + checkboxText + "')]/../../preceding-sibling::div[contains(@id, 'i')]");
            waitForElement(driver, locator).click();
        } catch (Exception e) {
            System.out.println("Error clicking checkbox: " + e.getMessage());
        }
    }

    public static void clickOnElement(WebDriver driver, By locator) {
        try {
            waitForElement(driver, locator).click();
        } catch (Exception e) {
            // Fallback for intercepted clicks
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public static void dropDownClickByLoop(List<WebElement> elements, String dropDownText) {
        for (WebElement element : elements) {
            if (element.getText().trim().equals(dropDownText)) {
                element.click();
                break;
            }
        }
    }

    public static String getdateSevenDaysAgo(int days) {
        // Note: Google Forms date input often expects ddMMyyyy format depending on locale
        return LocalDate.now().minusDays(days).format(DateTimeFormatter.ofPattern("ddMMyyyy"));
    }

    public static String getEpochTimeAsString() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }
}