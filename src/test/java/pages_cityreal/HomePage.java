package pages_cityreal;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage {
    @FindBy(how = How.CSS, using = "[id^='object-'] .label.label-default")
    private WebElement firstProductIdText;
    @FindBy(how = How.CSS, using = "[id^='object-'] .label.label-default")
    private List<WebElement> productIds;
    @FindBy(how = How.CSS, using = "input[type='text'][name='tx_realestate_pi1[id]']")
    private WebElement searchIdField;
    @FindBy(how = How.CSS, using = "button[type='submit'].btn-primary span")
    private WebElement searchButton;
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'btn') and contains(text(),'price')]")
    private WebElement orderByPrice;
    @FindBy(how = How.CSS, using = " h4:nth-child(4)")
    private List<WebElement> prices;
    @FindBy(how = How.CSS, using = "[id^='favorite-add-']")
    private List<WebElement> addToFavoritesButton;
    @FindBy(how = How.CSS, using = "[id^='favorite-view-']")
    private WebElement goToFavoritesButton;

    @FindBy(how = How.CSS, using = "[href='/mens']")
    private WebElement menCategory;
    @FindBy(how = How.CSS, using = "[href='/football/football-boots/mens-football-boots?promo_name=landing-mens']")
    private WebElement footballBootsSubCategory;
    @FindBy(how = How.CSS, using = ".s-productthumbbox")
    private WebElement firstProduct;
    @FindBy(how = How.CSS, using = ".addToBag")
    private WebElement addToBagButton;
    @FindBy(how = How.CSS, using = ".sizeButtonli:not(.greyOut)")
    private WebElement firstAvailableSize;
    @FindBy(how = How.ID, using = "aPersNoThanksBag")
    private WebElement noThanksButton;
    @FindBy(how = How.CLASS_NAME, using = "HeaderCheckoutLink")
    private WebElement checkoutButton;
    @FindBy(how = How.CSS, using = "[src*='AddToBag']")
    private WebElement noThanksIFrame;
    @FindBy(how = How.XPATH, using = "(//*[contains(@class,'ContinueOn')])[2]")
    private WebElement continueSecurelyButton;

    public String getPageUrl() {
        return "https://www.cityreal.lv/en/";
    }

    public String getFirstProductIdText() {
       return firstProductIdText.getText();
    }

    public void setTextToSearchIdField(String idText) {
       searchIdField.sendKeys(idText);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void setOrderByPrice() {
        orderByPrice.click();
    }

    public int getCountOfProductIDs() {
        return productIds.size();
    }

    public List<String> getAllPrices() {
        return prices.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public int getAddFavoritesButtonsQuantity() {
        return addToFavoritesButton.size();
    }
    public void clickAddFavoritesButtons(int index) {
       addToFavoritesButton.get(index).click();
    }

    public void clickGoToFavoritesButton() {
        goToFavoritesButton.click();
    }



    public void selectMenCategory() {
        menCategory.click();
    }

    public void selectFootballBootsSubCategory() {
        footballBootsSubCategory.click();
    }

    public void selectFirstProduct() {
        firstProduct.click();
    }

    public void clickAddToBagButton() {
        addToBagButton.click();
    }

    public void selectFirstAvailableSize() {
        firstAvailableSize.click();
    }

    public void clickNoThanksButton() {
        noThanksButton.click();
    }

    public void clickCheckoutButton() {
        checkoutButton.click();
    }

    public WebElement checkoutButton() {
        return checkoutButton;
    }

    public void clickContinueSecurelyButton() {
        continueSecurelyButton.click();
    }

    public WebElement noThanksIFrame() {
        return noThanksIFrame;
    }
}
