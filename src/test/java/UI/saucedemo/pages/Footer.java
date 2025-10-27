package UI.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Footer {

    private WebDriver driver;

    public Footer(WebDriver driver) {
        this.driver = driver;
    }

    public String getFooterCopyright() {
        return driver.findElement(By.xpath("//div[@data-test='footer-copy']")).getText();
    }

    public String getTwitterLink() {
        return driver.findElement(By.xpath("//a[@data-test='social-twitter']")).getAttribute("href");
    }

    public String getFacebookLink() {
        return driver.findElement(By.xpath("//a[@data-test='social-facebook']")).getAttribute("href");
    }

    public String getLinkedinLink() {
        return driver.findElement(By.xpath("//a[@data-test='social-linkedin']")).getAttribute("href");
    }
}
