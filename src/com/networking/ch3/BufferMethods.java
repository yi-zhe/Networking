package com.networking.ch3;

import java.nio.IntBuffer;

public class BufferMethods {

	public static void displayBuffer(IntBuffer buffer) {
		for (int i = 0; i < buffer.position(); i++) {
			System.out.print(buffer.get(i) + " ");
		}
		System.out.println();
	}
	
	public static void displayBuffer2(IntBuffer buffer) {
		int arr[] = new int[buffer.position()];
		buffer.rewind();
		buffer.get(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		
		int[] arr = {12, 51, 79, 54};
		IntBuffer buffer = IntBuffer.allocate(6);
		buffer.put(arr);
        System.out.println(buffer);
        displayBuffer(buffer);
        
        int length = buffer.remaining();
        buffer.put(arr, 0, length);
        System.out.println(buffer);
        displayBuffer(buffer);
        displayBuffer2(buffer);
        
        // using a view
        IntBuffer buffer2 = buffer.duplicate();
        buffer2.put(0, 0x4c); //'L'
        System.out.println((char)buffer.get(0));
        System.out.println((char)buffer2.get(0));
        
        // slice uses only a portion of the original buffer
        IntBuffer buffer3 = buffer.slice();
        
        // asReadOnlyBuffer

        IntBuffer buffer4 = buffer.asReadOnlyBuffer();
        buffer4.put(0, 0x4c); //'L'
	}

}
