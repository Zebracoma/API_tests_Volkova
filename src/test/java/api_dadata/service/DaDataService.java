package api_dadata.service;

import api_dadata.config.Endpoints;
import api_dadata.config.TestData;
import api_dadata.dto.IpResponse;
import api_dadata.dto.SuggestRequest;
import api_dadata.dto.SuggestResponse;
import api_dadata.spec.Specifications;

import static io.restassured.RestAssured.given;

public class DaDataService {

    public static SuggestResponse getAddressSuggestions(SuggestRequest requestBody) {
        return given()
                //Внедрение общих настроек
                .spec(Specifications.requestSpec())
                .body(requestBody)
                .when()
                .post(Endpoints.SUGGEST_ADDRESS.getPath())
                .then().log().all()
                //Проверка статуса кода
                .spec(Specifications.responseSpec200())
                .extract().as(SuggestResponse.class);
    }

    public static IpResponse getIpResponce(String ipAddress) {
        return given()
                //Внедрение общих настроек
                .spec(Specifications.requestSpec())
                .queryParam("ip", ipAddress)
                .when()
                .get(Endpoints.IP_LOCATE.getPath())
                .then().log().all()
                // Проверка статуса
                .spec(Specifications.responseSpec200())
                .extract().as(IpResponse.class);
    }
}
