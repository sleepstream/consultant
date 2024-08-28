package ui.fragments;

import com.codeborne.selenide.Container;
import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import org.openqa.selenium.support.FindBy;

@Data
public class ContentsFragment implements Container {

    @FindBy(css = "input.x-search-box__input")
    private SelenideElement searchField;

    @FindBy(css = "div.x-search-box__search-button > button")
    private SelenideElement searchButton;

    @FindBy(css = "div.x-list__item--current")
    private SelenideElement firstSearchResultInFocus;
}
