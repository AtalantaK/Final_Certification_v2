package com.saucedemo.pages;

import com.saucedemo.utils.ScreenshotUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class CheckoutPage {

    private WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getListItems() {
        return driver.findElements(By.xpath("//div[@class='cart_item']"));
    }

    public Integer getItemQuantity(WebElement item) {
        String itemQuantity = item.findElement(By.xpath(".//div[@data-test='item-quantity']")).getText();
        return Integer.parseInt(itemQuantity);
    }

    public String getItemName(WebElement item) {
        return item.findElement(By.xpath(".//div[@data-test='inventory-item-name']")).getText();
    }

    public String getItemDescription(WebElement item) {
        return item.findElement(By.xpath(".//div[@data-test='inventory-item-desc']")).getText();
    }

    public String getItemPrice(WebElement item) {
        return item.findElement(By.xpath(".//div[@data-test='inventory-item-price']")).getText();
    }

    @Step("Нажать кнопку 'Cancel'")
    public void clickCancel() throws IOException {
        WebElement cancel = driver.findElement(By.xpath("//button[@id='cancel']"));
        cancel.click();
        ScreenshotUtils.makeScreeshot(driver);
    }

    @Step("Нажать кнопку 'Continue'")
    public void clickContinue() throws IOException {
        WebElement continueButton = driver.findElement(By.xpath("//input[@id='continue']"));
        continueButton.click();
        ScreenshotUtils.makeScreeshot(driver);
    }

    @Step("Нажать кнопку 'Finish'")
    public void clickFinish() throws IOException {
        WebElement finishButton = driver.findElement(By.xpath("//button[@id='finish']"));
        finishButton.click();
        ScreenshotUtils.makeScreeshot(driver);
    }

    public String getErrorMessage() {
        return driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
    }

    @Step("Ввести 'First Name'")
    public void enterFirstName(String text) throws IOException {
        WebElement firstName = driver.findElement(By.xpath("//input[@id='first-name']"));
        firstName.sendKeys(text);
        ScreenshotUtils.makeScreeshot(driver);
    }

    @Step("Ввести 'Last Name'")
    public void enterLastName(String text) throws IOException {
        WebElement lastName = driver.findElement(By.xpath("//input[@id='last-name']"));
        lastName.sendKeys(text);
        ScreenshotUtils.makeScreeshot(driver);
    }

    @Step("Ввести 'Zip/Postal Code'")
    public void enterPostalCode(String text) throws IOException {
        WebElement postalCode = driver.findElement(By.xpath("//input[@id='postal-code']"));
        postalCode.sendKeys(text);
        ScreenshotUtils.makeScreeshot(driver);
    }

    @Step("Открыть товар на этапе 'Checkout'")
    public void openItemPageByName(String itemName) throws IOException {
        WebElement itemLink = driver.findElement(By.xpath("//div[text()='" + itemName + "']"));
        itemLink.click();
        ScreenshotUtils.makeScreeshot(driver);
    }

    public String getPaymentInfoValue() {
        return driver.findElement(By.xpath("//div[@data-test='payment-info-value']")).getText();
    }

    public String getShippingInfoValue() {
        return driver.findElement(By.xpath("//div[@data-test='shipping-info-value']")).getText();
    }

    public String getSubTotal() {
        return driver.findElement(By.xpath("//div[@data-test='subtotal-label']")).getText();
    }

    public String getTaxValue() {
        return driver.findElement(By.xpath("//div[@data-test='tax-label']")).getText();
    }

    public String getTotalValue() {
        return driver.findElement(By.xpath("//div[@data-test='total-label']")).getText();
    }

    public String getImage() {
        return driver.findElement(By.xpath("//img[@class='pony_express']")).getAttribute("src");
    }

    public String getCompleteHeader() {
        return driver.findElement(By.xpath("//h2[@class='complete-header']")).getText();
    }

    public String getCompleteText() {
        return driver.findElement(By.xpath("//div[@class='complete-text']")).getText();
    }

    public void clickBackHome() {
        WebElement backHome = driver.findElement(By.xpath("//button[text()='Back Home']"));
        backHome.click();
    }

}
