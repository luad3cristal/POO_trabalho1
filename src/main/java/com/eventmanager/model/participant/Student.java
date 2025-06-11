package com.eventmanager.model.participant;

public class Student extends Participant {
  private final String registrationNumber;
  private final String course;
  private final String institution;

  public Student(String name, String email, String cpf, String registrationNumber, String course, String institution) {
    super(name, email, cpf);
    this.registrationNumber = registrationNumber;
    this.course = course;
    this.institution = institution;
  }

  @Override
  public String getIdentifier() {
    return cpf;
  }

   
  @Override
  public String getDescription() {
    return "- Student: " + getName() + " (" + getRegistrationNumber() + ")" + "\n" + getCourse() + " - " + getInstitution();
  }

  public String getRegistrationNumber () {return registrationNumber;}
  public String getCourse () {return course;}
  public String getInstitution () {return institution;}
}