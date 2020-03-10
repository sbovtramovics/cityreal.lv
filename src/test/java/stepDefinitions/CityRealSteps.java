package stepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages_cityreal.FavouritesPage;
import pages_cityreal.HomePage;

import java.util.Arrays;
import java.util.Collections;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CityRealSteps {
    static HomePage homePage;
    static FavouritesPage favouritesPage;
    private WebDriver driver;
    private WebDriverWait wait;
    private int addedFavouriteSections;

    public CityRealSteps() {
        this.addedFavouriteSections = 0;
        this.driver = Hooks.driver;
        this.wait = Hooks.wait;
        homePage = PageFactory.initElements(Hooks.driver, HomePage.class);
        favouritesPage = PageFactory.initElements(Hooks.driver, FavouritesPage.class);
    }

    @Given("^I (?:am on|open) the home page$")
    public void iAmOnHomePage() {

        driver.manage().window().maximize();
        driver.get(homePage.getPageUrl());
    }

    @And("^I type the ID of the first land sale object to the ID field in search section$")
    public void iTypeTheIDOfTheFirstLandSaleObjectToTheIDFieldInSearchSection() {
        String firstProductID = homePage.getFirstProductIdText();
        String[] firstProductIDTextArray = firstProductID.split(" ");
        firstProductID = firstProductIDTextArray[1];
        homePage.setTextToSearchIdField(firstProductID);
    }

    @And("^I click Search button$")
    public void iClickSearchButton() {
        homePage.clickSearchButton();
    }

    @Then("^I see that on the left side of the page is found just one object$")
    public void iSeeThatOnTheLeftSideOfThePageIsFoundJustOneObject() {
        assertEquals("ID in land sale section is not unique", 1, homePage.getCountOfProductIDs());
    }

    @And("^I order items by price$")
    public void iOrderItemsByPrice() {
        homePage.setOrderByPrice();
    }

    @Then("^I verify that prices are sorted ascending$")
    public void iVerifyThatPricesAreSortedAscending() {
        double[] CurrentPricesOrder = homePage.getAllPrices()
                .stream()
                .map(s -> s.substring(0, s.indexOf(" ")))
                .mapToDouble(Double::parseDouble)
                .toArray();
        double[] SortedPricesOrder = Arrays
                .stream(CurrentPricesOrder)
                .sorted()
                .toArray();
        assertArrayEquals("Prices are not sorted ascending$ ", CurrentPricesOrder, SortedPricesOrder, 0.0);
    }

    @Then("^I verify that prices are sorted descending$")
    public void iVerifyThatPricesAreSortedDescending() {
        double[] CurrentPricesOrder = homePage.getAllPrices()
                .stream()
                .map(s -> s.substring(0, s.indexOf(" ")))
                .mapToDouble(Double::parseDouble)
                .toArray();
        Double[] PricesOrder;
        PricesOrder = ArrayUtils.toObject(CurrentPricesOrder);
        Arrays.sort(PricesOrder, Collections.reverseOrder());
        double[] SortedPricesOrder = ArrayUtils.toPrimitive(PricesOrder);
        assertArrayEquals("Prices are not sorted descending ", CurrentPricesOrder, SortedPricesOrder, 0.0);
    }

    @And("^I add to favourites all sections which are on the page$")
    public void iAddToFavouritesAllSectionsWhichAreOnThePage() {
        for (int i = 0; i < homePage.getAddFavoritesButtonsQuantity(); i++) {
            homePage.clickAddFavoritesButtons(i);
            addedFavouriteSections++;
        }
    }

    @And("^I go to the favourites$")
    public void iGoToTheFavourites() {
        homePage.clickGoToFavoritesButton();
    }

    @Then("^I verify that there are same quantity of sections, as was added on previous page$")
    public void iVerifyThatThereAreSameQuantityOfSectionsAsWasAddedOnPreviousPage() {
        assertEquals(
                "The quantity of added favorites sections on previous page is not the same with sections quantity on current page",
                addedFavouriteSections, favouritesPage.getRemoveFavoritesButtonsQuantity());
    }

    @And("^I remove all sections from favourites$")
    public void iRemoveAllSectionsFromFavourites() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        for (int i = 0; i < addedFavouriteSections; i++) {
            wait.until(ExpectedConditions.visibilityOf(favouritesPage.RemoveFavoritesFirstButton()));
            wait.until(ExpectedConditions.elementToBeClickable(favouritesPage.RemoveFavoritesFirstButton()));
            sleep(1000);
            favouritesPage.clickRemoveFavoritesFirstButton();
        }
    }

    @Then("^I verify that there is no any sections in favourites$")
    public void iVerifyThatThereIsNoAnySectionsInFavourites() {
        assertEquals("There is found section which was not removed", 0, favouritesPage.getRemoveFavoritesButtonsQuantity());
    }
}