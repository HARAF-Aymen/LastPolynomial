package Proj.polynomes.polynome.repository;

import Proj.polynomes.polynome.entity.Polynomial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolynomialRepository extends JpaRepository<Polynomial, Long> {
}
