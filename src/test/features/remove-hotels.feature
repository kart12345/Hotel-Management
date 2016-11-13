Feature: Remove hotel details from the list
  As an Admin
  I want to remove the exisitng hotel from the list
  So that I can not see redundant hotels in the list

  @deleteEntry @smoke1
  Scenario: Delete hotel details from the list
    Given I am on the Home page
    When I login as an admin
    When I enter hotel details as "Test Hotel Delete","1,Test Street, Test City, TE5T 1NG","Test Owner","07766564432", "testMail@email.com"
    And I select create
    Then "Test Hotel Delete" entry should be created successfully
    When I delete the hotel "Test Hotel Delete" details
    Then I should not see "Test Hotel Delete" details in the list
