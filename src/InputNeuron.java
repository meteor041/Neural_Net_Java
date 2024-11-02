import ActivationFunc.Linear;

public class InputNeuron extends Neuron{

    public InputNeuron() {
        super(1);
        setActivationFunction(new Linear(1));
        bias = 0.0;
    }

    @Override
    public void init() {
        try {
            this.weight.set(0, 1.0);
            this.weight.set(1, 0.0);
        } catch (IndexOutOfBoundsException e) {
            this.weight.add(1.0);
            this.weight.add(0.0);
        }
    }
}
