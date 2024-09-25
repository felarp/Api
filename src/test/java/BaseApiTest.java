
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import providers.ApiProvider;

public class BaseApiTest {

    protected static ApiProvider apiProvider = new ApiProvider();


    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://9b142cdd34e.vps.myjino.ru:49268";
        RestAssured.basePath = "";
        ApiProvider.filters();
    }
}
