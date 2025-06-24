import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.*;

public class VerifyJUnit5SnippetTest {

    @BeforeAll
    static void setupConfig() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void practiceFormTest() {

        open("/selenide/selenide");
        $(".BorderGrid-cell").shouldHave(text("Concise UI Tests with Java!"));

        $("#wiki-tab").click();
        $(".heading-element").shouldHave(text("Welcome to the selenide wiki!"));

        $("[data-filterable-for=wiki-pages-filter]").$(".wiki-more-pages-link").$("button").click();
        $(".wiki-pages-box").shouldHave(text("SoftAssertions"));
        $("[data-filterable-for=wiki-pages-filter]").$("a[href$='SoftAssertions']").click();

        $("#user-content-3-using-junit5-extend-test-class").preceding(0).shouldHave(text("3. Using JUnit5 extend test class:"));

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
