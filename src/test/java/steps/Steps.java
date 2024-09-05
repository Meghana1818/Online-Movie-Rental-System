package steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Assertions;

public class Steps {
    private Response response;

    @Given("the API is running")
    public void the_api_is_running() {
        RestAssured.baseURI = "http://localhost:3000";
    }

    @When("I register a new user with username {string}, email {string}, and password {string}")
    public void i_register_a_new_user_with_username_email_and_password(String username, String email, String password) {
        response = given()
            .contentType("application/json")
            .body("{\"username\": \"" + username + "\", \"email\": \"" + email + "\", \"password\": \"" + password + "\"}")
            .post("/api/users/register");
    }

    @Then("the registration response status should be {int}")
    public void the_registration_response_status_should_be(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response should contain a userId")
    public void the_response_should_contain_a_userId() {
        response.then().body("userId", notNullValue());
    }

    @When("I log in with username {string} and password {string}")
    public void i_log_in_with_username_and_password(String username, String password) {
        response = given()
            .contentType("application/json")
            .body("{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}")
            .post("/api/users/login");
    }

    @Then("the login response status should be {int}")
    public void the_login_response_status_should_be(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response should contain a token")
    public void the_response_should_contain_a_token() {
        response.then().body("token", notNullValue());
    }

    @When("I add a new movie with title {string}, director {string}, genre {string}, and releaseDate {string}")
    public void i_add_a_new_movie_with_title_director_genre_and_release_date(String title, String director, String genre, String releaseDate) {
        response = given()
            .contentType("application/json")
            .body("{\"title\": \"" + title + "\", \"director\": \"" + director + "\", \"genre\": \"" + genre + "\", \"releaseDate\": \"" + releaseDate + "\"}")
            .post("/api/movies");
    }

    @Then("the add movie response status should be {int}")
    public void the_add_movie_response_status_should_be(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response should contain a movieId")
    public void the_response_should_contain_a_movieId() {
        response.then().body("movieId", notNullValue());
    }

    @When("I retrieve the movie details with ID {int}")
    public void i_retrieve_the_movie_details_with_id(int movieId) {
        response = given().get("/api/movies/" + movieId);
    }

    @Then("the movie details should be returned")
    public void the_movie_details_should_be_returned() {
        response.then().statusCode(200);
    }

    @When("I search for movies with title {string}")
    public void i_search_for_movies_with_title(String title) {
        response = given()
            .queryParam("title", title)
            .get("/api/movies/search");
    }

    @Then("the search response status should be {int}")
    public void the_search_response_status_should_be(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response should contain a movie with title {string}")
    public void the_response_should_contain_a_movie_with_title(String title) {
        response.then().body("movies.title", hasItem(title));
    }

    @When("I filter movies by genre {string}")
    public void i_filter_movies_by_genre(String genre) {
        response = given()
            .queryParam("genre", genre)
            .get("/api/movies/filter");
    }

    @Then("the filter response status should be {int}")
    public void the_filter_response_status_should_be(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response should contain movies with genre {string}")
    public void the_response_should_contain_movies_with_genre(String genre) {
        response.then().body("movies.genre", hasItems(genre));
    }

    @When("I rent a movie with userId {string} and movieId {string}")
    public void i_rent_a_movie_with_userId_and_movieId(String userId, String movieId) {
        response = given()
            .contentType("application/json")
            .body("{\"userId\": " + userId + ", \"movieId\": " + movieId + "}")
            .post("/api/rentals");
    }

    @Then("the rental response status should be {int}")
    public void the_rental_response_status_should_be(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response should contain a rentalId")
    public void the_response_should_contain_a_rentalId() {
        response.then().body("rentalId", notNullValue());
    }

    @When("I request the rental details with ID {int}")
    public void i_request_the_rental_details_with_id(int rentalId) {
        response = given().get("/api/rentals/" + rentalId);
    }

    @Then("I should see the rental details for rental ID {int}")
    public void i_should_see_the_rental_details_for_rental_id(int rentalId) {
        response.then().statusCode(200)
                .body("id", equalTo(String.valueOf(rentalId)));
    }

    @When("I update the user with ID {int} to have username {string} and email {string}")
    public void i_update_the_user_with_id_to_have_username_and_email(int userId, String username, String email) {
        response = given()
            .contentType("application/json")
            .body("{\"username\": \"" + username + "\", \"email\": \"" + email + "\"}")
            .put("/api/users/" + userId);
    }

    @Then("the user profile should be updated successfully")
    public void the_user_profile_should_be_updated_successfully() {
        response.then().statusCode(200);
    }

    @When("I delete the user with ID {int}")
    public void i_delete_the_user_with_id(int userId) {
        response = given().delete("/api/users/" + userId);
    }

    @Then("the user with ID {int} should be deleted successfully")
    public void the_user_with_id_should_be_deleted_successfully(int userId) {
        response.then().statusCode(200);
        // Optionally verify that the user no longer exists
        given().get("/api/users/" + userId).then().statusCode(404);
    }
}
