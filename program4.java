import java.util.Scanner;


 class Matrix{
      private int rows, cols;
      private double [][] matrix;
                
                    public Matrix(){
                        this.rows = 0;
                        this.cols = 0;
                    }
                    public Matrix(int rows, int cols) {
                        this.rows = rows;
                        this.cols = cols;
                        matrix = new double[rows][cols];
                        System.out.println("enter matrix elements from row vise to column vise");
                        Scanner scanner = new Scanner(System.in);
                            for(int i = 0; i < rows; i++){
                                for(int j = 0; j < cols; j++){
                                    System.out.println("enter at row: " + i + " col: " + j);
                                    matrix[i][j] = scanner.nextDouble();
                                }
                            }
                        }
                    
                    
                    public Matrix(int rows, int cols, double n) {
                        this.rows = rows;
                        this.cols = cols;
                        matrix = new double[rows][cols];
                        for(int i = 0; i < rows; i++){
                            for(int j = 0; j < cols; j++){
                                matrix[i][j] = n;
                            }
                        }
                    } 

                    public int getRow() {
                        return rows;
                    }

                    public int getCol() {
                        return cols;
                    }

                    public  void Put(int row, int col, double n){
                        if(row >= this.rows || col >= this.cols) return;
                        this.matrix[row][col] = n; 
                    }

                    public double Get(int row, int col){
                        if(row >= this.rows || col >= this.cols) return -1;
                        return this.matrix[row][col];
                    }
                    
                }
class MatrixOpretion{
     static void PrintMatrix(Matrix matrix){
        for(int i=0; i<matrix.getRow(); i++){
        for(int j=0; j<matrix.getCol(); j++){
            System.out.print(matrix.Get(i, j) + " ");
            }
            System.out.println();
         }
        }
    
         static Matrix AddMatrix(Matrix a, Matrix b){
                     Matrix ans = new Matrix();
                     if(a.getRow() != b.getRow() || a.getCol() != b.getCol()){
                         System.out.println("Cant Perform Opretion");
                         return ans;
                     }
                     ans = new Matrix(a.getRow(), a.getCol(), 0);
                     for(int i=0; i<ans.getRow(); i++){
                         for(int j=0; j<ans.getCol(); j++){
                              ans.Put(i, j, a.Get(i, j) + b.Get(i, j));
                     }
                  }
                  return ans;
             }

             static Matrix SubMatrix(Matrix a, Matrix b){
                Matrix ans = new Matrix();
                if(a.getRow() != b.getRow() || a.getCol() != b.getCol()){
                    System.out.println("Cant Perform Opretion");
                    return ans;
                }
                ans = new Matrix(a.getRow(), a.getCol(), 0);
                for(int i=0; i<ans.getRow(); i++){
                    for(int j=0; j<ans.getCol(); j++){
                        ans.Put(i, j, a.Get(i, j) - b.Get(i, j));
                }
             }
             return ans;
        }

        static Matrix MulMatrix(Matrix a, Matrix b){
            Matrix ans = new Matrix();
            if(a.getCol() != b.getRow()){
                System.out.println("Cant Perform Opretion");
                return ans;
            }
            ans = new Matrix(a.getRow(), b.getCol(), 0);

            for (int i = 0; i < ans.getRow(); ++i) {
                for (int j = 0; j < ans.getCol(); ++j) {
                    int sum = 0;
                    for (int k = 0; k < a.getCol(); ++k) {
                        sum += a.Get(i, k) * b.Get(k, j);
                    }
                    ans.Put(i, j, sum);
                }
            }
            return ans;
        }

