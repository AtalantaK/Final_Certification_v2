package com.saucedemo.pages;

import com.saucedemo.utils.ScreenshotUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class ProductPage {

    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Добавить товар в корзину по имени со страницы товара")
    public void addItemToCartByName() throws IOException {
        WebElement addToCart = driver.findElement(By.xpath("//button[@id='add-to-cart']"));
        addToCart.click();
        ScreenshotUtils.makeScreeshot(driver);
    }

    @Step("Удалить товар из корзины по имени со страницы товара")
    public void removeItemFromCartByName() throws IOException {
        WebElement removeFromCart = driver.findElement(By.xpath("//button[@id='remove']"));
        removeFromCart.click();
        ScreenshotUtils.makeScreeshot(driver);
    }

    @Step("Нажать 'Back to Products'")
    public void clickBackToProducts() throws IOException {
        WebElement backToProducts = driver.findElement(By.xpath("//button[@id='back-to-products']"));
        backToProducts.click();
        ScreenshotUtils.makeScreeshot(driver);
    }

    public String getItemDescription(String itemName) {
        return driver.findElement(By.xpath("//div[@data-test='inventory-item-desc']")).getText();
    }

    public String getItemPrice(String itemName) {
        return driver.findElement(By.xpath("//div[@data-test='inventory-item-price']")).getText();
    }

    public String getItemImage(String itemName) {
        return driver.findElement(By.xpath("//img[@class='inventory_details_img']")).getAttribute("src");
    }
}
