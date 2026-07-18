package api_dadata.test;

import api_dadata.dto.SuggestRequest;
import api_dadata.service.DaDataService;
import api_dadata.spec.Specifications;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SuggestFioNegativeTest {

    @Test
    @DisplayName("Проверка запроса ФИО без токена (Ожидаем 401)")
    public void noTokenFioTest() {
        SuggestRequest requestBody = new SuggestRequest("мария");
        Response response = DaDataService.getFioErrorResponse(requestBody, null);

        Assertions.assertEquals(401, response.statusCode(), "API должен возвращать 401 при отсутствии токена");
    }

    @Test
    @DisplayName("Проверка запроса ФИО с невалидным токеном (Ожидаем 403)")
    public void invalidTokenFioTest() {
        SuggestRequest requestBody = new SuggestRequest("мария");
        String badToken = "1234567890abcdef1234567890abcdef12345678";

        Response response = DaDataService.getFioErrorResponse(requestBody, badToken);

        Assertions.assertEquals(403, response.statusCode(), "API должен возвращать 403 для неверного токена");
    }

    @Test
    @DisplayName("Проверка запроса с неверным HTTP-методом GET вместо POST")
    public void wrongHttpMethodTest() {
        String validToken = System.getenv("DADATA_TOKEN");

        // Отправляем GET-запрос на POST-эндпоинт
        Response response = DaDataService.getFioWithWrongHttpMethod(validToken);

        Assertions.assertEquals(200, response.statusCode(), "API должен возвращать 200 при использовании GET вместо POST");
    }

    @Test
    @DisplayName("Проверка обращения к несуществующему эндпоинту (Ожидаем 404)")
    public void endpointNotFoundTest() {
        Response response = DaDataService.getNotFoundResponse();

        Assertions.assertEquals(404, response.statusCode(), "API должен возвращать 404 для несуществующего пути");
    }
}