package tests;

import Models.Contact;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteContactTest extends TestBase {
    Logger logger = LoggerFactory.getLogger(DeleteContactTest.class);

    @BeforeMethod
    public void precondition(){
         if(!app.getUser().isLogged()){
             String email = "qwerty@gm.com", password = "abcD123$";
             app.getUser().openLoginForm();
             app.getUser().fillLoginForm(email, password);
             app.getUser().submitLogin();
            }
        }

    @Test
    public void deleteNewContactPositive() {
        if (!app.getHelperContact().isContactFind()) {
            AddNewContactTest addContact = new AddNewContactTest();
            addContact.addNewContactPositive();
        }
        int count1 = app.getHelperContact().countContact();
        app.getHelperContact().openExistContact();
        app.getHelperContact().removeContact();
        app.getHelperContact().pause(3000);
        int count2 = app.getHelperContact().countContact();
        app.getHelperContact().pause(3000);
        Assert.assertTrue(count1 - count2 == 1);
    }

    }
