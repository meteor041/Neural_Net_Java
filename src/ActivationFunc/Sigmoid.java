package ActivationFunc;

public class Sigmoid implements ActivationFunction{
    private double a = 1.0;
    public Sigmoid() {

    }
    public Sigmoid(double a) {
        this.a = a;
    }
    @Override
    public double calc(double x) {
        return 1.0 / (1.0 + Math.exp(-a*x));
    }
}
