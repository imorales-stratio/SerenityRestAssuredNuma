package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.JSONObject;
import restAssured.RestClient;

public class Steps {

    private final RestClient restClient = new RestClient();

    private static String lastUserId;

    @Given("I get all users")
    public void getAllUsers() {
        restClient.getRequest("users");
    }

    @Then("The response status code is {int}")
    public void checkStatusCode(Integer expectedStatusCode) {
        restClient.checkStatusCodeResponse(expectedStatusCode);
    }

    @Then("The response returns {int} users")
    public void checkUsersSize(Integer expectedUsers) {
        restClient.checkBodyLength(expectedUsers);
    }

    @Then("Returned user has {string} = {string}")
    public void checkUserAttribute(String field, String expectedValue) {
        restClient.checkBodyUsingJsonPath(field, expectedValue);
    }

    @When("I add a new user with firstName {string}, lastName {string}, email {string} and country {string}")
    public void addNewUser(String firstname, String lastName, String email, String country) {
        JSONObject jsonUser = new JSONObject();
        jsonUser.put("firstName", firstname);
        jsonUser.put("lastName", lastName);
        jsonUser.put("email", email);
        jsonUser.put("country", country);
        restClient.postRequest("users", jsonUser.toJSONString());
        lastUserId = restClient.getValueFromResponse("id");
    }

    @When("I get user")
    public void getUser() {
        restClient.getRequest("users/" + lastUserId);
    }

    @When("I update user with firstName {string}, lastName {string} and email {string}")
    public void updateUser(String firstname, String lastName, String email) {
        JSONObject jsonUser = new JSONObject();
        jsonUser.put("firstName", firstname);
        jsonUser.put("lastName", lastName);
        jsonUser.put("email", email);
        restClient.putRequest("users/" + lastUserId, jsonUser.toJSONString());
    }

    @When("I delete user")
    public void deleteUser() {
        restClient.deleteRequest("users/" + lastUserId);
    }
}
