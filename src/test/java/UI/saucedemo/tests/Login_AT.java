package UI.saucedemo.tests;

import UI.saucedemo.config.Config;
import UI.saucedemo.pages.LoginPage;
import UI.saucedemo.utils.Constants;
import UI.saucedemo.utils.PageFactory;
import UI.saucedemo.utils.WebDriverUtils;
import io.qameta.allure.Allure;
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

import static UI.saucedemo.utils.Users.standard_user;
import static UI.saucedemo.utils.Users.locked_out_user;
import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Тесты секьюрити матрицы")
public class Login_AT {

    @BeforeEach
    public void setupLabels() {
        Allure.label("parentSuite", "UI. Login АТ");
//        Allure.label("suite", "Создание нового сотрудника");
    }

    private WebDriver driver;
    private PageFactory pageFactory;

    @BeforeEach
    public void setUp() {
        driver = WebDriverUtils.driverSetUp();
        pageFactory = new PageFactory(driver);
    }

    @Test
    @DisplayName("Успешная авторизация")
    @Story("BS-SM-01 Security matrix")
    @Severity(BLOCKER)
    @Description("Успешная авторизация как пользователь = standard_user")
    @Tags({@Tag("Authorization"), @Tag("Smoke"), @Tag("Security_matrix"), @Tag("Positive")})
    public void successfulAuthorization() throws IOException {
        LoginPage loginPage = pageFactory.createLoginPage();
        loginPage.login(standard_user.getUserName(), Config.get("UI_PASSWORD"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@class='shopping_cart_link']"))));
    }

    @Test
    @DisplayName("Авторизация заблокированного пользователя")
    @Story("BS-SM-01 Security matrix")
    @Severity(NORMAL)
    @Description("Неуспешная авторизация как пользователь = locked_out_user")
    @Tags({@Tag("Authorization"), @Tag("Smoke"), @Tag("Security_matrix"), @Tag("Negative")})
    public void unsuccessfulAuthorization() throws IOException {
        LoginPage loginPage = pageFactory.createLoginPage();
        loginPage.login(locked_out_user.getUserName(), Config.get("UI_PASSWORD"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h3[text()='Epic sadface: Sorry, this user has been locked out.']"))));

        String actualBackgroundErrorMessage = loginPage.getErrorMessageText();
        String actualBorderUsername = loginPage.getBorderUsername();
        String actualBorderPassword = loginPage.getBorderPassword();

        step("Проверки в конце кейса", () -> assertAll("Несколько проверок",
                () -> assertThat(actualBackgroundErrorMessage).isEqualTo(Constants.BACKGROUND_COLOR),
                () -> assertThat(actualBorderUsername).isEqualTo(Constants.BACKGROUND_COLOR),
                () -> assertThat(actualBorderPassword).isEqualTo(Constants.BACKGROUND_COLOR)));
    }

    @AfterEach
    public void tearDown() {
        WebDriverUtils.driverQuit(driver);
    }
}
