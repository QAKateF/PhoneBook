package tests;

import manager.TestNGListener;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListener.class)

public class LoginTests extends TestBase {
    @BeforeMethod
    public void precondition(){
        if(TestBase.app.getUser().isLogged()){
            TestBase.app.getUser().logout();
        }
    }

    @Test
    public void loginPositiveTestBase(){
        String email = "qwerty@gm.com", password = "abcD123$";
        TestBase.app.getUser().openLoginForm();
        TestBase.app.getUser().fillLoginForm(email, password);
        TestBase.app.getUser().submitLogin();
        TestBase.app.getUser().pause(3000);
        Assert.assertTrue(TestBase.app.getUser().isElementPresent(By.xpath("//button")));
    }

    @Test
    public void loginNegativeWrongEmail(){
        String email = "qwertygm.com", password = "abcD123$";
        TestBase.app.getUser().openLoginForm();
        TestBase.app.getUser().fillLoginForm(email, password);
        TestBase.app.getUser().submitLogin();
//      app.getUser().pause(3000);
        Assert.assertTrue(TestBase.app.getUser().isWrongFormatMessage());
        Assert.assertTrue(TestBase.app.getUser().isAlertPresent());
    }
}