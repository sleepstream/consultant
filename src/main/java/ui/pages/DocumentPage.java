package ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ui.fragments.ContentsFragment;
import ui.fragments.SearchPanelFragment;

public class DocumentPage {

    @FindBy(css = "div.busy")
    public SelenideElement waiter;

    @FindBy(css = "title")
    public SelenideElement title;

    @FindBy(css = "div.searchPanel")
    public SearchPanelFragment searchPanel;

    @FindBy(css = "div.x-page-search-plus-progress__progress")
    public SelenideElement progressBar;

    @FindBy(css = "div.textContainer")
    public SelenideElement textContainer;

    @FindBy(css = "div.document.content")
    public SelenideElement textContent;

    @FindBy(css = "button[test='context-panel-contents-button']")
    public SelenideElement contentButton;

    @FindBy(css = "div.page.contents")
    public ContentsFragment contentsFragment;

    @FindBy(css = "div.documentLoader")
    public SelenideElement contentLoader;

    @FindBy(css = "div.documentConnectionLost")
    public SelenideElement documentConnectionLost;

    @FindBy(css = "button[test='context-panel-print-button']")
    public SelenideElement printButton;

    @FindBy(css = "button[test='context-panel-editions-button']")
    public SelenideElement editionsButton;

    @FindBy(css = "div.edition")
    public ElementsCollection editionsList;

    @FindBy(css = "div.edition div.redDate")
    public ElementsCollection editionsListNotActive;
}
