package main;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Compiler {
	
	/**
	 * Whether or not we're in comment mode 
	 * (that is, ignoring code)
	 */
	static boolean skipping;

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("code.arraycode"));

		FileOutputStream out = new FileOutputStream(new File("code.array"));
		
		// initialise the array
		out.write(0x00);
		if (in.hasNext("init")) {
			in.next();
			out.write(Byte.parseByte(in.next()));
		} else {
			out.write(50);
		}
		
		skipping = false;
		
		// go through all the code
		while (in.hasNext()) {
			String code = in.next();
			
			// toggle comment mode
			if (code.equals("$")) {
				skipping = !skipping;
				continue;
			}
			
			// skip if in comment mode
			if (skipping) {
				continue;
			}
			
			switch (code) {
			case "init":
				out.write(0x00);
				out.write(Byte.parseByte(in.next()));
				break;

			case "left":
				out.write(0x10);
				break;

			case "right":
				out.write(0x11);
				break;

			case "write":
				out.write(0x20);
				break;

			case "read":
				out.write(0x21);
				break;

			case "put":
				out.write(0x30);
				out.write(Byte.parseByte(in.next()));
				break;

			case "printInfo":
				out.write(0x40);
				break;

			case "add":
				out.write(0x50);
				break;

			case "neg":
				out.write(0x51);
				break;
				
			case "ifnull":
				out.write(0x60);
				break;
				
			case "ifpos":
				out.write(0x61);
				break;

			default:
				System.out.println("Unknown function: " + code);
				System.exit(-1);
			}
		}
	}

}
