package tests;

import Models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.TestBase;

public class RegistrationTests extends TestBase {
    @Test
    public void registrationPositive(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        String email = "qwerty" + i + "@gm.com", password = "abcD123$";
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(email, password);
        app.getUser().submitRegistration();
        app.getUser().pause(3000);
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//button")));
    }

    @Test
    public void registrationPositiveUser(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User().withEmail("qwerty" + i + "@gm.com").withPassword("abcD123$");
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitRegistration();
        app.getUser().pause(3000);
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//button")));
    }

    @Test
    public void registrationNegativeWrongEmail(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        String email = "qwerty" + i + "gm.com", password = "abcD123$";
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(email, password);
        app.getUser().submitRegistration();
    }
    @Test
    public void registrationNegativeWrongPassword(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        String email = "qwerty" + i + "@gm.com", password = "abcD123";
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(email, password);
        app.getUser().submitRegistration();
    }

}