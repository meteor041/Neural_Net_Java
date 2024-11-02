package ActivationFunc;

public class Linear implements ActivationFunction {
    private double a = 1.0;

    public Linear() {

    }

    public Linear(double a) {
        this.a = a;
    }

    @Override
    public double calc(double x) {
        return x * a;
    }
}
