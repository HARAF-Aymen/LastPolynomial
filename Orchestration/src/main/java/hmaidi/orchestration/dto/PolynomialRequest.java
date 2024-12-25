package hmaidi.orchestration.dto;


public class PolynomialRequest {
    private String expression;

    public PolynomialRequest() {}

    public PolynomialRequest(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }


}


