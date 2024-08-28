package ui.fragments;

import com.codeborne.selenide.Container;
import com.codeborne.selenide.ElementsCollection;
import lombok.Data;
import org.openqa.selenium.support.FindBy;

@Data
public class SearchResultFragment implements Container {

    @FindBy(css = "div.x-page-components-search-result-item")
    private ElementsCollection searchResults;
}
