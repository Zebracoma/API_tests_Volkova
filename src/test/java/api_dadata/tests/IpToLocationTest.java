package api_dadata.tests;

import api_dadata.config.TestData;
import api_dadata.dto.IpResponse;
import api_dadata.spec.Specifications;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class IpToLocationTest {
    private final String URL = "https://suggestions.dadata.ru/suggestions/api/4_1/rs";

    @Test
    public void checkCityByIpTest(){

        Specifications.installSpecification(Specifications.reuestSpec(URL), Specifications.responseSpecOK200());
        String testIp = TestData.VALID_IP;

        IpResponse resp = given()
                .queryParam("ip", testIp)
                .when()
                .get("/iplocate/address")
                .then().log().all()
                .extract().as(IpResponse.class);

        Assertions.assertNotNull(resp.getLocation(), "Локация не найдена (NULL)");

        String actualCity = resp.getLocation().getData().getCity();

        Assertions.assertEquals(TestData.EXPECTED_CITY, actualCity, "Город определен не верно!");

    }
}
