package ru.neisenbug;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class GoogleTest {

  @Test
  public void userCanOpen() {
    //Configuration.browser="firefox"; //либо WebDriver провайдер или DriverFactory
    open("https://duckduckgo.com/"); //закрытие не требуется
    $(By.name("q"))
            .setValue("selenide heisenbug")
    .pressEnter();

    Configuration.timeout=9000; //общий тайм-аут, только для падующих

    $$(".result").shouldHave(CollectionCondition.size(10));
  }

  @Test
  public void userExpirian() {
    open("https://www.google.ru/"); //закрытие не требуется
    $(By.name("q"))
            .setValue("selenide heisenbug")
            .pressEnter();

     $(".active")
            .shouldBe(Condition.visible) //waitUntil для ожидания точечно конкретного элемента
            .shouldHave(text("helo"),Condition.cssClass("grid"));

    sleep(5000);

  }
}
