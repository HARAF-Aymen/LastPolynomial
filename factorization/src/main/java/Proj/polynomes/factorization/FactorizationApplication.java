package Proj.polynomes.factorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient

public class FactorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactorizationApplication.class, args);
	}

}
