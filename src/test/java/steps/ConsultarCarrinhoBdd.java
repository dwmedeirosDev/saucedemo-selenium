package steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Base;
import pages.CartPage;
import pages.InventoryItemPage;
import pages.InventoryPage;
import pages.LoginPage;

public class ConsultarCarrinhoBdd {

    final WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private InventoryItemPage inventoryItemPage;
    private CartPage cartPage;

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
        inventoryItemPage = new InventoryItemPage(driver);
        assertEquals("Back to products", inventoryItemPage.LerTituloPageInventoryItem());
    }

    @When("valido novamente o nome {string} e o preço {string}")
    public void valido_novamente_o_nome_e_o_preço(String nomeProduto, String precoProduto) {
        assertEquals(nomeProduto, inventoryItemPage.lerNomeProduto());
        assertEquals(precoProduto, inventoryItemPage.lerPrecoProduto());
    }

    @When("clico no botão Add to cart")
    public void clico_no_botão_add_to_cart() {
        inventoryItemPage.clicarAddToCart();
    }

    @When("clico no ícone do carrinho")
    public void clico_no_ícone_do_carrinho() {
        inventoryItemPage.clicarCart();
    }

    @Then("sou redirecionado para a página Cart")
    public void sou_redirecionado_para_a_página_cart() {
        cartPage = new CartPage(driver);
        assertEquals("Your Cart", cartPage.lerTituloCartPage());
    }

    @Then("valido que o produto {string} com sku {string} e o preço {string} estão presentes no carrinho de compra")
    public void validarProdutoCarrinho(String nomeProduto, String skuProduto, String precoProduto) {
        assertEquals(nomeProduto, cartPage.lerNomeProduto(skuProduto));
        assertEquals(precoProduto, cartPage.lerPrecoProduto());
    }
}
