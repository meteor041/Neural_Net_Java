import ActivationFunc.ActivationFunction;

import java.util.ArrayList;

public class NeuralLayer {
    // 当前层的神经元数量
    protected int numberOfNeuronsInLayer;
    // 当前层的神经元
    private ArrayList<Neuron> neuron;
    // 激活函数
    protected ActivationFunction activationFunction;
    // 将值提供给当前层的前一层
    protected NeuralLayer previousLayer;
    protected NeuralLayer nextLayer;
    protected ArrayList<Double> input;
    protected ArrayList<Double> output;
    protected int numberOfInputs;

    public NeuralLayer(int numberOfNeuronsInLayer) {
        this.numberOfNeuronsInLayer = numberOfNeuronsInLayer;
        neuron = new ArrayList<>(numberOfNeuronsInLayer);
        output = new ArrayList<>(numberOfNeuronsInLayer);
    }

    public NeuralLayer(int numberOfNeuronsInLayer, ActivationFunction activationFunction) {
        this.numberOfNeuronsInLayer = numberOfNeuronsInLayer;
        this.activationFunction = activationFunction;
        neuron = new ArrayList<>(numberOfNeuronsInLayer);
        output = new ArrayList<>(numberOfNeuronsInLayer);
    }

    public void init() {
        if (numberOfNeuronsInLayer >= 0) {
            for (int i = 0; i < numberOfNeuronsInLayer; i++) {
                try {
                    neuron.get(i).setActivationFunction(activationFunction);
                    neuron.get(i).init();
                } catch (IndexOutOfBoundsException iobe) {
                    neuron.add(new Neuron(numberOfInputs, activationFunction));
                    neuron.get(i).init();
                }
            }
        }
    }

    protected void setInputs(ArrayList<Double> inputs) {
        this.numberOfInputs = inputs.size();
        this.input = inputs;
    }

    protected void calc() {
        if (input != null && neuron != null) {
            for (int i = 0; i < numberOfNeuronsInLayer; i++) {
                neuron.get(i).setInput(this.input);
                neuron.get(i).calc();
                try {
                    output.set(i, neuron.get(i).getOutput());
                } catch(IndexOutOfBoundsException iobe) {
                    output.add(neuron.get(i).getOutput());
                }
            }
        }
    }

    // 设置下一个神经网络层
    public void setNextLayer(NeuralLayer nextLayer) {
        this.nextLayer = nextLayer;
    }

    // 设置前一个神经网络层
    public void setPreviousLayer(NeuralLayer previousLayer) {
        this.previousLayer = previousLayer;
    }

    public void setNeuron(int i, Neuron neuron) {
        try {
            this.neuron.set(i, neuron);
        } catch (IndexOutOfBoundsException iobe) {
            this.neuron.add(neuron);
        }
    }

    public Neuron getNeuron(int i) {
        return neuron.get(i);
    }

    public ArrayList<Neuron> getListOfNeurons() {
        return neuron;
    }

    public NeuralLayer getPreviousLayer() {
        return previousLayer;
    }

    public NeuralLayer getNextLayer() {
        return nextLayer;
    }

    public ArrayList<Double> getOutput() {
        return output;
    }

    public int getNumberOfNeuronsInLayer() {
        return numberOfNeuronsInLayer;
    }
}
