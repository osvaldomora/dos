package mx.santander.fiduciario.gestion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GestionUsuarioServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionUsuarioServiceApplication.class, args);
	}
	
	//Inyectar un restTemplate
	@Bean("clienteRestTemplate")
	public  RestTemplate restTemplate() {
		return new RestTemplate();
	}
		
}
