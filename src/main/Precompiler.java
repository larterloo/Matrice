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
	
	public static void precompile(String name, String destination) throws Exception {
		File inFile = new File(name + ".arraycode");
		File outFile = new File(destination + ".tmp");
		
		outFile.mkdirs();
		
		Scanner in = new Scanner(inFile);
		PrintWriter out = new PrintWriter(outFile);
		
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
		
		in.close();
		out.close();
	}
}
