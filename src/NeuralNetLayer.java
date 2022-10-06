import JMat.DMatrix;
import JMat.util.MatMaths;

/**
 * A class to build, connect and update layers of a neural network.
 * 
 * @author Eliot MARTIN
 * 
 */
public class NeuralNetLayer {
    private int in_size;
    private int out_size;
    private DMatrix weights;
    private double lr;

    private ActivationFunctions activationFunction;
    private ActivationFunctions activationDerivative;

    private DMatrix backward_store_in;
    private DMatrix backward_store_out;

    public NeuralNetLayer(int in, int out, double learning_rate) {
        this.in_size = in;
        this.out_size = out;
        this.weights = new DMatrix(in, out);
        this.weights.doubleRandomise(-1., 1.);
        this.lr = learning_rate;
    }

    public NeuralNetLayer(int in, int out, double learning_rate, ActivationFunctions activation, ActivationFunctions activationDerivative) {
        this.in_size = in;
        this.out_size = out;
        this.weights = new DMatrix(in, out);
        this.weights.doubleRandomise(-1., 1.);
        this.lr = learning_rate;
        this.activationFunction = activation;
        this.activationDerivative = activationDerivative;
    }

    public int[] size() {
        int[] result = new int[]{this.in_size, this.out_size};
        return result;
    }

    public DMatrix activate(DMatrix input) {
        switch(this.activationFunction) {
            case MAT_RELU:
                return NNFunctions.matRelu(input);
            case MAT_SIGMOID:
                return NNFunctions.matSigmoid(input);
            default:
                System.out.println("Invalid function name, returning input as default.");
                return input;
        }
    }

    public DMatrix activateDerivative(DMatrix input) {
        switch(this.activationDerivative) {
            case MAT_RELU_DER:
                return NNFunctions.matReluDer(input);
            case MAT_SIGMOID_DER:
                return NNFunctions.matSigmoidDer(input);
            default:
                System.out.println("Invalid function name, returning input as default.");
                return input;
        }
    }

    public DMatrix forward(DMatrix input, Boolean remember_for_backprop) {
        input.addRow(1.);
        DMatrix unactivated = MatMaths.mul(this.weights, input);
        DMatrix output = unactivated.clone();

        //! Activation functions ? //
        if (this.activationFunction != null) {
            output = this.activate(output);
        }

        if (remember_for_backprop) {
            this.backward_store_in = input;
            this.backward_store_out = unactivated.clone();
        }

        return output;
    }

    public void update_weights(DMatrix gradient) {
        this.weights = MatMaths.sub(this.weights, MatMaths.mul(gradient, lr));
    }

    public DMatrix backward_propagation(DMatrix above_gradient) {
        DMatrix adjusted_mul = above_gradient.clone();
        
        //! Create dot function //
        if (this.activationFunction != null) {
            adjusted_mul = MatMaths.dot(this.activateDerivative(this.backward_store_out), above_gradient);
        }
        // Matricial gradient for weight update
        DMatrix D_i = MatMaths.mul(adjusted_mul.transpose(), this.backward_store_in);
        
        // Gradient for next layer of backprop
        DMatrix delta_i = MatMaths.mul(adjusted_mul, this.weights.transpose());
        delta_i = delta_i.getRows(-1);

        update_weights(D_i);

        return delta_i;
        
    }


}
