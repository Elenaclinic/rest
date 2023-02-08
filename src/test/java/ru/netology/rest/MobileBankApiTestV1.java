package ru.netology.rest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

class MobileBankApiTestV1 {
    @Test
    void shouldReturnDemoAccounts() {
        // Given - When - Then
        // Предусловия
        given()
                .baseUri("http://localhost:9999/api/v1")
                // Выполняемые действия
                .when()
                .get("/demo/accounts")
                // Проверки
                .then()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=UTF-8")
                .contentType(ContentType.JSON)
                .body("", hasSize(3))
                .body("[1].balance", greaterThanOrEqualTo(0))
                .body("every { it.balance >= 0}", is(true));

    }

    @Test
    void jsonSchemaValidator() {
        given()
                .baseUri("http://localhost:9999/api/v1")
                // Выполняемые действия
                .when()
                .get("/demo/accounts")
                // Проверки
                .then()
                .body(matchesJsonSchemaInClasspath("accounts.schema.json"));
    }
}
