import java.util.Random;
import org.apache.commons.math3.linear.*;

class Matrix {
    private int numberOfRows;
    private int numberOfColumns;
    private int[][] matrix;

    public int getNumberOfRows() {return numberOfRows;}
    public int getNumberOfColumns() {return numberOfColumns;}
    public int[][] getMatrix() {return matrix;}
    public int getValueAt(int x, int y) {
        return matrix[x][y];
    }

    Matrix(int x, int y) {
        numberOfRows = x;
        numberOfColumns = y;
        matrix = new int[x][y];
    }

    void fillRandom() {
        for (int i = 0; i < this.getNumberOfRows(); i++) {
            for (int j = 0; j < this.getNumberOfColumns(); j++) {
                Random r = new Random();
                int w = r.nextInt(11);
                this.matrix[i][j] = w;
            }
        }
    }

    void print() {
        for (int i = 0; i < getNumberOfRows(); i++) {
            for (int j = 0; j < getNumberOfColumns(); j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    Matrix multiplyByScalar(int scalar) {

        for(int i=0;i<getNumberOfColumns();i++) {
            for (int j=0;j<getNumberOfRows();j++){
                this.matrix[i][j]=this.matrix[i][j]*scalar;
            }
        } return this;
    }

    Matrix multiplyByMatrix(Matrix drugaMacierz) {

        Matrix t = new Matrix(this.getNumberOfRows(),drugaMacierz.getNumberOfColumns());

        if (this.getNumberOfColumns() != drugaMacierz.getNumberOfRows()) {
           System.out.println("The number of columns of the first matrix must be equal to the number of rows of the second matrix!");
        } else {
            for (int i=0;i<this.getNumberOfRows();i++) {
                for (int j=0;j<drugaMacierz.getNumberOfColumns();j++) {
                    for(int k = 0; k<t.getNumberOfRows(); k++) {
                        t.matrix[i][j]+=this.matrix[i][k]*drugaMacierz.matrix[k][j];
                    }
                }
            }
            t.print();
        }
        return t;
    }

    int calculateDeterminant(int[][] maatrix) {

        if(maatrix.length==1){
            return(maatrix[0][0]);
        }
        int sum = 0;
        for(int i = 0; i<maatrix.length; i++){
            int[][]smaller= new int[maatrix.length-1][maatrix.length-1];
            for(int a=1; a<maatrix.length; a++){
                for(int b=0; b<maatrix.length; b++){
                    if(b<i){
                        smaller[a-1][b]=maatrix[a][b];
                    }
                    else if(b>i){
                        smaller[a-1][b-1]=maatrix[a][b];
                    }
                }
            }
            int s;
            if(i%2==0){
                s=1;
            }
            else{
                s=-1;
            }
            sum+=s*maatrix[0][i]*calculateDeterminant(smaller);
        }
        return(sum);
    }

    RealMatrix inverse() {
       RealMatrix A = new Array2DRowRealMatrix( new double[this.getNumberOfRows()][this.getNumberOfColumns()],
                false);

        for (int i = 0; i < A.getRowDimension(); i++) {
            for (int j = 0; j < A.getColumnDimension(); j++) {
                A.setEntry(i,j,this.getValueAt(i,j));
            }
        }
        DecompositionSolver solver = new LUDecomposition(A).getSolver();

        RealMatrix inverse = solver.getInverse();
        System.out.println(inverse);
        return inverse;
    }

    Matrix invertMatrix() {
        if (this.numberOfRows != this.numberOfColumns) {
            System.out.println("Number of columns must be equal to number of rows");
        } else {
            if (calculateDeterminant(this.matrix) == 0)
                System.out.println("The determinant equals 0, the matrix does not have an inverse");
            else
                System.out.println("The determinant equals " + calculateDeterminant(this.matrix));

            this.inverse();
        }
        return this;
    }
}
