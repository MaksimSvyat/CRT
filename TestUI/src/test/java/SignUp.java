import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.Assert.assertEquals;

public class SignUp {

    private final SelenideElement EMAIL_FIELD = $x("/html/body/section/div[2]/div/div/div/form/div[1]/div/input");
    private final SelenideElement NAME_FIELD = $x("/html/body/section/div[2]/div/div/div/form/div[2]/div/input");
    private final SelenideElement PASSWORD_FIELD = $x("/html/body/section/div[2]/div/div/div/form/div[3]/div/input");
    private final SelenideElement SIGNUP_BUTTON = $x("/html/body/section/div[2]/div/div/div/form/button");
    private final SelenideElement SIGNUP_ERROR_MASSAGE = $x("/html/body/section/div[2]/div/div/div/div");
    private final SelenideElement ALLEGED_ERROR_MASSAGE = $x("/html/body/section/div[2]/div/div/div/div");

    public void enterEmail(String email) {
        EMAIL_FIELD.val(email);
    }

    public void enterName(String name) {
        NAME_FIELD.val(name);
    }

    public void enterPassword(String password) {
        PASSWORD_FIELD.val(password);
    }

    public void clickSignUpButton() {
        SIGNUP_BUTTON.click();
    }

    public String getAllegedMessageText() {
        return ALLEGED_ERROR_MASSAGE.getText();
    }

    public void assertAllegedErrorMessage(String expectedText) {
        String actualText = getAllegedMessageText();
        assertEquals(expectedText, actualText);
    }

    public String getMessageText() {
        return SIGNUP_ERROR_MASSAGE.getText();
    }

    public void assertErrorMessage(String expectedText) {
        String actualText = getMessageText();
        assertEquals(expectedText, actualText);
    }

    public void assertErrorEmailFormatMessage(String expectedErrorMessage) {
        String errorMessage = $("input[name='email']").getAttribute("validationMessage");
        assertEquals(expectedErrorMessage, errorMessage);
    }
}
