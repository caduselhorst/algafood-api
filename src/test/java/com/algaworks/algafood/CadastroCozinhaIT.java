package com.algaworks.algafood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

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
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.util.DataBaseCleaner;
import com.algaworks.algafood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroCozinhaIT {

	private static final int ID_COZINHA_INEXISTENTE = 100;

	@LocalServerPort
	private int port;
	
	@Autowired
	private DataBaseCleaner dbCleaner;
	
	@Autowired
	private CozinhaRepository repository;
	
	private List<Cozinha> cozinhas;
		
	@BeforeEach
	public void setUp() {

		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		
		dbCleaner.clearTables();
		prepararDados();
		
	}
	
	private void prepararDados() {
		
		cozinhas = new ArrayList<>();
		
		Cozinha c1 = new Cozinha();
		c1.setNome("Tailandesa");
		
		Cozinha c2 = new Cozinha();
		c2.setNome("Americana");
		
		cozinhas.add(c1);
		cozinhas.add(c2);
		

		cozinhas.forEach(c -> repository.save(c));
		
		
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
		
	}
	
	@Test
	public void deveConterXCozinhas_QuandoConsultarCozinhas() {

		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(cozinhas.size()));
		
		
	}
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarCozinha() {
		given()
			.body(ResourceUtils.getContentFromResource("/json/correto/cozinha-chinesa.json"))
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente() {
		given()
			.pathParam("cozinhaId", cozinhas.get(0).getId())
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo(cozinhas.get(0).getNome()));
			
	}
	
	@Test
	public void deveRetornarStatusCorreto_QuandoConsultarCozinhaInexistente() {
		given()
			.pathParam("cozinhaId", ID_COZINHA_INEXISTENTE)
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());

			
	}
	
	
	
	

}
