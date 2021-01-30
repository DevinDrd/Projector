import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class FloatBufferUtil {
	
	public static FloatBuffer createFloatBuffer(int num) {
		// make byte buffer big enough to hold the entire bytes
		ByteBuffer bb = ByteBuffer.allocateDirect( num*4 );
		
		// make sure that the order of the bytes in a single float is correct
		bb.order(ByteOrder.nativeOrder());
		
		// create float buffer from these bytes
		FloatBuffer fb = bb.asFloatBuffer();
		
		return fb;
	}
	
	public static FloatBuffer arrayToBuffer(float[] array) {
		ByteBuffer bb = ByteBuffer.allocateDirect(array.length * 4);
		
		// make sure that the order of the bytes in a single float is correct
		bb.order(ByteOrder.nativeOrder());
		
		// create float buffer from these bytes
		FloatBuffer fb = bb.asFloatBuffer();
		
		// put the bytes of array into fb
		fb.put(array);
		fb.rewind();
		
		return fb;
	}
	
	public static void sendArrayToBuffer(float[] array, FloatBuffer buffer) {
		buffer.rewind();
		for (int k = 0; k < array.length; k++)
			buffer.put(array[k]);
		buffer.rewind();
	}
	
	public static void putArray(float[] array, FloatBuffer buffer) {
		for (int k = 0; k < array.length; k++)
			buffer.put(array[k]);
	}

}
