package manager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    EventFiringWebDriver wd;     //WebDriver wd;
    HelperUser user;
    HelperContact helperContact;

    public HelperUser getUser() {
        return user;
    }

    public HelperContact getHelperContact() {
        return helperContact;
    }

    @BeforeSuite
    public void init(){
        wd = new EventFiringWebDriver(new ChromeDriver());        //wd = new ChromeDriver();
        wd.register(new WebDrListener());
        user = new HelperUser(wd);
        helperContact = new HelperContact(wd);
        wd.manage().window().maximize();
        wd.navigate().to("https://telranedu.web.app/home");
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterSuite
    public void tearDown(){
        //wd.quit();
    }

    @BeforeMethod
    public void startLogger(Method method) {
        logger.info(method.getName() + "is started");
    }

    @AfterMethod
    public void finishLogger(Method method) {
        logger.info("*********************************************************************************************");
    }
}