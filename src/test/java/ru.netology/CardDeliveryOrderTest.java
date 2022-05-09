package ru.netology;
import com.codeborne.selenide.Condition;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryOrderTest {
    @BeforeEach
    public void openPage() {
        open("http://localhost:9999/");
    }

    @Test
    void schouldRegistredAccount() {
        open("http://localhost:9999/");
        $("placeholder=\"Город\"").setValue("Краснодар");
        $("name=\"phone\"").setValue("+79912345678")
        $("name=\"name\"").setValue("Иван Иванов");
        $("data-test-id=\"agreement\"").click();
        $("//*[text()=\"Забронировать\"]").click();
        $(withText("Встреча успешно забронирована на ")).should(visible);

    }

}
