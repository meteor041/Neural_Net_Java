import ActivationFunc.ActivationFunction;
import ActivationFunc.Linear;
import ActivationFunc.RandomNumberGenerator;
import ActivationFunc.Sigmoid;

public class NeuralNetTest {
    public static void main(String[] args) {
        RandomNumberGenerator.setSeed(0);

        int numberOfInputs = 2;
        int numberOfOutputs = 1;
        int[] numberOfHiddenNeurons = {3};
        ActivationFunction[] hiddenAcFnc = {new Sigmoid(1.0)};
        Linear outputAcFnc = new Linear(1.0);
        System.out.println("Creating Neural Network...");
        NeuralNet nn = new NeuralNet(numberOfInputs, numberOfOutputs,
                 numberOfHiddenNeurons, hiddenAcFnc, outputAcFnc);
        System.out.println("Neural Network created.");

        double[] neuralInput = {1.5, 0.5};
        System.out.println("feeding the values {1.5,0.5} to the neural network...");
        double[] neuralOutput;
        nn.setInputs(neuralInput);
        nn.calc();

        neuralOutput = nn.getOutput();
        System.out.println("Output 1:" + neuralOutput[0]);
        neuralInput[0] = 1.0;
        neuralInput[1] = 2.1;
        System.out.println("Feeding the values {1.0,2.1} to the neural network...");
        nn.setInputs(neuralInput);
        nn.calc();
        neuralOutput = nn.getOutput();
        System.out.println("Output 2:" + neuralOutput[0]);
    }
}
