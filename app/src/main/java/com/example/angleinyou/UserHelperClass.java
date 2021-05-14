package com.example.angleinyou;

public class UserHelperClass {
    private String UserId;
    private String Name;
    private String Email;
    private String Phone;
    private String Age;
    private String Address;
    private String Gender;
    private String State;
    private String City;
    private String Password;

    public String getBloodGroup() { return BloodGroup; }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }

    private String BloodGroup;

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getState() {
        return State;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        this.UserId = userId;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public UserHelperClass(String name, String email, String phone, String age, String gender, String password, String userId) {
        Name = name;
        Email = email;
        Phone = phone;
        Age = age;
        UserId = userId;
        Gender = gender;
        Password = password;
    }

    UserHelperClass(){

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
