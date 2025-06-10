package steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import pages.Base;
import pages.LoginPage;
import pages.InventoryPage;

public class ConsultarCarrinhoBdd {

    final WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    public ConsultarCarrinhoBdd(Base base){
        this.driver = base.driver;
    }

    @Given("que acesso o site {string}")
    public void que_acesso_o_site(String url) {
        loginPage = new LoginPage(driver);
        loginPage.acessarLoginPage(url);
    }

    @When("insiro o usuário {string} e a senha {string}")
    public void insiro_o_usuário_e_a_senha(String username, String password) {
        loginPage.preencherUser(username);
        loginPage.preencherPassword(password);
    }

    @When("clico no botão Login")
    public void clico_no_botão_login() {
        loginPage.clicarLogin();
    }

    @Then("sou redirecionado para a página Inventory")
    public void sou_redirecionado_para_a_página_inventory() {
       inventoryPage = new InventoryPage(driver);
       assertEquals("Products", inventoryPage.lerTituloPageInventory());
    }

    @When("valido o sku {string} e o nome {string}")
    public void valido_o_sku_e_o_nome(String skuProduto, String nomeProduto) {
        assertEquals(nomeProduto, inventoryPage.verificarProdutoSku(skuProduto));
    }

    @When("clico no produto com sku {string}")
    public void clico_no_produto_com_sku(String skuProduto) {
        inventoryPage.selecionarProdutoSku(skuProduto);
    }

    @Then("sou redirecionado para a página Inventory Item")
    public void sou_redirecionado_para_a_página_inventory_item() {
        assertEquals("Back to products", driver.findElement(By.cssSelector("[data-test='back-to-products']")).getText());
    }

    @When("valido novamente o nome {string} e o preço {string}")
    public void valido_novamente_o_nome_e_o_preço(String nomeProduto, String precoProduto) {
        assertEquals(nomeProduto, driver.findElement(By.cssSelector("[data-test='inventory-item-name']")).getText());
        assertEquals(precoProduto, driver.findElement(By.cssSelector("[data-test='inventory-item-price']")).getText());
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
    public void valido_que_o_produto_com_o_preço_está_presente_no_carrinho(String nomeProduto, String precoProduto) {
        assertEquals(nomeProduto, driver.findElement(By.cssSelector("[data-test='inventory-item-name']")).getText());
        assertEquals(precoProduto, driver.findElement(By.cssSelector("[data-test='inventory-item-price']")).getText());
    }
}
