package providers;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
public class ApiProvider {
    public static void filters() {
        RestAssured.filters(new RequestLoggingFilter(System.out), new ResponseLoggingFilter(System.out));
    }
    public Response get(String endpoint) {
        return given()
                .accept(ContentType.JSON)
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract().response();
    }
    public Response get(String endpoint, String token) {
        return given()
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract().response();
    }
    public Response post(String endpoint, Object body) {
        return given()
                   .accept(ContentType.JSON)
                   .contentType("application/json")
                   .body(body)
                .when()
                   .post(endpoint)
                .then()
                   .log().all()
                   .extract().response();
    }
    public Response post(String endpoint, Object body, String token) {
        return given()
                .accept(ContentType.JSON)
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract().response();
    }
    public Response patch(String endpoint, Object body) {
        return given()
                .accept(ContentType.JSON)
                .body(body)
                .when()
                .patch(endpoint)
                .then()
                .log().all()
                .extract().response();
    }
    public Response delete(String endpoint) {
        return given()
                .accept(ContentType.JSON)
                .when()
                .delete(endpoint)
                .then()
                .log().all()
                .extract().response();
    }
}