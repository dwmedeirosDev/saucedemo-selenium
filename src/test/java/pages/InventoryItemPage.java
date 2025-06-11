package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryItemPage extends Common {

    public InventoryItemPage(WebDriver driver) {
        super(driver); // Herdado do CommonPage
        PageFactory.initElements(driver, this);
    }

    // Elementos mapeados
    @FindBy(id = "back-to-products")
    WebElement tituloPageInventoryItem;

    @FindBy(css = "[data-test='inventory-item-name']")
    WebElement nomeProduto;

    @FindBy(css = "[data-test='inventory-item-price']")
    WebElement precoProduto;

    @FindBy(id = "add-to-cart")
    WebElement botaoAddToCart;

    @FindBy(className = "shopping_cart_link")
    WebElement botaoCart;

    // Ações dos elementos
    public String LerTituloPageInventoryItem() {
        return tituloPageInventoryItem.getText();
    }

    public String lerNomeProduto() {
        return nomeProduto.getText();
    }

    public String lerPrecoProduto() {
        return precoProduto.getText();
    }

    public void clicarAddToCart() {
        botaoAddToCart.click();
    }

    public void clicarCart() {
        botaoCart.click();
    }
}
