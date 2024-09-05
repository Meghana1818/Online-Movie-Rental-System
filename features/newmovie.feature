  Scenario: Add a New Movie
    Given the API is running
    When I add a new movie with title "Tholi Prema", director "Venky Atluri", genre "Love Story", and releaseDate "2018-02-10"
    Then the add movie response status should be 201
    And the response should contain a movieId
