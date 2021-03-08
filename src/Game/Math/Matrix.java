package Game.Math;

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

    public Matrix multiply(Matrix m) {
        if (cols != m.rows) throw new ArithmeticException();

        float[][] product = new float[rows][m.cols];

        for (int i = 0; i < product.length; i++)
            for (int j = 0; j < product[i].length; j++)
                for (int k = 0; k < cols; k++)
                    product[i][j] += get(i, k)*m.get(k, j);

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
    
    public static Matrix lookAt(Vector e, Vector d, Vector u) {
        d = d.normalize();
        u = u.normalize();

        Vector r = d.cross(u);
        r = r.normalize();

        Vector w = r.cross(d);
        w = w.normalize();

        float[][] look = new float[][] {
            {r.get(0), r.get(1), r.get(2), -e.get(0)*r.get(0) - e.get(1)*r.get(1) - e.get(2)*r.get(2)},
            {w.get(0), w.get(1), w.get(2), -e.get(0)*w.get(0) - e.get(1)*w.get(1) - e.get(2)*w.get(2)},
            {-d.get(0), -d.get(1), -d.get(2), e.get(0)*d.get(0) + e.get(1)*d.get(1) + e.get(2)*d.get(2)},
            {0, 0, 0, 1}
        };

        return new Matrix(look);
    }

    public static Matrix translate(Vector v) {
        if (v.getLength() != 3) throw new ArithmeticException();

        float[][] matrix = new float[][] {
            {1, 0, 0, v.get(0)},
            {0, 1, 0, v.get(1)},
            {0, 0, 1, v.get(2)},
            {0, 0, 0, 1}
        };
        
        return new Matrix(matrix);
    }

    public static Matrix rotate(Vector position, Vector axis, float alpha) {
        if (axis.getLength() != 3) throw new IllegalArgumentException();
        if (axis.magnitude() - 0.000001f < 0) throw new ArithmeticException();

        axis = axis.normalize();

        float x = axis.get(0);
        float y = axis.get(1);
        float z = axis.get(2);

        float c = (float) Math.cos(Math.toRadians(alpha));
        float s = (float) Math.sin(Math.toRadians(alpha));

        float[][] rotate = new float[][] {
            {(x*x)*(1-c) + c, x*y*(1-c) - z*s, x*z*(1-c) + y*s, 0},
            {y*x*(1-c) + z*s, y*y*(1-c) + c, y*z*(1-c) - x*s, 0},
            {z*x*(1-c) - y*s, z*y*(1-c) + x*s, z*z*(1-c) + c, 0},
            {0, 0, 0, 1}
        };

        Matrix center = translate(position.multiply(-1));
        Matrix rotation = new Matrix(rotate);
        Matrix restore = translate(position);
        
        return restore.multiply(rotation.multiply(center));
    }

    public static Vector rotate(Matrix rotation, Vector vec) {
        if (vec.getLength() != 3) throw new ArithmeticException();

        vec = vec.homogenize();
        rotation = rotation.multiply(vec.toMatrix());
        vec = rotation.toVector().perspectiveDivide();

        return vec;
    }

    public Vector toVector() {
        if (cols != 1) throw new ArithmeticException();

        float[] f = new float[rows];

        for (int i = 0; i < f.length; i++)
            f[i] = matrix[i][0];

        return new Vector(f);
    }

    @Override
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) return false;
        if (this == obj) return true;

        Matrix object = (Matrix) obj;

        if (this.rows != object.rows) return false;
        if (this.cols != object.cols) return false;

        for (int i = 0; i < this.rows; i++)
            for (int j = 0; j < this.cols; j++)
                if (this.matrix[i][j] != object.matrix[i][j])
                    return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (rows*cols)%10000;
    }
}
