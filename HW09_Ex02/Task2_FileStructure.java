package bg.swift.HW09_Ex02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task2_FileStructure {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		List<FolderObject> objects = new ArrayList<>();
		int numberOfLines = 0;

		while (!"END".equals(input)) {
			String[] command = input.split(" ");
			String[] info = command[1].split("[/]");
			try {
				if (command[0].equals("mkdir")) {
					numberOfLines++;
					if (info.length > 1) {
						FileSystemObject.checkPreviousObjectIsItFile(info[info.length - 2]);
						checkForSubFolder(objects, info);
						if (getIndexOfObject(objects, info[info.length - 2]) != -1) {
							FolderObject folder = new FolderObject(
									objects.get(getIndexOfObject(objects, info[info.length - 2])),
									info[info.length - 1]);
							folder.getParent().addChildren(folder);
							objects.add(folder);
						}
					} else if (info.length == 1) {
						FolderObject folder = new FolderObject(info[info.length - 1]);
						objects.add(folder);
					}
				}

				if (command[0].equals("touch")) {
					numberOfLines++;
					FileSystemObject.checkPreviousObjectIsItFile(info[info.length - 2]);
					checkForSubFolder(objects, info);
					if (getIndexOfObject(objects, info[info.length - 2]) != -1) {
						FileObject file = new FileObject(objects.get(getIndexOfObject(objects, info[info.length - 2])),
								info[info.length - 1]);
						file.addFile(file);
					}
				}
			} catch (IllegalOperationException e) {
				System.out.println(numberOfLines + " - Illegal operation");
			} catch (InvalidNameException e) {
				System.out.println(numberOfLines + " - Invalid name specified");
			} catch (InvalidDirectoryException e) {
				System.out.println(numberOfLines + " - Directory does not exist");
			}
			input = sc.nextLine();
		}
		sc.close();
	}
	
	private static void checkForSubFolder(List<FolderObject> files, String [] input) throws InvalidDirectoryException {
		boolean consilience = false;
		for(int j = 0; j < input.length - 1; j++) {
			consilience = false;
			for(int i = 0; i < files.size(); i++) {
			if(input[j].equals(files.get(i).getName())) {
				consilience = true;
			}
		}
		}
		if(!consilience) {
			throw new InvalidDirectoryException();
		}
	}
	
	private static int getIndexOfObject(List<FolderObject> files, String name) {
			for(int i = 0; i < files.size(); i++) {
			if(name.equals(files.get(i).getName())) {
				return i;
			}
		}
			return -1;
	}
}