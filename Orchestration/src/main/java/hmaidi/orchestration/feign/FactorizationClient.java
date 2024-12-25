package hmaidi.orchestration.feign;

import hmaidi.orchestration.dto.PolynomialFactorizationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "F-SERVICE")
public interface FactorizationClient {

    @PostMapping("/api/factorization")
    ResponseEntity<String> factorizePolynomial(@RequestBody PolynomialFactorizationRequest request);
}
