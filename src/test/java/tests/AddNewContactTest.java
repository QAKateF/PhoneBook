package tests;

import Models.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

public class AddNewContactTest extends TestBase {
    Logger logger = LoggerFactory.getLogger(AddNewContactTest.class);

    @BeforeMethod(alwaysRun = true)
    public void precondition(){
         if(!app.getUser().isLogged()){
             String email = "qwerty@gm.com", password = "abcD123$";
             app.getUser().openLoginForm();
             app.getUser().fillLoginForm(email, password);
             app.getUser().submitLogin();
            }
        }

    @Test (invocationCount = 5, groups = {"positive"})
    public void addNewContactPositive(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        Contact contact = Contact.builder()
                .name("Funny_" + i)
                .lastName("Cat")
                .phone("025352645" + i)
                .email("funny" + i + "@mail.com")
                .address("Tel-Aviv")
                .description("Best friend")
                .build();
        logger.info("Phone number is " + contact.getPhone());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContactForm();
        app.getHelperContact().pause(3000);
        Assert.assertTrue(app.getHelperContact().isContactCreated(contact));
        }

    }
