package pages;

import framework.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class BasePage {
    protected Wait<WebDriver> wait;

    public BasePage() {
        PageFactory.initElements(Driver.getInstance().getDriver(), this);
    }

    public boolean waitForControl(WebElement webControl) {
        wait = getWait(120);
        return wait.until(webDriver -> webControl != null && webControl.isEnabled() && webControl.isDisplayed());
    }

    public void waitElementToClick(WebElement webElement){
        waitForControl(webElement);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void waitForPageLoad() {
        new WebDriverWait(Driver.getInstance().getDriver(), 20).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    private FluentWait getWait(int timeout) {
        return new FluentWait<>(Driver.getInstance().getDriver())
                .withTimeout(timeout, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
    }

    public void waitForAlert(int sec){
        WebDriverWait wait = new WebDriverWait(Driver.getInstance().getDriver(), sec,  100);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public String getPageTitle(){
        String title = Driver.getInstance().getDriver().getTitle();
        return title;
    }

    public void moveToElement(WebElement element) {
        Actions actions = new Actions(Driver.getInstance().getDriver());
        actions.moveToElement(element);
        actions.release().perform();
    }
}
