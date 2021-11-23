package main;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Precompiler {
	
	/**
	 * Whether or not we're in comment mode 
	 * (that is, ignoring code)
	 */
	static boolean skipping;
	
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("code.arraycode"));

		PrintWriter out = new PrintWriter(new File("code.tmp"));
		
		// initialise the array
		out.println("init");
		if (in.hasNext("init")) {
			in.next();
			out.println(in.next());
		} else {
			out.println(50);
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
				out.println("init");
				out.println(in.next());
				break;

			case "left":
				out.println("left");
				break;

			case "right":
				out.println("right");
				break;

			case "write":
				out.println("write");
				break;

			case "read":
				out.println("read");
				break;

			case "put":
				out.println("put");
				out.println(in.next());
				break;

			case "printInfo":
				out.println("printInfo");
				break;

			case "add":
				out.println("add");
				break;
				
			case "sub":
				// negate memory then add
				out.println("neg");
				out.println("add");
				break;

			case "neg":
				out.println("neg");
				break;
				
			case "if":
				switch(in.next()) {
				case "null":
					out.println("ifnull");
					break;
					
				case "pos":
					out.println("ifpos");
					break;
				}
				break;

			default:
				System.out.println("Unknown function: " + code);
				System.exit(-1);
			}
		}
	}
}
