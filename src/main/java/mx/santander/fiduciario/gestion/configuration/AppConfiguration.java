package mx.santander.fiduciario.gestion.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {

	@Bean("clienteRest")
//	@LoadBalanced
	public RestTemplate registrarRestTemplate()// Synchronous client to perform HTTP requests, exposing a simple,
												// templatemethod API over underlying HTTP client libraries such as the
												// JDK HttpURLConnection, Apache HttpComponents, and others.
	{
		return new RestTemplate();
	}
}
