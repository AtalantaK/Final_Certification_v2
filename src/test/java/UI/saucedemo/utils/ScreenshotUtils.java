package UI.saucedemo.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ScreenshotUtils {
    @Step("Скриншот")
    public static void makeScreeshot(WebDriver driver) throws IOException {
        Screenshot screenshot = new AShot().takeScreenshot(driver);
        //ImageIO.write(screenshot.getImage(), "PNG", new File("src/test/java/screenshots/" + screenshotName + "_AR.png"));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(screenshot.getImage(), "PNG", baos);
        byte[] screenshotBytes = baos.toByteArray();

        // Прикрепляем скриншот к Allure отчету
        Allure.addAttachment("Актуальный результат", "image/png", new ByteArrayInputStream(screenshotBytes), ".png");
    }
}
