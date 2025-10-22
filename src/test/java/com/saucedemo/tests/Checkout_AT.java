package com.saucedemo.tests;

import com.saucedemo.config.Config;
import com.saucedemo.pages.*;
import com.saucedemo.utils.Constants;
import com.saucedemo.utils.PageFactory;
import com.saucedemo.utils.WebDriverUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static com.saucedemo.utils.Products.item1;
import static com.saucedemo.utils.Products.item2;

public class Checkout_AT {

    private WebDriver driver;
    private PageFactory pageFactory;

    @BeforeEach
    public void setUp() {
        driver = WebDriverUtils.driverSetUp();
        pageFactory = new PageFactory(driver);
    }

    //todo: привести в божеский вид

    @Test
//    @DisplayName("Успешная авторизация")
//    @Story("BS-SM-01 Security matrix")
//    @Severity(BLOCKER)
//    @Description("Успешная авторизация как пользователь = standard_user")
//    @Tags({@Tag("Authorization"), @Tag("Smoke"), @Tag("Security_matrix"), @Tag("Positive")})
    public void checkout1() throws IOException {

        LoginPage loginPage = pageFactory.createLoginPage();
        loginPage.login(Constants.STANDARD_USER, Config.get("PASSWORD"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("shopping_cart_link"))));

        ProductsPage productsPage = pageFactory.createProductsPage();
        productsPage.addItemToCartByName(item1.getItemName());
        productsPage.addItemToCartByName(item2.getItemName());

        Header header = pageFactory.createHeader();
        header.openShoppingCart();

        CartPage cartPage = pageFactory.createCartPage();
        cartPage.clickCheckout();

        System.out.println(header.getTextHeader());
        System.out.println(header.getTextHeaderSecondaryContainer());

        CheckoutPage checkoutPage = pageFactory.createCheckoutPage();
        checkoutPage.enterFirstName();
        checkoutPage.enterLastName();
        checkoutPage.enterPostalCode();
        checkoutPage.clickCancel();
    }

    @Test
    public void checkout2() throws IOException {

        LoginPage loginPage = pageFactory.createLoginPage();
        loginPage.login(Constants.STANDARD_USER, Config.get("PASSWORD"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("shopping_cart_link"))));

        ProductsPage productsPage = pageFactory.createProductsPage();
        productsPage.addItemToCartByName(item1.getItemName());
        productsPage.addItemToCartByName(item2.getItemName());

        Header header = pageFactory.createHeader();
        header.openShoppingCart();

        CartPage cartPage = pageFactory.createCartPage();
        cartPage.clickCheckout();

        System.out.println(header.getTextHeader());
        System.out.println(header.getTextHeaderSecondaryContainer());

        CheckoutPage checkoutPage = pageFactory.createCheckoutPage();
        checkoutPage.enterFirstName();
        checkoutPage.enterLastName();
        checkoutPage.enterPostalCode();
        checkoutPage.clickContinue();
        System.out.println(header.getTextHeaderSecondaryContainer());

        List<WebElement> items = checkoutPage.getListItems();
        for (WebElement item : items) {
            System.out.println(checkoutPage.getItemName(item));
            System.out.println(checkoutPage.getItemDescription(item));
            System.out.println(checkoutPage.getItemQuantity(item));
            System.out.println(checkoutPage.getItemPrice(item));
        }

        System.out.println(checkoutPage.getSubTotal());
        System.out.println(checkoutPage.getTaxValue());
        System.out.println(checkoutPage.getTotalValue());

        System.out.println(checkoutPage.getPaymentInfoValue());
        System.out.println(checkoutPage.getShippingInfoValue());

        checkoutPage.clickFinish();

        System.out.println(header.getTextHeaderSecondaryContainer());
        System.out.println(checkoutPage.getImage());
        System.out.println(checkoutPage.getCompleteHeader());
        System.out.println(checkoutPage.getCompleteText());
        checkoutPage.clickBackHome();
    }

    @Test
    public void checkout3() throws IOException {

        LoginPage loginPage = pageFactory.createLoginPage();
        loginPage.login(Constants.STANDARD_USER, Config.get("PASSWORD"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("shopping_cart_link"))));

        ProductsPage productsPage = pageFactory.createProductsPage();
        productsPage.addItemToCartByName(item1.getItemName());
        productsPage.addItemToCartByName(item2.getItemName());

        Header header = pageFactory.createHeader();
        header.openShoppingCart();

        CartPage cartPage = pageFactory.createCartPage();
        cartPage.clickCheckout();

        System.out.println(header.getTextHeader());
        System.out.println(header.getTextHeaderSecondaryContainer());

        CheckoutPage checkoutPage = pageFactory.createCheckoutPage();
        checkoutPage.clickContinue();
        System.out.println(checkoutPage.getErrorMessage());
        checkoutPage.enterFirstName();
        checkoutPage.clickContinue();
        System.out.println(checkoutPage.getErrorMessage());
        checkoutPage.enterLastName();
        checkoutPage.clickContinue();
        System.out.println(checkoutPage.getErrorMessage());
        checkoutPage.enterPostalCode();
        checkoutPage.clickContinue();

        checkoutPage.openItemPageByName(item2.getItemName());
    }

    @AfterEach
    public void tearDown() {
        WebDriverUtils.driverQuit(driver);
    }
}
