package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LoginPage extends BasePage {
    @FindBy(css = "[type = 'email']")
    private WebElement emailField;
    @FindBy(xpath = "//button[text() = 'Forgot email?']")
    private WebElement forgotEmailButton;
    @FindBy(xpath = "//span[text() = \"Find your email\"]")
    private WebElement forgotEmailPageLabel;
    @FindBy(xpath = "//span[text() = 'Create account']")
    private WebElement createAccountButton;
    @FindBy(css = "[aria-label='Create account'] li")
    private List<WebElement> createAccountOption;
    @FindBy(css = "[data-primary-action-label = 'Next'] button")
    private WebElement btnNext;
    @FindBy(css = "[data-profile-identifier]")
    private WebElement accountName;
    @FindBy(css = "[type = 'password']")
    private WebElement passwordField;
    @FindBy(xpath = "//div[text() = 'Show password']")
    private WebElement showPasswordCheckbox;
    @FindBy(css = "#forgotPassword button")
    private WebElement forgotPasswordButton;
    // had to rely on xpath selectors using text as there were no other reliable element tags/attributes
    @FindBy(xpath = "//span[text() = \"Account recovery\"]")
    private WebElement forgotPasswordPageLabel;
    @FindBy(xpath = "//*[text() = \"Enter an email or phone number\"]")
    private WebElement emptyEmailFieldWarningMessage;
    @FindBy(xpath = "//div[text() = \"Couldn’t find your Google Account\"]")
    private WebElement nonExistingAccountWarningMessage;
    @FindBy(xpath = "//span[text() = \"Wrong password. Try again or click Forgot password to reset it.\"]")
    private WebElement wrongPassWarningMessage;
    @FindBy(xpath = "//span[text() = \"Enter a password\"]")
    private WebElement enterPassWarningMessage;

    public void fillInEmailAddress(String email) {
        emailField.sendKeys(email);
        this.clickNext();
    }
    public void clickNext() {
        waitForControl(btnNext);
        this.moveToElement(btnNext);
        btnNext.click();
        waitForPageLoad();
    }
    public String getEmailAccountName() {
        waitForPageLoad();
        waitForControl(accountName);
        return accountName.getText();
    }

    public void fillInPasswordField(String password) {
        waitForControl(passwordField);
        passwordField.sendKeys(password);
        this.clickNext();
        waitForPageLoad();
    }
    public void clickCreateAccountButton() {
        waitForControl(createAccountButton);
        createAccountButton.click();
    }
    public void clickForgotEmailButton() {
        waitForControl(forgotEmailButton);
        this.moveToElement(forgotEmailButton);
        forgotEmailButton.click();
    }
    public void clickForgotPassButton() {
        waitForControl(forgotPasswordButton);
        this.moveToElement(forgotPasswordButton);
        forgotPasswordButton.click();
    }
    public void selectShowPassword() {
        waitForControl(showPasswordCheckbox);
        showPasswordCheckbox.click();
    }
    public boolean isEmptyEmailFieldWarningMessageVisible() {
        return emptyEmailFieldWarningMessage.isDisplayed();
    }

    public boolean isNonExistingAccountWarningMessageVisible() {
        return nonExistingAccountWarningMessage.isDisplayed();
    }

    public boolean isWrongPassWarningMessageVisible() {
        return wrongPassWarningMessage.isDisplayed();
    }

    public boolean isEnterPassWarningMessageVisible() {
        return enterPassWarningMessage.isDisplayed();
    }
    public boolean isForgotEmailPageVisible() {
        return forgotEmailPageLabel.isDisplayed();
    }
    public boolean isForgotPassPageVisible() {
        return forgotPasswordPageLabel.isDisplayed();
    }
    public boolean isCreateAccountOptionsVisible() {
        return createAccountOption.stream().allMatch(WebElement::isDisplayed);
    }
}
