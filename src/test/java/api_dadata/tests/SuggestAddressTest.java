package api_dadata.tests;

import api_dadata.dto.SuggestRequest;
import api_dadata.dto.SuggestResponse;
import api_dadata.spec.Specifications;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class SuggestAddressTest{

    private final String URL = "https://suggestions.dadata.ru/suggestions/api/4_1/rs";

    @Test
    public void checkAddressSuggestionTest() {

        Specifications.installSpecification(
                Specifications.reuestSpec(URL),
                Specifications.responseSpecOK200()
        );

        String queryText = "москва хабаровская";
        SuggestRequest requestBody = new SuggestRequest(queryText);

        SuggestResponse response = given()
                .body(requestBody)
                .when()
                .post("/suggest/address")
                .then().log().all()
                .extract().as(SuggestResponse.class);

        // Проверка - список подсказок не пустой - нашлось хоть какое-то совпадение
        Assertions.assertFalse(response.getSuggestions().isEmpty(), "Сервер вернул пустой список подсказок!");

        String actualCity = response.getSuggestions().get(0).getData().getCity();
        String actualStreet = response.getSuggestions().get(0).getData().getStreet();

        // Проверка корректной работы алгоритма
        Assertions.assertEquals("Москва", actualCity, "Город определен неверно");
        Assertions.assertEquals("Хабаровская", actualStreet, "Улица определена неверно");
    }
}