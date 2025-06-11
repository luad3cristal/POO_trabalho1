package com.eventmanager.model.participant;

public class External extends Participant {
  private String profession;
  private String workPlace;

  public External(String name, String email, String cpf, String profession, String workPlace) {
    super(name, email, cpf);
    this.profession = profession;
    this.workPlace = workPlace;
  }

  @Override
  public String getIdentifier() {
    return cpf;
  }
 
  @Override
  public String getDescription() {
    return "- External Person: " + getName() + " | " + getIdentifier() + "\n" + getProfession() + " - " + getWorkPlace();
  }

  public void setProfession (String profession) {this.profession = profession;}
  public void setWorkPlace (String workPlace) {this.workPlace = workPlace;}

  public String getProfession () {return profession;}
  public String getWorkPlace () {return workPlace;}
}