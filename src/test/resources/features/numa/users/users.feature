Feature: Users

  Scenario: Get all users
    When I get all users
    Then The response status code is 200
    And The response returns 50 users

  Scenario: Add new user
    When I add a new user with firstName 'Ivan', lastName 'Morales', email 'imorales@stratio.com' and country 'ESP'
    Then The response status code is 201
    When I get all users
    Then The response status code is 200
    And The response returns 51 users

  Scenario: Get user added
    When I get user
    Then The response status code is 200
    And Returned user has 'firstName' = 'Ivan'
    And Returned user has 'lastName' = 'Morales'
    And Returned user has 'email' = 'imorales@stratio.com'
    And Returned user has 'country' = 'ESP'

  Scenario: Update user
    When I update user with firstName 'Test', lastName 'Numa' and email 'numa@stratio.com'
    Then The response status code is 200
    When I get user
    Then The response status code is 200
    And Returned user has 'firstName' = 'Test'
    And Returned user has 'lastName' = 'Numa'
    And Returned user has 'email' = 'numa@stratio.com'
    When I get all users
    Then The response status code is 200
    And The response returns 51 users

  Scenario: Remove user
    When I delete user
    Then The response status code is 200
    When I get all users
    Then The response status code is 200
    And The response returns 50 users

  Scenario: Get user (deleted)
    When I get user
    Then The response status code is 404