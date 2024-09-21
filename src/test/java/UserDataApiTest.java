import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import dto.User;


class UserDataApiTest extends BaseApiTest {

    private void assertValidToken(String token) {
        Assertions.assertNotNull(token, "Token should not be null");
        Assertions.assertFalse(token.isEmpty(), "Token should not be empty");
    }

        @Test
    public void testRegisteredUser() {
        User user = new User("string", "string");
        Response response =  apiProvider.post("/login", user);
                response.then().assertThat()
                .statusCode(200);

        String token = response.jsonPath().getString("token");
        assertValidToken(token);
    }

    @Test
    public void testUnregisteredUser() {
        User user = new User("strin", "strin");
        apiProvider.post("/login", user).then().assertThat().statusCode(401);

    }

    @Test
    public void testIncorrectPasswordUser() {
        User user = new User("string", "strin");
        apiProvider.post("/login", user).then().assertThat().statusCode(401);

    }

    @Test
    public void testGetProductList() {
        apiProvider.get("/products").then().assertThat().statusCode(200);
    }

    @Test
    public void testGetSpecificProduct() {
        String productId = "1";
        Response response = apiProvider.get("products/" + productId);
        response.then().statusCode(200);

    }
}