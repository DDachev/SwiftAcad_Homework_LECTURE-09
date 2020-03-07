package bg.swift.HW09_Ex02;

import java.util.ArrayList;
import java.util.List;

public class FolderObject extends FileSystemObject {
	private List<FileSystemObject> children = new ArrayList<>();
	
	public FolderObject(String name) throws InvalidNameException {
		super(name);
	}
	
	public FolderObject(FolderObject parent, String name) throws InvalidNameException {
		super(parent, name);
	}

	public void addChildren(FileSystemObject obj) throws IllegalOperationException, InvalidDirectoryException {
		children.add(obj);
	}


}
