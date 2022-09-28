import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;


public class webApplicationTests {
    @Test
    public void testHTTPResponseCode() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 800;

        RequestSpecification HTTPRequest = RestAssured.given();
        Response res = HTTPRequest.get();
        int obtainedCode = res.getStatusCode();

        Assertions.assertEquals(200, obtainedCode, "Correct code returned");
    }

    @Test
    public void testWebApplicationTitle() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8000");
        String obtainedTitle = driver.getTitle();
        driver.quit();
        Assertions.assertEquals("HIT - DevOps course", obtainedTitle);
    }

    @Test
    public void testSiteUI() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8000");
        String obtainedName = driver.findElement(By.xpath("/html/body/h2[3]")).getText();
        driver.quit();
        Assertions.assertEquals("Daniel Weinermann", obtainedName);
    }
}
