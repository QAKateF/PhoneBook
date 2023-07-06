package manager;

import Models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperContact extends HelperBase {
    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openContactForm(){
            wd.findElement(By.xpath("//*[.='ADD']")).click();
    }

    public void fillContactForm(Contact contact) {
        type(By.xpath("//input[@placeholder='Name']"), contact.getName());
        type(By.xpath("//input[@placeholder='Last Name']"), contact.getLastName());
        type(By.xpath("//input[@placeholder='Phone']"), contact.getPhone());
        type(By.xpath("//input[@placeholder='email']"), contact.getEmail());
        type(By.xpath("//input[@placeholder='Address']"), contact.getAddress());
        type(By.xpath("//input[@placeholder='description']"), contact.getDescription());
    }

    public void submitContactForm() {
        click(By.xpath("//button[.='Save']"));
    }

    public boolean isContactCreated(Contact contact) {
        String phone = wd.findElement(By.xpath("//div[@class='contact-item_card__2SOIM'][last()]//h3")).getText();
        return phone.equals(contact.getPhone());
    }

    public boolean isContactFind() {
        return wd.findElements(By.xpath("//div[@class='contact-item_card__2SOIM']")).size() > 0;
    }

    public void openExistContact() {
        click(By.cssSelector(".contact-item_card__2SOIM:first-child"));
    }

    public void removeContact(){
        click(By.xpath("//*[text()='Remove']"));
    }

    public int countContact() {
        return wd.findElements(By.xpath("//div[@class='contact-item_card__2SOIM']")).size();
    }
}
