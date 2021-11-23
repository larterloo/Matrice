package main;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Compiler {

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("code.arraycode"));

		FileOutputStream out = new FileOutputStream(new File("code.array"));
		
		out.write(0x00);
		if (in.hasNext("init")) {
			in.next();
			out.write(Byte.parseByte(in.next()));
		} else {
			out.write(50);
		}
		
		boolean skipping = false;

		while (in.hasNext()) {
			String code = in.next();
			
			if (code.equals("$")) {
				skipping = !skipping;
				continue;
			}
			
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

			default:
				System.out.println("Unknown function: " + code);
				System.exit(-1);
			}
		}
	}

}
