import ActivationFunc.ActivationFunction;

public class OutputLayer extends NeuralLayer{
    public OutputLayer(int numberOfNeurons, ActivationFunction activationFunction, int numberOfInputs) {
        super(numberOfNeurons, activationFunction);
        this.numberOfInputs = numberOfInputs;
        nextLayer = null;
        init();
    }

    @Override
    public void setNextLayer(NeuralLayer nextLayer) {
        nextLayer = null;
    }

    @Override
    public void setPreviousLayer(NeuralLayer previousLayer) {
        this.previousLayer = previousLayer;
        if (previousLayer.nextLayer != this) {
            previousLayer.setNextLayer(this);
        }
    }
}

