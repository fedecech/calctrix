import java.util.List;

public class Row {
    private double[] elements;

    public Row(double[] elements){
        this.elements = elements.clone();
    }

    public Row(int size){
        this.elements = zeroRow(size);
    }
    
    public static double[] zeroRow(int size){
        double[] row = new double[size];
        for(int i=0; i<row.length; i++) row[i] = 0;
        
        return row;
    }
    
    public double[] asArray(){
        return this.elements.clone();
    }

    public double elementAt(int index) throws Exception {
        if (!inBound(index)) throw new Exception("Error[Row]: Index out of bounds");
        return this.elements[index];
    }

    public boolean inBound(int index){
        return index > 0 && index < elements.length;
    }

    public void setElementAt(int index, double value){
        if (!inBound(index)) return;
        this.elements[index] = value;
    }

    public int size(){
     return this.elements.length;
    }

    static Row zero(int size){
        Row row = new Row(size);
        for (int i=0;i<size; i++) row.setElementAt(i, 0);
        return row;
    }
}
