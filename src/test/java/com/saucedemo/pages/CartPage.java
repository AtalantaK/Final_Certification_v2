package com.saucedemo.pages;

import com.saucedemo.utils.ScreenshotUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

// для работы с корзиной
public class CartPage {

    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getListItems() {
        return driver.findElements(By.xpath("//div[@class='cart_item']"));
    }

    public Integer getItemQuantity(WebElement item) {
        String itemQuantity = item.findElement(By.xpath(".//div[@data-test='item-quantity']")).getText();
        return Integer.parseInt(itemQuantity);
    }

    public String getItemName(WebElement item) {
        return item.findElement(By.xpath(".//div[@data-test='inventory-item-name']")).getText();
    }

    public String getItemDescription(WebElement item) {
        return item.findElement(By.xpath(".//div[@data-test='inventory-item-desc']")).getText();
    }

    public String getItemPrice(WebElement item) {
        return item.findElement(By.xpath(".//div[@data-test='inventory-item-price']")).getText();
    }

    @Step("Удалить товар из корзины по имени")
    public void removeItemFromCartByName(String itemName) throws IOException {
        WebElement removeFromCart = driver.findElement(By.xpath("//div[text()='" + itemName + "']/../../div[@class='item_pricebar']/button"));
        removeFromCart.click();
        ScreenshotUtils.makeScreeshot(driver);
    }

    @Step("Нажать на кнопку 'Continue Shopping'")
    public void clickContinueShopping() throws IOException {
        WebElement continueShopping = driver.findElement(By.xpath("//button[@id='continue-shopping']"));
        continueShopping.click();
        ScreenshotUtils.makeScreeshot(driver);
    }

    @Step("Нажать на кнопку 'Checkout'")
    public void clickCheckout() throws IOException {
        WebElement checkout = driver.findElement(By.xpath("//button[@id='checkout']"));
        checkout.click();
        ScreenshotUtils.makeScreeshot(driver);
    }

    @Step("Открыть товар в корзине")
    public void openItemPageByName(String itemName) throws IOException {
        WebElement itemLink = driver.findElement(By.xpath("//div[text()='" + itemName + "']"));
        itemLink.click();
        ScreenshotUtils.makeScreeshot(driver);
    }
}
