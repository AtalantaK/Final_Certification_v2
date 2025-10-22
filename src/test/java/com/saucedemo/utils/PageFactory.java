package com.saucedemo.utils;

import com.saucedemo.pages.*;
import org.openqa.selenium.WebDriver;

public class PageFactory {

    private WebDriver driver;

    public PageFactory(WebDriver driver) {
        this.driver = driver;
    }

    public CartPage createCartPage() {
        return new CartPage(driver);
    }

    public CheckoutPage createCheckoutPage() {
        return new CheckoutPage(driver);
    }

    public Footer createFooter() {
        return new Footer(driver);
    }

    public Header createHeader() {
        return new Header(driver);
    }

    public LoginPage createLoginPage() {
        return new LoginPage(driver);
    }

    public ProductPage createProductPage() {
        return new ProductPage(driver);
    }

    public ProductsPage createProductsPage() {
        return new ProductsPage(driver);
    }
}
