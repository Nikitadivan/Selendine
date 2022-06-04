package ru.netology.web;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;



public class CardFormTest {
    @Test
    void shouldReservationMeeting() {
        String planningDate = generateDate(4);
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id=name] input").setValue("Никита");
        $("[data-test-id=phone] input").setValue("+79120050949");
        $("[data-test-id=agreement]").click();
        $(withText("Забронировать")).click();
        $("[data-test-id=notification]").shouldHave(exactText("Успешно! Встреча успешно забронирована на "+planningDate), Duration.ofSeconds(15));
    }

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
