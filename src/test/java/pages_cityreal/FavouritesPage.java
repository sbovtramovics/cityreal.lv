package pages_cityreal;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class FavouritesPage {
    @FindBy(how = How.CSS, using = "[id^='favorite-remove-']:not(.hidden)")
    private List<WebElement> removeFavoritesButton;
    @FindBy(how = How.CSS, using = "[id^='favorite-remove-']:not(.hidden)")
    private WebElement removeFavoritesFirstButton;

    public int getRemoveFavoritesButtonsQuantity() {
        return removeFavoritesButton.size();
    }

    public void clickRemoveFavoritesFirstButton() {
        removeFavoritesFirstButton.click();
    }

    public WebElement RemoveFavoritesFirstButton() {
        return removeFavoritesFirstButton;
    }
}
