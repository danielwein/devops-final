import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class webApplicationTests {
    @Test
    public void testHTTPResponseCode() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-dev-shm-usage"); 
        options.addArguments("--no-sandbox"); 
        options.addArguments("--whitelisted-ips=\"\"");
        System.setProperty("webdriver.chrome.whitelistedIps", "");
        ChromeDriver driver = new ChromeDriver(options);
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 800;

        RequestSpecification HTTPRequest = RestAssured.given();
        Response res = HTTPRequest.get();
        int obtainedCode = res.getStatusCode();
        driver.quit();
        
        Assertions.assertEquals(200, obtainedCode, "Correct code returned");
    }

    @Test
    public void testWebApplicationTitle() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-dev-shm-usage"); 
        options.addArguments("--no-sandbox"); 
        options.addArguments("--whitelisted-ips=\"\"");
        System.setProperty("webdriver.chrome.whitelistedIps", "");
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("http://localhost:8000");
        String obtainedTitle = driver.getTitle();
        driver.quit();
        Assertions.assertEquals("HIT - DevOps course", obtainedTitle);
    }

    @Test
    public void testSiteUI() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-dev-shm-usage"); 
        options.addArguments("--no-sandbox"); 
        options.addArguments("--whitelisted-ips=\"\"");
        System.setProperty("webdriver.chrome.whitelistedIps", "");
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("http://localhost:8000");
        String obtainedName = driver.findElement(By.xpath("/html/body/h2[3]")).getText();
        driver.quit();
        Assertions.assertEquals("Daniel Weinermann", obtainedName);
    }
}
