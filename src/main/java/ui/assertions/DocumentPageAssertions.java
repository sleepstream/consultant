package ui.assertions;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import ui.pages.DocumentPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static org.assertj.core.api.Assertions.assertThat;

public class DocumentPageAssertions {

    private DocumentPage documentPage;

    public DocumentPageAssertions() {
        this.documentPage = page(DocumentPage.class);
    }

    @Step("Check Tab name contain expected text ignoring case")
    public DocumentPageAssertions checkTabNameContains(String expectedTitle) {
        assertThat(Selenide.title())
                .withFailMessage("Actual title '%s' doesn't contain expected '%s' text",
                        Selenide.title(), expectedTitle)
                .containsIgnoringCase(expectedTitle);
        return this;
    }


    @Step("Check document contains expected text ignoring case")
    public DocumentPageAssertions checkTextContains(String expectedText) {
        assertThat(documentPage.textContent.getText())
                .withFailMessage("Actual text doesn't contain expected '%s' text", expectedText)
                .containsIgnoringCase(expectedText);
        return this;
    }

    @Step("Check search field contains expected data")
    public DocumentPageAssertions checkSearchFieldContains(String expectedText) {
        documentPage.searchPanel.getSearchField().shouldHave(value(expectedText));
        return this;
    }

    @Step("Check document fully loaded")
    public void checkDocumentFullyLoaded() {
        documentPage.textContent.scrollIntoView(false);
        documentPage.textContent.scrollIntoView(true);
        documentPage.contentLoader.shouldNotBe(visible);
        documentPage.documentConnectionLost.shouldNotBe(visible);
    }

    @Step("Check editions contains not active")
    public DocumentPageAssertions checkEditionsContainsNotActive() {
        StringBuilder logs = new StringBuilder();
        List<SelenideElement> notActiveEditions = documentPage.editionsListNotActive.stream().filter(item -> {
            if (item.getText().matches(".*[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}.*")) {
                String cutDate = item.getText().replaceAll(".*([0-9]{2}\\.[0-9]{2}\\.[0-9]{4}).*", "$1");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                LocalDate date = LocalDate.parse(cutDate, formatter);
                if (date.isAfter(LocalDate.now())) {
                    logs.append(cutDate);
                    logs.append("\n");
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());

        assertThat(notActiveEditions)
                .hasSizeGreaterThan(1);

        Allure.addAttachment("Not yet active editions: ", logs.toString());

        return this;
    }
}
