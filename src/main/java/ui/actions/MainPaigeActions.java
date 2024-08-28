package ui.actions;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import ui.pages.MainPage;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.switchTo;

public class MainPaigeActions {

    private MainPage mainPage;

    public MainPaigeActions() {
        this.mainPage = page(MainPage.class);
    }

    @Step("Fill search filed with expected data")
    public MainPaigeActions fillSearchField(String searchStr) {
        mainPage.searchField.setValue(searchStr);
        return this;
    }

    @Step("Click 'Найти' button")
    public MainPaigeActions clickSearchButton() {
        mainPage.searchButton.click();
        mainPage.progressBar.shouldBe(Condition.disappear);
        return this;
    }

    @Step("Go to search result")
    public MainPaigeActions clickSearchButton(int searchResultIndex) {
        mainPage.searchResultFragment
                .getSearchResults()
                .shouldHave(CollectionCondition.sizeGreaterThan(0))
                .get(searchResultIndex)
                .click();

        switchTo().window(1);
        mainPage.waiter.shouldBe(disappear);
        mainPage.textContainer.shouldBe(visible);
        return this;
    }
}
