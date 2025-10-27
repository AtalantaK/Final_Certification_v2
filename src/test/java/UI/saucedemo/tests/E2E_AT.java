package UI.saucedemo.tests;

import UI.saucedemo.config.Config;
import UI.saucedemo.pages.*;
import UI.saucedemo.utils.Constants;
import UI.saucedemo.utils.PageFactory;
import UI.saucedemo.utils.WebDriverUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static UI.saucedemo.utils.Products.*;
import static UI.saucedemo.utils.Users.user1;
import static io.qameta.allure.Allure.step;
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
        loginPage.login(user1.getUserName(), Config.get("UI_PASSWORD"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@class='shopping_cart_link']"))));

        ProductsPage productsPage = pageFactory.createProductsPage();
        productsPage.chooseSortingMethodByIndex(2);

        List<WebElement> list = productsPage.getListItems();
        step("Получение списка продуктов", () -> {
            for (int i = 0; i < list.size(); i++) {
                WebElement item = list.get(i);
                step("Продукт " + (i + 1), () -> {
                    step("Название продукта: " + productsPage.getItemName(item));
                    step("Описание продукта: " + productsPage.getItemDescription(item));
                    step("Стоимость продукта: " + productsPage.getItemPrice(item));
                    step("Изображение продукта: " + productsPage.getImage(item));
                });
            }
        });


        productsPage.addItemToCartByName(item1.getItemName());
        productsPage.addItemToCartByName(item3.getItemName());
        productsPage.addItemToCartByName(item5.getItemName());
        productsPage.addItemToCartByName(item6.getItemName());

        Header header = pageFactory.createHeader();
        header.openShoppingCart();

        CartPage cartPage = pageFactory.createCartPage();
        cartPage.removeItemFromCartByName(item6.getItemName());
        Integer actualShoppingCartBadge = header.getShoppingCartBadge();
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = pageFactory.createCheckoutPage();
        checkoutPage.clickContinue();
        checkoutPage.enterFirstName(Constants.FIRST_NAME);
        checkoutPage.clickContinue();
        checkoutPage.enterLastName(Constants.LAST_NAME);
        checkoutPage.clickContinue();
        checkoutPage.enterPostalCode(Constants.POSTAL_CODE);
        checkoutPage.clickContinue();
        String actualTotal = checkoutPage.getTotalValue();
        checkoutPage.clickFinish();

        String actualHeader = checkoutPage.getCompleteHeader();
        String actualText = checkoutPage.getCompleteText();

        checkoutPage.clickBackHome();

        step("Проверки в конце кейса", () -> assertAll("Несколько проверок",
                () -> assertThat(actualShoppingCartBadge).isEqualTo(Constants.EXPECTED_SHOPPING_CART_BADGE),
                () -> assertThat(actualTotal).isEqualTo(Constants.EXPECTED_TOTAL),
                () -> assertThat(actualHeader).isEqualTo(Constants.EXPECTED_HEADER),
                () -> assertThat(actualText).isEqualTo(Constants.EXPECTED_TEXT)));
    }

    @AfterEach
    public void tearDown() {
        WebDriverUtils.driverQuit(driver);
    }
}
