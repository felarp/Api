package accertions;


import dto.CartResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HttpAssertions {

    private final ValidatableResponse validatableResponse;
    private final Response response;


    public HttpAssertions(Response response) {
        this.validatableResponse = response.then();
        this.response = response;
    }
    @Step("Сообщение, о статусе проверки")
    public HttpAssertions assertMessage(String expectedMessage) {
        validatableResponse.body("message", equalTo(expectedMessage));
        return this;
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

    @Step("Проверяем содержимое CartResponse")
    public HttpAssertions validateCartResponse() {
        CartResponse cartResponse = response.as(CartResponse.class);

        assertThat(cartResponse, notNullValue());
        assertThat(cartResponse.getCart(), notNullValue());
        assertThat(cartResponse.getTotalPrice(), is(greaterThan(0.0)));
        assertThat(cartResponse.getTotalDiscount(), is(greaterThanOrEqualTo(0.0)));
        return this;
    }

}

