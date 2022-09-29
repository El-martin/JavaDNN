/**
 * A class providing various activation functions and their derivatives
 * 
 * @author Eliot MARTIN
 * 
 */
import JMat.DMatrix;

public class NNFunctions {

    /**
     * Relu function relu:x->x if x>0, 0 otherwise
     * @param x input
     * @return relu(x)
     */
    public static double relu(double x) {
        return ((x > 0) ? x : 0);
    }

    /**
     * Sigmoid function sig:x->1/(1+e^x)
     * @param x input
     * @return sig(x)
     */
    public static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    /**
     * Softmax function, out_i = e^in_i / sum(e^in_j)
     * @param X input vector
     * @return vector containing the term by term result
     */
    public static double[] softmax(double[] X) {
        int n = X.length;
        double sum = 0;
        double[] result = new double[n];

        for (int i=0; i<n; i++) {
            sum += Math.exp(X[i]);
        }
        
        for (int i=0; i<n; i++) {
            result[i] = Math.exp(X[i])/sum;
        }
        return result;
    }

    /**
     * Matricial version of the relu function, the formula is applied factor by factor
     * @param M the matrix from which the coefficients are extracted
     * @return result, the relu version of M
     */
    public static DMatrix matRelu(DMatrix M) {
        int[] MSize = M.size();
        int height = MSize[0];
        int width = MSize[1];
        DMatrix result = new DMatrix(height, width);

        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++){
                M.set(i, j, relu(M.get(i, j)));
            }
        }
        return result;
    }

    /**
     * Matricial version of the sigmoid function, the formula is applied factor by factor
     * @param M the matrix from which the coefficients are extracted
     * @return result, the sigmoid version of M
     */
    public static DMatrix matSigmoid(DMatrix M) {
        int[] MSize = M.size();
        int height = MSize[0];
        int width = MSize[1];
        DMatrix result = new DMatrix(height, width);

        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++){
                M.set(i, j, sigmoid(M.get(i, j)));
            }
        }
        return result;
    }
}
