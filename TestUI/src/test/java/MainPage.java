import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    private final SelenideElement LOGIN_LINK = $x("//*[@id=\"navbarMenuHeroA\"]/div/a[2]");
    private final SelenideElement SINGUP_LINK = $x("//*[@id=\"navbarMenuHeroA\"]/div/a[3]");

    public MainPage(String url) {
        Selenide.open(url);
    }

    public void clickOnLogin() {
        LOGIN_LINK.click();
    }

    public void clickOnSignUp() {
        SINGUP_LINK.click();
    }
}
