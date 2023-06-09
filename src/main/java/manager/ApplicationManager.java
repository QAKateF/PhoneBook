package manager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
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

    String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public HelperUser getUser() {
        return user;
    }

    public HelperContact getHelperContact() {
        return helperContact;
    }

    @BeforeSuite(alwaysRun = true)
    public void init(){
        if(browser.equals(BrowserType.CHROME)) {
            wd = new EventFiringWebDriver(new ChromeDriver()); //wd = new ChromeDriver();
            logger.info("Tests start on Chrome");
        } else if (browser.equals(BrowserType.FIREFOX)) {
            wd = new EventFiringWebDriver(new FirefoxDriver()); //wd = new ChromeDriver();
            logger.info("Tests start on Firefox");
        }
        wd.register(new WebDrListener());
        user = new HelperUser(wd);
        helperContact = new HelperContact(wd);
        wd.manage().window().maximize();
        wd.navigate().to("https://telranedu.web.app/home");
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown(){
        wd.quit();
    }

    @BeforeMethod(alwaysRun = true)
    public void startLogger(Method method) {
        logger.info(method.getName() + "is started");
    }

    @AfterMethod(alwaysRun = true)
    public void finishLogger(Method method) {
        logger.info("*********************************************************************************************");
    }
}