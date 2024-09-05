Feature: Movie Rental Management System

  Scenario: User Registration
    Given the API is running
    When I register a new user with username "Siri", email "siri@gmail.com", and password "siri@123"
    Then the registration response status should be 201
    And the response should contain a userId

  Scenario: User Login
    Given the API is running
    When I log in with username "Siri" and password "siri@123"
    Then the login response status should be 200
    And the response should contain a token

  Scenario: Add a New Movie
    Given the API is running
    When I add a new movie with title "Tholi Prema", director "Venky Atluri", genre "Love Story", and releaseDate "2018-02-10"
    Then the add movie response status should be 201
    And the response should contain a movieId

  Scenario: Retrieve Movie Details
    Given the API is running
    When I retrieve the movie details with ID 1
    Then the movie details should be returned

  Scenario: Search Movies by Title
    Given the API is running
    When I search for movies with title "Dangal"
    Then the search response status should be 200
    And the response should contain a movie with title "Dangal"

  Scenario: Filter Movies by Genre
    Given the API is running
    When I filter movies by genre "Action"
    Then the filter response status should be 200
    And the response should contain movies with genre "Action"

  Scenario: Rent a Movie
    Given the API is running
    When I rent a movie with userId "1" and movieId "1"
    Then the rental response status should be 201
    And the response should contain a rentalId

  Scenario: Retrieve Rental Details
    Given the API is running
    When I request the rental details with ID 4
    Then I should see the rental details for rental ID 4

  Scenario: Update User Profile
    Given the API is running
    When I update the user with ID 1 to have username "JohnDoe" and email "johndoe@example.com"
    Then the user profile should be updated successfully

  Scenario: Delete User
    Given the API is running
    When I delete the user with ID 1
    Then the user with ID 1 should be deleted successfully