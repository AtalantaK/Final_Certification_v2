package API.utils;

import UI.saucedemo.config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ServerUp {

    public static void isServerUp() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");  // Включаем headless режим
        options.addArguments("--disable-gpu"); // Для Windows, чтобы избежать ошибок
        options.addArguments("--no-sandbox");  // Для CI/CD, если нужно
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        WebDriver driver = new EdgeDriver(options);
        driver.manage().window().setPosition(new Point(0, 0));
        driver.get(Config.get("API_URL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.navigate().refresh();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h2[text()='A swagger API']"))));

        driver.quit();
    }
}
