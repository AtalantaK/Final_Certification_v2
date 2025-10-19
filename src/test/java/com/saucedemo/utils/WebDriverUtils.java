package com.saucedemo.utils;

import com.saucedemo.config.Config;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

public class WebDriverUtils {
    public static WebDriver driverSetUp() {
        EdgeOptions options = new EdgeOptions();
//        options.addArguments("--headless");  // Включаем headless режим
//        options.addArguments("--disable-gpu"); // Для Windows, чтобы избежать ошибок
//        options.addArguments("--no-sandbox");  // Для CI/CD, если нужно
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        WebDriver driver = new EdgeDriver(options);
        driver.manage().window().setPosition(new Point(0, 0));
        driver.get(Config.get("URL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }

    public static void driverQuit(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}
