package ui.assertions;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import ui.pages.MainPage;

import static com.codeborne.selenide.Selenide.page;
import static org.assertj.core.api.Assertions.assertThat;

public class MainPageAssertions {

    private MainPage mainPage;

    public MainPageAssertions() {
        this.mainPage = page(MainPage.class);
    }

    @Step("Check Tab name contain expected text ignoring case")
    public MainPageAssertions checkTabNameContains(String expectedTitle) {
        assertThat(Selenide.title())
                .withFailMessage("Actual title '%s' doesn't contain expected '%s' text",
                        Selenide.title(), expectedTitle)
                .containsIgnoringCase(expectedTitle);
        return this;
    }

}
