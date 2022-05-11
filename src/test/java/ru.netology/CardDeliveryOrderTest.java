package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryOrderTest {
    @BeforeEach
    public void openPage() {
        open("http://localhost:9999/");
    }

    @Test
    void schouldRegistredAccount() {
        Configuration.timeout = 15000;
        $("[placeholder='Город']").setValue("Краснодар");
        $(".menu-item__control").click();
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("+77412369852");
        $(".checkbox__box").click();
        $(withText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible);
    }


    @Test
    public void shouldSuccessfullyWithManualFilling() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String meetingDate = LocalDate.now().plusDays(6).format(formatter);
        $("[placeholder='Город']").setValue("Санкт-Петербург");
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(meetingDate);
        $("[name='name']").setValue("Иван Иванов");
        $("[name='phone']").setValue("+77412369852");
        $(".checkbox__box").click();
        $(withText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(Condition.visible, Duration.ofMillis(15000));
        $("[data-test-id='notification'] .notification__content")
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + meetingDate));
    }

    @Test
    public void shouldSuccessfullyWithManualFillingThreeDays() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String meetingDate = LocalDate.now().plusDays(3).format(formatter);
        $("[placeholder='Город']").setValue("Санкт-Петербург");
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(meetingDate);
        $("[name='name']").setValue("Иван Иванов");
        $("[name='phone']").setValue("+77412369852");
        $(".checkbox__box").click();
        $(withText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(Condition.visible, Duration.ofMillis(15000));
        $("[data-test-id='notification'] .notification__content")
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + meetingDate));
    }

    @Test
    public void shouldNoNumberTell(){
        Configuration.timeout = 15000;
        $("[placeholder='Город']").setValue("Краснодар");
        $(".menu-item__control").click();
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("");
        $(".checkbox__box").click();
        $(withText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldNoName(){
        Configuration.timeout = 15000;
        $("[placeholder='Город']").setValue("Краснодар");
        $(".menu-item__control").click();
        $("[name='name']").setValue("");
        $("[name='phone']").setValue("+77412369852");
        $(".checkbox__box").click();
        $(withText("Забронировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

//    @Test
//    public void shouldNoCity(){
//        Configuration.timeout = 15000;
//        $("[placeholder='Город']").setValue("");
//        $(".menu-item__control").click();
//        $("[name='name']").setValue("Иванов Иван");
//        $("[name='phone']").setValue("+77412369852");
//        $(".checkbox__box").click();
//        $("[placeholder='Город']").setValue("");
//        $(withText("Забронировать")).click();
//        $("[data-test-id='Город'].input_invalid .input__sub").shouldHave(Condition.exactText("Доставка в выбранный город недоступна"));
//    }
//    @Test
//    public void shouldShowErrorNoSymbol() {
//        open("http://localhost:9999/");
//        Configuration.timeout = 15000;
//        $("[placeholder='Город']").setValue("Krasnodar");
//        $(".menu-item__control").click();
//        $("[data-test-id=name] input").setValue("");
//        $("[data-test-id=phone] input").setValue("");
//        $(".checkbox__box").click();
//        $(withText("Забронировать")).click();
//        $("[data-test-id=''].input_invalid .input__sub").shouldHave(Condition.exactText("Поля обязательные для заполнения"));
//    }





}
