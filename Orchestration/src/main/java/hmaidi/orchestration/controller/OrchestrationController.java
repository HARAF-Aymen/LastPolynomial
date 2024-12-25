package hmaidi.orchestration.controller;

import hmaidi.orchestration.dto.Polynomial;
import hmaidi.orchestration.service.OrchestrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orchestration")
@CrossOrigin("http://localhost:3000")

public class OrchestrationController {

    @Autowired
    private OrchestrationService orchestrationService;

    @PostMapping("/process")
    public ResponseEntity<String> processPolynomial(@RequestBody Polynomial expression) {

        String result = orchestrationService.processPolynomial(expression);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/roots")
    public ResponseEntity<List<Double>> getRoots(@RequestBody Polynomial expression) {
        List<Double> roots = orchestrationService.getRoots(expression.getExpression());
        return ResponseEntity.ok(roots);
    }


}