package Math;

public class Matrix {

    private final int rows;
    private final int cols;

    private final float[][] matrix;

    public static final Matrix IDTTY4 = new Matrix(new float[][] {
        {1, 0, 0, 0},
        {0, 1, 0, 0},
        {0, 0, 1, 0},
        {0, 0, 0, 1}
    });

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

    public static Matrix frustum(float l, float r, float b, float t, float n, float f) {
		Matrix frustum = new Matrix(new float[][] {
				{(2*n)/(r-l), 0, (r+l)/(r-l), 0},
				{0, (2*n)/(t-b), (t+b)/(t-b), 0},
				{0, 0, -(f+n)/(f-n), -(2*f*n)/(f-n)},
				{0, 0, -1, 0}
		});

		return frustum;
    }
    
    public static Matrix ortho(float l, float r, float b, float t, float n, float f) {
		Matrix ortho = new Matrix(new float[][] {
				{2/(r-l), 0, 0, -(r+l)/(r-l)},
				{0, 2/(t-b), 0, -(t+b)/(t-b)},
				{0, 0, -2/(f-n), -(f+n)/(f-n)},
				{0, 0, 0, 1}
		});

		return ortho;
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
