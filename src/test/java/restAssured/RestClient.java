package restAssured;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class RestClient {

    private final String baseURL = "http://localhost:3004/";

    private Response response;

    public RestClient() {

    }

    public void getRequest(String path) {
        response = SerenityRest.get(baseURL + path);
    }

    public void postRequest(String path, String body) {
        response = SerenityRest.given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(baseURL + path);
    }

    public void putRequest(String path, String body) {
        response = SerenityRest.given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .put(baseURL + path);
    }

    public void deleteRequest(String path) {
        response = SerenityRest.delete(baseURL + path);
    }

    public void checkStatusCodeResponse(int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

    public void checkBodyLength(Integer expectedValue) {
        response.then().assertThat().body("size()", is(expectedValue));
    }

    public void checkBodyUsingJsonPath(String jsonPath, String expectedValue) {
        response.then().body(jsonPath, equalTo(expectedValue));
    }

    public String getValueFromResponse(String attribute) {
        return response.then().extract().path(attribute).toString();
    }
}
