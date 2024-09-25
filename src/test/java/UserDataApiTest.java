import accertions.HttpAssertions;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.*;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;


class UserDataApiTest extends BaseApiTest {

    private static String token;
    private static final User user = new User("string", "string");

    @BeforeAll
    public static void initializeUserData() {
        registerUser();
        getToken();
    }

    private static void registerUser() {
        Response response = apiProvider.post("/register", user);
        response.then().statusCode(201);
    }

    private static void getToken() {
        Response response = apiProvider.post("/login", user);
        response.then().statusCode(200);
        token = response.jsonPath().getString("access_token");
    }

    @Test
    public void testRegisteredUser() {
        Response response = apiProvider.post("/login", user);
        new HttpAssertions(response)
                .statusCode(200)
                .assertionsJsonPathValueNotEmpty("access_token");
        response.then().body("message", equalTo("User authorized successfully"));

    }

    @Test
    public void testUnregisteredUser() {
        User unRegisteredUser = new User("unregistered", "user");
        apiProvider.post("/login", unRegisteredUser).then().statusCode(401)
                .extract().response().then().body("message", equalTo("User not registered"));
    }



    @Test
    public void testIncorrectPasswordUser() {
        User user = new User("string", "strin");
        apiProvider.post("/login", user).then().statusCode(401)
                .extract().response().then().body("message", equalTo("Wrong password"));
    }

    @Test
    public void testGetProductList() {
        apiProvider.get("/products").then().statusCode(200)
                .extract()
                .as(new TypeRef<List<CartResponse>>() {});


    }

    @Test
    public void testGetSpecificProduct() {
        String productId = "1";
        apiProvider.get("/products/" + productId, token).then().statusCode(201)
                .extract().response().then().body("message", equalTo("Got specific product"));

    }

    @Test
    public void testGetUnrealIdProduct() {
        String productId = "111";
        apiProvider.get("/products/" + productId, token).then().statusCode(404)
                .extract().response().then().body("message", equalTo("Got unreal product"));
    }

    @Test
    public void testGetCustomerCart() {
        Response response = apiProvider.get("/cart", token);
        new HttpAssertions(response)
                .statusCode(200)
                .assertionsJsonPathValueNotEmpty("cart");
        response.then().body("message", equalTo("Got customer cart"));
    }

    @Test
    public void testAddExistingProductToCart() {
        CartRequest requestBody = new CartRequest(1, 2);
        Response response = apiProvider.post("/cart", requestBody, token);
        new HttpAssertions(response).statusCode(201);
        response.then().body("message", equalTo("Existing product added"));
    }

    @Test
    public void testAddNotExistingProductToCart() {
        CartRequest requestBody = new CartRequest(111, 2);
        Response response = apiProvider.post("/cart", requestBody, token);
        new HttpAssertions(response).statusCode(404);
        response.then().body("message", equalTo("Product not added to cart"));

    }

    @Test
    public void testAddProductWithInvalidToken() {
        String wrongToken = "SGHTT";
        CartRequest requestBody = new CartRequest(1, 2);
        Response response = apiProvider.post("/cart", requestBody, wrongToken);
        new HttpAssertions(response).statusCode(401);
    }
}

