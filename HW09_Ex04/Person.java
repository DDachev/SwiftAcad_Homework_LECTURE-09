package bg.swift.HW09_Ex04;

import java.time.LocalDate;

public class Person {

	private String firstName;
	private String middleName;
	private String lastName;
	private char gender;
	private LocalDate dateOfBirth;
	private short height;
	private char codeOfEducation;
	protected Education ed;
	protected Address address;

	protected Person(String firstName, String middleName, String lastName, char gender, LocalDate dateOfBirth,
			char codeOfEducation, short height, String country, String city, String municipality, int postalCode,
			String street, int number, int floor, int apartamentNo, LocalDate enrollmentDate, LocalDate graduationDate,
			String institutionName, double finalGrade) {
		try {
		if (checkNamesOfPerson(firstName, middleName, lastName)) {
			this.firstName = firstName;
			this.middleName = middleName;
			this.lastName = lastName;
		}
		if (checkGender(gender)) {
			this.gender = gender;
		}
		if (checkDateOfBirth(dateOfBirth)) {
			this.dateOfBirth = dateOfBirth;
		}
		if (checkCodeOfEducation(codeOfEducation)) {
			this.codeOfEducation = codeOfEducation;
		}
		if (checkHeight(height)) {
			this.height = height;
		}
		this.address = new Address(country, city, municipality, postalCode, street, number, floor, apartamentNo);
		if (checkInstitutionName(institutionName)) {
			if (this.codeOfEducation == 'P') {
				this.ed = new PrimaryEducation(enrollmentDate, graduationDate, institutionName);
			} else if (this.codeOfEducation == 'S') {
				this.ed = new SecondaryEducation(enrollmentDate, graduationDate, institutionName, finalGrade);
			} else if (this.codeOfEducation == 'B' || this.codeOfEducation == 'M' || this.codeOfEducation == 'D') {
				this.ed = new HigherEducation(enrollmentDate, graduationDate, institutionName, finalGrade);
			}
			}
		} catch (EmptyNameException e) {
			System.out.println("Expected non-empty <first,last,...> name.");
		} catch (GenderException e) {
			System.out.println("Expected M or F for gender.");
		} catch (DateOfBirthException e) {
			System.out.println("Date of birth is expected to be after 01.01.1900 and before now.");
		} catch (UnrecognizedCodeException e) {
			System.out.println("Unrecognized education code.");
		} catch (HeightException e) {
			System.out.println("Expected height is between 40 and 300 cm.");
		} catch (FinalGradeException e) {
			System.out.println("Graduation grade is expected to be between 2 and 6.");
		} catch (NoGradeException e) {
			System.out.println("No final grade can be provided before graduation");
		}
	}

