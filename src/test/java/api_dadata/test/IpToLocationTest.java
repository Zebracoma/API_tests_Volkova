package api_dadata.test;

import api_dadata.dto.IpResponse;
import api_dadata.service.DaDataService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@ResourceLock("DaData_API")
class IpToLocationTest {

    @DisplayName("Поиск города по различным IP-адресам")
    @ParameterizedTest
    // Передаем в тест пары: IP, ожидаемый город для данного IP
    @CsvSource({
            "46.226.227.20, Москва",
            "217.197.224.44, Санкт-Петербург",
            "217.118.93.33, Нижний Новгород"
    })
    void checkCityByIpTest(String ipAddress, String expectedCity) {
        String cleanIp = ipAddress.trim();
        IpResponse resp = DaDataService.getIpResponse(ipAddress);

        Assertions.assertNotNull(resp.getLocation(), "Локация не найдена (NULL)");

        String actualCity = resp.getLocation().getData().getCity();

        Assertions.assertEquals(expectedCity, actualCity, "Город определен не верно!");

    }
}
