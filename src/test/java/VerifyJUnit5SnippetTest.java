import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.*;

public class VerifyJUnit5SnippetTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
        //Configuration.holdBrowserOpen = true; //не закрывать браузер после прогона
        //Configuration.timeout = 5000;
    }

    @Test
    void practiceFormTest() {

        /* Заметки для себя
        $("#submit") - поиск элемента по атрибуту id
        $(".table-responsive") - поиск по классу
        $("label[for='gender-radio-1']") - поиск элемента <label> с атрибутом 'for="gender-radio-1"'
        $("[data-filterable-for=wiki-pages-filter]") - поиск элемента по атрибуту, но когда нет уникального навзания
        */

        //Открываем страницу
        open("/selenide/selenide");
        //проверяем что страница открылась
        $(".BorderGrid-cell").shouldHave(text("Concise UI Tests with Java!"));

        //тапаем по вики
        $("#wiki-tab").click();
        //проверяем что страница c wiki открылась
        $(".heading-element").shouldHave(text("Welcome to the selenide wiki!"));

        //Открываем "Show 3 more pages…"
        $("[data-filterable-for=wiki-pages-filter]").$(".wiki-more-pages-link").$("button").click();
        //проверяем что SoftAssertions есть
        $(".wiki-pages-box").shouldHave(text("SoftAssertions"));
        //Переходим на SoftAssertions
        $("[data-filterable-for=wiki-pages-filter]").$("a[href$='SoftAssertions']").click();


        // проверка что внутри есть заголовок JUnit5 и пример кода
        $("#user-content-3-using-junit5-extend-test-class").preceding(0).shouldHave(text("3. Using JUnit5 extend test class:"));

        // Проверка наличиня сниппета кода
        $("#wiki-wrapper").shouldHave(text("""
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {
                  @Test
                  void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");
                
                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                  }
                }
                """));

    }
}