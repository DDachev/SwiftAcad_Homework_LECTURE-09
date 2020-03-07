package bg.swift.HW09_Ex04;

import java.time.LocalDate;
import java.util.Scanner;


public class Task4_PersonCharacteristics {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Insert the number of people:");
		int numberOfPeople = sc.nextInt();
		System.out.println("Insert the charachteristics of the person: first name; last name; gender; date of birth; \r\n"
						+ " height; country; city; municipality; postal code; street; number; floor; apartament No; "
						+ "code of education; name of institution; enrollment date; graduation date; (final grade if the person is graduated).");

		String[] personInput = new String[numberOfPeople];
		Person[] people = new Person[numberOfPeople];

		// Fix scanner next line.
		sc.nextLine();

//		Read next line from console
		for (int i = 0; i < numberOfPeople; i++) {
			personInput[i] = sc.nextLine();
		}

		sc.close();

//      Create people from type Person and fill up the array of type Person
		try {
		for (int i = 0; i < numberOfPeople; i++) {
			String personInfo[] = personInput[i].split("[;]");
			String[] dateOfBirth = personInfo[4].split("[.]");
			LocalDate birth = LocalDate.of(Integer.parseInt(dateOfBirth[2]), Integer.parseInt(dateOfBirth[1]),
					Integer.parseInt(dateOfBirth[0]));
			String[] enrollmentDate = personInfo[16].split("[.]");
			LocalDate enrollment = LocalDate.of(Integer.parseInt(enrollmentDate[2]),
					Integer.parseInt(enrollmentDate[1]), Integer.parseInt(enrollmentDate[0]));
			String[] graduationDate = personInfo[17].split("[.]");
			LocalDate graduation = LocalDate.of(Integer.parseInt(graduationDate[2]),
					Integer.parseInt(graduationDate[1]), Integer.parseInt(graduationDate[0]));

			if (personInfo[12].equals("") && personInfo[13].equals("")) {
				if (personInfo.length == 18) {
					people[i] = new Person(personInfo[0], personInfo[1], personInfo[2], personInfo[3].charAt(0), birth,
							personInfo[14].charAt(0), Short.parseShort(personInfo[5]), personInfo[6], personInfo[7],
							personInfo[8], Integer.parseInt(personInfo[9]), personInfo[10],
							Integer.parseInt(personInfo[11]), 0, 0, enrollment, graduation, personInfo[15], 0.0);
				}
				if (personInfo.length == 19) {
					people[i] = new Person(personInfo[0], personInfo[1], personInfo[2], personInfo[3].charAt(0), birth,
							personInfo[14].charAt(0), Short.parseShort(personInfo[5]), personInfo[6], personInfo[7],
							personInfo[8], Integer.parseInt(personInfo[9]), personInfo[10],
							Integer.parseInt(personInfo[11]), 0, 0, enrollment, graduation, personInfo[15],
							Double.parseDouble(personInfo[18]));
				}
			} else {
				if (personInfo.length == 18) {
					people[i] = new Person(personInfo[0], personInfo[1], personInfo[2], personInfo[3].charAt(0), birth,
							personInfo[14].charAt(0), Short.parseShort(personInfo[5]), personInfo[6], personInfo[7],
							personInfo[8], Integer.parseInt(personInfo[9]), personInfo[10],
							Integer.parseInt(personInfo[11]), Integer.parseInt(personInfo[12]),
							Integer.parseInt(personInfo[13]), enrollment, graduation, personInfo[15], 0.0);
				}
				if (personInfo.length == 19) {
					people[i] = new Person(personInfo[0], personInfo[1], personInfo[2], personInfo[3].charAt(0), birth,
							personInfo[14].charAt(0), Short.parseShort(personInfo[5]), personInfo[6], personInfo[7],
							personInfo[8], Integer.parseInt(personInfo[9]), personInfo[10],
							Integer.parseInt(personInfo[11]), Integer.parseInt(personInfo[12]),
							Integer.parseInt(personInfo[13]), enrollment, graduation, personInfo[15],
							Double.parseDouble(personInfo[18]));
				}
			}
		}

//		Print the information for the people from the array
		
			for (int i = 0; i < numberOfPeople; i++) {
				System.out.println(people[i].toString());
				System.out.println();
			}
		} catch (NullPointerException e) {
			System.out.println("Cannot create a person with this charachteristics");
		}
	}
}

