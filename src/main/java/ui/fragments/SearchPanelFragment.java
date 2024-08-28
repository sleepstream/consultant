package ui.fragments;

import com.codeborne.selenide.Container;
import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import org.openqa.selenium.support.FindBy;

@Data
public class SearchPanelFragment implements Container {

    @FindBy(css = "form.filterForm input")
    private SelenideElement searchField;

    @FindBy(css = "form.filterForm button")
    private SelenideElement searchButton;
}
