import org.junit.Test;

public class AuthTest extends BaseTest {

    private final static String BASE_URL = "http://localhost:5000/";
    private final static String VALID_EMAIL1 = "test1@test.com";
    private final static String VALID_EMAIL2 = "test2@test.com";
    private final static String VALID_NAME1 = "test1Name";
    private final static String EMPTY = "";
    private final static String VALID_PASSWORD1 = "test1";
    private final static String VALID_PASSWORD2 = "test2";
    private final static String VALID_TITLE1 = "Welcome, test1Name!";
    private final static String VALID_TITLE2 = "Welcome, !";
    private final static String INVALID_EMAIL_FORMAT = "test1test.com";
    private final static String INVALID_EMAIL = "test11@test.com";
    private final static String INVALID_PASSWORD = "test11";
    private final static String LOGIN_ERROR_MESSAGE = "Please check your login details and try again.";
    private final static String SIGNUP_ERROR_MESSAGE = "Email address already exists. Go to login page.";
    private final static String VALIDATION_MESSAGE = "Адрес электронной почты должен содержать символ \"@\". В адресе " +
            "\"test1test.com\" отсутствует символ \"@\".";

    @Test
    public void testSuccessfulRegistrationAndLogin() {
        MainPage mainPage = new MainPage(BASE_URL);
        mainPage.clickOnSignUp();

        SignUp signUpPage = new SignUp();
        signUpPage.enterEmail(VALID_EMAIL1);
        signUpPage.enterName(VALID_NAME1);
        signUpPage.enterPassword(VALID_PASSWORD1);
        signUpPage.clickSignUpButton();

        LoginPage loginPage = new LoginPage();
        loginPage.enterEmail(VALID_EMAIL1);
        loginPage.enterPassword(VALID_PASSWORD1);
        loginPage.clickCheckbox();
        loginPage.clickLoginButton();

        ProfilePage profilePage = new ProfilePage();
        profilePage.assertTitleText(VALID_TITLE1);
        profilePage.clickOnHome();
        profilePage.clickOnProfile();
        profilePage.assertTitleText(VALID_TITLE1);
        profilePage.clickOnLogout();
    }

    @Test
    public void testSuccessfulRegistrationAndLoginWithEmptyName() {
        MainPage mainPage = new MainPage(BASE_URL);
        mainPage.clickOnSignUp();

        SignUp signUpPage = new SignUp();
        signUpPage.enterEmail(VALID_EMAIL2);
        signUpPage.enterName(EMPTY);
        signUpPage.enterPassword(VALID_PASSWORD2);
        signUpPage.clickSignUpButton();

        LoginPage loginPage = new LoginPage();
        loginPage.enterEmail(VALID_EMAIL2);
        loginPage.enterPassword(VALID_PASSWORD2);
        loginPage.clickLoginButton();

        ProfilePage profilePage = new ProfilePage();
        profilePage.assertTitleText(VALID_TITLE2);
    }

    @Test
    public void testUnsuccessfulLoginWithEmptyEmail() {
        MainPage mainPage = new MainPage(BASE_URL);
        mainPage.clickOnLogin();

        LoginPage loginPage = new LoginPage();
        loginPage.enterEmail(EMPTY);
        loginPage.enterPassword(VALID_PASSWORD1);
        loginPage.clickLoginButton();

        loginPage.assertErrorMessage(LOGIN_ERROR_MESSAGE);
    }

    @Test
    public void testUnsuccessfulLoginWithEmptyPassword() {
        MainPage mainPage = new MainPage(BASE_URL);
        mainPage.clickOnLogin();

        LoginPage loginPage = new LoginPage();
        loginPage.enterEmail(VALID_EMAIL1);
        loginPage.enterPassword(EMPTY);
        loginPage.clickLoginButton();

        loginPage.assertErrorMessage(LOGIN_ERROR_MESSAGE);
    }

    @Test
    public void testUnsuccessfulLoginWithIncorrectEmail() {
        MainPage mainPage = new MainPage(BASE_URL);
        mainPage.clickOnLogin();

        LoginPage loginPage = new LoginPage();
        loginPage.enterEmail(INVALID_EMAIL);
        loginPage.enterPassword(VALID_PASSWORD1);
        loginPage.clickLoginButton();

        loginPage.assertErrorMessage(LOGIN_ERROR_MESSAGE);
    }

    @Test
    public void testUnsuccessfulLoginWithIncorrectPassword() {
        MainPage mainPage = new MainPage(BASE_URL);
        mainPage.clickOnLogin();

        LoginPage loginPage = new LoginPage();
        loginPage.enterEmail(VALID_EMAIL1);
        loginPage.enterPassword(INVALID_PASSWORD);
        loginPage.clickLoginButton();

        loginPage.assertErrorMessage(LOGIN_ERROR_MESSAGE);
    }

    @Test
    public void testUnsuccessfulLoginWithInvalidEmailFormat() {
        MainPage mainPage = new MainPage(BASE_URL);
        mainPage.clickOnLogin();

        LoginPage loginPage = new LoginPage();
        loginPage.enterEmail(INVALID_EMAIL_FORMAT);
        loginPage.enterPassword(VALID_PASSWORD1);
        loginPage.clickLoginButton();

        loginPage.assertErrorEmailFormatMessage(VALIDATION_MESSAGE);
    }

    @Test
    public void testUnsuccessfulRegistrationWithInvalidEmailFormat() {
        MainPage mainPage = new MainPage(BASE_URL);
        mainPage.clickOnSignUp();

        SignUp signUp = new SignUp();
        signUp.enterEmail(INVALID_EMAIL_FORMAT);
        signUp.enterPassword(VALID_PASSWORD1);
        signUp.clickSignUpButton();

        signUp.assertErrorEmailFormatMessage(VALIDATION_MESSAGE);
    }

    @Test
    public void testUnsuccessfulDuplicateRegistration() {
        MainPage mainPage = new MainPage(BASE_URL);
        mainPage.clickOnSignUp();

        SignUp signUpPage = new SignUp();
        signUpPage.enterEmail(VALID_EMAIL1);
        signUpPage.enterName(VALID_NAME1);
        signUpPage.enterPassword(VALID_PASSWORD1);
        signUpPage.clickSignUpButton();

        signUpPage.assertAllegedErrorMessage(SIGNUP_ERROR_MESSAGE);
    }

    @Test
    public void testUnsuccessfulRegistrationWithEmptyEmailAndPassword() {
        MainPage mainPage = new MainPage(BASE_URL);
        mainPage.clickOnSignUp();

        SignUp signUpPage = new SignUp();
        signUpPage.clickSignUpButton();

        // Это просто моя выдумка, какой там должен быть элемент и текст я не знаю :)
        signUpPage.assertAllegedErrorMessage("Заполните обязательные поля: Email и Password.");
    }
}
