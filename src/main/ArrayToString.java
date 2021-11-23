package main;

public class ArrayToString {
	public static String arrayToString(byte[] arr) {
		String retVal = "[";
		
		for (byte e : arr) {
			retVal += e + ", ";
		}
		
		return retVal.substring(0, retVal.length() - 2) + "]";
	}
}
