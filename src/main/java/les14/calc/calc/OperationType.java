package les14.calc.calc;

/**
 * Created by Santer on 16.03.2016.
 */
public enum OperationType {

    ADD("+"), SUBTRACT("-"), MULTIPLY("*"), DIVIDE("/");

    private String stringValue;

    private OperationType(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}
