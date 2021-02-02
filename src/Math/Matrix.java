package Math;

public class Matrix {

    private final int rows;
    private final int cols;

    private final float[][] matrix;

    public Matrix(float[][] nums) {
        rows = nums.length;
        cols = nums[0].length;

        if (rows == 0) throw new IllegalArgumentException();
        if (cols == 0) throw new IllegalArgumentException();
        for (float[] f:nums)
            if (f.length != cols)
                throw new IllegalArgumentException();

        matrix = nums;
    }

    public int[] dimensions() {
        return new int[] {rows, cols};
    }

    public float[] toArray() {
        float[] data = new float[rows * cols];

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                data[i*cols + j] = matrix[i][j];

        return data;
    }

    public String toString() {
        String output = "";

        for (float[] row: matrix) {
            output += "|";

            for (float num:row)
                output += num + " ";
            
            output = output.substring(0, output.length() - 1); // chop off extra ' '
            output += "|\n";
        }

        return output.substring(0, output.length() - 1);
    }
    
}
