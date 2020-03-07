package bg.swift.HW09_Ex03;

public class Credentials {
	private static Credentials [] cr = new Credentials[200];
	private static int countOfCredentials = 0;
    
	private String username;
	private String password;
    private String [] passes = new String [100];
	private int countOfPass = 0;

	private Credentials(String username, String password) {
		this.username = username;
		this.password = password;
		this.passes[0] = this.password;
		this.countOfPass = 1;
	}
	
	private static boolean isValidAll(String username, String password) {
		if (username != null && !username.trim().isEmpty() && 
			password != null && !password.trim().isEmpty()) {
			return true;
		}
		return false;
	}
	
	public static Credentials getUser(String username) {
		for (int i = 0; i < countOfCredentials; i++) {
			if(cr[i].getUserName().equals(username)) {
				return cr[i];
			}
		}
		return null;
	}
	
	public static Credentials addNewUser(String username, String password) {
		if (isValidAll(username, password)) {
			Credentials creds = new Credentials(username, password);
			cr[countOfCredentials] = creds;
			countOfCredentials++;
			return creds;
		}
		return null;
	}

	public static boolean passwordChange(String username, String oldPassword, String newPassword) throws OldPasswordConflictException{
		Credentials cred = Credentials.getUser(username);

		if (cred == null || 
			!cred.password.equals(oldPassword) ||
			cred.countOfPass > 100) {
			return false;
		}
		
		for (int j = 0; j < cred.countOfPass; j++){
			if (cred.passes[cred.countOfPass - j - 1].equals(newPassword)) {
				throw new OldPasswordConflictException("Index of exception: " + j);
			}
		}
		
		cred.password = newPassword;
		cred.passes[cred.countOfPass] = cred.password;
		cred.countOfPass++;
		return true;
	}

	public static boolean authentication(String username, String password) {
		Credentials cred = Credentials.getUser(username);

		if (cred != null) {
			if (cred.password.equals(password)) {
				return true;
			}
		}
		return false;
	}

	public String getUserName() {
		return username;
	}
}
