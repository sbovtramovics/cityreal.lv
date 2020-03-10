Feature: Functionality to verify:
  Scenario: Verify that ID in land sale section is unique;
    When I am on the home page
    And I type the ID of the first land sale object to the ID field in search section
    And I click Search button
    Then I see that on the left side of the page is found just one object

  Scenario: Price can be sorted ascending and descending;
    When I am on the home page
    And I click Search button
    And I order items by price
    Then I verify that prices are sorted ascending
    And I order items by price
    Then I verify that prices are sorted descending

  Scenario: Favourites section (item can be added form any section and removed from favourites section)
    When I am on the home page
    And I add to favourites all sections which are on the page
    And I go to the favourites
    Then I verify that there are same quantity of sections, as was added on previous page
    And I remove all sections from favourites
    Then I verify that there is no any sections in favourites