import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import org.junit.Test;
import static org.junit.Assert.*;

import Game.Math.Matrix;

public class TestMatrix {

    @Test
    public void dimensionTests() {
        Matrix m;
        
        try {
            m = new Matrix(new float[][] {{}});
        } catch (IllegalArgumentException e) {};


        m = new Matrix(new float[][] {{1}});
        assertEquals(1, m.dimensions()[0]);
        assertEquals(1, m.dimensions()[1]);

        m = new Matrix(new float[][] {{1, 2}});
        assertEquals(1, m.dimensions()[0]);
        assertEquals(2, m.dimensions()[1]);

        m = new Matrix(new float[][] {{1}, {2}});
        assertEquals(2, m.dimensions()[0]);
        assertEquals(1, m.dimensions()[1]);

        m = new Matrix(new float[][] {{1, 2}, {3, 4}});
        assertEquals(2, m.dimensions()[0]);
        assertEquals(2, m.dimensions()[1]);

        try {
            m = new Matrix(new float[][] {{1, 2, 3}, {3, 4}});
        } catch (IllegalArgumentException e) {};
    }

    @Test
    public void getTests() {
        Matrix m = new Matrix(new float[][] {{1}});
        assertEquals(1, m.get(0, 0), .000000000001);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestMatrix.class);
          
        for (Failure failure : result.getFailures()) {
           System.out.println(failure.toString());
        }
          
        System.out.println(result.wasSuccessful());
     }
    
}
