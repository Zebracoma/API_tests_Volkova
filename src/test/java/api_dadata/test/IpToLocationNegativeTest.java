package api_dadata.test;

import api_dadata.dto.IpResponse;
import api_dadata.service.DaDataService;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;

@ResourceLock("DaData_API")
class IpToLocationNegativeTest {

    private static final String INVALID_TOKEN = "1234567890abcdef1234567890abcdef12345678";

    @Test
    @DisplayName("Проверка запроса по IP без токена (Ожидаем 401)")
    void noTokenIpTest() {
        Response response = DaDataService.getIpErrorResponse("46.226.227.20", null);

        Assertions.assertEquals(401, response.statusCode(), "API должен возвращать 401 при отсутствии токена");
    }

    @Test
    @DisplayName("Проверка запроса по IP с неверным токеном (Ожидаем 403)")
    void invalidTokenIpTest() {
        Response response = DaDataService.getIpErrorResponse("46.226.227.20", INVALID_TOKEN);

        Assertions.assertEquals(403, response.statusCode(), "API должен возвращать 403 для неверного токена");
    }

    @Test
    @DisplayName("Проверка неизвестного IP (Ожидаем 200 и location = null)")
    void unknownIpTest() {
        String unknownIp = "127.0.0.1";

        IpResponse resp = DaDataService.getIpResponse(unknownIp);

        Assertions.assertNull(resp.getLocation(), "Для неизвестного IP объект location должен быть null!");
    }

    @Test
    @DisplayName("Проверка на отправку пустого IP (Ожидаем 200 и Определение локации вызывающего)")
    void emptyIpTest() {
        IpResponse resp = DaDataService.getIpResponse("");

        Assertions.assertNotNull(resp.getLocation(), "При пустом IP сервер должен вернуть локацию вызывающего");
    }
}