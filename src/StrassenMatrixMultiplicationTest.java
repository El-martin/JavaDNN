import com.github.stefanbirkner.systemlambda.SystemLambda;
import org.assertj.core.api.WithAssertions;
import org.assertj.core.api.WithAssumptions;
import org.junit.jupiter.api.Test;

public class StrassenMatrixMultiplicationTest implements WithAssertions, WithAssumptions {
    StrassenMatrixMultiplication tested = new StrassenMatrixMultiplication();
    private static final double EPSILON = 1e-8;

    public boolean matrixEquals(double[][] A, double[][] B) {
        if (A.length != B.length || A[0].length != B[0].length) {
            return false;
        }
        else {
            int n = A.length;
            int m = A[0].length;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (A[i][j] != B[i][j]) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

    @Test
    public void testAddition() {
        double[][] A = new double[][] {{1, 2, 1}, {3, 4, 3}, {5, 6, 5}};
        double[][] B = new double[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        double[][] C = tested.add(A, B);
        assertThat(matrixEquals(A, C));

    }
}
