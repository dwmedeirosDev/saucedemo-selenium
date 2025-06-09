package steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ConsultarCarrinhoBdd {

    WebDriver driver;

    @Before
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

    @After
    public void finalizar() {
        driver.quit();
    }

    @Given("que acesso o site {string}")
    public void que_acesso_o_site(String url) {
        driver.get(url);
    }

    @When("insiro o usuário {string} e a senha {string}")
    public void insiro_o_usuário_e_a_senha(String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @When("clico no botão Login")
    public void clico_no_botão_login() {
        driver.findElement(By.id("login-button")).click();
    }

    @Then("sou redirecionado para a página Inventory")
    public void sou_redirecionado_para_a_página_inventory() {
        assertEquals("Products", driver.findElement(By.cssSelector("[data-test='title']")).getText());

    }

    @When("valido o nome {string} e o preço {string}")
    public void valido_o_nome_e_o_preço(String string, String string2) {
        assertEquals("Sauce Labs Backpack", driver.findElement(By.cssSelector("[data-test='inventory-item-name']")).getText());
        assertEquals("$29.99", driver.findElement(By.cssSelector("[data-test='inventory-item-price']")).getText());
    }

    @When("clico no produto")
    public void clico_no_produto() {
        driver.findElement(By.cssSelector("[data-test='item-4-title-link']")).click();
    }

    @Then("sou redirecionado para a página Inventory Item")
    public void sou_redirecionado_para_a_página_inventory_item() {
        assertEquals("Back to products", driver.findElement(By.cssSelector("[data-test='back-to-products']")).getText());
    }

    @When("valido novamente o nome {string} e o preço {string}")
    public void valido_novamente_o_nome_e_o_preço(String string, String string2) {
        assertEquals("Sauce Labs Backpack", driver.findElement(By.cssSelector("[data-test='inventory-item-name']")).getText());
        assertEquals("$29.99", driver.findElement(By.cssSelector("[data-test='inventory-item-price']")).getText());
    }

    @When("clico no botão Add to cart")
    public void clico_no_botão_add_to_cart() {
        driver.findElement(By.id("add-to-cart")).click();
    }

    @When("clico no ícone do carrinho")
    public void clico_no_ícone_do_carrinho() {
        driver.findElement(By.cssSelector("[data-test='shopping-cart-link']")).click();
    }

    @Then("sou redirecionado para a página Cart")
    public void sou_redirecionado_para_a_página_cart() {
        assertEquals("Your Cart", driver.findElement(By.cssSelector("[data-test='title']")).getText());
    }

    @Then("valido que o produto {string} com o preço {string} está presente no carrinho")
    public void valido_que_o_produto_com_o_preço_está_presente_no_carrinho(String string, String string2) {
        assertEquals("Sauce Labs Backpack", driver.findElement(By.cssSelector("[data-test='inventory-item-name']")).getText());
        assertEquals("$29.99", driver.findElement(By.cssSelector("[data-test='inventory-item-price']")).getText());
    }
}
