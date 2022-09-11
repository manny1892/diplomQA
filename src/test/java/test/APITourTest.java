package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.APIDBHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import pages.TourPage;

public class APITourTest {
    TourPage tourPage = new TourPage();

    @AfterEach
    void clearDB() {
        APIDBHelper.clearDB();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("87) HappyPath PayCard Status Approved API Test Response 200")
    void shouldResponseTwoHundredByPayPayCardApproved() {
        Assertions.assertAll(
                APIDBHelper::buyApprovedPayCardAPI,
                () -> tourPage.payApprovedStatusAssertion(),
                () -> tourPage.payAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("88) HappyPath PayCard Status Declined API Test Response 200")
    void shouldResponseTwoHundredByPayPayCardDeclined() {
        Assertions.assertAll(
                APIDBHelper::buyDeclinedPayCardAPI,
                () -> tourPage.payDeclinedStatusAssertion(),
                () -> tourPage.payAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("89) HappyPath CreditCard Status Approved API Test Response 200")
    void shouldResponseTwoHundredByPayCreditCardApproved() {
        Assertions.assertAll(
                APIDBHelper::buyApprovedCreditCardAPI,
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("90) HappyPath CreditCard Status Declined API Test Response 200")
    void shouldResponseTwoHundredByPayCreditCardDeclined() {
        Assertions.assertAll(
                APIDBHelper::buyDeclinedCreditCardAPI,
                () -> tourPage.creditDeclinedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("91) Empty PayCard API Test Response 400")
    void shouldResponseFourHundredByPayEmptyPayCard() {
        Assertions.assertAll(
                APIDBHelper::buyEmptyPayCardAPI,
                () -> tourPage.payDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("92) Approved PayCard And Random Invalid Other Field API Test Response 400")
    void shouldResponseFourHundredByApprovedPayCardAndRandomInvalidOtherField() {
        Assertions.assertAll(
                APIDBHelper::buyApprovedPayCardAndRandomInvalidOtherField,
                () -> tourPage.payDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("93) Approved PayCard And Empty Other Field API Test Response 400")
    void shouldResponseFourHundredByApprovedPayCardAndEmptyOtherField() {
        Assertions.assertAll(
                APIDBHelper::buyApprovedPayCardAndEmptyOtherField,
                () -> tourPage.payDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("94) Random PayCard Number And Valid Other Field API Test Response 400")
    void shouldResponseFourHundredByRandomCardNumberAndValidOtherField() {
        Assertions.assertAll(
                APIDBHelper::buyRandomCardNumberAndValidOtherField,
                () -> tourPage.payDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("95) Empty CreditCard API Test Response 400")
    void shouldResponseFourHundredByPayEmptyCreditCard() {
        Assertions.assertAll(
                APIDBHelper::buyEmptyCreditCardAPI,
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("96) Approved CreditCard And Random Invalid Other Field API Test Response 400")
    void shouldResponseFourHundredByApprovedCreditCardAndRandomInvalidOtherField() {
        Assertions.assertAll(
                APIDBHelper::buyApprovedCreditCardAndRandomInvalidOtherField,
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("97) Approved CreditCard And Empty Other Field API Test Response 400")
    void shouldResponseFourHundredByApprovedCreditCardAndEmptyOtherField() {
        Assertions.assertAll(
                APIDBHelper::buyApprovedCreditCardAndEmptyOtherField,
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("98) Random CreditCard Number And Valid Other Field API Test Response 400")
    void shouldResponseFourHundredByRandomCreditCardNumberAndValidOtherField() {
        Assertions.assertAll(
                APIDBHelper::buyRandomCreditCardNumberAndValidOtherField,
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }
}
