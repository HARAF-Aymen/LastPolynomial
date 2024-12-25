package hmaidi.orchestration.service;

import hmaidi.orchestration.dto.Polynomial;
import hmaidi.orchestration.dto.PolynomialFactorizationRequest;
import hmaidi.orchestration.feign.FactorizationClient;
import hmaidi.orchestration.feign.PolynomialClient;
import hmaidi.orchestration.feign.RootsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class OrchestrationService {

    private final PolynomialClient crudClient;
    private final RootsService rootsClient;
    private final FactorizationClient factorizationClient;

    public OrchestrationService(PolynomialClient crudClient, RootsService rootsClient, FactorizationClient factorizationClient) {
        this.crudClient = crudClient;
        this.rootsClient = rootsClient;
        this.factorizationClient = factorizationClient;
    }

    public String processPolynomial(Polynomial request) {
        try {
            Polynomial polynomialRequestForCreate = new Polynomial();
            polynomialRequestForCreate.setExpression(request.getExpression());
            System.out.println("Expression envoyée à PolynomialClient : " + polynomialRequestForCreate.getExpression());

            ResponseEntity<Polynomial> polynomialResponse = crudClient.createPolynomial(polynomialRequestForCreate);
            if (!polynomialResponse.getStatusCode().is2xxSuccessful() || polynomialResponse.getBody() == null) {
                throw new RuntimeException("Erreur lors de la création du polynôme");
            }
            Polynomial createdPolynomial = polynomialResponse.getBody();
            System.out.println("Réponse de PolynomialClient : " + createdPolynomial);

            Polynomial polynomialRequestForRoots = new Polynomial();
            polynomialRequestForRoots.setExpression(request.getExpression().trim());
            System.out.println("Expression envoyée à ROOTS-SERVICE : " + polynomialRequestForRoots.getExpression());

            ResponseEntity<List<Double>> rootsResponse = rootsClient.calculateRoots(polynomialRequestForRoots);
            if (!rootsResponse.getStatusCode().is2xxSuccessful() || rootsResponse.getBody() == null) {
                throw new RuntimeException("Erreur lors de la récupération des racines depuis ROOTS-SERVICE");
            }
            List<Double> roots = rootsResponse.getBody();
            System.out.println("Réponse de ROOTS-SERVICE (racines) : " + roots);

            if (roots.isEmpty()) {
                System.out.println("Aucune racine réelle trouvée. Aucun résultat à retourner.");
                return "";
            }

            PolynomialFactorizationRequest factorizationRequest = new PolynomialFactorizationRequest();
            factorizationRequest.setCoefficients(extractCoefficientsFromExpression(request.getExpression()));
            factorizationRequest.setRoots(roots);
            System.out.println("Requête envoyée à FactorizationClient : " + factorizationRequest);

            ResponseEntity<String> factorizationResponse = factorizationClient.factorizePolynomial(factorizationRequest);
            if (!factorizationResponse.getStatusCode().is2xxSuccessful() || factorizationResponse.getBody() == null) {
                throw new RuntimeException("Erreur lors de la factorisation");
            }
            System.out.println("Réponse de FactorizationClient : " + factorizationResponse.getBody());

            return factorizationResponse.getBody();

        } catch (Exception e) {
            System.err.println("Erreur lors du traitement du polynôme : " + e.getMessage());
            throw new RuntimeException("Erreur lors du traitement du polynôme : " + e.getMessage(), e);
        }
    }

    public static double[] extractCoefficientsFromExpression(String expression) {
        expression = expression.replace(" ", "");

        expression = expression.replaceAll("(?<!\\d|\\))x", "1x");
        expression = expression.replaceAll("(^|\\+|\\-)x", "$1" + "1x");

        Pattern termPattern = Pattern.compile("([+-]?\\d*\\.?\\d*)x\\^?(\\d*)|([+-]?\\d+)");
        Matcher matcher = termPattern.matcher(expression);

        int degree = 0;
        while (matcher.find()) {
            if (matcher.group(2) != null && !matcher.group(2).isEmpty()) {
                degree = Math.max(degree, Integer.parseInt(matcher.group(2)));
            }
        }

        double[] coefficients = new double[degree + 1];

        matcher.reset();

        while (matcher.find()) {
            String coefficientStr = matcher.group(1);
            String powerStr = matcher.group(2);
            String constantTerm = matcher.group(3);

            if (constantTerm != null) {
                coefficients[degree] = Double.parseDouble(constantTerm);
            } else {
                int power = (powerStr == null || powerStr.isEmpty()) ? 1 : Integer.parseInt(powerStr);
                double coefficient = coefficientStr.isEmpty() || coefficientStr.equals("+") ? 1 :
                        coefficientStr.equals("-") ? -1 : Double.parseDouble(coefficientStr);
                coefficients[degree - power] = coefficient;
            }
        }

        return coefficients;
    }

    public List<Double> getRoots(String expression) {
        try {
            Polynomial polynomialRequest = new Polynomial();
            polynomialRequest.setExpression(expression.trim());
            System.out.println("Expression envoyée à ROOTS-SERVICE pour les racines : " + polynomialRequest.getExpression());

            ResponseEntity<List<Double>> rootsResponse = rootsClient.calculateRoots(polynomialRequest);
            if (!rootsResponse.getStatusCode().is2xxSuccessful() || rootsResponse.getBody() == null) {
                throw new RuntimeException("Erreur lors de la récupération des racines depuis ROOTS-SERVICE");
            }

            List<Double> roots = rootsResponse.getBody();
            System.out.println("Racines obtenues depuis ROOTS-SERVICE : " + roots);

            return roots;

        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des racines : " + e.getMessage());
            throw new RuntimeException("Erreur lors de la récupération des racines : " + e.getMessage(), e);
        }
    }



}
