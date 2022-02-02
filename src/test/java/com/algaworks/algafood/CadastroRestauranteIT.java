package com.algaworks.algafood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.util.DataBaseCleaner;
import com.algaworks.algafood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroRestauranteIT {

	private static final int ID_RESTAURANTE_INEXISTENTE = 100;
    private static final String DADOS_INVALIDOS_PROBLEM_TITLE = "Dados inválidos";
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private DataBaseCleaner dbCleaner;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	private List<Restaurante> restaurantes;
	
	@BeforeEach
	public void setUp() {

		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/restaurantes";
		
		dbCleaner.clearTables();
		prepararDados();
		
	}
	
	private void prepararDados() {
		
		Cozinha c = new Cozinha();
		c.setNome("Italiana");
		
		c = cozinhaRepository.save(c);
		
		restaurantes = new ArrayList<>();
		
		Restaurante r1 = new Restaurante();
		r1.setCozinha(c);
		r1.setNome("Cantina da Nona");
		r1.setTaxaFrete(BigDecimal.valueOf(10));
		
		Restaurante r2 = new Restaurante();
		r2.setCozinha(c);
		r2.setNome("Restaurante Madalosso");
		r2.setTaxaFrete(BigDecimal.valueOf(10));
		
		Restaurante r3 = new Restaurante();
		r3.setCozinha(c);
		r3.setNome("Cantina Dona Gioconda");
		r3.setTaxaFrete(BigDecimal.valueOf(10));
		
		restaurantes.add(r1);
		restaurantes.add(r2);
		restaurantes.add(r3);
		
		
		restaurantes.forEach(r -> restauranteRepository.save(r));
	}
	
	@Test
	public void deveRetornarRestaurantesEStatus200_QuandoConsultarRestaurantes() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(restaurantes.size()))
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarStatusOkERestaurante_QuandoConsultarUmRestauranteExistente() {
		given()
			.pathParam("restauranteId", restaurantes.get(0).getId())
			.accept(ContentType.JSON)
		.when()
			.get("/{restauranteId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo(restaurantes.get(0).getNome()));
	}
	
	@Test
	public void deveRetornar404_QuandoConsultarUmRestauranteInexistente() {
		given()
			.pathParam("restauranteId", ID_RESTAURANTE_INEXISTENTE)
			.accept(ContentType.JSON)
		.when()
			.get("/{restauranteId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void deveRetornarStatusCreated_QuandoCadastrarUmNovoRestaurante() {
		given()
			.body(ResourceUtils.getContentFromResource("/json/correto/novo-restaurante.json"))
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deveRetornarBadRequest_QuandoCadastrarUmRestauranteSemNome() {
		given()
			.body(ResourceUtils.getContentFromResource("/json/errado/restaurante-sem-nome.json"))
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("title", equalTo(DADOS_INVALIDOS_PROBLEM_TITLE));
	}
	
	@Test
	public void deveRetornarBadRequest_QuandoCadastrarUmRestauranteSemTaxaFrete() {
		given()
			.body(ResourceUtils.getContentFromResource("/json/errado/restaurante-sem-taxafrete.json"))
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("title", equalTo(DADOS_INVALIDOS_PROBLEM_TITLE));
	}
	
	@Test
	public void deveRetornarBadRequest_QuandoAtualizarUmRestauranteParaTaxaFreteZeroSemAlterarONome() {
		given()
			.pathParam("restauranteId", restaurantes.get(0).getId())
			.body(ResourceUtils.getContentFromResource("/json/errado/restaurante-taxafretezero-sem-frete-gratis-no-nome.json"))
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
		.when()
			.put("/{restauranteId}")
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("title", equalTo(DADOS_INVALIDOS_PROBLEM_TITLE));
	}
	
	@Test
	public void deveRetornarBadRequest_QuandoInserirUmRestauranteSemCozinha() {
		given()
			.body(ResourceUtils.getContentFromResource("/json/errado/restaurante-sem-cozinha.json"))
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("title", equalTo(DADOS_INVALIDOS_PROBLEM_TITLE));
	}
	
}
