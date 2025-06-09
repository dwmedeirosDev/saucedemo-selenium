import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ConsultarCarrinhoTest {
    private WebDriver driver;

    @BeforeEach
    public void iniciar() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Remover mensagem de vazamento de dados devido aos testes utilizando login e senha
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
    }

    @Test
    public void ConsultarCarrinho() {
        driver.get("https://www.saucedemo.com/");

        String usuario = "standard_user";
        String senha = "secret_sauce";

        // Página de Login
        driver.findElement(By.id("user-name")).sendKeys(usuario);
        driver.findElement(By.id("password")).sendKeys(senha);
        driver.findElement(By.id("login-button")).click();

        // Página "Inventory"
        assertEquals("Products", driver.findElement(By.cssSelector("[data-test='title']")).getText());
        driver.findElement(By.cssSelector("[data-test='item-4-title-link']")).click();

        // Página "Inventory Item"
        assertEquals("Back to products", driver.findElement(By.cssSelector("[data-test='back-to-products']")).getText());
        assertEquals("Sauce Labs Backpack", driver.findElement(By.cssSelector("[data-test='inventory-item-name']")).getText());
        assertEquals("$29.99", driver.findElement(By.cssSelector("[data-test='inventory-item-price']")).getText());
        driver.findElement(By.id("add-to-cart")).click();
        driver.findElement(By.cssSelector("[data-test='shopping-cart-badge']")).click();

        // Página "Your Cart"
        assertEquals("Your Cart", driver.findElement(By.cssSelector("[data-test='title']")).getText());
        assertEquals("Sauce Labs Backpack", driver.findElement(By.cssSelector("[data-test='inventory-item-name']")).getText());
        assertEquals("$29.99", driver.findElement(By.cssSelector("[data-test='inventory-item-price']")).getText());
    }

    @AfterEach
    public void finalizar() {
        driver.quit();
    }

}
