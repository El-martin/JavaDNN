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
        double[][] C = new double[][] {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        double[][] D = new double[][] {{2, 3, 2}, {4, 5, 4}, {6, 7, 6}};

        double[][] E = tested.add(A, B);
        assertThat(matrixEquals(A, E)).isTrue();

        double[][] F = tested.add(A, C);
        assertThat(matrixEquals(D, F)).isTrue();

    }

    @Test
    public void testSubstraction() {
        double[][] A = new double[][] {{1, 2, 1}, {3, 4, 3}, {5, 6, 5}};
        double[][] B = new double[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        double[][] C = new double[][] {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        double[][] D = new double[][] {{0, 1, 0}, {2, 3, 2}, {4, 5, 4}};

        double[][] E = tested.sub(A, B);
        assertThat(matrixEquals(A, E)).isTrue();

        double[][] F = tested.sub(A, C);
        assertThat(matrixEquals(D, F)).isTrue();
    }

    @Test
    public void testSplit() {
        double[][] A = new double[][] {{1, 2, 1}, {3, 4, 3}, {5, 6, 5}};
        double[][] subA = new double[2][2];
        double[][] expectedSubA = new double[][] {{1, 2}, {3, 4}};

        tested.split(A, subA, 0, 0);
        assertThat(matrixEquals(subA, expectedSubA)).isTrue();

        tested.split(A, subA, 1, 1);
        double[][] newExpSubA = new double[][] {{4, 3}, {6, 5}};
        assertThat(matrixEquals(subA, newExpSubA)).isTrue();
    }

    @Test
    public void testJoin() {
        double[][] A = new double[][] {{1, 2, 1}, {3, 4, 3}, {5, 6, 5}};
        double[][] subA = new double[][] {{0, 0}, {0, 0}};
        double[][] expectedA = new double[][] {{0, 0, 1}, {0, 0, 3}, {5, 6, 5}};

        tested.join(subA, A, 0, 0);
        assertThat(matrixEquals(A, expectedA)).isTrue();

        tested.join(subA, A, 0, 1);
        double[][] newExpectedA = new double[][] {{0, 0, 0}, {0, 0, 0}, {5, 6, 5}};
        assertThat(matrixEquals(A, newExpectedA)).isTrue();
    }

    @Test
    public void TestMultiplication() {
        double[][] A = new double[][] {{1, 2, 1}, {3, 4, 3}, {5, 6, 5}};
        double[][] B = new double[][] {{1, 1, 2}, {2, 2, 3}, {3, 3, 4}};
        double[][] I = new double[][] {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};

        assertThat(matrixEquals(A, tested.multiply(A, I)));

        double[][] C = new double[][] {{8, 8, 12}, {20, 20, 30}, {32, 32, 48}};
        assertThat(matrixEquals(C, tested.multiply(A, B)));
    }

}
