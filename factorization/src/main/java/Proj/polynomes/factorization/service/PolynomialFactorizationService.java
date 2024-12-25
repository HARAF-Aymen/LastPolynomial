package Proj.polynomes.factorization.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolynomialFactorizationService {


    public String factorizePolynomial(double[] coefficients, List<Double> roots) {
        StringBuilder factorizedForm = new StringBuilder();

        double leadingCoefficient = coefficients[0];
        if (leadingCoefficient != 1.0) {
            factorizedForm.append(leadingCoefficient).append(" * ");
        }

        for (Double root : roots) {
            if (root == 0) {
                factorizedForm.append("(x)");
            } else if (root > 0) {
                factorizedForm.append("(x - ").append(roundToPrecision(root, 6)).append(")");
            } else {
                factorizedForm.append("(x + ").append(roundToPrecision(Math.abs(root), 6)).append(")");
            }
        }

        return factorizedForm.toString();
    }

    private double roundToPrecision(double value, int precision) {
        double scale = Math.pow(10, precision);
        return Math.round(value * scale) / scale;
    }
}
