import java.util.Arrays;

public class Matrix implements Operations<Matrix>{
    private Row[] rows;

    public Matrix(int rows, int cols) {
        this.rows = zero(rows, cols).rows;
    }

    public Matrix(Row[] rows){
        this.rows = rows.clone();
    }

    public Matrix(double[] elements, int cols){
        this.rows = fromElements(elements, cols).rows;
    }

    public static Matrix fromElements(double[] elements, int cols){
        int nRows = elements.length/cols;
        Row[] rows = new Row[nRows];
        for (int i=0; i<nRows; i++) {
            rows[i] = new Row(Arrays.copyOfRange(elements, i*cols, (i*cols)+cols));
        }
        return new Matrix(rows);
    }

    public static Matrix zero(int rows, int cols) {
        Row[] r = new Row[rows];
        for (int i=0; i<rows; i++) r[i] = Row.zero(cols);
        return new Matrix(r);
    }

    public static Matrix identity(int size) {
        Row[] rows = new Row[size];
        for(int i=0; i<size; i++){
            rows[i] = Row.zero(size);
            rows[i].setElementAt(i, 1);
        }
        return new Matrix(rows);
    }

    public static Matrix empty() {
        return new Matrix(0, 0);
    }

    public int rowSize(){
        return rows.length;
    }

    public int colSize(){
        if (rowSize() < 1) return 0;
        return rows[0].size();
    }

    public Row rowAt(int index) throws Exception {
        if (index < 0 || index >= rows.length) throw new Exception("Error[Matrix]: Index out of bounds");
        return this.rows[index];
    }

    public double elementAt(int row, int col) throws Exception {
        if (row < 0 || row >= rows.length) throw new Exception("Error[Matrix]: Index out of bounds");
        return rowAt(row).elementAt(col);
    }

    public void setElementAt(int row, int col, double value) throws Exception {
        rowAt(row).setElementAt(col, value);
    }


    @Override
    public Matrix multiply(Matrix other) throws Exception {
        if (colSize() != other.rowSize()) return empty();

        Matrix res = new Matrix(rowSize(), colSize());
        for (int row=0; row<rowSize(); row++)
            for (int col=0; col<other.colSize(); col++)
                for (int k=0; k<other.rowSize(); k++)
                    res.setElementAt(row, col, res.elementAt(row, col) + elementAt(row, k) * other.elementAt(k, col));
        return res;
    }

    @Override
    public Matrix multiply(double other) throws Exception {
        Matrix res = new Matrix(rowSize(), colSize());
        for (int row=0; row<rowSize(); row++)
            for (int col=0; col<colSize(); col++)
                res.setElementAt(row, col, rowAt(row).elementAt(col) * other);
        return res;
    }

    @Override
    public Matrix add(Matrix other) throws Exception {
        if (rowSize() != other.rowSize() || colSize() != other.colSize()) return empty();
        Matrix res = new Matrix(rowSize(), colSize());
        for (int row=0; row<rowSize(); row++)
            for (int col=0; col<colSize(); col++)
                res.setElementAt(row, col, rowAt(row).elementAt(col) + other.rowAt(row).elementAt(col));
        return res;
    }

    @Override
    public Matrix inverse() {
        return null;
    }
}
