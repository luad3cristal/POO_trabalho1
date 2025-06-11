package com.eventmanager.model.participant;

public class Teacher extends Participant {
  private final String department;
  private final String institution;

  public Teacher(String name, String email, String cpf, String department, String institution) {
    super(name, email, cpf);
    this.department = department;
    this.institution = institution;
  }

  @Override
  public String getIdentifier() {
    return cpf;
  }

   
  @Override
  public String getDescription() {
    return "- Student: " + getName() + " | " + getIdentifier() + "\n" + getDepartment() + " - " + getInstitution();
  }

  public String getDepartment () {return department;}
  public String getInstitution () {return institution;}
}