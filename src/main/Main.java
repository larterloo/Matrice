package main;

public class Main {
	public static void main(String[] args) throws Exception {
		Precompiler.precompile(args[0]);
		Compiler.compile(args[0]);
		Interpreter.interpret(args[0]);
	}
}
