package steps;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import pages.Base;

public class Hooks {
    Base base;

    public Hooks(Base base) {
        this.base = base;
    }

    @Before
    public void iniciar() {
        ChromeOptions options = new ChromeOptions();
        // Evita a mensagem "senha encontrada em vazamento"
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        // Outras opções úteis para testes automatizados
        options.addArguments("--incognito");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        options.setExperimentalOption("useAutomationExtension", false);

        base.driver = new ChromeDriver(options); // AQUI está o ponto chave
        base.driver.manage().window().maximize();
    }

    @After
    public void finalizar() {
        base.driver.quit();
    }
}
