import org.testng.annotations.Test;
import org.testng.Assert;

public class LoginFunctionalityTest extends BaseTest{

    @Test(priority = 1)
    public void loginWithEmptyEmail() {
        loginPage.fillInEmailAddress("");
        Assert.assertEquals(loginPage.getPageTitle(), "Sign in - Google Accounts");
        Assert.assertTrue(loginPage.isEmptyEmailFieldWarningMessageVisible());
    }
    @Test(priority = 2)
    public void loginWithInvalidEmail() {
        loginPage.fillInEmailAddress("nonexistingemailaccount");
        Assert.assertTrue(loginPage.isNonExistingAccountWarningMessageVisible());
    }
    @Test(priority = 3)
    public void loginWithEmailAddressWithoutDomainName() {
        loginPage.fillInEmailAddress("freeforyou14");
        Assert.assertEquals(loginPage.getEmailAccountName(), "freeforyou14@gmail.com");
    }
    @Test(priority = 4)
    public void loginWithoutFillingInPassword() {
        loginPage.fillInEmailAddress("freeforyou14@gmail.com");
        Assert.assertEquals(loginPage.getEmailAccountName(), "freeforyou14@gmail.com");
        loginPage.fillInPasswordField("");
        Assert.assertTrue(loginPage.isEnterPassWarningMessageVisible());
    }
    @Test(priority = 5)
    public void loginWithInvalidPassword() {
        loginPage.fillInEmailAddress("freeforyou14@gmail.com");
        Assert.assertEquals(loginPage.getEmailAccountName(), "freeforyou14@gmail.com");
        loginPage.fillInPasswordField("wrong");
        Assert.assertTrue(loginPage.isWrongPassWarningMessageVisible());
    }
    @Test(priority = 6)
    public void chooseForgotEmailOption() {
        loginPage.clickForgotEmailButton();
        Assert.assertTrue(loginPage.isForgotEmailPageVisible());
    }
    @Test(priority = 7)
    public void chooseCreateAccountOption() {
        loginPage.clickCreateAccountButton();
        Assert.assertTrue(loginPage.isCreateAccountOptionsVisible());
    }
    @Test(priority = 8)
    public void chooseForgotPasswordOption() { //
        loginPage.fillInEmailAddress("freeforyou14@gmail.com");
        loginPage.clickForgotPassButton();
        Assert.assertTrue(loginPage.isForgotPassPageVisible());
    }
    @Test(priority = 9)
    public void loginWithValidCredentials(){
        loginPage.fillInEmailAddress("freeforyou14@gmail.com");
        loginPage.fillInPasswordField("macmac90");
        Assert.assertTrue(loginPage.getPageTitle().contains("Inbox"));
    }
}
