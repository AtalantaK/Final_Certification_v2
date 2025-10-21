package com.saucedemo.tests;

import com.saucedemo.config.Config;
import com.saucedemo.pages.*;
import com.saucedemo.utils.Constants;
import com.saucedemo.utils.SortngMethods;
import com.saucedemo.utils.WebDriverUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static com.saucedemo.utils.Products.*;

public class Products_AT {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = WebDriverUtils.driverSetUp();
    }

    //todo: привести в божеский вид

    @Test
//    @DisplayName("Успешная авторизация")
//    @Story("BS-SM-01 Security matrix")
//    @Severity(BLOCKER)
//    @Description("Успешная авторизация как пользователь = standard_user")
//    @Tags({@Tag("Authorization"), @Tag("Smoke"), @Tag("Security_matrix"), @Tag("Positive")})
    public void item() throws IOException {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(Constants.STANDARD_USER, Config.get("PASSWORD"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("shopping_cart_link"))));

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addItemToCartByName(item1.getItemName());
        productsPage.removeItemFromCartByName(item1.getItemName());
        System.out.println(productsPage.getItemDescriptionByName(item1.getItemName()));
        System.out.println(productsPage.getItemPriceByName(item1.getItemName()));
        System.out.println(productsPage.getItemImageByName(item1.getItemName()));

        productsPage.openItemByName(item2.getItemName());
        ProductPage productPage = new ProductPage(driver);
        productPage.addItemToCartByName();
        productPage.removeItemFromCartByName();
        System.out.println(productPage.getItemDescription(item2.getItemName()));
        System.out.println(productPage.getItemPrice(item2.getItemName()));
        System.out.println(productPage.getItemImage(item2.getItemName()));
        productPage.clickBackToProducts();

        productsPage.chooseSortingMethodByName(SortngMethods.lohi.getSortingName());
        productsPage.chooseSortingMethodByValue((SortngMethods.hilo).toString());
        productsPage.chooseSortingMethodByIndex(2);
    }

    @Test
//    @DisplayName("Успешная авторизация")
//    @Story("BS-SM-01 Security matrix")
//    @Severity(BLOCKER)
//    @Description("Успешная авторизация как пользователь = standard_user")
//    @Tags({@Tag("Authorization"), @Tag("Smoke"), @Tag("Security_matrix"), @Tag("Positive")})
    public void item2() throws IOException {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(Constants.STANDARD_USER, Config.get("PASSWORD"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("shopping_cart_link"))));

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addItemToCartByName(item1.getItemName());
        productsPage.addItemToCartByName(item2.getItemName());

        Header header = new Header(driver);
        System.out.println(header.getTextHeaderSecondaryContainer());
        header.openShoppingCart();
        System.out.println(header.getShoppingCartBadge());

        CartPage cartPage = new CartPage(driver);
        System.out.println(header.getTextHeaderSecondaryContainer());
        cartPage.removeItemFromCartByName(item1.getItemName());
        cartPage.clickContinueShopping();
        header.openShoppingCart();
        cartPage.openItemPageByName(item2.getItemName());
//        cartPage.clickCheckout();
    }

    @Test
//    @DisplayName("Успешная авторизация")
//    @Story("BS-SM-01 Security matrix")
//    @Severity(BLOCKER)
//    @Description("Успешная авторизация как пользователь = standard_user")
//    @Tags({@Tag("Authorization"), @Tag("Smoke"), @Tag("Security_matrix"), @Tag("Positive")})
    public void item3() throws IOException {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(Constants.STANDARD_USER, Config.get("PASSWORD"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("shopping_cart_link"))));

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addItemToCartByName(item1.getItemName());
        productsPage.addItemToCartByName(item2.getItemName());

        Header header = new Header(driver);
        header.openShoppingCart();

        CartPage cartPage = new CartPage(driver);
        List<WebElement> items = cartPage.getListItems();
        for (WebElement item : items) {
            System.out.println(cartPage.getItemName(item));
            System.out.println(cartPage.getItemDescription(item));
            System.out.println(cartPage.getItemQuantity(item));
            System.out.println(cartPage.getItemPrice(item));
        }
    }

    @AfterEach
    public void tearDown() {
        WebDriverUtils.driverQuit(driver);
    }
}
