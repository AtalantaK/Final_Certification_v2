package com.saucedemo.pages;

import com.saucedemo.utils.ScreenshotUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.util.List;

// для работы с товарами на странице товаров
public class ProductsPage {

    private WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getListItems() {
        return driver.findElements(By.xpath("//div[@class='inventory_item']"));
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

    public String getImage(WebElement item) {
        return item.findElement(By.xpath(".//img[@class='inventory_item_img']")).getAttribute("src");
    }

    @Step("Добавить товар в корзину по имени с Products страницы")
    public void addItemToCartByName(String itemName) throws IOException {
        WebElement addToCart = driver.findElement(By.xpath("//div[text()='" + itemName + "']/../../../div[@class='pricebar']/button[text()='Add to cart']"));
        addToCart.click();
        ScreenshotUtils.makeScreeshot(driver);
    }

    @Step("Удалить товар из корзины по имени с Products страницы")
    public void removeItemFromCartByName(String itemName) throws IOException {
        WebElement removeFromCart = driver.findElement(By.xpath("//div[text()='" + itemName + "']/../../../div[@class='pricebar']/button[text()='Remove']"));
        removeFromCart.click();
        ScreenshotUtils.makeScreeshot(driver);
    }

    public String getItemDescriptionByName(String itemName) {
        return driver.findElement(By.xpath("//div[text()='" + itemName + "']/../../div[@class='inventory_item_desc']")).getText();
    }

    public String getItemPriceByName(String itemName) {
        return driver.findElement(By.xpath("//div[text()='" + itemName + "']/../../../div[@class='pricebar']/div[@class='inventory_item_price']")).getText();
    }

    public String getItemImageByName(String itemName) {
        return driver.findElement(By.xpath("//div[text()='" + itemName + "']/../../../../div[@class='inventory_item_img']/a/img")).getAttribute("src");
    }

    @Step("Открыть страницу с товаром")
    public void openItemByName(String itemName) throws IOException {
        WebElement item = driver.findElement(By.xpath("//div[text()='" + itemName + "']"));
        item.click();
        ScreenshotUtils.makeScreeshot(driver);
    }

    @Step("Выбрать тип сортировки по тексту")
    public void chooseSortingMethodByName(String sortingMethod) throws IOException {
        WebElement sorting = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        Select select = new Select(sorting);
        select.selectByVisibleText(sortingMethod);
        ScreenshotUtils.makeScreeshot(driver);
    }

    @Step("Выбрать тип сортировки по индексу")
    public void chooseSortingMethodByIndex(int index) throws IOException {
        WebElement sorting = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        Select select = new Select(sorting);
        select.selectByIndex(index - 1);
        ScreenshotUtils.makeScreeshot(driver);
    }

    @Step("Выбрать тип сортировки по значению")
    public void chooseSortingMethodByValue(String value) throws IOException {
        WebElement sorting = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        Select select = new Select(sorting);
        select.selectByValue(value);
        ScreenshotUtils.makeScreeshot(driver);
    }
}
