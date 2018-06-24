public class Main {

    public static void main(String[] args) {
        Matrix first = new Matrix(3, 3);
        first.fillRandom();
        first.print();
        first.multiplyByScalar(2);
        first.print();

        Matrix second = new Matrix(3,2);
        second.fillRandom();
        second.print();
        first.multiplyByMatrix(second);

        Matrix third = new Matrix(3,3);
        third.fillRandom();
        third.print();
        third.invertMatrix();
    }
}
