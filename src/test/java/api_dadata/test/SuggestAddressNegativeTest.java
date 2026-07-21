package api_dadata.test;

import api_dadata.dto.SuggestRequest;
import api_dadata.service.DaDataService;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;

@ResourceLock("DaData_API")
class SuggestAddressNegativeTest {

    private static final String INVALID_TOKEN = "1234567890abcdef1234567890abcdef12345678";

    @Test
    @DisplayName("Проверка запроса с невалидным токеном (Ожидаем 403)")
    void invalidTokenTest() {
        SuggestRequest requestBody = new SuggestRequest("москва");

        Response response = DaDataService.getAddressErrorResponse(requestBody, INVALID_TOKEN);

        Assertions.assertEquals(403, response.statusCode(), "Ожидался статус 403 Forbidden");
    }

    @Test
    @DisplayName("Проверка запроса без токена (Ожидаем 401)")
    void noTokenTest() {
        SuggestRequest requestBody = new SuggestRequest("москва");

        Response response = DaDataService.getAddressErrorResponse(requestBody, null);

        Assertions.assertEquals(401, response.statusCode(), "Ожидался статус 401 Unauthorized");
    }
}