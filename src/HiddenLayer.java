import ActivationFunc.ActivationFunction;

public class HiddenLayer extends NeuralLayer{
    public HiddenLayer(int numberOfNeurons, ActivationFunction iaf,
                       int numberOfInputs) {
        super(numberOfNeurons, iaf);
        this.numberOfInputs = numberOfInputs;
        init();
    }

    @Override
    public void setPreviousLayer(NeuralLayer previousLayer) {
        this.previousLayer = previousLayer;
    }

    @Override
    public void setNextLayer(NeuralLayer nextLayer) {
        this.nextLayer = nextLayer;
        if (nextLayer.previousLayer != this) {
            nextLayer.setPreviousLayer(this);
        }
    }
}

