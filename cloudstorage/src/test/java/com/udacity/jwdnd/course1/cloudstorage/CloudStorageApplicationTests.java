package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CloudStorageApplicationTests {

    public String baseURL;
    String username = "testUser";
    String password = "testPassword";

    String testTitle = "test title";
    String testDescription = "test description";
    String testTitleEdit = "edited test title";
    String testDescriptionEdit = "edited test description";

    String testURL = "www.amazon.com";
    String testUsername = "NotAnotherUser";
    String testPassword = "HereWeGoAgain";

    String testURLEdit = "www.ebay.com";
    String testUsernameEdit = "YouAreSoRight";
    String testPasswordEdit = "WhySoSerious?";

    @LocalServerPort
    private int port;
    private WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
        baseURL = "http://localhost:" + port;
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    @Order(1)
    public void testUnauthorizedAccess() {
        driver.get(baseURL + "/home");
        Assertions.assertEquals("Login", driver.getTitle());

        driver.get(baseURL + "/login");
        Assertions.assertEquals("Login", driver.getTitle());

        driver.get(baseURL + "/signup");
        Assertions.assertEquals("Sign Up", driver.getTitle());
    }

    @Test
    @Order(2)
    public void testUserSignupProcess() {
        driver.get(baseURL + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup("John", "Doe", username, password);
        Assertions.assertTrue(signupPage.returnSuccessMsg().contains("You successfully signed up"));

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        Assertions.assertEquals("Home", driver.getTitle());

        HomePage homePage = new HomePage(driver);
        homePage.logout();
        Assertions.assertTrue(homePage.returnLogoutMsg().contains("You have been logged out"));

        driver.get(baseURL + "/home");
        Assertions.assertEquals("Login", driver.getTitle());
    }

    @Test
    @Order(3)
    public void testNoteCreate() {
        driver.get(baseURL + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        Assertions.assertEquals("Home", driver.getTitle());

        NotesPage notesPage = new NotesPage(driver);
        notesPage.insertNewNote(testTitle, testDescription);
        Assertions.assertTrue(notesPage.returnSuccessMsg().contains("Your note was successfully saved"));

        notesPage.returnHome();
        Assertions.assertEquals(testTitle, notesPage.getNoteTitle());
        Assertions.assertEquals(testDescription, notesPage.getNoteDescription());
    }

    @Test
    @Order(4)
    public void testNoteEdit() {
        driver.get(baseURL + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        Assertions.assertEquals("Home", driver.getTitle());

        NotesPage notesPage = new NotesPage(driver);
        notesPage.editNote(testTitleEdit, testDescriptionEdit);
        Assertions.assertTrue(notesPage.returnSuccessMsg().contains("Your note was successfully saved"));

        notesPage.returnHome();
        Assertions.assertEquals(testTitleEdit, notesPage.getNoteTitle());
        Assertions.assertEquals(testDescriptionEdit, notesPage.getNoteDescription());
    }

    @Test
    @Order(5)
    public void testNoteDelete() {
        driver.get(baseURL + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        Assertions.assertEquals("Home", driver.getTitle());

        NotesPage notesPage = new NotesPage(driver);
        notesPage.deleteNote();
        Assertions.assertTrue(notesPage.returnSuccessMsg().contains("Your note was successfully deleted"));

        notesPage.returnHome();
        Assertions.assertEquals("Example Note Title", notesPage.getNoteTitle());
        Assertions.assertEquals("Example Note Description", notesPage.getNoteDescription());
    }

    @Test
    @Order(6)
    public void testCredentialCreate() {
        driver.get(baseURL + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        Assertions.assertEquals("Home", driver.getTitle());

        CredentialsPage credentialsPage = new CredentialsPage(driver);
        credentialsPage.insertNewCredential(testURL, testUsername, testPassword);
        Assertions.assertTrue(credentialsPage.returnSuccessMsg().contains("Your credential was successfully saved"));

        credentialsPage.returnHome();
        Assertions.assertEquals(testURL, credentialsPage.getCredentialsUrl());
        Assertions.assertEquals(testUsername, credentialsPage.getCredentialsUsername());
        Assertions.assertNotEquals(testPassword, credentialsPage.getCredentialsPassword());
    }

    @Test
    @Order(7)
    public void testCredentialEdit() {
        driver.get(baseURL + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        Assertions.assertEquals("Home", driver.getTitle());

        CredentialsPage credentialsPage = new CredentialsPage(driver);
        Assertions.assertEquals(testPassword, credentialsPage.returnUncryptedPassword());

        credentialsPage.editCredential(testURLEdit, testUsernameEdit, testPasswordEdit);
        Assertions.assertTrue(credentialsPage.returnSuccessMsg().contains("Your credential was successfully saved"));

        credentialsPage.returnHome();
        Assertions.assertEquals(testURLEdit, credentialsPage.getCredentialsUrl());
        Assertions.assertEquals(testUsernameEdit, credentialsPage.getCredentialsUsername());
        Assertions.assertNotEquals(testPasswordEdit, credentialsPage.getCredentialsPassword());
    }

    @Test
    @Order(8)
    public void testCredentialDelete() {
        driver.get(baseURL + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        Assertions.assertEquals("Home", driver.getTitle());

        CredentialsPage credentialsPage = new CredentialsPage(driver);
        credentialsPage.deleteCredential();
        Assertions.assertTrue(credentialsPage.returnSuccessMsg().contains("Your credential was successfully deleted"));

        credentialsPage.returnHome();
        Assertions.assertEquals("Example Credential URL", credentialsPage.getCredentialsUrl());
        Assertions.assertEquals("Example Credential Username", credentialsPage.getCredentialsUsername());
        Assertions.assertEquals("Example Credential Password", credentialsPage.getCredentialsPassword());
    }
}

