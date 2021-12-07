package com.example.exercise646d.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Group {
    @SerializedName("NAME")
    @Expose
    private String name;
    @SerializedName("SURNAME")
    @Expose
    private String surname;
    @SerializedName("AGE")
    @Expose
    private int age;
    @SerializedName("CITY")
    @Expose
    private String city;
    @SerializedName("ID")
    @Expose
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
