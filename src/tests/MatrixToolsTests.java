package tests;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import JMat.DMatrix;
import JMat.GMatrix;
import JMat.util.MatMaths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class MatrixToolsTests {
    private GMatrix M = new GMatrix<>(2,3);
    Double[][] v1 = {{1., 1., 1., 1.}};
    private DMatrix v = new DMatrix(v1);

    @BeforeEach
    void setup() {  
    }

    @Test
    @Order(1)
    void sizeOfGMatrix() {
        assertThat(M).isNotNull();
        assertThat(M.size()[0]).isEqualTo(2);
        assertThat(M.size()[1]).isEqualTo(3);
    }

    @Test
    @Order(2)
    void normOfDoubleVector() {
        assertThat(v).isNotNull();
        assertThat(v.norm()).isEqualTo(2.0);
    }

    @Test
    @Order(3)
    void dotProductTest() {
        Double[][] A_content = {{1., 0.}, {1., 0.}};
        Double[][] B_content = {{1., 2.}, {3., 4.}};
        Double[][] C_control_content = {{1., 0.}, {3., 4.}};
        DMatrix A = new DMatrix(A_content);
        DMatrix B = new DMatrix(B_content);
        DMatrix C_control = new DMatrix(C_control_content);

        DMatrix C = MatMaths.dot(A, B);
        assertThat(C).isNotNull();
        assertThat(C.equalTo(C_control));
    }
}
