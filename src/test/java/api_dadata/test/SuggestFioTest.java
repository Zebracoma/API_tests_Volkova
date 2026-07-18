package api_dadata.test;

import api_dadata.dto.FioResponse;
import api_dadata.dto.SuggestRequest;
import api_dadata.service.DaDataService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SuggestFioTest {

    @DisplayName("Проверка разбивки ФИО и определения пола")
    @ParameterizedTest
    @CsvSource({
            "виктор иван, Виктор, Иванович, MALE",      // Запрос, Ожидаемое Имя, Ожидаемое Отчество, Пол
            "мария волкова, Мария, Волкова, FEMALE",
            "александр пушкин, Александр, Пушкин, MALE"
    })
    public void checkFioSuggestionTest(String query, String expectedName, String expectedSecondPart, String expectedGender) {

        SuggestRequest requestBody = new SuggestRequest(query);
        FioResponse response = DaDataService.getFioSuggestions(requestBody);

        Assertions.assertFalse(response.getSuggestions().isEmpty(), "Сервер вернул пустой список для запроса: " + query);

        String actualName = response.getSuggestions().get(0).getData().getName();
        String actualGender = response.getSuggestions().get(0).getData().getGender();

        Assertions.assertEquals(expectedName, actualName, "Имя определено неверно!");
        Assertions.assertEquals(expectedGender, actualGender, "Пол определен неверно!");
    }
}