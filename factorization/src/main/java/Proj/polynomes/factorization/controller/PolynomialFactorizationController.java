package Proj.polynomes.factorization.controller;

import Proj.polynomes.factorization.model.PolynomialFactorizationRequest;
import Proj.polynomes.factorization.service.PolynomialFactorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factorization")
public class PolynomialFactorizationController {

    @Autowired
    private PolynomialFactorizationService factorizationService;

    @PostMapping
    public ResponseEntity<String> factorizePolynomial(
            @RequestBody PolynomialFactorizationRequest request) {

        double[] coefficients = request.getCoefficients();
        List<Double> roots = request.getRoots();

        String factorizedPolynomial = factorizationService.factorizePolynomial(coefficients, roots);

        return ResponseEntity.ok(factorizedPolynomial);
    }
}