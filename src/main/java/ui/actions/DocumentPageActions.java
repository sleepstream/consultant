package ui.actions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import ui.pages.DocumentPage;

import static com.codeborne.selenide.Selenide.page;

public class DocumentPageActions {

    private DocumentPage documentPage;

    public DocumentPageActions() {
        this.documentPage = page(DocumentPage.class);
    }

    @Step("Fill search filed with expected data")
    public DocumentPageActions fillSearchField(String searchStr) {
        documentPage.searchPanel.getSearchField().setValue(searchStr);
        return this;
    }

    @Step("Click 'Найти' button")
    public DocumentPageActions clickSearchButton() {
        documentPage.searchPanel.getSearchButton().click();
        documentPage.progressBar.shouldBe(Condition.disappear);
        return this;
    }

    @Step("Click 'Оглавление' button")
    public DocumentPageActions clickContentsButton() {
        documentPage.contentButton.click();
        return this;
    }

    @Step("Search expected text in contents")
    public DocumentPageActions searchInContents(String expectedText) {
        documentPage.contentsFragment.getSearchField().setValue(expectedText);
        documentPage.contentsFragment.getSearchButton().click();
        return this;
    }

    @Step("Click on fist search result in contents")
    public DocumentPageActions clickFirstSearchResultInContents() {
        documentPage.contentsFragment.getFirstSearchResultInFocus().click();
        return this;
    }

    @Step("Switch to frame with document content")
    public DocumentPageActions switchToFrameWithDocumentContent() {
        Selenide.switchTo().frame(documentPage.textContainer.findElement(By.tagName("iframe")));
        return this;
    }

    @Step("Switch to main frame")
    public DocumentPageActions switchToMainFrame() {
        Selenide.switchTo().parentFrame();
        return this;
    }

    @Step("Select text fragment of article")
    public void selectArticleText(String searchContentTextFrom, String searchContentTextTo) {
        SelenideElement firstElementOfArticle = documentPage.textContent
                .find(By.xpath(".//div[contains(@class, 'icon i_1') and contains(@name, '" + searchContentTextFrom + "')]/parent::a/parent::div"));
        SelenideElement firstElementOfNextArticle = documentPage.textContent
                .find(By.xpath(".//div[contains(@class, 'icon i_1') and contains(@name, '" + searchContentTextTo + "')]/parent::a/parent::div"));

        firstElementOfArticle.scrollIntoView(false);

        ((JavascriptExecutor) Selenide.webdriver().driver().getWebDriver()).executeScript("var range = document.createRange();\n" +
                "        var sel = window.getSelection();\n" +
                "        sel.removeAllRanges();\n" +
                "        range.selectNodeContents(arguments[0]);\n" +
                "        sel.addRange(range);", firstElementOfArticle.toWebElement());

        Selenide.switchTo().parentFrame();

        documentPage.printButton.click();
    }


    @Step("Click 'Редакции' button")
    public DocumentPageActions clickEditionsButton() {
        documentPage.editionsButton.click();
        return  this;
    }
}

