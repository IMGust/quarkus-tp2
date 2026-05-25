package org.gustavo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductCatalogSeleniumTest {

    private WebDriver driver;
    private WebDriverWait wait;
    

    private final String ANGULAR_APP_URL = "http://localhost:4200";

    @BeforeAll
    void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        ChromeOptions options = new ChromeOptions();
        
       
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
       
        driver = new ChromeDriver(options);
        
    
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    void teardown() {

        if (driver != null) {
            driver.quit();
        }
    }

    
    private void loginAsAdmin() throws InterruptedException {
        driver.get(ANGULAR_APP_URL + "/login");
        
        WebElement usernameInput = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("username"))
        );
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.cssSelector("button.submit-btn"));
        
        usernameInput.sendKeys("admin");
        passwordInput.sendKeys("admin123");
        submitButton.click();
        
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("app-sidebar")));
    }

    @Test
    @Order(1)
    @DisplayName("1. Deve realizar login com sucesso no painel administrativo")
    void testAdminLogin() throws InterruptedException {
        driver.get(ANGULAR_APP_URL + "/login");

       
        WebElement usernameInput = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("username"))
        );
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.cssSelector("button.submit-btn"));

        Thread.sleep(1500);

       
        usernameInput.sendKeys("admin");
        passwordInput.sendKeys("admin123");
        Thread.sleep(1500);

       
        submitButton.click();

        // Aguarda o redirecionamento e a sidebar administrativa estar visível
        WebElement sidebar = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.className("app-sidebar"))
        );
        assertNotNull(sidebar, "O painel administrativo deveria carregar com a sidebar.");
        
    
        assertTrue(driver.getCurrentUrl().endsWith("/motores"), "Deveria ter redirecionado para a lista de motores.");
        
        Thread.sleep(2000);
    }

    @Test
    @Order(2)
    @DisplayName("2. Deve navegar pelas abas administrativas (Pistões, Turbos, Motores)")
    void testAdminNavigation() throws InterruptedException {
        loginAsAdmin();
        Thread.sleep(1500);

        // 1. Clicar em "Pistões" na sidebar
        WebElement pistoesLink = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(., 'Pistões')]"))
        );
        pistoesLink.click();
        Thread.sleep(2000);
        
        // Verifica se carregou a área de pistões (URL mudou para /pistoes)
        assertTrue(driver.getCurrentUrl().endsWith("/pistoes"), "A URL deveria ser a de pistões.");
        WebElement pistoesHeader = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(., 'Pistões') or contains(., 'Pistao')]"))
        );
        assertNotNull(pistoesHeader);

        // 2. Clicar em "Turbos" na sidebar
        WebElement turbosLink = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(., 'Turbos')]"))
        );
        turbosLink.click();
        Thread.sleep(2000);

        // Verifica se carregou a área de turbos (URL mudou para /turbos)
        assertTrue(driver.getCurrentUrl().endsWith("/turbos"), "A URL deveria ser a de turbos.");
        WebElement turbosHeader = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(., 'Turbos')]"))
        );
        assertNotNull(turbosHeader);

        // 3. Clicar em "Motores" para voltar
        WebElement motoresLink = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(., 'Motores')]"))
        );
        motoresLink.click();
        Thread.sleep(2000);

        // Verifica se voltou a área de motores (URL mudou para /motores)
        assertTrue(driver.getCurrentUrl().endsWith("/motores"), "A URL deveria ser a de motores.");
        WebElement motoresHeader = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(., 'Motores')]"))
        );
        assertNotNull(motoresHeader);
    }

    @Test
    @Order(3)
    @DisplayName("3. Deve preencher e cadastrar um novo motor com sucesso")
    void testCreateMotorForm() throws InterruptedException {
        loginAsAdmin();
        Thread.sleep(1500);

        // Clica no botão "+ Novo Motor" (classe add-btn no cabeçalho)
        WebElement addBtn = wait.until(
            ExpectedConditions.elementToBeClickable(By.className("add-btn"))
        );
        addBtn.click();
        Thread.sleep(2000);

        // Preenche o formulário
        WebElement nomeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nome")));
        WebElement cilindradaInput = driver.findElement(By.id("cilindrada"));
        WebElement potenciaInput = driver.findElement(By.id("potencia"));
        WebElement torqueInput = driver.findElement(By.id("torque"));
        WebElement taxaCompressaoInput = driver.findElement(By.id("taxaCompressao"));
        WebElement rpmMaxInput = driver.findElement(By.id("rpmMax"));
        WebElement precoInput = driver.findElement(By.id("preco"));

        nomeInput.sendKeys("V10 5.2L FSI");
        cilindradaInput.sendKeys("5200");
        potenciaInput.sendKeys("610");
        torqueInput.sendKeys("560");
        taxaCompressaoInput.sendKeys("12.5");
        rpmMaxInput.sendKeys("8500");
        precoInput.sendKeys("120000");

        Thread.sleep(2500);

        // Encontra o botão de cadastrar (do tipo submit)
        WebElement saveBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        assertTrue(saveBtn.isEnabled(), "O botão de cadastrar deveria estar habilitado após preencher os campos.");
        saveBtn.click();
        Thread.sleep(2500);

        // Aguarda retornar para a lista de motores e verifica se o novo motor está na tabela
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("motor-table")));
        
        WebElement newMotorRow = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(., 'V10 5.2L FSI')]"))
        );
        assertNotNull(newMotorRow, "O novo motor cadastrado deveria aparecer na tabela de motores.");
        
        Thread.sleep(2000);
    }

    @Test
    @Order(4)
    @DisplayName("4. Deve excluir o motor cadastrado e sair do painel com sucesso")
    void testDeleteAndLogout() throws InterruptedException {
        loginAsAdmin();
        Thread.sleep(1500);

        // Encontra a linha da tabela que contém o motor que criamos
        WebElement row = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[td[contains(., 'V10 5.2L FSI')]]"))
        );
        
        // Dentro dessa linha, encontra o botão de deletar (classe delete)
        WebElement deleteBtn = row.findElement(By.className("delete"));
        deleteBtn.click();
        
        // Aguarda a caixa de diálogo de confirmação (confirm) aparecer
        wait.until(ExpectedConditions.alertIsPresent());
        Thread.sleep(1000); // Garante que o diálogo do Chrome está totalmente instanciado e o loop de JS não seja abortado
        driver.switchTo().alert().accept();
        
        // Pausa para assistir à exclusão
        Thread.sleep(2500);

        // Garante que o motor não está mais presente na tabela
        boolean deleted = wait.until(
            ExpectedConditions.invisibilityOfElementLocated(By.xpath("//td[contains(., 'V10 5.2L FSI')]"))
        );
        assertTrue(deleted, "O motor V10 5.2L FSI deveria ter sido removido da tabela.");

        // Clica no botão de Sair na sidebar
        WebElement logoutBtn = wait.until(
            ExpectedConditions.elementToBeClickable(By.className("logout-sidebar-btn"))
        );
        logoutBtn.click();
        Thread.sleep(2000);

        // Verifica se voltou para a página pública (onde a sidebar sumiu)
        boolean sidebarGone = wait.until(
            ExpectedConditions.invisibilityOfElementLocated(By.className("app-sidebar"))
        );
        assertTrue(sidebarGone, "A sidebar deveria desaparecer após fazer logout.");
    }
}