        static Matrix MulMatrix(double n, Matrix b){
            Matrix ans = new Matrix(b.getRow(), b.getCol(), 0);
            for(int i=0; i<ans.getRow(); i++){
                for(int j=0; j<ans.getCol(); j++){
                    ans.Put(i, j, b.Get(i, j) * n);
                }
            }
            return ans;
        }
        static Matrix MulMatrix(Matrix a, double n){
            Matrix ans = new Matrix(a.getRow(), a.getCol(), 0);
            for(int i=0; i<ans.getRow(); i++){
                for(int j=0; j<ans.getCol(); j++){
                    ans.Put(i, j, a.Get(i, j) * n);
                }
            }
            return ans;
        }
        static Matrix Transpose(Matrix A) {
            Matrix result = new Matrix(A.getCol(), A.getRow(), 1);
            for (int i = 0; i < A.getRow(); i++) {
                for (int j = 0; j < A.getCol(); j++) {
                    result.Put(j, i, A.Get(i, j));
                }
            }
            return result;
        }
        
        static double det(Matrix A){
            //Determinant finding algorithm for any square matrix

            int n = A.getRow();
    if (n != A.getCol()) {
        System.out.println("Determinant is not possible for non-square matrices.");
        return -1;
    }

    if (n == 1) return A.Get(0, 0); // Base case for 1x1 matrix
    if (n == 2) return A.Get(0, 0) * A.Get(1, 1) - A.Get(0, 1) * A.Get(1, 0); // Base case for 2x2 matrix

     double determinant = 0;
            for (int j = 0; j < A.getCol(); j++) {
                Matrix temp = new Matrix(A.getRow() - 1, A.getCol() - 1, 1);
                for (int i = 1; i < A.getRow(); i++) {
                    for (int k = 0; k < j; k++) {
                        temp.Put(i - 1, k, A.Get(i, k));
                    }
                    for (int k = j + 1; k < A.getCol(); k++) {
                        temp.Put(i - 1, k - 1, A.Get(i, k));
                    }
                }
                determinant += Math.pow(-1, j) * A.Get(0, j) * det(temp);
            }
            return determinant;

           
        }
        static Matrix Inverse(Matrix A) {
            if (A.getRow()!= A.getCol()) {
                System.out.println("Inverse is not possible for non-square matrices.");
                return null;
            }
            double determinant = det(A);

            if(determinant == 0){
                System.out.println("Matrix is singular, no inverse exists");
                return null;
            }

            Matrix adjugate = new Matrix(A.getRow(), A.getCol(), 1);
            adjugate.Put(0,0,A.Get(1,1));
            adjugate.Put(0,1, -A.Get(1,0));
            adjugate.Put(1,0, -A.Get(0,1));
            adjugate.Put(1,1, A.Get(0,0));
            adjugate = MatrixOpretion.MulMatrix(adjugate, 1/determinant);

            return adjugate;

        }
        
}
         public class program4{
             public static void main(String[] args) {
                 Matrix matrix1 = new Matrix(3,3);
                 Matrix matrix2 = new Matrix(3,3);
                 System.out.println("------------Matrix1--------------");
                 MatrixOpretion.PrintMatrix(matrix1);
                 System.out.println("---------------------------------");
                 System.out.println("------------Matrix2--------------");
                 MatrixOpretion.PrintMatrix(matrix2);
                 System.out.println("---------------------------------");
                 MatrixOpretion.PrintMatrix(MatrixOpretion.AddMatrix(matrix1, matrix2));
                 System.out.println("---------------------------------");
                 MatrixOpretion.PrintMatrix(MatrixOpretion.SubMatrix(matrix1, matrix2));
                 System.out.println("---------------------------------");
                 MatrixOpretion.PrintMatrix(MatrixOpretion.MulMatrix(matrix1, matrix2));
                 System.out.println("---------------------------------");
                 MatrixOpretion.PrintMatrix(MatrixOpretion.MulMatrix(2, matrix1));
                 System.out.println("---------------------------------");
                 MatrixOpretion.PrintMatrix(MatrixOpretion.MulMatrix(matrix1, 2));
                 System.out.println("---------------------------------");
                 MatrixOpretion.PrintMatrix(MatrixOpretion.Transpose(matrix1));
                 System.out.println("---------------------------------");
                 System.out.println("Determinant: " + MatrixOpretion.det(matrix1));
                 System.out.println("---------------------------------");
                 Matrix inverse = MatrixOpretion.Inverse(matrix1);
                 if(inverse!= null){
                     MatrixOpretion.PrintMatrix(inverse);
                 }
        
    }
}
