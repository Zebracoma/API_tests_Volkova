package api_dadata.spec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import api_dadata.config.TestData;

public class Specifications {

    private static final String API_TOKEN = System.getenv("DADATA_TOKEN");

    // Единая спецификация для всех запросов
    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(TestData.BASE_URL)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                // Используем заголовок, т.к. DaData требует 'Token', а не 'Bearer'
                .addHeader("Authorization", "Token " + API_TOKEN)
                .build();
    }
    // Спецификация для успешного ответа
    public static ResponseSpecification responseSpec200() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }
}