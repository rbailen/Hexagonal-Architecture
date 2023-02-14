package es.rbailen.sample.hexagonalarchitecture;

import es.rbailen.sample.hexagonalarchitecture.infrastructure.adapters.output.persistence.entity.ProductE;
import es.rbailen.sample.hexagonalarchitecture.infrastructure.adapters.output.persistence.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductPostgresSQLTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }

    @Test
    @Order(1)
    void saveProductTest() {
        webTestClient.post()
                .uri("/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("{\"name\":\"MacBook Pro\",\"description\":\"14-inch MacBook Pro model\"}"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody().json("{\"id\":1,\"name\":\"MacBook Pro\",\"description\":\"14-inch MacBook Pro model\"}");


    }

    @Test
    @Order(2)
    void getOrderTest() {

        final var savedProduct = productRepository.save(new ProductE("Lenovo", "IdeaPad Laptop"));

        webTestClient.get()
                .uri("/v1/products/" + savedProduct.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody().json("{\"id\":" + savedProduct.getId() + ",\"name\":\"Lenovo\",\"description\":\"IdeaPad Laptop\"}");
    }

}