package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    @Test
    public void validLoginShouldOpenProductsPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(loginPage.isOnProductsPage(),
                "User should land on the products page after a valid login");
    }

    @Test
    public void invalidPasswordShouldShowError() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("standard_user", "wrong_password");

        Assert.assertTrue(loginPage.getErrorMessage().contains("do not match"),
                "Error should say username and password do not match");
    }

    @Test
    public void lockedOutUserShouldShowError() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");

        Assert.assertTrue(loginPage.getErrorMessage().contains("locked out"),
                "Error should say the user has been locked out");
    }

    @Test
    public void emptyUsernameShouldShowError() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("", "secret_sauce");

        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"),
                "Error should say the username is required");
    }
}
