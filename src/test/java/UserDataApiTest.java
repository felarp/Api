import accertions.HttpAssertions;
import dto.Cart;
import dto.CartRequest;
import dto.ResponseMessage;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import dto.User;

import java.util.List;

class UserDataApiTest extends BaseApiTest {

    private static String token;

    @BeforeAll
    public static void getToken() {
        token = getValidToken();
    }
    private static String getValidToken() {
        User user = new User("string", "string");
        Response response = apiProvider.post("/login", user);
        response.then().statusCode(200);
        return response.jsonPath().getString("access_token");
    }

    @Test
    public void testRegisteredUser() {
        User user = new User("string", "string");
        Response response = apiProvider.post("/login", user);
        new HttpAssertions(response)
                .statusCode(200)
                .assertionsJsonPathValueNotEmpty("access_token");
        ResponseMessage responseMessage = new ResponseMessage("User registered successfully", user);
        System.out.println(responseMessage.getMessage());
    }

    @Test
    public void testUnregisteredUser() {
        User user = new User("strin", "strin");
        apiProvider.post("/login", user).then().statusCode(401);
    }

    @Test
    public void testIncorrectPasswordUser() {
        User user = new User("string", "strin");
        apiProvider.post("/login", user).then().statusCode(401);
    }

    @Test
    public void testGetProductList() {
        apiProvider.get("/products", token).then().statusCode(200)
                .extract()
                .as(new TypeRef<List<Cart>>() {});

    }

    @Test
    public void testGetSpecificProduct() {
        String productId = "1";
        Response response = apiProvider.get("/products/" + productId, token);
        response.then().statusCode(200);
    }

    @Test
    public void testGetUnrealIdProduct() {
        String productId = "111";
        Response response = apiProvider.get("/products/" + productId, token);
        response.then().statusCode(404);
    }

    @Test
    public void testGetCustomerCart() {
        Response response = apiProvider.get("/cart", token);
        new HttpAssertions(response)
                .statusCode(200)
                .assertionsJsonPathValueNotEmpty("cart");
    }

    @Test
    public void testAddExistingProductToCart() {
        CartRequest requestBody = new CartRequest(1, 2);
        Response response = apiProvider.post("/cart", requestBody, token);
        new HttpAssertions(response).statusCode(201);
    }

    @Test
    public void testAddNotExistingProductToCart() {
        CartRequest requestBody = new CartRequest(111, 2);
        Response response = apiProvider.post("/cart", requestBody, token);
        new HttpAssertions(response).statusCode(404);

    }

    @Test
    public void testAddProductWithInvalidToken() {
        String wrongToken = "SGHTT";
        CartRequest requestBody = new CartRequest(1, 2);
        Response response = apiProvider.post("/cart", requestBody, wrongToken);
        new HttpAssertions(response).statusCode(401);
    }
}

