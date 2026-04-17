package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.InterruptedIOException;
import java.time.Duration;
import java.util.List;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     @Test
     public void testCase01() throws InterruptedException{
        WebElement nameInputBox = driver.findElement(By.xpath("//div[contains(@class,'k3kHxc')]//input"));
        System.out.println("wait 1");
        Thread.sleep(3000);
        Wrappers.enterText(nameInputBox, "Crio Learner");
        WebElement practicingAutomationTextArea = driver.findElement(By.xpath("//textarea[contains(@class, 'tL9Q4c')]"));
        String practicingAutomationText = "I want to be the best QA Engineer!";
        String epochTimeString = Wrappers.getEpochTimeAsString();
        System.out.println("wait 2");
        Wrappers.enterText(practicingAutomationTextArea, practicingAutomationText + " " + epochTimeString);

        System.out.println("wait 3");
        Wrappers.radioButton(driver, "6-10");
               Thread.sleep(3000);
        System.out.println("wait 4");
        
        Wrappers.checkBox(driver, "Java");
        Wrappers.checkBox(driver, "Selenium");
        Wrappers.checkBox(driver, "TestNG");
        WebElement dropDoWebElement = driver.findElement(By.xpath("//div[contains(@class,'DEh1R')]"));
        System.out.println("wait 5");
        Thread.sleep(3000);
        Wrappers.clickOnElement(driver, dropDoWebElement);
        List<WebElement> dropDownList = driver.findElements(By.xpath("//div[contains(@class,'ncFHed')]//span[not(contains(text(),'Choose'))]"));
        Wrappers.dropDownClickByLoop(dropDownList, "Mr"); 

        WebElement dateInputBox = driver.findElement(By.xpath("//div[contains(@class,'A6uyJd')]//input"));
        String sevenDaysAgoDate=Wrappers.getdateSevenDaysAgo(7);
        Thread.sleep(3000);
        System.out.println("wait 6");
        Wrappers.enterText(dateInputBox, sevenDaysAgoDate);

        WebElement hourElement = driver.findElement(By.xpath("//input[@aria-label='Hour']"));
        WebElement minElement = driver.findElement(By.xpath("//input[@aria-label='Minute']"));
        WebElement submitBtn=driver.findElement(By.xpath("//div[@class='lRwqcd']/div"));

        Wrappers.enterText(hourElement, "07");
        Wrappers.enterText(minElement, "30");
        Wrappers.clickOnElement(driver, submitBtn);

        Thread.sleep(3000);
        System.out.println("wait 7");
       // WebElement successMsgElement = driver.findElement(By.xpath("//div[contains(@class,'vHW')]"));

      //  System.out.println(successMsgElement.getText());
     }
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://forms.gle/wjPkzeSEk1CM7KgGA");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}