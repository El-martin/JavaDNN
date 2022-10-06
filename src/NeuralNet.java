/**
 * Class used by NNTools to build a neural network 
 * composed of multiple NeuralNetLayers.
 * 
 * @author Eliot MARTIN
 */

import java.util.HashMap;
import java.util.Map.Entry;

import JMat.DMatrix;


public class NeuralNet {
    private HashMap<Integer,NeuralNetLayer> layers;

    public NeuralNet() {
        layers = new HashMap<>();
    }

    /**
     * Used to get the output layer as an integer array
     * @return array composed of all 
     */
    public int[] layersDimensions() {
        int[] sizes = new int[this.layers.size()+1];                  // N layers, so N+1 dimensions

        for (Entry<Integer,NeuralNetLayer> entry: this.layers.entrySet()) {
            sizes[entry.getKey()] = entry.getValue().size()[0];       // Input dimension of the layer
        }
        return sizes;
    }

    /**
     * @return number of layers in the network
     */
    public int NLayers() {
        return layersDimensions().length-1;
    }

    /**
     * Normalize entry for stability
     * @param vector input to normalize
     * @return normalized vector
     */
    public DMatrix normalize(DMatrix vector) {
        return null;
    }

    /**
     * Propagate forward an input vector through the network
     * @param input vector to pass through the network
     * @return output of the network
     */
    public DMatrix forward(DMatrix input) {
        return null;
    }

    /**
     * Propagate the error backward to tune weights in order to minimize cost
     * (see gradient descent for further details)
     * @param expected Target output
     * @param actual Output produced by the network
     */
    public void backward(DMatrix expected, DMatrix actual) {
        
    }

}
