import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.Assert.assertEquals;

public class LoginPage {

    private final SelenideElement EMAIL_FIELD = $x("/html/body/section/div[2]/div/div/div/form/div[1]/div/input");
    private final SelenideElement PASSWORD_FIELD = $x("/html/body/section/div[2]/div/div/div/form/div[2]/div/input");
    private final SelenideElement REMEMBER_ME_CHECKBOX = $x("/html/body/section/div[2]/div/div/div/form/div[3]/label");
    private final SelenideElement LOGIN_BUTTON = $x("/html/body/section/div[2]/div/div/div/form/button");
    private final SelenideElement ERROR_MASSAGE = $x("/html/body/section/div[2]/div/div/div/div");

    public void enterEmail(String email) {
        EMAIL_FIELD.val(email);
    }

    public void enterPassword(String password) {
        PASSWORD_FIELD.val(password);
    }

    public void clickCheckbox() {
        REMEMBER_ME_CHECKBOX.click();
    }

    public void clickLoginButton() {
        LOGIN_BUTTON.click();
    }

    public String getTitleText() {
        return ERROR_MASSAGE.getText();
    }

    public void assertErrorMessage(String expectedText) {
        String actualText = getTitleText();
        assertEquals(expectedText, actualText);
    }

    public void assertErrorEmailFormatMessage(String expectedErrorMessage) {
        String errorMessage = $("input[name='email']").getAttribute("validationMessage");
        assertEquals(expectedErrorMessage, errorMessage);
    }
}
