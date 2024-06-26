package com.macwie.app.product;

import com.macwie.app.BaseIT;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

class ProductControllerIT extends BaseIT {

    @LocalServerPort
    private Integer port;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost:" + port;
        productRepository.deleteAll();
    }

    @Test
    void shouldAddProduct() {
        // given
        ProductDTO dto = new ProductDTO(null, "new product", new BigDecimal("123.45"));

        // then
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(dto)
                .post("/products")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", equalTo(dto.name()))
                .body("price", equalTo(dto.price().toString()));
    }

    @Test
    void shouldReturnFailedValidationForNullNameField() {
        // given
        ProductDTO dto = new ProductDTO(null, null, new BigDecimal("123.45"));

        // then
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(dto)
                .post("/products")
                .then()
                .statusCode(400)
                .body("fieldErrors[0].field", equalTo("name"))
                .body("fieldErrors[0].error", equalTo("must not be empty"));
    }

    @Test
    void shouldReturnFailedValidationForEmptyNameField() {
        // given
        ProductDTO dto = new ProductDTO(null, "", new BigDecimal("123.45"));

        // then
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(dto)
                .post("/products")
                .then()
                .statusCode(400)
                .body("fieldErrors[0].field", equalTo("name"))
                .body("fieldErrors[0].error", equalTo("must not be empty"));
    }

    @Test
    void shouldReturnFailedValidationForNullPriceField() {
        // given
        ProductDTO dto = new ProductDTO(null, "new product", null);

        // then
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(dto)
                .post("/products")
                .then()
                .statusCode(400)
                .body("fieldErrors[0].field", equalTo("price"))
                .body("fieldErrors[0].error", equalTo("must not be null"));
    }

    @Test
    void shouldReturnProducts() {
        // given
        ProductDTO dto = new ProductDTO(null, "new product", new BigDecimal("123.45"));

        given()
                .contentType(ContentType.JSON)
                .when()
                .body(dto)
                .post("/products");

        // then
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .body(".", hasSize(1))
                .body("[0].id", notNullValue())
                .body("[0].name", equalTo(dto.name()))
                .body("[0].price", equalTo(dto.price().toString()));
    }

    @Test
    void shouldReturnProduct() {
        // given
        ProductDTO dto = new ProductDTO(null, "new product", new BigDecimal("123.45"));

        ProductDTO product = given()
                .contentType(ContentType.JSON)
                .when()
                .body(dto)
                .post("/products")
                .getBody()
                .as(ProductDTO.class);

        // then
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/products/" + product.id())
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", equalTo(product.name()))
                .body("price", equalTo(product.price().toString()));
    }

    @Test
    void shouldDeleteProduct() {
        // given
        ProductDTO dto = new ProductDTO(null, "new product", new BigDecimal("123.45"));

        ProductDTO product = given()
                .contentType(ContentType.JSON)
                .when()
                .body(dto)
                .post("/products")
                .getBody()
                .as(ProductDTO.class);

        // then
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/products/" + product.id())
                .then()
                .statusCode(200);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/products/" + product.id())
                .then()
                .statusCode(404);
    }


}
