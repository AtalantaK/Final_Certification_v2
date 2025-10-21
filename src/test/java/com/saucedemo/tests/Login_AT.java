package com.saucedemo.tests;

import com.saucedemo.config.Config;
import com.saucedemo.pages.Footer;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.Constants;
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

import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Login_AT {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = WebDriverUtils.driverSetUp();
    }

    @Test
    @DisplayName("Успешная авторизация")
    @Story("BS-SM-01 Security matrix")
    @Severity(BLOCKER)
    @Description("Успешная авторизация как пользователь = standard_user")
    @Tags({@Tag("Authorization"), @Tag("Smoke"), @Tag("Security_matrix"), @Tag("Positive")})
    public void successfulAuthorization() throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(Constants.STANDARD_USER, Config.get("PASSWORD"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //todo: возможно потом нужно будет переписать строчку ниже когда появится работа с корзиной
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("shopping_cart_link"))));
        //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@class='shopping_cart_link']"))));

        Footer footer = new Footer(driver);
        System.out.println(footer.getFooterCopyright());
        System.out.println(footer.getTwitterLink());
        System.out.println(footer.getFacebookLink());
        System.out.println(footer.getLinkedinLink());
    }

    @Test
    @DisplayName("Авторизация заблокированного пользователя")
    @Story("BS-SM-01 Security matrix")
    @Severity(NORMAL)
    @Description("Неуспешная авторизация как пользователь = locked_out_user")
    @Tags({@Tag("Authorization"), @Tag("Smoke"), @Tag("Security_matrix"), @Tag("Negative")})
    public void unsuccessfulAuthorization() throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(Constants.LOCKED_OUT_USER, Config.get("PASSWORD"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h3[text()='Epic sadface: Sorry, this user has been locked out.']"))));

        String actualBackgroundErrorMessage = loginPage.getErrorMessageText();
        String actualBorderUsername = loginPage.getBorderUsername();
        String actualBorderPassword = loginPage.getBorderPassword();

        assertAll("Несколько проверок",
                () -> assertThat(actualBackgroundErrorMessage).isEqualTo(Constants.BACKGROUND_COLOR),
                () -> assertThat(actualBorderUsername).isEqualTo(Constants.BACKGROUND_COLOR),
                () -> assertThat(actualBorderPassword).isEqualTo(Constants.BACKGROUND_COLOR));
    }

    @AfterEach
    public void tearDown() {
        WebDriverUtils.driverQuit(driver);
    }
}
