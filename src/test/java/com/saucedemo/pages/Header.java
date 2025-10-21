package com.saucedemo.pages;

import com.saucedemo.utils.ScreenshotUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class Header {

    //todo: методы для работы с верхней плашкой: меню
    WebDriver driver;

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

    public String getTextHeaderSecondaryContainer() {
        return driver.findElement(By.xpath("//div[@class='header_secondary_container']/span")).getText();
    }
}
