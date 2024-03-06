package coluchy;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class StepsForTest {
    public static final String REPOSITORY = "AleksShakhmatov/HW12_Allure_Reports";
    public static final int ISSUE = 1;


    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Ищем репозиторий {REPOSITORY}")
    public void searchForRepository(String REPOSITORY) {
        $(".header-search-button").click();
        $("#query-builder-test").sendKeys(REPOSITORY);
        $("#query-builder-test").submit();
    }

    @Step("Кликаем по ссылке репозитория {REPOSITORY}")
    public void clickOnRepositoryLink(String REPOSITORY) {
        $(linkText(REPOSITORY)).click();
    }

    @Step("Открываем таб Issues")
    public void openIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие Issue с номером {ISSUE}")
    public void shouldSeeIssueWithNumber(int ISSUE) {
        $(withText("#" + ISSUE)).should(Condition.exist);
    }


    @Test
    public void AnnotatedStepTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        StepsForTest steps = new StepsForTest();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE);

    }

}
