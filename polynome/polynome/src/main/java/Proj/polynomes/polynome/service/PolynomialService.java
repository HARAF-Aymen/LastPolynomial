package Proj.polynomes.polynome.service;


import Proj.polynomes.polynome.entity.Polynomial;
import Proj.polynomes.polynome.entity.PolynomialRequest;
import Proj.polynomes.polynome.repository.PolynomialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PolynomialService {

    @Autowired
    private PolynomialRepository repository;

    public Polynomial createPolynomial(Polynomial request) {
        Polynomial polynomial = new Polynomial();
        polynomial.setExpression(request.getExpression());
        polynomial.setDateCreated(LocalDateTime.now());
        return repository.save(polynomial);
    }


    public List<Polynomial> getAllPolynomials() {
        return repository.findAll();
    }

    public Polynomial getPolynomialById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deletePolynomial(Long id) {
        repository.deleteById(id);
    }
}
