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
	
	public static void precompile(String name) throws Exception {
		Scanner in = new Scanner(new File(name + ".arraycode"));

		PrintWriter out = new PrintWriter(new File(name + "code.tmp"));
		
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

			case "put":
				out.println("put");
				out.println(in.next());
				break;
				
			case "sub":
				// negate memory then add
				out.println("neg");
				out.println("add");
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
				
			// this instruction is just a standard matric assembly instruction
			default:
				out.println(code);
			}
		}
		
		out.close();
	}
}
