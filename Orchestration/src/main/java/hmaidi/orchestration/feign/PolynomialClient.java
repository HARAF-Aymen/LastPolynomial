package hmaidi.orchestration.feign;


import hmaidi.orchestration.dto.Polynomial;
import hmaidi.orchestration.dto.PolynomialRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "POLYNOMIAL-SERVICE")
public interface PolynomialClient {

    @PostMapping(value = "/api/polynomials")
    ResponseEntity<Polynomial> createPolynomial(Polynomial request);

    @GetMapping("/api/polynomials/{id}")
    ResponseEntity<?> getPolynomialById(@PathVariable Long id);
}
