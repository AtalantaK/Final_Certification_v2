package com.saucedemo.pages;

import com.saucedemo.utils.ScreenshotUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

// для работы с элементами на странице логина
public class LoginPage {

    private WebDriver driver;

    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввести имя пользователя")
    public void enterUsername(String username) throws IOException {
        WebElement usernameElement = driver.findElement(usernameField);
        usernameElement.sendKeys(username);
        ScreenshotUtils.makeScreeshot(driver);
    }

    @Step("Ввести пароль пользователя")
    public void enterPassword(String password) throws IOException {
        WebElement passwordElement = driver.findElement(passwordField);
        passwordElement.sendKeys(password);
        ScreenshotUtils.makeScreeshot(driver);
    }

    @Step("Нажать кнопку 'Login'")
    public void clickLogin() throws IOException {
        WebElement loginButtonElement = driver.findElement(loginButton);
        loginButtonElement.click();
        ScreenshotUtils.makeScreeshot(driver);
    }

    public void login(String username, String password) throws IOException {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessageText() {
        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']"));
        return errorMessage.getCssValue("background-color");
    }

    public String getBorderUsername() {
        WebElement usernameElement = driver.findElement(usernameField);
        return usernameElement.getCssValue("border-bottom-color");
    }

    public String getBorderPassword() {
        WebElement passwordElement = driver.findElement(passwordField);
        return passwordElement.getCssValue("border-bottom-color");
    }
}
