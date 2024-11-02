import ActivationFunc.ActivationFunction;

import java.util.ArrayList;

public class NeuralNet {
    private InputLayer inputLayer;
    private ArrayList<HiddenLayer> hiddenLayer;
    private OutputLayer outputLayer;
    private int numberOfHiddenLayers;
    private int numberOfInputs;
    private int numberOfOutputs;
    private ArrayList<Double> input;
    private ArrayList<Double> output;

    public NeuralNet(int numberOfInputs, int numberOfOutputs, int[] numberOfHiddenNeurons,
                     ActivationFunction[] hiddenAcFnc, ActivationFunction outputAcFnc) {
        this.numberOfHiddenLayers = numberOfHiddenNeurons.length;
        this.numberOfInputs = numberOfInputs;
        this.numberOfOutputs = numberOfOutputs;
        if (numberOfHiddenLayers == hiddenAcFnc.length) {
            input = new ArrayList<>(numberOfInputs);
            inputLayer = new InputLayer(numberOfInputs);
            if (numberOfHiddenLayers > 0) {
                hiddenLayer = new ArrayList<>(numberOfHiddenLayers);
            }
            for (int i = 0; i < numberOfHiddenLayers; i++) {
                if (i == 0) {
                    try {
                        hiddenLayer.set(i, new HiddenLayer(numberOfHiddenNeurons[i],
                                hiddenAcFnc[i],
                                inputLayer.getNumberOfNeuronsInLayer()));
                    } catch(IndexOutOfBoundsException iobe) {
                        hiddenLayer.add(new HiddenLayer(numberOfHiddenNeurons[i],
                                hiddenAcFnc[i],
                                inputLayer.getNumberOfNeuronsInLayer()));
                    }
                    inputLayer.setNextLayer(hiddenLayer.get(i));
                }
                else {
                    try {
                        hiddenLayer.set(i, new HiddenLayer(numberOfHiddenNeurons[i],
                                hiddenAcFnc[i],
                                hiddenLayer.get(i-1).getNumberOfNeuronsInLayer()));
                    } catch(IndexOutOfBoundsException iobe) {
                        hiddenLayer.add(new HiddenLayer(numberOfHiddenNeurons[i],
                                hiddenAcFnc[i],
                                hiddenLayer.get(i-1).getNumberOfNeuronsInLayer()));
                    }
                    hiddenLayer.get(i-1).setNextLayer(hiddenLayer.get(i));
                }
            }
            if (numberOfHiddenLayers > 0) {
                outputLayer = new OutputLayer(numberOfInputs, outputAcFnc,
                        numberOfOutputs);
                inputLayer.setNextLayer(outputLayer);
            }
        }
    }

    public void setInputs (ArrayList<Double> inputs) {
        if (inputs.size() == numberOfInputs) {
            this.input = inputs;
        }
    }

    public void setInputs (double[] inputs) {
        if (inputs.length == numberOfInputs) {
            for (int i = 0; i < numberOfInputs; i++) {
                try {
                    input.set(i, inputs[i]);
                } catch (IndexOutOfBoundsException e) {
                    input.add(inputs[i]);
                }
            }
        }
    }

    public void calc() {
        inputLayer.setInputs(input);
        inputLayer.calc();
        if (numberOfHiddenLayers > 0) {
            for (int i = 0; i < numberOfHiddenLayers; i++) {
                HiddenLayer hl = hiddenLayer.get(i);
                hl.setInputs(hl.getPreviousLayer().getOutput());
                hl.calc();
            }
        }
        outputLayer.setInputs(outputLayer.getPreviousLayer().getOutput());
        outputLayer.calc();
        this.output = outputLayer.getOutput();
    }

    public double[] getOutput() {
        double[] ret = new double[numberOfOutputs];
        for (int i = 0; i < numberOfOutputs; i++) {
            ret[i] = output.get(i);
        }

        return ret;
    }
}
