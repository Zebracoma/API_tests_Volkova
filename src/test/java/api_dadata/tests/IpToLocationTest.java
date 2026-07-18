package api_dadata.tests;

import api_dadata.config.TestData;
import api_dadata.dto.IpResponse;
import api_dadata.service.DaDataService;
import api_dadata.spec.Specifications;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static api_dadata.service.DaDataService.getIpResponce;
import static io.restassured.RestAssured.given;

public class IpToLocationTest {

    @Test
    public void checkCityByIpTest(){

        String testIp = TestData.VALID_IP;

        IpResponse resp = DaDataService.getIpResponce(testIp);

        Assertions.assertNotNull(resp.getLocation(), "Локация не найдена (NULL)");

        String actualCity = resp.getLocation().getData().getCity();

        Assertions.assertEquals(TestData.EXPECTED_CITY, actualCity, "Город определен не верно!");

    }
}
