Feature: Adding hotels to the list
  As an Admin
  I want to see add hotels option on the admin panel
  So that I can add hotels

  @addEntry @smoke
  Scenario: Add hotel details
    Given I am on the Home page
    When I login as an admin
    Then I should see create option on the page
    When I enter hotel details as "Test Hotel One","1,Test Street, Test City, TE5T 1NG","Test Owner","07766564432", "testMail@email.com"
    And I select create
    Then "Test Hotel One" entry should be created successfully


  @addEntries @smoke
  Scenario Outline: Add multiple hotel details
    Given I am on the Home page
    When I login as an admin
    Then I should see create option on the page
    When I enter hotel details as "<hotel_name>","<hotel_address>","<owner_name>","<phone_number>", "<email>"
    And I select create
    Then "Test Hotel One" entry should be created successfully
    Examples:
      | hotel_name       | hotel_address                      | owner_name  | phone_number | email               |
      | Test Hotel One   | 1,Test Street, Test City, TE5T 1NG | Test Owner1 | 07766564432  | testMail1@email.com |
      | Test Hotel Two   | 2,Test Street, Test City, TE5T 1NG | Test Owner2 | 07766564432  | testMail2@email.com |
      | Test Hotel Three | 3,Test Street, Test City, TE5T 1NG | Test Owner3 | 07766564432  | testMail3@email.com |