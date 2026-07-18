package api_dadata.test;

import api_dadata.dto.IpResponse;
import api_dadata.service.DaDataService;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IpToLocationNegativeTest {

    @Test
    @DisplayName("Проверка запроса по IP без токена (Ожидаем 401)")
    public void noTokenIpTest() {
        Response response = DaDataService.getIpErrorResponse("46.226.227.20", null);

        Assertions.assertEquals(401, response.statusCode(), "API должен возвращать 401 при отсутствии токена");
    }

    @Test
    @DisplayName("Проверка запроса по IP с неверным токеном (Ожидаем 403)")
    public void invalidTokenIpTest() {
        String badToken = "1234567890abcdef1234567890abcdef12345678";

        Response response = DaDataService.getIpErrorResponse("46.226.227.20", badToken);

        Assertions.assertEquals(403, response.statusCode(), "API должен возвращать 403 для неверного токена");
    }

    @Test
    @DisplayName("Проверка неизвестного IP (Ожидаем 200 и location = null)")
    public void unknownIpTest() {
        String unknownIp = "127.0.0.1";

        IpResponse resp = DaDataService.getIpResponse(unknownIp);

        Assertions.assertNull(resp.getLocation(), "Для неизвестного IP объект location должен быть null!");
    }

    @Test
    @DisplayName("Проверка на отправку пустого IP (Ожидаем 200 и Определение локации вызывающего)")
    public void emptyIpTest() {
        IpResponse resp = DaDataService.getIpResponse("");

        Assertions.assertNotNull(resp.getLocation(), "При пустом IP сервер должен вернуть локацию вызывающего");
        }
}