package bg.swift.HW09_Ex02;

public class FileObject extends FileSystemObject{

	protected FileObject(FolderObject parent, String name) throws InvalidNameException {
		super(parent, name);
	}
	
	protected void addFile(FileSystemObject fs) throws InvalidDirectoryException,  IllegalOperationException{
		fs.getParent().addChildren(fs);
	}

}
