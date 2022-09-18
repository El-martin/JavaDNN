public class StrassenMatrixMultiplication {
    // Strassen multiplication algorithm in O(n^2.8074)

    public double[][] multiply(double[][] A, double[][] B) {
        int n = A.length;
        double [][] R = new double[n][n];

        if (n==1) 
            R[0][0] = A[0][0];

        else {
            double[][] A11 = new double[n / 2][n / 2];
            double[][] A12 = new double[n / 2][n / 2];
            double[][] A21 = new double[n / 2][n / 2];
            double[][] A22 = new double[n / 2][n / 2];
            double[][] B11 = new double[n / 2][n / 2];
            double[][] B12 = new double[n / 2][n / 2];
            double[][] B21 = new double[n / 2][n / 2];
            double[][] B22 = new double[n / 2][n / 2];

            split(A, A11, 0, 0);
            split(A, A12, 0, n / 2);
            split(A, A21, n / 2, 0);
            split(A, A22, n / 2, n / 2);
 
            split(B, B11, 0, 0);
            split(B, B12, 0, n / 2);
            split(B, B21, n / 2, 0);
            split(B, B22, n / 2, n / 2);

             // M1:=(A1+A3)×(B1+B2)
             double[][] M1 = multiply(add(A11, A22), add(B11, B22));
        
            // M2:=(A2+A4)×(B3+B4)
            double[][] M2 = multiply(add(A21, A22), B11);
            
            // M3:=(A1−A4)×(B1+A4)
            double[][] M3 = multiply(A11, sub(B12, B22));
            
            // M4:=A1×(B2−B4)
            double[][] M4 = multiply(A22, sub(B21, B11));
            
            // M5:=(A3+A4)×(B1)
            double[][] M5 = multiply(add(A11, A12), B22);
            
            // M6:=(A1+A2)×(B4)
            double[][] M6 = multiply(sub(A21, A11), add(B11, B12));
            
            // M7:=A4×(B3−B1)
            double[][] M7 = multiply(sub(A12, A22), add(B21, B22));

            // P:=M2+M3−M6−M7
            double[][] C11 = add(sub(add(M1, M4), M5), M7);
            
            // Q:=M4+M6
            double[][] C12 = add(M3, M5);
            
            // R:=M5+M7
            double[][] C21 = add(M2, M4);
            
            // S:=M1−M3−M4−M5
            double[][] C22 = add(sub(add(M1, M3), M2), M6);

            // Step 3: Join 4 halves doubleo one result matrix
            join(C11, R, 0, 0);
            join(C12, R, 0, n / 2);
            join(C21, R, n / 2, 0);
            join(C22, R, n / 2, n / 2);
        }

        return R;
    }

    public double[][] sub(double[][] A, double[][] B) {
        int n = A.length;

        double[][] C = new double[n][n];

        for (int i=0; i < n; i++) {
            for (int j=0; j<n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    public double[][] add(double[][] A, double[][] B) {
        int n = A.length;

        double[][] C = new double[n][n];

        for (int i=0; i < n; i++) {
            for (int j=0; j<n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    // Extracts from A a matrix the size of subA beginning at indices i, j
    public void split(double[][] A, double[][] subA, int i0, int j0) {
        for (int i1=0, i2=j0; i1 < subA.length; i1++, i2++) {
            for (int j1=0, j2=j0; j1 < subA.length; j1++, j2++) {
                subA[i1][j1] = A[i2][j2];
            }
        }
    }

    public void join(double[][] subA, double[][] A, int i0, int j0) {
        for (int i1=0, i2=j0; i1 < subA.length; i1++, i2++) {
            for (int j1=0, j2=j0; j1 < subA.length; j1++, j2++) {
                A[i1][j1] = subA[i2][j2];
            }
        }
    }
}
