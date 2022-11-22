package modules;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseModule {

    protected static final WebDriver driver = new ChromeDriver();
    private static final int UI_WAIT_TIMEOUT_IN_SECONDS = 3;

    public void goToUrl(String url){
        driver.get(url);
        driver.manage().window().maximize();
    }

    public void quitDriver(){
        driver.quit();
    }

    protected void click(By by){
        WebElement element = driver.findElement(by);
        scrollToElement(element);
        waitForVisibilityOfElement(by);
        element.click();
    }

    protected String getText(By by){
        WebElement element = driver.findElement(by);
        scrollToElement(element);
        waitForVisibilityOfElement(by);
        return element.getText();
    }

    protected String getText(WebElement webElement){
        scrollToElement(webElement);
        waitForVisibilityOfElement(webElement);
        return webElement.getText();
    }

    protected void scrollToElement(WebElement webElement){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    protected void waitForVisibilityOfElement(By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(UI_WAIT_TIMEOUT_IN_SECONDS));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(by.toString())));
    }

    protected void waitForVisibilityOfElement(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(UI_WAIT_TIMEOUT_IN_SECONDS));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }
}
