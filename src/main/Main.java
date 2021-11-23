package main;

public class Main {
	public static void main(String[] args) throws Exception {
		String mode = args[0];
		String name = args[1];
		
		switch(mode) {
		case "compile":
			Precompiler.precompile(name, "bin/" + name);
			Compiler.compile("bin/" + name);
			Interpreter.interpret("bin/" + name);
			
			break;
			
		case "interpret":
			Interpreter.interpret(name);
			break;
		
		default:
			System.out.println("Unknown mode " + mode);
		}
	}
}
