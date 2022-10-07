import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class webApplicationTests {
    public ChromeDriver driver;
    public ChromeOptions opts;
    public String SITE = "http://localhost";
    public int PORT = 800;
    public String TITLE = "HIT - DevOps course";
    public String NAME = "Adam Aharony";

    @BeforeEach
    public void setup() {
        opts = new ChromeOptions();
        opts.addArguments("--headless");
        opts.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        opts.addArguments("--no-sandbox"); // Bypass OS security model
        opts.addArguments("--whitelisted-ips=\"\"");
        System.setProperty("webdriver.chrome.whitelistedIps", "");
        driver = new ChromeDriver(opts);
        // Navigate to site
        driver.get(String.format("%s:%s", SITE, Integer.toString(PORT)));
    }

    @AfterEach
    public void quit() {
        driver.quit();
    }

    @Test
    public void testHTTPResponseCode() {
        RestAssured.baseURI = SITE;
        RestAssured.port = PORT;

        RequestSpecification HTTPRequest = RestAssured.given();
        Response res = HTTPRequest.get();
        int actualCode = res.getStatusCode();

        Assertions.assertEquals(200, actualCode, "Correct code returned");
    }

    @Test
    public void testWebApplicationTitle() {
        String actualTitle = driver.getTitle();
        Assertions.assertEquals(TITLE, actualTitle, "Correct title returned");
    }

    @Test
    public void testSiteUI() {
        String actualName = driver.findElement(By.xpath("/html/body/h2[3]")).getText();
        Assertions.assertEquals(NAME, actualName, "Correct name returned");
    }
}
