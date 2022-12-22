

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import JMat.DMatrix;
import JMat.Matrix;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)

public class NeuralNetLayerTest {
    @BeforeEach
    void setup() {}

    @Test
    @Order(1)
    void BasicConstructorTest() {
        NeuralNetLayer layer = new NeuralNetLayer(3, 2, 0.01);
        assertThat(layer).isNotNull();
        assertThat(layer.size()[0]).isEqualTo(3);
        assertThat(layer.size()[1]).isEqualTo(2);
    }

    @Test
    @Order(2)
    void ReluActivationTest() {
        Double[][] vec = {{1., 2., 3.}};
        DMatrix input_vec = new DMatrix(vec);

        DMatrix id2 = Matrix.makeIdentityMatrix(2);
        NeuralNetLayer layer = new NeuralNetLayer(3, 2, 0.01, id2, ActivationFunctions.MAT_RELU, ActivationFunctions.MAT_RELU_DER);
        
        assertThat(layer).isNotNull();
        
        DMatrix activated_vec = layer.activate(input_vec);
        assertThat(activated_vec.get(0,0)).isEqualTo(1.);
        assertThat(activated_vec.get(0,1)).isEqualTo(2.);
        assertThat(activated_vec.get(0,2)).isEqualTo(3.);
    }

    @Test
    @Order(3)
    void SigmoidActivationTest() {
        Double[][] vec = {{1., 2., 3.}};
        DMatrix input_vec = new DMatrix(vec);

        DMatrix id2 = Matrix.makeIdentityMatrix(2);
        NeuralNetLayer layer = new NeuralNetLayer(3, 2, 0.01, id2, ActivationFunctions.MAT_SIGMOID, ActivationFunctions.MAT_SIGMOID_DER);
        
        assertThat(layer).isNotNull();
        
        DMatrix activated_vec = layer.activate(input_vec);
        assertThat(activated_vec.get(0,0)).isEqualTo(NNFunctions.sigmoid(1.));
        assertThat(activated_vec.get(0,1)).isEqualTo(NNFunctions.sigmoid(2.));
        assertThat(activated_vec.get(0,2)).isEqualTo(NNFunctions.sigmoid(3.));
    }

    @Test
    @Order(10)
    void ForwardPropagationTest() {
        Double[][] vec = {{1., 2., 3.}};
        DMatrix input_vec = new DMatrix(vec);

        DMatrix id2 = Matrix.makeIdentityMatrix(2);
        NeuralNetLayer layer = new NeuralNetLayer(3, 2, 0.01, id2, ActivationFunctions.MAT_RELU, ActivationFunctions.MAT_RELU_DER);
        
        DMatrix output_vec = layer.forward(input_vec, true);

        assertThat(output_vec.get(0, 0)).isEqualTo(1.);
        assertThat(output_vec.get(0, 1)).isEqualTo(2.);
        assertThat(output_vec.get(0, 2)).isEqualTo(3.);
    }
}
