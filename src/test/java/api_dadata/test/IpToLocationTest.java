package api_dadata.test;

import api_dadata.config.TestData;
import api_dadata.dto.IpResponse;
import api_dadata.service.DaDataService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class IpToLocationTest {

    @DisplayName("Поиск города по различным IP-адресам")
    @ParameterizedTest
    // Передаем в тест пары: IP, ожидаемый город для данного IP
    @CsvSource({
            "46.226.227.20, Москва",
            "31.184.238.128, Санкт-Петербург",
            "178.248.232.209, Казань"
    })
    public void checkCityByIpTest(String ipAddress, String expectedCity){
        IpResponse resp = DaDataService.getIpResponce(ipAddress);

        Assertions.assertNotNull(resp.getLocation(), "Локация не найдена (NULL)");

        String actualCity = resp.getLocation().getData().getCity();

        Assertions.assertEquals(expectedCity, actualCity, "Город определен не верно!");

    }
}
