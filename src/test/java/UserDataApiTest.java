import accertions.HttpAssertions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.*;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.hamcrest.Matchers.equalTo;


class UserDataApiTest extends BaseApiTest {
    ObjectMapper objectMapper = new ObjectMapper();
    private static String token;
    private static final User user = new User("string", "string");

    @BeforeAll
    public static void initializeUserData() {
        registerUser();
        getToken();
    }
    private static void registerUser() {
        Response response = apiProvider.post("/register", user);
        response.then().statusCode(200);
    }

    private static void getToken() {
        Response response = apiProvider.post("/login", user);
        response.then().statusCode(200);
        token = response.jsonPath().getString("access_token");
        response.then().body("message", equalTo("Token got successfully"));
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
        ValidatableResponse validatableResponse = apiProvider.get("/products").then().statusCode(200);
        List<CartResponse> cartResponses = validatableResponse.extract().as(new TypeRef<>() {});
        HttpAssertions assertions = new HttpAssertions((Response) cartResponses);
        assertions.validateCartResponse()
                .statusCode(200)
                .assertMessage("Got product list");

    }

    @Test
    public void testGetSpecificProduct() {
        String productId = "1";
        ValidatableResponse validatableResponse = (ValidatableResponse) apiProvider.get("/products/" + productId);
        List<CartResponse> cartResponses = validatableResponse.extract().as(new TypeRef<>() {});
        HttpAssertions assertions = new HttpAssertions((Response) cartResponses);
        assertions.validateCartResponse()
                .statusCode(200)
                .validateCartResponse();
        validatableResponse.body("message", equalTo("Got specific product"));
    }


    @Test
    public void testGetUnrealIdProduct() {
        String productId = "111";
        Response response = apiProvider.get("/products/" + productId)
                .then()
                .statusCode(404)
                .extract()
                .response();
        response.then()
                .body("message", equalTo("Product not found"));
    }


    @Test
    public void testGetCustomerCart() {
        ValidatableResponse validatableResponse = apiProvider.get("/cart", token).then();
        List<CartResponse> cartResponses = validatableResponse.extract().as(new TypeRef<>() {});
        HttpAssertions assertions = new HttpAssertions((Response) cartResponses);
        assertions.validateCartResponse()
                .statusCode(200)
                .assertionsJsonPathValueNotEmpty("cart")
                .validateCartResponse();

        validatableResponse.body("message", equalTo("Got customer cart"));
    }


    @Test
    public void testAddExistingProductToCart() {

        ValidatableResponse response = apiProvider.post("/cart", new CartRequest(1, 2), token).then();
        HttpAssertions assertions = new HttpAssertions((Response) response);
        assertions.statusCode(200)
                .assertionsJsonPathValueNotEmpty("message");
        String jsonResponse = response.extract().asString();
        try {
            CartResponse cartResponse = objectMapper.readValue(jsonResponse, CartResponse.class);
        } catch (JsonProcessingException e) {

            throw new RuntimeException(e);
        }

        response.body("message", equalTo("Existing product added"));
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

