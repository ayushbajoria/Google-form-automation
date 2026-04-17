package demo.wrappers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    public static void enterText ( WebElement element, String text)
    {
        try{
            element.clear();
            element.sendKeys((text));
        }catch(Exception e){
            e.printStackTrace();
        }
        }

    public static void enterText ( WebDriver driver, By locator, String text)
    {
        try{
            WebElement element = driver.findElement(locator);
            element.clear();
            element.sendKeys((text));
        }catch(Exception e){
            e.printStackTrace();
        }
        }

        public static void radioButton( ChromeDriver driver, String radioButtonText)
    {
        try{
           WebElement element = driver.findElement(By.xpath("//span[contains(@class, '0vPDhc') and contains(text(),'" +
             radioButtonText + "')]/../../..//div[@class = 'vd3tt']" ));
            element.click();
        }catch(Exception e){
            e.printStackTrace();
        }
        }

         public static void checkBox( ChromeDriver driver, String checkboxText)
    {
        try{
           WebElement element =driver.findElement(By.xpath("//span[contains(@class, 'n5vBHf') and contains(text(),'" +
             checkboxText + "')]/../../..preceding-sibling::div[contains(@id, 'i')]" ));
            element.click();
        }catch(Exception e){
            e.printStackTrace();
        }
        }

        public static void dropDownClick( ChromeDriver driver, String dropDownText)
    {
        try{
           WebElement element = driver.findElement(By.xpath("//div[contains(@class, 'QXL7Te')]//span[text()='"+dropDownText+"']" ));
            element.click();
        }catch(Exception e){
            e.printStackTrace();
        }
        }

         public static void dropDownClickByLoop(List<WebElement> elements, String dropDownText)
    {
        try
        {
            for(WebElement element:elements)
                {
                if(element.getText().equals(dropDownText))
                {
                    element.click();
                    break;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        }

        public static void clickOnElement(ChromeDriver driver, WebElement element)
        {
            try{
                JavascriptExecutor js =(JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();",element);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        public static String getdateSevenDaysAgo(int days){
            LocalDate currentDate = LocalDate.now();
            LocalDate dateMinus7Days = currentDate.minusDays(days);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");
            String formattedDate = dateMinus7Days.format(formatter);
            return formattedDate;
        }

        public static String getEpochTimeAsString(){
            long epochTime = System.currentTimeMillis()/1000;
            String epochTimeString = String.valueOf(epochTime);
            return epochTimeString;
        }

        public static boolean handleAlert(ChromeDriver driver)
        {
            boolean status = false;
            driver.switchTo().alert().dismiss();
            status = true;
            
            return status;
        }

        

        
    }

