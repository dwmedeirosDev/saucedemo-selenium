package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage extends Common {

    public InventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Elementos mapeados
    @FindBy(className = "title")
    WebElement tituloPageInventory;

    public By byProdutoSku(String skuProduto) {
        return By.id("item_" + skuProduto + "_title_link");
    }

    // Ações dos elementos
    public String lerTituloPageInventory() {
        return tituloPageInventory.getText();
    }

    public String verificarProdutoSku(String skuProduto) {
        return driver.findElement(byProdutoSku(skuProduto)).getText();

    }

    public void selecionarProdutoSku(String skuProduto) {
        driver.findElement(byProdutoSku(skuProduto)).click();
    }
}
