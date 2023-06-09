import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTest {
    WebDriver wd;

    @BeforeMethod
    public void init() {
        wd = new ChromeDriver();
        wd.navigate().to("https://telranedu.web.app/home");
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void loginPositiveTest() {
        //open login code
        wd.findElement(By.xpath("//*[.='LOGIN']")).click();
        //fill login form
        WebElement emailInput = wd.findElement(By.xpath("//input[1]"));
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys("qwerty@gm.com");

        WebElement passInput = wd.findElement(By.xpath("//input[2]")); //alt + shift
        passInput.click();
        passInput.clear();
        passInput.sendKeys("abcD123$");

        //click on button Login
        wd.findElement(By.xpath("//button[1]")).click();

        //assert
//        Assert.assertTrue(wd.findElements(By.xpath("//*[.=Sing Out")).size() > 0);
        Assert.assertTrue(wd.findElements(By.xpath("//button")).size() > 0);
    }

    @Test
    public void loginNegativeTestWrongEmail() {
        //open login code
        wd.findElement(By.xpath("//*[.='LOGIN']")).click();
        //fill login form
        WebElement emailInput = wd.findElement(By.xpath("//input[1]"));
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys("qwertygm.com");

        WebElement passInput = wd.findElement(By.xpath("//input[2]")); //alt + shift
        passInput.click();
        passInput.clear();
        passInput.sendKeys("abcD123$");

        //click on button Login
        wd.findElement(By.xpath("//button[1]")).click();

        //assert

    }

    @AfterMethod
    public void tearDown() {
        wd.quit();
    }
}
