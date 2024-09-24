package accertions;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;


import static org.hamcrest.Matchers.*;


public class HttpAssertions {

    private final ValidatableResponse validatableResponse;


    public HttpAssertions(Response response) {
        this.validatableResponse = response.then();
    }

    @Step("Проверяем, что статус код равен '{expectStatus}'")
    public HttpAssertions statusCode(int expectStatus) {
        validatableResponse.statusCode(expectStatus);
        return this;
    }

    @Step("Проверяем, что Ответ не пустой")
    public HttpAssertions assertionsJsonPathValueNotEmpty(String path) {
        validatableResponse.body(path, not(emptyOrNullString()));
        return this;
    }



}
