package bg.swift.HW09_Ex03;

public class OldPasswordConflictException extends Exception{


	public OldPasswordConflictException(String message) {
		super(message);
	}
	
	public int getPasswordConflictIndex() {
		String output = super.getMessage();
		return Integer.parseInt(super.getMessage().substring(output.length()-1, output.length()));
	}
}
