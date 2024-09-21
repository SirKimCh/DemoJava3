package org.java3.demojava3.model;

public class Student {
    private int id;
    private String name;
    private String phone;
    private String address;
    private String major;
    private boolean status;
    private int classId;

    public Student(int id, String name, String phone, String address, String major, boolean status, int classId) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.major = major;
        this.status = status;
        this.classId = classId;
    }

    public Student(String name, String phone, String address, String major, boolean status, int classId) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.major = major;
        this.status = status;
        this.classId = classId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}
