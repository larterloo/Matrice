package main;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Interpreter {

	/**
	 * The array the represents the "tape"
	 */
	static byte[] array;
	
	/**
	 * The current position of the "tape head"
	 */
	static int index = 0;

	/**
	 * What's currently in memory
	 */
	static byte mem;

	/**
	 * The current byte code instruction
	 */
	static byte code;
	
	/**
	 * The iterator that iterates through the byte code
	 */
	static Iterator<Byte> iter;

	public static void interpret(String name) throws Exception {
		FileInputStream in = new FileInputStream(new File(name + ".array"));

		List<Byte> input = new ArrayList<>();

		for (byte b : in.readAllBytes())
			input.add(b);

		iter = input.iterator();

		while (iter.hasNext()) {
			code = iter.next();
			action();
		}

		in.close();
	}
	
	/**
	 * Perform the byte code action currently stored in {@link #code}
	 */
	static void action() {
		switch (code) {
		case 0x00:
			init(iter.next());
			break;

		case 0x10:
			left();
			break;

		case 0x11:
			right();
			break;

		case 0x20:
			write();
			break;

		case 0x21:
			read();
			break;

		case 0x30:
			put(iter.next());
			break;

		case 0x40:
			info();
			break;

		case 0x50:
			add();
			break;

		case 0x51:
			neg();
			break;
			
		case 0x60:
			ifnull();
			break;
			
		case 0x61:
			ifpos();
			break;
		}
	}

	/**
	 * Initialise the array to the given size
	 * 
	 * @param param The size of the array
	 */
	static void init(byte param) {
		int size = (int) param;
		array = new byte[size];
	}

	/**
	 * Move the index to the left
	 */
	static void left() {
		index--;
	}

	/**
	 * Move the index to the right
	 */
	static void right() {
		index++;
	}

	/**
	 * Write the value in memory to the current index
	 */
	static void write() {
		array[index] = mem;
	}

	/**
	 * Put the value at the current index into memory
	 */
	static void read() {
		mem = array[index];
	}

	/**
	 * Put a given value into memory
	 * 
	 * @param param The value to put into memory
	 */
	static void put(byte param) {
		mem = param;
	}

	/**
	 * Print the info about the current state to the console
	 */
	static void info() {
		System.out.println("Array Size: " + array.length + ", Index: " + index + ", Memory: " + mem);
		System.out.println(ArrayToString.arrayToString(array));
	}

	/**
	 * Add the value at the current index to the value in memory
	 * <p>
	 * The result is stored in memory
	 */
	static void add() {
		mem += array[index];
	}
	
	/**
	 * Multiply the current memory by -1
	 */
	static void neg() {
		mem *= -1;
	}
	
	/**
	 * If memory is null, perform the next action, otherwise skip to the action after
	 */
	static void ifnull() {
		code = iter.next();
		if (mem == 0) {
			action();
		}
	}
	
	/**
	 * If memort is positive and not zero, perform the next action, otherwise skip the action after
	 */
	static void ifpos() {
		code = iter.next();
		if (mem > 0) {
			action();
		}
	}
}
