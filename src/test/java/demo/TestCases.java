package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void startBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://forms.gle/wjPkzeSEk1CM7KgGA");
    }

    @Test
    public void testCase01() {
        // Step 1: Enter Name
        Wrappers.enterText(driver, By.xpath("//div[contains(@class,'k3kHxc')]//input"), "Crio Learner");

        // Step 2: Enter Practice Statement + Epoch Time
        String epoch = Wrappers.getEpochTimeAsString();
        Wrappers.enterText(driver, By.xpath("//textarea[contains(@class, 'tL9Q4c')]"), 
                "I want to be the best QA Engineer! " + epoch);

        // Step 3: Radio Button
        Wrappers.radioButton(driver, "6 - 10");

        // Step 4: Checkboxes
        Wrappers.checkBox(driver, "Java");
        Wrappers.checkBox(driver, "Selenium");
        Wrappers.checkBox(driver, "TestNG");

        // Step 5: Dropdown selection
        // First click to open dropdown
        Wrappers.clickOnElement(driver, By.xpath("//div[contains(@class,'DEh1R')]"));
        
        // Wait for options and select "Mr"
        By optionsLocator = By.xpath("//div[contains(@class,'ncFHed')]//span[not(contains(text(),'Choose'))]");
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(optionsLocator));
        Wrappers.dropDownClickByLoop(options, "Mr");

        // Step 6: Date (7 days ago)
        Wrappers.enterText(driver, By.xpath("//input[@type='date']"), Wrappers.getdateSevenDaysAgo(7));

        // Step 7: Time
        Wrappers.enterText(driver, By.xpath("//input[@aria-label='Hour']"), "07");
        Wrappers.enterText(driver, By.xpath("//input[@aria-label='Minute']"), "30");

        // Step 8: Submit
        Wrappers.clickOnElement(driver, By.xpath("//div[@class='lRwqcd']/div"));

        // Step 9: Verify Success
        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'vHW')]")));
        System.out.println("Form Submission Response: " + successMsg.getText());
    }

    @AfterTest
    public void endTest() {
        if (driver != null) {
            driver.quit();
        }
    }
}