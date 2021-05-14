package com.example.angleinyou;

public class DonationHelper {
    private String OrganName;
    private String Id;
    private String userId;
    private String Time;
    private Integer Founded;
    private String City;
    private String State;
    private String Address;
    private String BloodGroup;
    private String Link;
    public DonationHelper(){

    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public DonationHelper(String organVal, String id, String userId, String bloodGroup, String link){
        this.OrganName = organVal;
        this.Id = id;
        this.userId = userId;
        this.BloodGroup = bloodGroup;
        this.Link = link;
    }
    public DonationHelper(String organVal, String id, String userId, String link){
        this.OrganName = organVal;
        this.Id = id;
        this.userId = userId;
        this.Link = link;
//        this.BloodGroup = bloodGroup;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }
//    public DonationHelper(String organName, String id, String userId, String time, Integer founded) {
//        OrganName = organName;
//        Id = id;
//        this.userId = userId;
//        Time = time;




    public String getOrganName() {
        return OrganName;
    }

    public void setOrganName(String organName) {
        OrganName = organName;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public Integer getFounded() {
        return Founded;
    }

    public void setFounded(Integer founded) {
        Founded = founded;
    }
}
