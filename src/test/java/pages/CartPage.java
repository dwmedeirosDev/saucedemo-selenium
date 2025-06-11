package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends Common {

    public CartPage(WebDriver driver) {
        super(driver); // Herdado do CommonPage
        PageFactory.initElements(driver, this);
    }

    // Elementos mapeados
    @FindBy(className = "title")
    WebElement tituloCartPage;

    public By byNomeProduto(String skuProduto) {
        return By.id("item_" + skuProduto + "_title_link");
    }

    @FindBy(className = "inventory_item_price")
    WebElement precoProduto;

    @FindBy(id = "checkout")
    WebElement botaoCheckout;

    // Ações dos elementos
    public String lerTituloCartPage() {
        return tituloCartPage.getText();
    }

    public String lerNomeProduto(String skuProduto) {
        return driver.findElement(byNomeProduto(skuProduto)).getText();
    }

    public String lerPrecoProduto() {
        return precoProduto.getText();
    }

    public void clicarBotaoCheckout() {
        botaoCheckout.click();
    }
}