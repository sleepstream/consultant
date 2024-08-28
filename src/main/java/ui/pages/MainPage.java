package ui.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ui.fragments.SearchResultFragment;

public class MainPage {

    @FindBy(css = "div.busy")
    public SelenideElement waiter;

    @FindBy(css = "title")
    public SelenideElement title;

    @FindBy(css ="div.x-page-search-plus-results")
    public SearchResultFragment searchResultFragment;

    @FindBy(css = "input.x-search-box__input")
    public SelenideElement searchField;

    @FindBy(css = "div.x-search-box__search-button > button")
    public SelenideElement searchButton;

    @FindBy(css = "div.x-page-search-plus-progress__progress")
    public SelenideElement progressBar;

    @FindBy(css = "div.textContainer")
    public SelenideElement textContainer;

    @FindBy(css = "div.document.content")
    public SelenideElement textContent;
}
