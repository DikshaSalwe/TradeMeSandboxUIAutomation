Feature: Query exiting used car
  search for used car and verify deatails
  
  Scenario: Query for used car
  search for used car and verify that car is available
    Given I go to "https://www.tmsandbox.co.nz/"
    When I query for "1998 BMW 328i E46" in searchbox
    And I click on search button
    Then I verify that "1998 BMW 328i E46" item is present 
 
  Scenario: Details of used car
	confirm that car details are available
    Given I am on search result page 
    When I click on "1998 BMW 328i E46" item
    Then I verify Number plate
    And I verify Kilometres
    And I verify Body
    And I verify Seats
