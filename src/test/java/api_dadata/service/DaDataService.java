package api_dadata.service;

import api_dadata.config.TestData;
import api_dadata.dto.IpResponse;
import api_dadata.dto.SuggestRequest;
import api_dadata.dto.SuggestResponse;
import static io.restassured.RestAssured.given;

public class DaDataService {

    public static SuggestResponse getAddressSuggestions(SuggestRequest requestBody) {
        return given()
                .body(requestBody)
                .when()
                .post("/suggest/address")
                .then().log().all()
                .extract().as(SuggestResponse.class);
    }

    public static IpResponse getIpResponce() {
        return given()
                .queryParam("ip", TestData.VALID_IP)
                .when()
                .get("/iplocate/address")
                .then().log().all()
                .extract().as(IpResponse.class);
    }
}
