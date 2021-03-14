package Game.Util;

import java.util.ArrayList;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class BufferUtil {
	
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

	public static FloatBuffer arraylistToBuffer(ArrayList<Float> array) {
		float[] floats = new float[array.size()];

		for (int i = 0; i < floats.length; i++)
			floats[i] = array.get(i);

		return arrayToBuffer(floats);
	}

	public static float[] toFloats(ArrayList<Float> array) {
		float[] floats = new float[array.size()];

		for (int i = 0; i < floats.length; i++)
			floats[i] = array.get(i);

		return floats;
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

	public static byte[] intToByte(int[] data) {
		byte[] bytes = new byte[data.length];

		for (int i = 0; i < bytes.length; i++)
			bytes[i] = (byte) data[i];

		return bytes;
	}

	public static void printFloats(float[] array) {
		String output = "[";

		for (float f:array) output += f + ", ";
		output = output.substring(0, output.length() - 2);

		output += "]\n";
		System.out.print(output);
	}

}
