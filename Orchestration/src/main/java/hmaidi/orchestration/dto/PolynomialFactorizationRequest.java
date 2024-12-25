package hmaidi.orchestration.dto;

import java.util.List;

public class PolynomialFactorizationRequest {
    private double[] coefficients;
    private List<Double> roots;

    public double[] getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(double[] coefficients) {
        this.coefficients = coefficients;
    }

    public List<Double> getRoots() {
        return roots;
    }

    public void setRoots(List<Double> roots) {
        this.roots = roots;
    }
}
