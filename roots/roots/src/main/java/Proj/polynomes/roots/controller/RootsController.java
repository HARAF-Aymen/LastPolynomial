package Proj.polynomes.roots.controller;

import Proj.polynomes.roots.model.Polynomial;
import Proj.polynomes.roots.model.PolynomialRequest;
import Proj.polynomes.roots.service.RootCalculationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/roots")
public class RootsController {

    private static final Logger log = LoggerFactory.getLogger(RootsController.class);

    @Autowired
    private RootCalculationService rootCalculationService;

    @PostMapping
    public ResponseEntity<List<Double>> calculateRoots(@RequestBody Polynomial request) {
        System.out.println("Expression reçue pour calcul des racines : " + request.getExpression());

        if (request.getExpression() == null || request.getExpression().isEmpty()) {
            System.err.println("Expression reçue vide ou null : " + request);
            throw new RuntimeException("Expression invalide reçue");
        }

        List<Double> roots = new ArrayList<>(rootCalculationService.calculateRoots(request.getExpression()));
        return ResponseEntity.ok(roots);
    }
}

