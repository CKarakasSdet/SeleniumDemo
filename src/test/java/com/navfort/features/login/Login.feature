Feature: Login
  As user I want to login under different roles


  @storemanager
  Scenario: Login as a store manager
    Given user landed on login page
    When user logs in as a store manager
    Then user verifies that "Dashboard" page name is displayed

  @driver
  Scenario: Login as a driver
    When user logs in as a driver
    Then user verifies that "Quick Launchpad" page name is displayed

  @negative
  Scenario: Verify warning message for invalid credentials

    When user logs in with "wrong" username and "wrong" password
    Then user verifies that "Invalid user name or password." warning message is displayed

  @scenario_outline_example
  Scenario Outline: Loging in as different users
    Given user logs in as a "<user_type>"
    And user verifies that "<page_name>" page name is displayed

    Examples:
    |user_type   | page_name      |
    |driver      |Quick Launchpad |
    |storemanager|Dashboard       |
    |salesmanager|Dashboard       |




