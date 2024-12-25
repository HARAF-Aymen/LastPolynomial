package Proj.polynomes.polynome.controller;


import Proj.polynomes.polynome.entity.Polynomial;
import Proj.polynomes.polynome.entity.PolynomialRequest;
import Proj.polynomes.polynome.service.PolynomialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/polynomials")
public class PolynomialController {

    private static final Logger logger = LoggerFactory.getLogger(PolynomialController.class);


    @Autowired
    private PolynomialService service;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Polynomial> createPolynomial(@RequestBody Polynomial request) {

        logger.info("Received PolynomialRequest: {}", request);

        if (request.getExpression() == null || request.getExpression().isEmpty()) {
            logger.warn("PolynomialRequest expression is null or empty!");
            return ResponseEntity.badRequest().build();
        }


        return ResponseEntity.ok(service.createPolynomial(request));

    }

    @GetMapping
    public ResponseEntity<List<Polynomial>> getAllPolynomials() {
        return ResponseEntity.ok(service.getAllPolynomials());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Polynomial> getPolynomialById(@PathVariable Long id) {
        Polynomial polynomial = service.getPolynomialById(id);
        return polynomial != null ? ResponseEntity.ok(polynomial) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePolynomial(@PathVariable Long id) {
        service.deletePolynomial(id);
        return ResponseEntity.noContent().build();
    }
}
