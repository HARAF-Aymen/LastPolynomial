package Proj.polynomes.roots.service;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.solvers.BrentSolver;
import org.apache.commons.math3.analysis.solvers.LaguerreSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.complex.Complex;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RootCalculationService {


    public Set<Double> calculateRoots(String expression) {
        try {
            double[] coefficients = parsePolynomial(expression);
            printCoefficients(coefficients);

            Set<Double> realRoots = new HashSet<>();
            LaguerreSolver solver = new LaguerreSolver();
            double[] startPoints = {0.0, -10.0, 10.0, -100.0, 100.0, 50.0, -50.0};

            for (double start : startPoints) {
                try {
                    Complex[] complexRoots = solver.solveAllComplex(coefficients, start);
                    for (Complex root : complexRoots) {
                        if (Math.abs(root.getImaginary()) < 1e-6) {
                            double realPart = roundToPrecision(root.getReal(), 6);
                            if (!containsWithTolerance(realRoots, realPart, 1e-6) && isRootValid(coefficients, realPart)) {
                                realRoots.add(realPart);
                            }
                        }
                    }
                } catch (Exception e) {
                    System.err.println("LaguerreSolver failed for start point " + start + ": " + e.getMessage());
                }
            }

            for (double start : startPoints) {
                try {
                    double newtonRoot = newtonRaphson(coefficients, start, 100, 1e-5);
                    if (!Double.isNaN(newtonRoot)) { // Skip failed iterations
                        double roundedRoot = roundToPrecision(newtonRoot, 6);
                        if (!containsWithTolerance(realRoots, roundedRoot, 1e-6) && isRootValid(coefficients, roundedRoot)) {
                            realRoots.add(roundedRoot);
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Newton-Raphson failed: " + e.getMessage());
                }
            }

            return realRoots;

        } catch (Exception e) {
            throw new RuntimeException("Error calculating roots: " + e.getMessage());
        }
    }


    private double[] parsePolynomial(String expression) {
        expression = expression.replaceAll("\\s+", "");
        String[] terms = expression.split("(?=[+-])");

        int maxDegree = 0;
        for (String term : terms) {
            if (term.contains("x^")) {
                maxDegree = Math.max(maxDegree, Integer.parseInt(term.split("x\\^")[1]));
            } else if (term.contains("x")) {
                maxDegree = Math.max(maxDegree, 1);
            }
        }

        double[] coefficients = new double[maxDegree + 1];

        for (String term : terms) {
            double coefficient = 1.0;
            int power = 0;

            if (term.contains("x^")) {
                String[] split = term.split("x\\^");
                coefficient = split[0].isEmpty() || split[0].equals("+") ? 1.0 : split[0].equals("-") ? -1.0 : Double.parseDouble(split[0]);
                power = Integer.parseInt(split[1]);
            } else if (term.contains("x")) {
                String coef = term.split("x")[0];
                coefficient = coef.isEmpty() || coef.equals("+") ? 1.0 : coef.equals("-") ? -1.0 : Double.parseDouble(coef);
                power = 1;
            } else {
                coefficient = Double.parseDouble(term);
                power = 0;
            }

            coefficients[coefficients.length - 1 - power] = coefficient;
        }

        return coefficients;
    }


    private boolean isRootValid(double[] coefficients, double root) {
        double result = 0.0;
        int degree = coefficients.length - 1;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(root, degree - i);
        }
        return Math.abs(result) < 1e-6; // Increased precision for validation
    }


    private boolean containsWithTolerance(Set<Double> roots, double value, double tolerance) {
        for (double root : roots) {
            if (Math.abs(root - value) < tolerance) {
                return true;
            }
        }
        return false;
    }


    private double newtonRaphson(double[] coefficients, double initialGuess, int maxIterations, double tolerance) {
        double x = initialGuess;
        for (int i = 0; i < maxIterations; i++) {
            double fx = 0.0;
            double fpx = 0.0; // Derivative of the polynomial

            int degree = coefficients.length - 1;
            for (int j = 0; j < coefficients.length; j++) {
                fx += coefficients[j] * Math.pow(x, degree - j);
                if (degree - j > 0) {
                    fpx += coefficients[j] * (degree - j) * Math.pow(x, degree - j - 1);
                }
            }

            if (Math.abs(fx) < tolerance) {
                return x; // Root found
            }

            if (Math.abs(fpx) < tolerance) {
                System.err.println("Derivative near zero, skipping this iteration.");
                return Double.NaN; // Indicate a failure for this root
            }

            x -= fx / fpx; // Newton-Raphson step
        }

        throw new ArithmeticException("Max iterations reached without convergence.");
    }


    private double roundToPrecision(double value, int precision) {
        double scale = Math.pow(10, precision);
        return Math.round(value * scale) / scale;
    }


    private void printCoefficients(double[] coefficients) {
        System.out.println("Parsed coefficients:");
        for (int i = 0; i < coefficients.length; i++) {
            System.out.println("Degree " + (coefficients.length - 1 - i) + ": " + coefficients[i]);
 }}




}
