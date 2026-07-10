package api_dadata.tests;

import api_dadata.config.TestData;
import api_dadata.dto.SuggestRequest;
import api_dadata.dto.SuggestResponse;
import api_dadata.service.DaDataService;
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

        SuggestRequest requestBody = new SuggestRequest(TestData.VALID_SEARCH_QUERY);

        SuggestResponse response = DaDataService.getAddressSuggestions(requestBody);

        // Проверка - список подсказок не пустой - нашлось хоть какое-то совпадение
        Assertions.assertFalse(response.getSuggestions().isEmpty(), "Сервер вернул пустой список подсказок!");

        String actualCity = response.getSuggestions().get(0).getData().getCity();
        String actualStreet = response.getSuggestions().get(0).getData().getStreet();

        // Проверка корректной работы алгоритма
        Assertions.assertEquals(TestData.EXPECTED_CITY, actualCity, "Город определен неверно");
        Assertions.assertEquals(TestData.EXPECTED_STREET, actualStreet, "Улица определена неверно");
    }
}