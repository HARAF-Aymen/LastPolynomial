package Proj.polynomes.polynome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient

public class PolynomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PolynomeApplication.class, args);
	}

}
