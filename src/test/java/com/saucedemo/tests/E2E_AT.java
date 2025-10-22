package com.saucedemo.tests;

import com.saucedemo.config.Config;
import com.saucedemo.pages.*;
import com.saucedemo.utils.Constants;
import com.saucedemo.utils.PageFactory;
import com.saucedemo.utils.WebDriverUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

import static com.saucedemo.utils.Products.*;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("E2E кейсы")
public class E2E_AT {

    private WebDriver driver;
    private PageFactory pageFactory;

    @BeforeEach
    public void setUp() {
        driver = WebDriverUtils.driverSetUp();
        pageFactory = new PageFactory(driver);
    }

    @Test
    @DisplayName("Добавление товаров в корзину и оформление заказа")
    @Story("BС-E2E-01 Business cases")
    @Severity(BLOCKER)
    @Description("Пользователь входит в систему как 'standard_user'. " +
            "\nДобавляет несколько товаров в корзину и оформляет заказ.")
    @Tags({@Tag("Authorization"), @Tag("E2E"), @Tag("Positive")})
    public void E2E_001_PlaceOrder() throws IOException {
        LoginPage loginPage = pageFactory.createLoginPage();
        loginPage.login(Constants.STANDARD_USER, Config.get("PASSWORD"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@class='shopping_cart_link']"))));

        ProductsPage productsPage = pageFactory.createProductsPage();
        productsPage.chooseSortingMethodByIndex(2);
        productsPage.addItemToCartByName(item2.getItemName());
        productsPage.addItemToCartByName(item3.getItemName());
        productsPage.addItemToCartByName(item4.getItemName());

        Header header = pageFactory.createHeader();
        header.openShoppingCart();

        CartPage cartPage = pageFactory.createCartPage();
        cartPage.removeItemFromCartByName(item4.getItemName());
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = pageFactory.createCheckoutPage();
        checkoutPage.clickContinue();
        checkoutPage.enterFirstName();
        checkoutPage.clickContinue();
        checkoutPage.enterLastName();
        checkoutPage.clickContinue();
        checkoutPage.enterPostalCode();
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();

        assertAll("Несколько проверок",
                () -> assertThat(checkoutPage.getCompleteHeader()).isEqualTo("Thank you for your order!"),
                () -> assertThat(checkoutPage.getCompleteText()).isEqualTo("Your order has been dispatched, " +
                        "and will arrive just as fast as the pony can get there!"));
    }

    @AfterEach
    public void tearDown() {
        WebDriverUtils.driverQuit(driver);
    }
}
