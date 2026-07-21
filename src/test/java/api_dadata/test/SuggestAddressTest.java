package api_dadata.test;

import api_dadata.dto.SuggestRequest;
import api_dadata.dto.SuggestResponse;
import api_dadata.service.DaDataService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@ResourceLock("DaData_API")
class SuggestAddressTest {

    @DisplayName("Проверка подсказок адреса по текстовому запросу")
    @ParameterizedTest
    // Передаем в тест данные: текстовая последовательность, ожидаемый город, ожидаемая улица
    @CsvSource({
            "москва хабаровская, Москва, Хабаровская",
            "санкт-петербург невский, Санкт-Петербург, Невский",
            "казань баумана, Казань, Баумана"
    })
    void checkAddressSuggestionTest(String query, String expectedCity, String expectedStreet) {
        SuggestRequest requestBody = new SuggestRequest(query);

        SuggestResponse response = DaDataService.getAddressSuggestions(requestBody);

        // Проверка - список подсказок не пустой - нашлось хоть какое-то совпадение
        Assertions.assertFalse(response.getSuggestions().isEmpty(), "Сервер вернул пустой список подсказок!");

        String actualCity = response.getSuggestions().get(0).getData().getCity();
        String actualStreet = response.getSuggestions().get(0).getData().getStreet();

        // Проверка корректной работы алгоритма
        Assertions.assertEquals(expectedCity, actualCity, "Город определен неверно");
        Assertions.assertEquals(expectedStreet, actualStreet, "Улица определена неверно");
    }
}