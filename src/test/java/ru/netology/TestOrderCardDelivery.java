package ru.netology;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestOrderCardDelivery {

    @Test
    void shouldSuccessDeliveryCard() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Самара");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        $("[placeholder='Дата встречи']").setValue(String.valueOf(date));
        $("[type=text][name=name]").setValue("Гусев Тимур");
        $("[type=tel][name=phone]").setValue("+79472512638");
        $("[data-test-id=agreement]").click();
        $("[class=button__text]").click();
        $(withText("Забронировать")).shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldNotAdministrativeCenter() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Кинель-Черкассы");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        $("[placeholder='Дата встречи']").setValue(String.valueOf(date));
        $("[type=text][name=name]").setValue("Гусев Тимур");
        $("[type=tel][name=phone]").setValue("+79472512638");
        $("[data-test-id=agreement]").click();
        $("[class=button__text]").click();
        $(withText("Забронировать")).shouldBe(visible, Duration.ofSeconds(15));
        $(withText("Доставка в выбранный город недоступна")).shouldBe(visible);
    }

    @Test
    void shouldEmptyFieldCity() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue(" ");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        $("[placeholder='Дата встречи']").setValue(String.valueOf(date));
        $("[type=text][name=name]").setValue("Гусев Тимур");
        $("[type=tel][name=phone]").setValue("+79472512638");
        $("[data-test-id=agreement]").click();
        $("[class=button__text]").click();
        $(withText("Забронировать")).shouldBe(visible, Duration.ofSeconds(15));
        $(withText("Поле обязательно для заполнения")).shouldBe(visible);
    }

    @Test
    void shouldInvalidDate() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Самара");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        $("[placeholder='Дата встречи']").setValue(String.valueOf(date));
        $("[type=text][name=name]").setValue("Гусев Тимур");
        $("[type=tel][name=phone]").setValue("+79472512638");
        $("[data-test-id=agreement]").click();
        $("[class=button__text]").click();
        $(withText("Забронировать")).shouldBe(visible, Duration.ofSeconds(15));
        $(withText("Заказ на выбранную дату невозможен")).shouldBe(visible);
    }

    @Test
    void shouldEmptyFieldDate() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Самара");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(" ");
        $("[type=text][name=name]").setValue("Гусев Тимур");
        $("[type=tel][name=phone]").setValue("+79472512638");
        $("[data-test-id=agreement]").click();
        $("[class=button__text]").click();
        $(withText("Забронировать")).shouldBe(visible, Duration.ofSeconds(15));
        $(withText("Неверно введена дата")).shouldBe(visible);
    }

    @Test
    void shouldEmptyFieldName() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Самара");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        $("[placeholder='Дата встречи']").setValue(String.valueOf(date));
        $("[type=text][name=name]").setValue(" ");
        $("[type=tel][name=phone]").setValue("+79472512638");
        $("[data-test-id=agreement]").click();
        $("[class=button__text]").click();
        $(withText("Забронировать")).shouldBe(visible, Duration.ofSeconds(15));
        $(withText("Поле обязательно для заполнения")).shouldBe(visible);
    }

    @Test
    void shouldEmptyFieldPhone() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Самара");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        $("[placeholder='Дата встречи']").setValue(String.valueOf(date));
        $("[type=text][name=name]").setValue("Гусев Тимур");
        $("[type=tel][name=phone]").setValue("");
        $("[data-test-id=agreement]").click();
        $("[class=button__text]").click();
        $(withText("Забронировать")).shouldBe(visible, Duration.ofSeconds(15));
        $(withText("Поле обязательно для заполнения")).shouldBe(visible);
    }

    @Test
    void shouldWithoutInstallCheckbox() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Самара");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        $("[placeholder='Дата встречи']").setValue(String.valueOf(date));
        $("[type=text][name=name]").setValue("Гусев Тимур");
        $("[type=tel][name=phone]").setValue("+79472512638");
        $("[class=button__text]").click();
        $(withText("Забронировать")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }
}