package ru.netology;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestOrderCardDelivery {
    private String date;

    public static void currentDate() {
        LocalDate date = LocalDate.now();
        date = date.plusDays(3);
    }

    @Test
    void shouldSuccessSubmissionOfForm() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Самара");
        $("[placeholder='Дата встречи']").setValue(date);
        $("[type=text][name=name]").setValue("Гусев Тимур");
        $("[type=tel][name=phone]").setValue("+79472512638");
        $("[data-test-id=agreement]").click();
        $("[class=button__text]").click();
        $(withText("Забронировать")).shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Успешно!")).shouldBe(hidden);
    }
}
