/*import java.util.* */;

public class matrix {
    double [][] mat;
    int n_rows, n_cols;

    public matrix(int n_rows, int n_cols) {
        this.n_rows = n_rows;
        this.n_cols = n_cols;
        mat = new double [n_rows][n_cols];
    }

    public double getVal(int i, int j) {
        return mat[i][j];
    }

    public void setVal(int i, int j, double val) {
        mat[i][j] = val;
    }

    public int[] shape() {
        int [] shape = new int[] {this.n_rows, this.n_cols};
        return shape;
    }


}
