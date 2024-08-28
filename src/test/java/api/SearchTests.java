package api;

import base.SelenideBase;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.actions.DocumentPageActions;
import ui.actions.MainPaigeActions;
import ui.assertions.DocumentPageAssertions;

import static com.codeborne.selenide.Selenide.open;

public class SearchTests extends SelenideBase {

	private MainPaigeActions mainPaigeActions;
	private DocumentPageAssertions documentPageAssertions;
	private DocumentPageActions documentPageActions;
	private final String SEARCH_TEXT = "нк ч2";
	private final String SEARCH_CONTENT_TEXT = "Статья 163";
	private final String SELECT_TEXT_LIMIT_TO = "Статья 164";

	@BeforeMethod
	public void beforeMethod() {
		open("/cons/");
		mainPaigeActions = new MainPaigeActions();
		documentPageAssertions = new DocumentPageAssertions();
		documentPageActions = new DocumentPageActions();
	}

	@AfterMethod
	public void afterMethod() {

	}

	@Test(description = "Search, select and print text from article")
	@Feature("")
	@Story("")
	@Severity(SeverityLevel.CRITICAL)
	public void searchSelectAndPrintPartOfArticle() {
		mainPaigeActions
				//1
				.fillSearchField(SEARCH_TEXT)
				.clickSearchButton()
				//2-3
				.clickSearchButton(0);

		//4a
		documentPageAssertions
			.checkTabNameContains("налоговый кодекс")
			.checkTabNameContains("часть вторая");

		documentPageActions.switchToFrameWithDocumentContent();

		//4b
		documentPageAssertions
			.checkTextContains("налоговый кодекс")
			.checkTextContains("часть вторая");

		documentPageActions.switchToMainFrame();
				//5
		documentPageAssertions.checkSearchFieldContains(SEARCH_TEXT);

		//6
		documentPageActions
				.clickContentsButton()
				.searchInContents(SEARCH_CONTENT_TEXT)
				.clickFirstSearchResultInContents();

		//9
		documentPageActions
				.switchToMainFrame()
				.clickEditionsButton();

		documentPageAssertions
				.checkEditionsContainsNotActive();
	}
}