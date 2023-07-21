package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class CepTest {

    @BeforeEach
    public void beforeEach() {
        baseURI = "https://viacep.com.br/";
        basePath = "ws";
    }

    @Test
    public void  testCepValido(){
        given()
                .pathParam("cep", "86990970")
                .when()
                    .get("/{cep}/json")
                .then()
                    .body("cep", is("86990-970"))
                    .body("logradouro", is("Rua Presidente Nereu Ramos"))
                    .body("complemento", is("895"))
                    .body("localidade", is("Marialva"))
                    .body("uf", is("PR"))
                    .statusCode(200);
    }

    @Test
    public void testCepInvalido(){
        given()
                .pathParam("cep", "99999999")
                .when()
                    .get("/{cep}/json")
                .then()
                    .body("erro", is(true))
                    .statusCode(200);
    }

}
