import ActivationFunc.ActivationFunction;
import ActivationFunc.RandomNumberGenerator;

import java.util.ArrayList;

public class Neuron {
    // 神经元的权重
    protected ArrayList<Double> weight;
    // 神经元的输入
    private ArrayList<Double> input;
    // 神经元的输出
    private Double output;
    // 传递给激活函数的值
    private Double outputBeforeActivation;
    // 输入的数量
    private int numberOfInputs;
    // 神经元的偏差
    protected Double bias = 1.0;
    // 激活函数
    private ActivationFunction activationFunction;

    // 实例化Neuron
    public Neuron(int noi, ActivationFunction iaf) {
        numberOfInputs = noi;
        weight = new ArrayList<>();
        input = new ArrayList<>();
        activationFunction = iaf;
    }

    public Neuron(int noi) {
        numberOfInputs = noi;
        weight = new ArrayList<>();
        input = new ArrayList<>();
    }

    // 初始化
    public void init() {
        if (numberOfInputs > 0) {
            for (int i = 0; i <= numberOfInputs; i++) {
                double newWeight = RandomNumberGenerator.GenerateNext();
                try {
                    this.weight.set(i, newWeight);
                } catch (IndexOutOfBoundsException e) {
                    this.weight.add(newWeight);
                }
            }
        }
    }

    // 计算
    public void calc() {
        outputBeforeActivation = 0.0;
        if (numberOfInputs > 0) {
            if (input != null && weight != null) {
                for (int i = 0; i <= numberOfInputs; i++) {
                    outputBeforeActivation += (i==numberOfInputs ? bias : input.get(i)) * weight.get(i);
                }
            }
        }
        output = activationFunction.calc(outputBeforeActivation);
    }

    public void setActivationFunction(ActivationFunction af) {
        activationFunction = af;
    }

    public void setInput(ArrayList<Double> input) {
        this.input = input;
    }

    public double getOutput() {
        return output;
    }

    public void setInputs(ArrayList<Double> values) {
        if (values.size() == numberOfInputs) {
            input = values;
        }
    }

    public void setInputs(double[] values) {
        if (values.length == numberOfInputs) {
            for (int i = 0; i < numberOfInputs; i++) {
                try {
                    input.set(i, values[i]);
                } catch (IndexOutOfBoundsException e) {
                    input.add(values[i]);
                }
            }
        }
    }

}
