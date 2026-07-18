package api_dadata.service;

import api_dadata.config.Endpoints;
import api_dadata.config.TestData;
import api_dadata.dto.FioResponse;
import api_dadata.dto.IpResponse;
import api_dadata.dto.SuggestRequest;
import api_dadata.dto.SuggestResponse;
import api_dadata.spec.Specifications;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

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

    public static IpResponse getIpResponse(String ipAddress) {
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

    // Метод специально для негативных тестов подсказки адреса (post)
    public static Response getAddressErrorResponse(SuggestRequest requestBody, String token) {

        var request = given()
                .baseUri(TestData.BASE_URL)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody);

        if (token != null) {
            request.header("Authorization", "Token " + token);
        }

        return request
                .when()
                .post(Endpoints.SUGGEST_ADDRESS.getPath())
                .then().log().all()
                .extract().response();
    }

    // Метод специально для негативных тестов поиска по IP (get)
    public static Response getIpErrorResponse(String ipAddress, String token) {

        var request = given()
                .baseUri(TestData.BASE_URL)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);

        if (token != null) {
            request.header("Authorization", "Token " + token);
        }

        return request
                .queryParam("ip", ipAddress)
                .when()
                .get(Endpoints.IP_LOCATE.getPath())
                .then().log().all()
                .extract().response(); // Возвращаем сырой ответ без проверок статуса
    }

    // Метод для успешного поиска ФИО
    public static FioResponse getFioSuggestions(SuggestRequest requestBody) {
        return given()
                .spec(Specifications.requestSpec())
                .body(requestBody)
                .when()
                .post(Endpoints.SUGGEST_FIO.getPath())
                .then().log().all()
                .spec(Specifications.responseSpec200())
                .extract().as(FioResponse.class);
    }

    // Сырой метод для негативных сценариев ФИО
    public static Response getFioErrorResponse(Object requestBody, String token) {
        var request = given()
                .baseUri(TestData.BASE_URL)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody);

        if (token != null) {
            request.header("Authorization", "Token " + token);
        }

        return request
                .when()
                .post(Endpoints.SUGGEST_FIO.getPath())
                .then().log().all()
                .extract().response();
    }

    // Метод для проверки ошибки 405 (Неверный метод HTTP)
    public static Response getFioWithWrongHttpMethod(String token) {
        return given()
                .baseUri(TestData.BASE_URL)
                .header("Authorization", "Token " + token)
                .when()
                // Эндпоинт ФИО ждет POST, а высылается GET
                .get(Endpoints.SUGGEST_FIO.getPath())
                .then().log().all()
                .extract().response();
    }

    // Метод для проверки несуществующего эндпоинта (Ожидаем 404)
    public static Response getNotFoundResponse() {
        return given()
                .spec(Specifications.requestSpec())
                .body("{}")
                .when()
                .post("/suggest/enotiki")
                .then().log().all()
                .extract().response();
    }
}