	@Override
	public String toString() {
		try {
			if (this.gender == 'M') {
				String firstLine = String.format("%s %s %s is %d years old. He was born in %d.%n", this.firstName,
						this.middleName, this.lastName, this.getAgeOfPerson(), this.getDateOfBirth().getYear());
				if (!this.isUnderAge(dateOfBirth)) {
					String isUnderAged = String.format("%s %s %s is under-aged.%n", this.firstName, this.middleName,
							this.lastName);
					firstLine = firstLine + isUnderAged;
				}
				String secondLine = String.format("He " + this.address.toString() + "He started %s degree in %s on "
						+ this.ed.getEnrollmentData() + " and ", this.getDegree(), this.ed.getInstutionName());
				firstLine = firstLine + secondLine;
				if (this.ed.isGraduated()) {
					String lastLine = String.format(
							"finished on " + this.ed.getGraduationDate() + ". His grade was %.3f.",
							((GradedEducation) this.ed).getFinalGrade());
					firstLine = firstLine + lastLine;

				} else {
					String lastLine = String.format("is supposed to graduate on " + this.ed.getGraduationDate() + ".");
					firstLine = firstLine + lastLine;
				}
				return firstLine;
			}
			String firstLine = String.format("%s %s %s is %d years old. She was born in %d.%n", this.firstName,
					this.middleName, this.lastName, this.getAgeOfPerson(), this.getDateOfBirth().getYear());
			if (!this.isUnderAge(dateOfBirth)) {
				String isUnderAged = String.format("%s %s %s is under-aged.%n", this.firstName, this.middleName,
						this.lastName);
				firstLine = firstLine + isUnderAged;
			}
			String secondLine = String.format("She " + this.address.toString() + "She started %s degree in %s on "
					+ this.ed.getEnrollmentData() + " and ", this.getDegree(), this.ed.getInstutionName());
			firstLine = firstLine + secondLine;
			if (this.ed.isGraduated()) {
				String lastLine = String.format("finished on " + this.ed.getGraduationDate() + ". Her grade was %.3f.",
						((GradedEducation) this.ed).getFinalGrade());
				firstLine = firstLine + lastLine;
			} else {
				String lastLine = String.format("is supposed to graduate on " + this.ed.getGraduationDate() + ".");
				firstLine = firstLine + lastLine;
			}
			return firstLine;
		} catch (FinalGradeException e) {
			System.out.println("Graduation grade is expected to be between 2 and 6.");
		} catch (NoGradeException e) {
			System.out.println("No final grade can be provided before graduation");
		}
			return "Cannot create a person with this charachteristics.";
	}

	private static boolean checkDateOfBirth(LocalDate dateOfBirth) throws DateOfBirthException {
		if (dateOfBirth.isBefore(LocalDate.of(1900, 1, 1)) || dateOfBirth.isAfter(LocalDate.now())) {
			throw new DateOfBirthException();
		}
		return true;
	}

	private static boolean checkCodeOfEducation(char code) throws UnrecognizedCodeException {
		if (code == 'P' || code == 'S' || code == 'B' || code == 'M' || code == 'D') {
			return true;
		}
		throw new UnrecognizedCodeException();
	}

	private static boolean checkInstitutionName(String name) throws EmptyNameException {
		if (name != null && !name.trim().isEmpty()) {
			return true;
		}
		throw new EmptyNameException();
	}

	private static boolean checkNamesOfPerson(String firstName, String middleName, String lastName)
			throws EmptyNameException {
		if (firstName != null && !firstName.trim().isEmpty() && middleName != null && !middleName.trim().isEmpty()
				&& lastName != null && !lastName.trim().isEmpty()) {
			return true;
		}
		throw new EmptyNameException();
	}
	
	private static boolean checkHeight(short height) throws HeightException{
		if(height >= 40 && height <= 300) {
			return true;
		}
		throw new HeightException();
	}
	
	private static boolean checkGender(char gender) throws GenderException{
		if(gender == 'M' || gender == 'F') {
			return true;
		}
		throw new GenderException();
	}
	
	public boolean isUnderAge(LocalDate dateOfBirth) {
		LocalDate dateNow = LocalDate.now();
		if (dateNow.minusYears(18).compareTo(dateOfBirth) >= 0) {
			return true;
		}
		return false;
	}

	public int getAgeOfPerson() {
		LocalDate dateNow = LocalDate.now();
		int ageOfPerson = dateNow.compareTo(this.dateOfBirth);
		boolean yearIsAfter = this.dateOfBirth.plusYears(dateNow.getYear() - this.dateOfBirth.getYear())
				.isAfter(dateNow);

		if (yearIsAfter) {
			return ageOfPerson - 1;
		}
		return ageOfPerson;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public char getGender() {
		return gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public short getHeight() {
		return height;
	}

	public char getCodeOfEducation() {
		return this.codeOfEducation;
	}

	public String getDegree() {
		char code = getCodeOfEducation();
		if (code == 'P') {
			return "primary";
		} else if (code == 'S') {
			return "secondary";
		} else if (code == 'B') {
			return "bachelor";
		} else if (code == 'M') {
			return "master";
		} else if (code == 'D') {
			return "doctorate";
		}
		return null;
	}
}
