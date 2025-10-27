package UI.saucedemo.pages;

import UI.saucedemo.utils.ScreenshotUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class Header {

    private WebDriver driver;

    public Header(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Перейти в корзину")
    public void openShoppingCart() throws IOException {
        WebElement shoppingCart = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        shoppingCart.click();
        ScreenshotUtils.makeScreeshot(driver);
    }

    public Integer getShoppingCartBadge() {
        String string = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();
        return Integer.parseInt(string);
    }

    public String getTextHeader() {
        return driver.findElement(By.xpath("//div[@class='header_label']/div")).getText();
    }

    public String getTextHeaderSecondaryContainer() {
        return driver.findElement(By.xpath("//div[@class='header_secondary_container']/span")).getText();
    }

    @Step("Открыть меню")
    public void openBurgerMenu() throws IOException {
        WebElement burgerMenu = driver.findElement(By.xpath("//button[text()='Open Menu']"));
        burgerMenu.click();
        ScreenshotUtils.makeScreeshot(driver);
    }

    @Step("Нажать на кнопку 'All Items'")
    public void clickAllItems() throws IOException {
        WebElement allItems = driver.findElement(By.xpath("//a[text()='All Items']"));
        allItems.click();
        ScreenshotUtils.makeScreeshot(driver);
    }

    @Step("Нажать на кнопку 'About'")
    public void clickAbout() throws IOException {
        WebElement about = driver.findElement(By.xpath("//a[text()='About']"));
        about.click();
        ScreenshotUtils.makeScreeshot(driver);
    }

    @Step("Нажать на кнопку 'Logout'")
    public void clickLogout() throws IOException {
        WebElement logout = driver.findElement(By.xpath("//a[text()='Logout']"));
        logout.click();
        ScreenshotUtils.makeScreeshot(driver);
    }

    @Step("Нажать на кнопку 'Reset App State'")
    public void clickResetAppState() throws IOException {
        WebElement resetAppState = driver.findElement(By.xpath("//a[text()='Reset App State']"));
        resetAppState.click();
        ScreenshotUtils.makeScreeshot(driver);
    }

    @Step("Закрыть меню")
    public void clickCloseMenu() throws IOException {
        WebElement closeMenu = driver.findElement(By.xpath("//button[text()='Close Menu']"));
        closeMenu.click();
        ScreenshotUtils.makeScreeshot(driver);
    }
}
