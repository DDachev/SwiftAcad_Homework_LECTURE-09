package bg.swift.HW09_Ex02;

public class FileSystemObject {

	private FolderObject parent;
	private String name;

	public FileSystemObject(String name) throws InvalidNameException {
		if (isValidName(name)) {
			this.name = name;
		}
	}

	public FileSystemObject(FolderObject parent, String name) throws InvalidNameException {
		if (checkNameIsOnAFile(name)) {
			if (isValidName(name) && isValidNameOfFile(name)) {
				this.parent = parent;
				this.name = name;
			}
		} else {
			if (isValidName(name)) {
				this.parent = parent;
				this.name = name;
			}
		}
	}
	
	protected static boolean checkPreviousObjectIsItFile(String fileName) throws IllegalOperationException{
		String[] input = fileName.split("[.]");
		if(input.length > 1) {
			throw new IllegalOperationException();
		}
		return false;
	}

	protected boolean isValidName(String name) throws InvalidNameException {
		if (name.trim().isEmpty()) {
			throw new InvalidNameException();
		}
		if (name.charAt(0) < 65 || (name.charAt(0) > 90 && name.charAt(0) < 97) || name.charAt(0) > 122) {
			throw new InvalidNameException();
		}
		char[] input = name.toCharArray();
		for (int i = 0; i < input.length; i++) {
			if ((input[i] < 65 && input[i] != 33 && input[i] != 38 && input[i] != 40 && input[i] != 41
					&& input[i] != 46) || (input[i] > 90 && input[i] < 97 && input[i] != 95) || (input[i] > 122)) {
				throw new InvalidNameException();
			}
		}
		return true;
	}

	protected boolean isValidNameOfFile(String name) throws InvalidNameException {
		String[] line = name.split("[.]");
		if (line[1].length() > 5) {
			throw new InvalidNameException();
		}
		return true;
	}

	protected boolean checkNameIsOnAFile(String name) {
		String[] input = name.split("[.]");
		if (input.length > 1) {
			return true;
		}
		return false;
	}

	public FolderObject getParent() {
		return parent;
	}

	public String getName() {
		return name;
	}
	
	
}
