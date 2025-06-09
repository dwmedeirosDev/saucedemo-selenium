import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Consultar {
    private WebDriver driver;

    @BeforeEach
    public void iniciar() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void ConsultarCompra() {
        driver.get("https://www.saucedemo.com/");

        String usuario = "standard_user";
        String senha = "secret_sauce";

        // Página Login
        driver.findElement(By.id("user-name")).sendKeys(usuario);
        driver.findElement(By.id("password")).sendKeys(senha);
        driver.findElement(By.id("login-button")).click();

        // Página Inventory
        driver.findElement(By.cssSelector("[data-test='item-4-title-link']")).click();
    }

    @AfterEach
    public void finalizar() {
        driver.quit();
    }

}
