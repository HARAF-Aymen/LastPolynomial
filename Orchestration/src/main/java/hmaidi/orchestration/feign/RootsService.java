package hmaidi.orchestration.feign;

import hmaidi.orchestration.dto.Polynomial;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="ROOTS-SERVICE")
public interface RootsService {
    @PostMapping(value = "/api/roots",consumes = "application/json")
    ResponseEntity<List<Double>> calculateRoots(@RequestBody Polynomial request);

}
