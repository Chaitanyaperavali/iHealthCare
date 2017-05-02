package com.ase.team22.ihealthcare.othermodel;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

/**
 * Created by chaitanya on 27/03/2017.
 */
//TODO - Use this class to store user details like anything... (do not create any method to impelment functionalities)feel free to create any no of instance variables, getter and setter(All)
@IgnoreExtraProperties
public class UserDetails {
    private static UserDetails userDetails;
    private String dateOfBirth;
    private String gender;
    private String height;
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String weight;
    private Integer age;
    private ArrayList<String> previousConditions;

    public static UserDetails getActiveUserInstance(){
        if(userDetails == null){
            userDetails = new UserDetails();
        }
        return userDetails;
    }

    private UserDetails(){}


    public static void setUserDetails(UserDetails userDetails) {
        UserDetails.userDetails = userDetails;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ArrayList<String> getPreviousConditions() {
        return previousConditions;
    }

    public void setPreviousConditions(ArrayList<String> previousConditions) {
        this.previousConditions = previousConditions;
    }
}
