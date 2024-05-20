import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static org.junit.Assert.assertEquals;

public class ProfilePage {

    private final SelenideElement LOGOUT_LINK = $x("//*[@id=\"navbarMenuHeroA\"]/div/a[3]");
    private final SelenideElement PROFILE_LINK = $x("//*[@id=\"navbarMenuHeroA\"]/div/a[2]");
    private final SelenideElement HOME_LINK = $x("//*[@id=\"navbarMenuHeroA\"]/div/a[1]");
    private final SelenideElement TITLE = $x("/html/body/section/div[2]/div/h1");

    public void clickOnHome() {
        HOME_LINK.click();
    }

    public void clickOnLogout() {
        LOGOUT_LINK.click();
    }

    public void clickOnProfile() {
        PROFILE_LINK.click();
    }

    public String getTitleText() {
        return TITLE.getText();
    }

    public void assertTitleText(String expectedText) {
        String actualText = getTitleText();
        assertEquals(expectedText, actualText);
    }
}
