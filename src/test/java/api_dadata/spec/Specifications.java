package api_dadata.spec;

import api_dadata.config.TestData;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {

    private static final String API_TOKEN = System.getenv("DADATA_TOKEN");

    public static RequestSpecification requestSpec(String token) {
        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(TestData.BASE_URL)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON);

        if (token != null) {
            builder.addHeader("Authorization", "Token " + token);
        }

        return builder.build();
    }

    public static RequestSpecification requestSpec() {
        return requestSpec(API_TOKEN);
    }

    public static ResponseSpecification responseSpec200() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }
}