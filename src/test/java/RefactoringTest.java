import org.junit.Test;
import org.openqa.selenium.Keys;
import pages.InsuranceCatalogPage;
import pages.InsurancePage;
import pages.IssuePage;
import pages.MainPage;

import static org.junit.Assert.assertEquals;

public class RefactoringTest extends BaseTest {

    @Test
    public void newInsuranceTest() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.buttonCookieClose.click();                                                             // Закрытие cookie
                                                                                                        // Главная
        mainPage.selectMainMenu("Страхование");
        mainPage.selectInsuranceMenu("Перейти в каталог");
                                                                                                        // Каталог страхования
        InsuranceCatalogPage insuranceCatalogPage = new InsuranceCatalogPage(driver);
        assertEquals("Страхование для путешественников", insuranceCatalogPage.titleTravel.getText());
        insuranceCatalogPage.sendButton.click();
                                                                                                        // Выбор полиса
        InsurancePage insurancePage = new InsurancePage(driver);
        insurancePage.minCardProgram.isDisplayed();
        insurancePage.buttonIssue.click();
                                                                                                        // Оформление
        IssuePage issuePage = new IssuePage(driver);
        fillField(issuePage.firstPersonSurname, "Иванов");
        fillField(issuePage.firstPersonName, "Иван");
        fillField(issuePage.firstPersonBirthDate, "20.09.1997");
        issuePage.firstPersonBirthDate.sendKeys(Keys.TAB);
        fillField(issuePage.personLastName, "Иванов");
        fillField(issuePage.personFirstName, "Иван");
        fillField(issuePage.personMiddleName, "Иванович");
        fillField(issuePage.personBirthDate, "20.09.1997");
        issuePage.firstPersonBirthDate.sendKeys(Keys.TAB);
        fillField(issuePage.passportSeries, "1234");
        fillField(issuePage.passportNumber, "567890");
        fillField(issuePage.documentDate, "20.12.2019");
        issuePage.firstPersonBirthDate.sendKeys(Keys.TAB);
        fillField(issuePage.documentIssue, "УФМС РОССИИ");
        issuePage.buttonMan.click();
        issuePage.buttonContinue.click();
        issuePage.checkErrorMessage("Поле не заполнено.", issuePage.phoneError.getText());
        issuePage.checkErrorMessage("Поле не заполнено.", issuePage.emailError.getText());
        issuePage.checkErrorMessage("Поле не заполнено.", issuePage.repeatEmailError.getText());
        issuePage.checkErrorMessage("При заполнении данных произошла ошибка", issuePage.errorTitle.getText());
    }
}