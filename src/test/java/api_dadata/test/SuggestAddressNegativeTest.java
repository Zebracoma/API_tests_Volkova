package api_dadata.test;

import api_dadata.dto.IpResponse;
import api_dadata.dto.SuggestRequest;
import api_dadata.dto.SuggestResponse;
import api_dadata.service.DaDataService;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SuggestAddressNegativeTest {

    @Test
    @DisplayName("Проверка запроса с невалидным токеном (Ожидаем 403)")
    public void invalidTokenTest() {
        SuggestRequest requestBody = new SuggestRequest("москва");
        String badToken = "1234567890abcdef1234567890abcdef12345678";

        Response response = DaDataService.getAddressErrorResponse(requestBody, badToken);

        Assertions.assertEquals(403, response.statusCode(), "API должен возвращать 403 для неверного токена");
    }

    @Test
    @DisplayName("Проверка запроса без токена (Ожидаем 401)")
    public void noTokenTest() {
        SuggestRequest requestBody = new SuggestRequest("москва");

        Response response = DaDataService.getAddressErrorResponse(requestBody, null);

        Assertions.assertEquals(401, response.statusCode(), "API должен возвращать 401 при отсутствии токена");
    }

    @Test
    @DisplayName("Проверка запроса с пустым телом поиска (Ожидаем 200 и пустой список)")
    public void emptyQueryTest() {
        SuggestRequest requestBody = new SuggestRequest(""); // Пустая строка поиска
        String validToken = System.getenv("DADATA_TOKEN");

        SuggestResponse response = DaDataService.getAddressSuggestions(requestBody);

        Assertions.assertTrue(response.getSuggestions().isEmpty(), "Для пустого запроса список должен быть пустым");
    }

    @Test
    @DisplayName("Проверка IP, для которого нет результата (Ожидаем location: null)")
    public void ipWithoutResultTest() {
        String unknownIp = "127.0.0.1";

        IpResponse resp = DaDataService.getIpResponse(unknownIp);

        Assertions.assertNull(resp.getLocation(), "Для неизвестного IP локация должна быть null!");
    }

    @Test
    @DisplayName("Проверка слишком короткого запроса (Ожидаем 200 и пустой список)")
    public void shortQueryTest() {
        SuggestRequest requestBody = new SuggestRequest("ъ");

        SuggestResponse response = DaDataService.getAddressSuggestions(requestBody);

        Assertions.assertTrue(response.getSuggestions().isEmpty(), "Для 1 буквы список должен быть пустым");
    }
}