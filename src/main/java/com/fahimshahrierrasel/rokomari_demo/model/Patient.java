package com.fahimshahrierrasel.rokomari_demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String mobile;
    private Integer age;
    private String gender;
    private String occupation;
    private String symptom_summery;

    public Patient() { }

    public Patient(String name, String mobile, Integer age, String gender, String occupation, String symptom_summery) {
        this.name = name;
        this.mobile = mobile;
        this.age = age;
        this.gender = gender;
        this.occupation = occupation;
        this.symptom_summery = symptom_summery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getSymptom_summery() {
        return symptom_summery;
    }

    public void setSymptom_summery(String symptom_summery) {
        this.symptom_summery = symptom_summery;
    }
}
