package main;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Compiler {

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("code.arraycode"));

		FileOutputStream out = new FileOutputStream(new File("code.array"));

		while (in.hasNext()) {
			String code = in.next();

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

			case "sub":
				out.write(0x51);
				break;

			default:
				System.out.println("Unknown function: " + code);
				System.exit(-1);
			}
		}
	}

}
