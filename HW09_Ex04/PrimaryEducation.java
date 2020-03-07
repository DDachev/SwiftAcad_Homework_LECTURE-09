package bg.swift.HW09_Ex04;

import java.time.LocalDate;

public class PrimaryEducation extends Education{

	protected PrimaryEducation(LocalDate enrollmentDate, LocalDate graduationDate, String institutionName) throws NoGradeException{
		super(enrollmentDate, graduationDate, institutionName);
	}

	@Override
	protected String getDegree() {
		return "primary";
	}

	@Override
	protected boolean gotGraduated() {
		return false;
	}
}
