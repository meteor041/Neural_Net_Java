package ActivationFunc;

public interface ActivationFunction {
    double calc(double x);
    public enum ActivationFunctionEnum {
        STEP, LINEAR, SIGMOID, HYPERTAN
    }
}
