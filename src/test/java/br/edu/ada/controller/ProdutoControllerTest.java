package br.edu.ada.controller;

import br.edu.ada.model.Produto;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

@QuarkusTest
public class ProdutoControllerTest {

    @Test
    public void deveExibirListaVaziaProdutosEndpoint() {
        given()
                .when().get("/produtos")
                .then()
                .statusCode(200)
                .body("$", (empty()));
    }

    @Test
    public void deveListarProdutosEndpoint() {
//        Produto produto = new Produto(1L, "TesteNome", "TesteDescriçao", BigDecimal.valueOf(300.00));
//
//        given()
//                .when().get("/produtos")
//                .then()
//                .statusCode(200)
//                .body("$", (empty()));
    }


}
