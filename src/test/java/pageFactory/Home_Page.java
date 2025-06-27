package pageFactory;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import common.Helper;
import common.TestContext;

public class Home_Page {

    private WebDriver driver;
    private Helper helper;

    @FindBy(className = "navbar-logo") private WebElement diaLogo;
    @FindBy(css = "nav a") private List<WebElement> navList;
    @FindBy(xpath = "//a[contains(@class, 'btn')]") private List<WebElement> navButtons;
    @FindBy(className = "auth-provider-text") private WebElement loginGoogle;
    @FindBy(xpath = "//a[@class='modern-brand']/span[text()='DIA']") private WebElement homeTextDIA;

    public Home_Page(TestContext context) {
        this.driver = context.getDriver();
        this.helper = context.getHelper();  // ✅ declared and initialized
        PageFactory.initElements(driver, this);
    }

    public boolean isLogoVisible() {
        return diaLogo.isDisplayed();
    }

    public List<String> getNavLinkTexts() {
        return helper.getTexts(navList);  // ✅ use instance, not static
    }

    public void clickNavByText(String linkText) {
        helper.clickElementByText(navList, linkText);  // ✅ fixed
    }

    public List<String> getNavBtnTexts() {
        return helper.getTexts(navButtons);
    }

    public void clickNavButton(String btn) {
        helper.clickElementByText(navButtons, btn);
    }

    public String homeTextDIAText() {
        return homeTextDIA.getText();
    }

    public String getGoogleLoginText() {
        return loginGoogle.getText();
    }
}
