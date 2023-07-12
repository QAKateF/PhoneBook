package tests;

import Models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.TestBase;

public class RegistrationTests extends TestBase {
    @BeforeMethod(alwaysRun = true)
    public void precondition(){
        if(TestBase.app.getUser().isLogged()){
            TestBase.app.getUser().logout();
        }
    }
    @Test(groups = {"smoke", "positive", "regress"})
    public void registrationPositive(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        String email = "qwerty" + i + "@gm.com", password = "abcD123$";
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(email, password);
        app.getUser().submitRegistration();
        app.getUser().pause(3000);
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//button")));
    }

    @Test(groups = {"positive"})
    public void registrationPositiveUser(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User().withEmail("qwerty" + i + "@gm.com").withPassword("abcD123$");
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitRegistration();
        app.getUser().pause(3000);
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//button")));
    }

    @Test(groups = {"regress", "negative"})
    public void registrationNegativeWrongEmail(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        String email = "qwerty" + i + "gm.com", password = "abcD123$";
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(email, password);
        app.getUser().submitRegistration();
        Assert.assertTrue(app.getUser().isAlertPresent());
    }
    @Test(groups = {"regress", "negative"})
    public void registrationNegativeWrongPassword(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        String email = "qwerty" + i + "@gm.com", password = "abcD123";
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(email, password);
        app.getUser().submitRegistration();
        Assert.assertTrue(app.getUser().isAlertPresent());
    }

}