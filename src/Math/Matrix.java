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

    public float get(int row, int col) {
        return matrix[row][col];
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

    public static Matrix multiply(Matrix m1, Matrix m2) {
        if (m1.cols != m2.rows) throw new ArithmeticException();

        float[][] product = new float[m1.rows][m2.cols];

        for (int i = 0; i < product.length; i++)
            for (int j = 0; j < product[i].length; j++)
                for (int k = 0; k < m1.cols; k++)
                    product[i][j] += m1.get(i, k)*m2.get(k, j);

        return new Matrix(product);
    }

    public static Matrix identity(int dimension) {
        if (dimension == 0) throw new IllegalArgumentException();

        float[][] idtty = new float[dimension][dimension];
        
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                idtty[i][j] = i == j ? 1: 0;

        return new Matrix(idtty);
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
    
    public static Matrix lookAt(Tuple e, Vector d, Vector u) {
        Vector r = Vector.cross(d, u);
        Vector w = Vector.cross(r, d);

        float[][] look = new float[][] {
            {r.get(0), r.get(1), r.get(2), -e.get(0)*r.get(0) - e.get(1)*r.get(1) - e.get(2)*r.get(2)},
            {w.get(0), w.get(1), w.get(2), -e.get(0)*w.get(0) - e.get(1)*w.get(1) - e.get(2)*w.get(2)},
            {-d.get(0), -d.get(1), -d.get(2), e.get(0)*d.get(0) + e.get(1)*d.get(1) + e.get(2)*d.get(2)},
            {0, 0, 0, 1}
        };

        return new Matrix(look);
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
